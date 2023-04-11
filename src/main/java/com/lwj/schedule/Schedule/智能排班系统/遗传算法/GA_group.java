package com.lwj.schedule.Schedule.智能排班系统.遗传算法;

import com.lwj.schedule.Schedule.智能排班系统.遗传算法.Chromo;
import com.lwj.schedule.Schedule.智能排班系统.遗传算法.GA_group;

import java.net.Inet4Address;
import java.util.*;

/**
 *使用说明：
 * ①无法保证每周40小时的排班，只能通过适当人员的增加来完成这个适合度
 * ②group是一天内所有员工的时间染色体集，也就是种群，用于遗传
 * ③chromo是染色体
 */
public class GA_group {
    /***
     * 规则是用来固定len和限制适应度函数fitness的
     *     固定规则：
     *     ①周一~周五 9-21  12小时
     *     ②周末    10-22  12小时
     *     ③每天最多8
     *     ④每班最少2，最多4 可以多个连续班次，也就是说可以连续两个2
     *     ⑤最长连续工作时间4，必须休息
     *     ⑥工作时间完全覆盖饭点的员工安排吃饭时间
     *     ⑦午餐：12：00~13：00  晚餐：18：00~19：00  休息至少半小时
     *     用户定义：
     *     ①开店前a小时准备 门店面积/b = 需要人数
     *     ②每天至少安排不超过4小时的班次 客流/c = 需要人数
     *     ③没有客流的时候 至少d人员值班
     *     ④关门后需要e小时收尾 门店买诺记/f + g = 人数
     */


//    这里的group和Store中定义了一样的数据结构的group
    public ArrayList<Chromo> group;//种群，染色体集

    //动态数组记录状态
//    check和status的形状是Size x len， workingTime的形状是Size x len
    public ArrayList<ArrayList<Integer>> check;//check表示员工一天的那么多个时间片中是否在工作
    //check需要是二维数组来确认整个种群上的染色体点位
    public ArrayList<ArrayList<Integer>> status;//状态，最开始都为待机wait，除了偏好员工外
    public ArrayList<ArrayList<Integer>> workingTime;//0下标表示连续工作时间，1下标表示日工作时间 2下标表示周工作时间


    private double pm = 0.5;//变异概率，//一般以上概率不能手动改变，只能程序员改代码
    private int len =32;//染色体长度，需要传入上班前工作时间，下班后工作时间
    private int Size = 20;//可上班员工数目，种群规模

    public void setLen(int len) {
        this.len = len;
    }

    public void setSize(int size) {
        Size = size;
    }

    public GA_group(ArrayList<Chromo> group) {
        this.group = group;
    }//

    /**
     * 某个班次的上班人数
     */
    private int countPopulations(int index) {
        int res=0;
//        Size为可上班的人数，为所有员工数量
        for(int i=0;i<Size;i++) {
//            可上班的人数Size一定就是group里前Size个人吗？
            if(group.get(i).chromo.get(index)==1) res++;
        }
        return res;//多少个人
    }//

    /**
     * 持续上班时间
     */
    private int persistentTime(int index) {//持续时间
        return workingTime.get(index).get(0);
    }//

    /**
     * 日工作时间
     */
    private int DayTime(int index) {
        return workingTime.get(index).get(1);
    }//

    private void resetPerTime(int index)//下班后重置
    {
        ArrayList<Integer> tmp=workingTime.get(index);
        tmp.set(0,0);//持续时间重置0
        workingTime.set(index,tmp);
    }

    private int checkPopulation(int index)//还差多少人
    {
        int res=0;
        for(int i=0;i<Size;i++) {
            if(check.get(i).get(index)==1) res++;
        }
        return res;//多少个人
    }

    //PreNum为上班前需要的人数，FreeNum为没人时至少需要几人值班，PassFlowNum为各个时间片需要的员工数量，pre为上班前的时间片数目，而later为下班后的时间片数目，day为一周中的第几天，loves_arr为员工的偏好
    private boolean fitness(int PreNum,int FreeNum,int AftNum,ArrayList<Double> PassFlowNum,int pre,int later,ArrayList<ArrayList<Integer>> loves_arr) {
        //如果现在len是28，代表 pre 24 later的一个时间构造，解析上班时间的时候，就需要传上下班这个参数来确定

        Random r = new Random();
        // group变量是包含了所有员工一天的时间片列表合集，同时group里存储的Chromo是最终要返回的所有员工在每个时间片是否要上班
        for(int i=0;i<group.size();i++)
        {
            Chromo tmp=group.get(i);
            for(int j=0;j<len;j++)
            {
//              将所有员工的每个时间片都初始化设为不在工作
                tmp.chromo.set(j,0);
            }
            group.set(i,tmp);
        }
        //每次排班重置group，不然都是一样的？？？这句话我没理解，如果每次调用fitness函数都重置group，那么上次排班结果会被覆盖掉，那些check status workingtime数组记录还在，各自有什么作用？

//        如果上班前打扫为生的人不够，那就一直变异，直到人数够为止
        while(countPopulations(0)!=PreNum) {//PreNum个人工作前打扫
            mutate(0);//变异第一列
        }

//       pre为上班前的时间片数目
        for(int i=1;i<pre;i++) {
           for(int j=0;j<Size;j++) {
//               下面这句代码意思是把每个员工从group中取出来
                Chromo tmp = group.get(j);
//              下面这句代码——如果变异后你是要工作的人，那么打扫卫生的全部时间段你都要参加，如果不打扫，那么所有时间段都不用参与（下面的for循环也是这样的作用）
                tmp.chromo.set(i,tmp.chromo.get(i-1));
                group.set(j,tmp);
           }
            System.out.println("基因位"+i+"已确认");
        }


        //确认变异的第一列后进行check，status，workingTime的修改
        for(int i=0;i<pre;i++) {
            for(int j=0;j<Size;j++) {//j需要参数
                if (group.get(j).chromo.get(i) == 1) {//如果第一班次被选中
                    ArrayList<Integer> tmp = check.get(j);
                    tmp.set(i, 1);//check 1代表确认
                    check.set(i, tmp);
                    tmp = status.get(j);
                    tmp.set(i, 2);//status 2代表上班中
                    status.set(j, tmp);
                    tmp = workingTime.get(j);
                    tmp.set(0, persistentTime(j) + 1);
                    tmp.set(1,DayTime(j)+ 1);
                    workingTime.set(j, tmp);
                }
                //虽然其他的未被确定，但是也用不上了，只需要确认需要上班的即可
            }
        }

        //第一班次设完之后，后面就是人数多，那么如果时间没<8,就上  人数少，那么随机挑选持续时间<8的n个人上
        //大循环
        //int ran=0;
        for(int i=pre;i<len;i++) {
            //ran++;
            double PopulationsNeed = 0;
            if (i >= pre && i < len-later) PopulationsNeed = PassFlowNum.get(i-pre);//如果是在正常上班的时间段
            else if (i >= len-later) PopulationsNeed = AftNum;//如果是在下班后的那个时间段的话

            if((int)PopulationsNeed==0) PopulationsNeed =FreeNum;//没有人时需要1人

            /*if(i==2) {
                System.out.println(pre);
                System.out.println((int)Math.ceil(PopulationsNeed));
                System.exit(0);
            }*/

            //正常休息
//            i代表当前是哪个时间片
            for (int j = 0; j < Size; j++) {//j是人，i是位点
                if (persistentTime(j) >= 8) {//如果一个员工连续工作时间超过了4小时，由于每个小时两个时间片，那么四小时就是8个时间片
                    ArrayList<Integer> tmp = check.get(j);
                    tmp.set(i, 1);//确认
                    if(i+1<Size)//如果后面的一个员工也是可以上班的员工，那么也确认ta的状态，之后将ta设为休息中？？这里应该是不对的，需要将Size转换成len
                    tmp.set(i + 1, 1);
                    check.set(j, tmp);

                    tmp = status.get(j);
                    tmp.set(i, 3);//休息中
                    if(i+1<Size)
                    tmp.set(i + 1, 3);//休息
                    status.set(j, tmp);
                    resetPerTime(j);//重置这个人的连续工作时间
                }//安排休息没问题
//                lovers_arr中3为日工作时间偏好，loves_arr.get(j).get(32)为将日工作时间偏好取出来
//                DayTime（j）为员工j的日工作时间
//                下面判断条件为两种，第一种：如果日工作时间超过了八小时，那么就把一天中剩下的时间设为不可工作
//                第二种：如果某个员工有日工作时长偏好且达到了偏好值，也把剩下的时间设为不可工作
                if ((DayTime(j) >= 16)||(loves_arr.get(j).get(0)==3&&DayTime(j)==loves_arr.get(j).get(32))) {
                    for (int k = i; k < len; k++) {
//                下面这个判断是 如果一个人的日工作时间超过了八小时，那么就把剩余的时间都给他设置为休息
                        resetPerTime(j);
                        ArrayList<Integer> tmp = check.get(j);
                        tmp.set(k, 1);//确认
                        check.set(j, tmp);

                        tmp = status.get(j);
                        tmp.set(k, 3);//休息中
                        status.set(j, tmp);
                    }
                }//今天就工作完了
            }



            //随机2-4小时的员工休息半小时
            /*while (ran == 7 ) {
                int index = Math.abs(r.nextInt() % Size);
                for(int j=index;j<Size;j++) {
                    if (status.get(index).get(i) == 1 && persistentTime(index) >= 4 && persistentTime(index) < 8) {
                        ArrayList<Integer> tmp = check.get(index);
                        tmp.set(i, 1);//确认
                        check.set(index, tmp);
                        tmp = status.get(index);
                        tmp.set(i, 3);//休息中
                        status.set(index, tmp);
                        resetPerTime(index);//重置这个人的连续工作时间
                        ran = 0;
                    }
                }
            }*/


            //首先是确定需要的人数
//            PopulationsNeed为需要上班的人数，countPopulations（i）为第i个时间片上班的人数
//            不明白这里为什么是i-1而不是i，而且不明白这个分支的作用
            if(countPopulations(i-1)<=(int)Math.ceil(PopulationsNeed)){//还需要人上班，可以上的都上去

//                增加工作人员的第一个措施
                for(int j=0;j<Size;j++)
                {
//                  如果员工连续工作时间不到4小时，且前一个时间片在工作，且status当前状态为可以上班
                    if(persistentTime(j)>0&&persistentTime(j)<8&&group.get(j).chromo.get(i-1)==1&&status.get(j).get(i)==1){//等待上班，并且可以上
//                        将第j个人的工作状态设为上班
                        Chromo work = group.get(j);
                        work.chromo.set(i,1);
                        group.set(j,work);
//                      确认上班
                        ArrayList<Integer> tmp = check.get(j);
                        tmp.set(i,1);
                        check.set(j,tmp);
//                      上班中
                        tmp = status.get(j);
                        tmp.set(i,2);
                        status.set(j,tmp);
//                      工作时间按照各自的位置加上
                        tmp = workingTime.get(j);
                        tmp.set(0,persistentTime(j)+1);
                        tmp.set(1,DayTime(j)+1);
                        workingTime.set(j,tmp);
                    }
                }

//              增加工作人员的第二个措施
                //还缺多少人，随机上，
                while((int)Math.ceil(PopulationsNeed)!=countPopulations(i)) {//人不够,随机选在wait的人上班,每次选一个，直到满足为止
                    System.out.println(countPopulations(i)+" "+(int)Math.ceil(PopulationsNeed)+" "+1);
                    int index = Math.abs(r.nextInt()%Size);

//                    check为0表示未确认，status为1表示waiting，Daytime表示工作时间小于8小时
                    if(check.get(index).get(i)==0&&status.get(index).get(i)==1&&DayTime(index)<16)
                    {//满足要求，上班
//                        对应时间片设为上班
                        Chromo work = group.get(index);
                        work.chromo.set(i,1);
                        group.set(index,work);

//                      状态确认
                        ArrayList<Integer> tmp = check.get(index);
                        tmp.set(i,1);
                        check.set(index,tmp);

//                      status设为上班状态
                        tmp = status.get(index);
                        tmp.set(i,2);//上班中
                        status.set(index,tmp);

//                      对应工作时间都要加上
                        tmp = workingTime.get(index);
                        tmp.set(0,persistentTime(index)+1);
                        tmp.set(1,DayTime(index)+1);
                        workingTime.set(index,tmp);
                    }
                }
                //已凑上s
            }

//            上面的大if是代表还需要人上班，这里代表工作人数已足够
            //决定执行工作时长<2时就算人太多也不下班（难道这就是传说中的加班？？？）
            else if(countPopulations(i-1)>(int)Math.ceil(PopulationsNeed)) {//如果能够上班的人数>实际需要的人数
                    for(int j=0;j<Size;j++)
                    {
                        if(group.get(j).chromo.get(i-1)==1&&persistentTime(j)<4&&persistentTime(j)>0)
                        {
                            Chromo work = group.get(j);
                            work.chromo.set(i, 1);
                            group.set(j, work);

                            ArrayList<Integer> tmp = check.get(j);
                            tmp.set(i, 1);//确认上班
                            check.set(j, tmp);

                            tmp = status.get(j);
                            tmp.set(i, 2);//上班中
                            status.set(j, tmp);

                            tmp = workingTime.get(j);
                            tmp.set(0, persistentTime(j) + 1);
                            tmp.set(1, DayTime(j) + 1);
                            workingTime.set(j, tmp);
                        }
                    }

//                    之前是判断上一个时间片上班的人和需要上班的人之间的关系
//                    这里代表当前的时间片中上班的人少于需要上班的人数
                    while(countPopulations(i)<(int)Math.ceil(PopulationsNeed)) {
                        System.out.println(countPopulations(i)+" "+(int)Math.ceil(PopulationsNeed)+" "+2);
                        int index = Math.abs(r.nextInt()%Size);
//                        check.get(index)代表第index个员工，后面再.get(i)代表当前的第i个时间片
//                      如果当前时间片还有员工状态没有确认，且可以上班且连续工作时间不超过4小时，且日工作时间不超过2小时
                        if(check.get(index).get(i)==0&&status.get(index).get(i)==1&&DayTime(index)<16&&persistentTime(index)<8) {
                            //index上班，其他的下班(重置持续时间)
                            Chromo work = group.get(index);
                            work.chromo.set(i, 1);
                            group.set(index, work);

//                          check确认
                            ArrayList<Integer> tmp = check.get(index);
                            tmp.set(i, 1);
                            check.set(index, tmp);

//                          确认上班状态
                            tmp = status.get(index);
                            tmp.set(i, 2);
                            status.set(index, tmp);

                            tmp = workingTime.get(index);
                            tmp.set(0, persistentTime(index) + 1);
                            tmp.set(1, DayTime(index) + 1);
                            workingTime.set(index, tmp);
                        }
                    }
                for(int j=0;j<Size;j++) {
//                  下面是看所有员工的check是否有为0的，也就是没被确认过的，如果有一定是没被排班，那么resetperTime
                    if(check.get(j).get(i)==0)
                    {
                        resetPerTime(j);//重置它的持续时间
                    }
                }

            }
            System.out.println("基因位"+i+"已确认");
        }
        return true;
    }


    //变异，变异概率pm
    //对某一点位全部随机变异
    private void mutate(int pos) {
        //取反即可
        Random r = new Random();
        for(int i=0;i<Size;i++) {//
            //System.out.println(i+" "+Size+" "+check.size()+" "+status.size());
//            status=1代表waiting即准备上班，check代表每个时间片中是否在工作
            if(Math.abs(r.nextInt()%10000/10000.0)<pm&&check.get(i).get(pos)==0&&status.get(i).get(pos)==1){//未确认，在waiting才能变异上班,并且满足概率
                if(group.get(i).chromo.get(pos)==0) {//这个分支设成要上班
                    Chromo chro = group.get(i);
                    chro.chromo.set(pos,1);
                    group.set(i,chro);
                }
                else{//这个分支设成不要上班
                    Chromo chro = group.get(i);
                    chro.chromo.set(pos,0);
                    group.set(i,chro);
                }
            }
        }
    }

//  初始化的具体内容——check，status，working
    private void init()
    {
        //初始化check，将所有员工的状态设成一天中时间片数目的全0数组
//        len为一天中时间片的数量
//        check为员工在每个时间片中是否在工作
//        初始化：令所有时间片的check为0，即——所有的时间片都需要之后去确认
        check = new ArrayList<>();
        for (int i = 0; i < Size; i++) {
            ArrayList<Integer> tmp = new ArrayList<>();
            for(int j=0;j<len;j++)
            {
                tmp.add(0);
            }
            check.add(tmp);
        }

        //初始化状态status，status的意思是表示当前所有员工每个时间片的状态
//        初始化令所有时间片状态为waiting
        status = new ArrayList<>();
        //1代表waiting 准备上班，应该是当前时间没有被排班的意思
        //2代表working 上班中
        //3代表resting 休息中/吃饭中，这个时候不能派班
        for (int i = 0; i < Size; i++) {
            ArrayList<Integer> tmp = new ArrayList<>();
            for(int j=0;j<len;j++)
            {
                tmp.add(1);
            }
            status.add(tmp);
        }

        //初始化工作时间workingTime，
//        当前作者没有把周工作时间写进去，把连续工作时间和日工作时间初始化为0
        workingTime = new ArrayList<>();
        //0下标表示连续工作时间，1下标表示日工作时间 2下标表示周工作时间
        for (int i = 0; i < Size; i++) {
            ArrayList<Integer> tmp = new ArrayList<>();
            for(int j=0;j<2;j++)
            {
                tmp.add(0);
            }
            workingTime.add(tmp);
        }
    }

    //GA算法主体
    //PreNum为上班前需要的人数，FreeNum为没人时至少需要几人值班，PassFlowNum为各个时间片需要的员工数量，pre为上班前的时间片数目，而later为下班后的时间片数目，day为一周中的第几天，loves_arr为员工的偏好
    public ArrayList<Chromo> GA(int PreNum, int FreeNum, int AftNum, ArrayList<Double> PassFlowNum, int pre, int later,int day,ArrayList<ArrayList<Integer>> loves_arr)//偏好数组和星期几，用于check
    {
//        group变量是包含了所有员工一天的时间片列表合集
        ArrayList<Chromo> groupTmp1=group;
        ArrayList<Chromo> groupTmp2=group;

//      先看一下员工的状态：  对员工一天中的每个时间片状态进行遍历并输出
        //员工开始的随机时间安排，对所有员工进行遍历
        for(int i=0;i<Size;i++)
        {
            System.out.println("员工"+i);
//            len为一天中的时间片数量
            for(int j=0;j<len;j++)
            {
                System.out.print(group.get(i).chromo.get(j));
                System.out.print(" ");
            }
            System.out.println();
        }

        //开始对 记录所有员工情况的数组进行初始化
        init();

        /**
         * 初始化完了之后使用loves_arr进行check
         */
//        下面这个大的for作用是确定今天不能排版的人，将ta们的状态（也就是status数组）设为不可以排班，不能排版的天数或者工作时间偏好对应的check设为1
//        这个地方（下面这个大循环）我抱有疑问，这里是设置不能排班的人——》那我如果工作偏好是周一，那么剩下六天就不能排班了？这样肯定不行吧
//        有工作偏好是正常的，按照偏好排版也是正常地，但是完全严格地去排班肯定是不行的
        for(int a=0;a<loves_arr.size();a++)//a是偏好数组中一个人对应的编号
        {
            //每一个爱好数组
            //0设置种类(1 2 3) 1-7 工作日   8-31 工作时间（工作日代表9-21 周末代表10-22）   32 日工作时间偏好
            if(loves_arr.get(a).get(0)==1){//工作日偏好
                for(int i=1;i<7;i++) {
                    //i代表一周中的哪一天
//                    love_arr.get(a).get(i)==0代表不是这一天的工作时间偏好，先把今天不能排班的人确定
                    if(loves_arr.get(a).get(i)==0&&((day%7)+1)==i) {
//                        check是一个人一天中所有时间片的数组集合（0 1代表是否工作），status是一个人一天中所有时间片的状态的数组集合（1 2 3分别代表 准备上班、上班中、休息中）
                        ArrayList<Integer> tmp1=check.get(a);
                        ArrayList<Integer> tmp2=status.get(a);
                        //工作日偏好，那么就把这一天中的状态都确认且设为不可排班？
                        for(int j=0;j<tmp1.size();j++)
                        {
                            tmp1.set(j,1);
                            tmp2.set(j,3); // 一天中的每个时间段都设为不可排班
                        }
                        check.set(a,tmp1);
                        status.set(a,tmp2);
                    }
                }
            }
            else if(loves_arr.get(a).get(0)==2){// 工作时间偏好
                    for(int i=8;i<=31;i++){//9:00-21:00 或者 10:00-22:00
                        //因为解析decode的时候解析的时间不同，所以说都是8-31，代表周末和工作日
                        if(loves_arr.get(a).get(i)==0){//说明偏好为不想上班//工作日
                            ArrayList<Integer> tmp1=check.get(a);
                            ArrayList<Integer> tmp2=status.get(a);
                            tmp1.set(i-8+pre,1);
                            tmp2.set(i-8+pre,3); // love_arr数组中如果是工作时间偏好，那么loc0=2，且对应工作时间loc为1，其他为0的代表不是偏好工作时间
                            check.set(a,tmp1);
                            status.set(a,tmp2);
                        }
                    }
            }
            else if(loves_arr.get(a).get(0)==3){// 日工作时间偏好
                //这里什么都不做，第三种偏好需要传给fitness去适应
            }

        }

        System.out.println("请稍后，正在GA中......");
        //1.初始化种群
        //2.while次迭代以全局适应
        Random r = new Random();

        while(fitness(PreNum,FreeNum,AftNum,PassFlowNum,pre,later,loves_arr)!=true)//while循环中一直在进行种群更新
        {
            //在fitness中进行变异
        }

        //完成GA，显示结果
        System.out.println("Day"+day+"已完成排班，结果如下：");
        for(int i=0;i<Size;i++)//Size其实就是全体员工的数量，这个for循环是输出全部员工一天的排班结果
        {
            System.out.println("员工"+i);
            for(int j=0;j<len;j++)
            {
                System.out.print(group.get(i).chromo.get(j).intValue());
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println("结果显示完成!!!");
//        下面这两次几句代码就是将排完班的结果返回并将排班结果覆盖成没排班之前的状态
        groupTmp1=group;
        group=groupTmp2;
        return groupTmp1;
        //为什么每天GA的结果都是一样
    }
}
