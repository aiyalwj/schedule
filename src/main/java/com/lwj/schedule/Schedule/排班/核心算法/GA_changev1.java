package com.lwj.schedule.Schedule.排班.核心算法;

import java.util.ArrayList;
import java.util.Random;


public class GA_changev1 {
    //规则是用来固定len和限制适应度函数fitness的
    //固定规则：
    //①周一~周五 9-21  12小时
    //②周末    10-22  12小时
    //③每天最多8
    //④每班最少2，最多4 可以多个连续班次，也就是说可以连续两个2
    //⑤最长连续工作时间4，必须休息
    //⑥工作时间完全覆盖饭点的员工安排吃饭时间
    //⑦午餐：12：00~13：00  晚餐：18：00~19：00  休息至少半小时
    //用户定义：
    //①开店前a小时准备 门店面积/b = 需要人数
    //②每天至少安排不超过4小时的班次 客流/c = 需要人数
    //③没有客流的时候 至少d人员值班
    //④关门后需要e小时收尾 门店买诺记/f + g = 人数

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

    public GA_changev1(ArrayList<Chromo> group) {
        this.group = group;
    }//

//     某个班次的上班人数
    private int countPopulations(int index) {
        int res=0;
        for(int i=0;i<Size;i++) {
            if(group.get(i).chromo.get(index)==1) res++;
        }
        return res;
    }

//  持续上班时间
    private int persistentTime(int index) {
        return workingTime.get(index).get(0);
    }

//    日工作时间
    private int DayTime(int index) {
        return workingTime.get(index).get(1);
    }//

//  周工作时间
    private int WeekTime(int index){
        return workingTime.get(index).get(2);
    }

//  下班后重置员工的连续工作时间
    private void resetPerTime(int index)
    {
        ArrayList<Integer> tmp=workingTime.get(index);
        tmp.set(0,0);//持续时间重置0
        workingTime.set(index,tmp);
    }

//  重置所有员工的日工作时间
    private void resetDayTime(){
        for(int j=0;j<Size;j++){
            ArrayList<Integer> tmp=workingTime.get(j);
            tmp.set(1,0);//持续时间重置0
            workingTime.set(j,tmp);
        }
    }

    //还差多少人
    private int checkPopulation(int index)
    {
        int res=0;
        for(int i=0;i<Size;i++) {
            if(check.get(i).get(index)==1) res++;
        }
        return res;//多少个人
    }

    //PreNum为上班前需要的人数，FreeNum为没人时至少需要几人值班，PassFlowNum为各个时间片需要的员工数量，pre为上班前的时间片数目，而later为下班后的时间片数目，day为一周中的第几天，loves_arr为员工的偏好
    private void fitness(int PreNum,int FreeNum,int AftNum,ArrayList<Double> PassFlowNum,int pre,int later,ArrayList<ArrayList<Integer>> loves_arr) {
        //如果现在len是28，代表 pre 24 later的一个时间构造，解析上班时间的时候，就需要传上下班这个参数来确定

        Random r = new Random();


//        如果上班前打扫为生的人不够，那就一直变异，直到人数够为止
        while(countPopulations(0)!=PreNum) {
            mutate(0);//变异第一列
        }

        for(int i=1;i<pre;i++) {
           for(int j=0;j<Size;j++) {
                Chromo tmp = group.get(j);
//              下面这句代码——如果变异后你是要工作的人，那么打扫卫生的全部时间段你都要参加，如果不打扫，那么所有时间段都不用参与（下面的for循环也是这样的作用）
                tmp.chromo.set(i,tmp.chromo.get(i-1));
                group.set(j,tmp);
           }
            System.out.println("基因位"+i+"已确认");
        }


        //确认变异的第一列后进行check，status，workingTime的修改
        for(int i=0;i<pre;i++) {
            for(int j=0;j<Size;j++) {
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
                    tmp.set(2, WeekTime(j) + 1);
                    workingTime.set(j, tmp);
                }
                //虽然其他的未被确定，但是也用不上了，只需要确认需要上班的即可
            }
        }

//      下面这个大循环开始进行正式上班时间的排班
        for(int i=pre;i<len;i++) {

//            首先确定需要上班的人数
            double PopulationsNeed = 0;
            if (i >= pre && i < len-later) PopulationsNeed = PassFlowNum.get(i-pre);//如果是在正常上班的时间段
            else if (i >= len-later) PopulationsNeed = AftNum;//如果是在下班后的那个时间段的话
            if((int)PopulationsNeed==0) PopulationsNeed =FreeNum;//没有人时至少需要1人


            //根据规则将超出工作时间的员工设为休息状态
//            i代表当前是哪个时间片
            for (int j = 0; j < Size; j++) {//j是人，i是位点
                if (persistentTime(j) >= 8) {//如果一个员工连续工作时间超过了4小时，由于每个小时两个时间片，那么四小时就是8个时间片
                    ArrayList<Integer> tmp = check.get(j);
                    tmp.set(i, 1);//确认
                    if(i+1<len)//这里应该是希望给员工安排一个小时的休息时间，而一个小时两个时间片，那么就就需要看是否越界了
                    tmp.set(i + 1, 1);
                    check.set(j, tmp);

                    tmp = status.get(j);
                    tmp.set(i, 3);//休息中
                    if(i+1<len)
                    tmp.set(i + 1, 3);//休息
                    status.set(j, tmp);
                    resetPerTime(j);//重置这个人的连续工作时间
                }

                if (WeekTime(j) >= 80) {//如果一个员工连续工作时间超过了40小时，由于每个小时两个时间片，那么四小时就是8个时间片
                    for (int k = i; k < len; k++) {
                        resetPerTime(j);
                        ArrayList<Integer> tmp = check.get(j);
                        tmp.set(k, 1);//确认
                        check.set(j, tmp);

                        tmp = status.get(j);
                        tmp.set(k, 3);//休息中
                        status.set(j, tmp);
                    }
                }

//                lovers_arr中3为日工作时间偏好，loves_arr.get(j).get(32)为将日工作时间偏好取出来
//                DayTime（j）为员工j的日工作时间
//                下面判断条件为三种，第一种：如果日工作时间超过了八小时，那么就把一天中剩下的时间设为不可工作
//                第二种：如果某个员工有日工作时长偏好且达到了偏好值，也把剩下的时间设为不可工作
//                第三种：如果某个员工工作时间超过了6小时但不到八小时，那么也设为不可工作，因为要满足连续工作时间至少为2小时
                if ((WeekTime(j)<80 && WeekTime(j)>76)||((DayTime(j)>12)&&(DayTime(j)<16)&&(group.get(j).chromo.get(i-1)==0))||(DayTime(j) >= 16)||(loves_arr.get(j).get(0)==3&&DayTime(j)==loves_arr.get(j).get(32))) {
                    for (int k = i; k < len; k++) {
                        resetPerTime(j);
                        ArrayList<Integer> tmp = check.get(j);
                        tmp.set(k, 1);//确认
                        check.set(j, tmp);

                        tmp = status.get(j);
                        tmp.set(k, 3);//休息中
                        status.set(j, tmp);
                    }
                }
            }


            //这里是固定规则，如果前一个时间片在工作，到现在工作时间小于2小时，那就需要继续工作
            for(int j=0;j<Size;j++)
            {
                if((group.get(j).chromo.get(i-1)==1)&&(persistentTime(j)<4)&&(persistentTime(j)>0)&&(status.get(j).get(i)==1)&&(check.get(j).get(i)==0))
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
                    tmp.set(2, WeekTime(j) + 1);
                    workingTime.set(j, tmp);
                }
            }


//            PopulationsNeed为需要上班的人数，countPopulations（i）为第i个时间片上班的人数
//            假如当前需要上班的人是不够的，那么进行第一轮变异——有工作时间偏好的人先变异增加
            if(countPopulations(i)<=(int)Math.ceil(PopulationsNeed)){
//                这里我的思想是——先安排有工作偏好的人上班，然后安排前一个时间片上班的人上班，然后再变异
                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                增加工作人员的第一个措施，先安排当前时间片有工作偏好的人上班
                for(int j=0;j<Size;j++){
                    if(loves_arr.get(j).get(0)==2){
                        if((loves_arr.get(j).get(i)==1)&&(r.nextDouble()<1)&&(persistentTime(j)>0&&persistentTime(j)<8)&&(status.get(j).get(i)==1)&&(check.get(j).get(i)==0)){//等待上班，并且可以上
//                        将第j个人的工作状态设为上班

                        System.out.println();

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
                            tmp.set(2, WeekTime(2)+2);
                            workingTime.set(j,tmp);
                        }
                    }

                }

//              增加工作人员第二个措施，如果前一个时间片的人在工作，那么这个时间片可以给他大概率的工作机会
                for(int j=0;j<Size;j++)
                {
//                  如果员工连续工作时间不到4小时，且前一个时间片在工作，且status当前状态为可以上班
                    if((r.nextDouble() < 0.5)&&persistentTime(j)>0&&persistentTime(j)<8&&group.get(j).chromo.get(i-1)==1&&status.get(j).get(i)==1){//等待上班，并且可以上
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
                        tmp.set(2,WeekTime(j)+1);
                        workingTime.set(j,tmp);
                    }
                }

//              增加工作人员的第三个措施
                //还缺多少人，随机上
                while((int)Math.ceil(PopulationsNeed)>countPopulations(i)) {//人不够,随机选在wait的人上班,每次选一个，直到满足为止
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
                        tmp.set(2,WeekTime(index)+1);
                        workingTime.set(index,tmp);
                    }
                }

                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            }
                for(int j=0;j<Size;j++) {
//                  下面是看所有员工的check是否有为0的，也就是没被确认过的，如果有一定是没被排班，那么重置连续工作时间
                    if(check.get(j).get(i)==0)
                    {
                        resetPerTime(j);
                    }
                }
            System.out.println("基因位"+i+"已确认");
        }
    }


    //变异，变异概率pm
    //对某一点位全部随机变异
    private void mutate(int pos) {
        //取反即可
        Random r = new Random();
        for(int i=0;i<Size;i++) {
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


    private void init(int Day)
    {
//      初始化check，将所有员工的状态设成一天中时间片数目的全0数组
        check = new ArrayList<>();
        for (int i = 0; i < Size; i++) {
            ArrayList<Integer> tmp = new ArrayList<>();
            for(int j=0;j<len;j++)
            {
                tmp.add(0);
            }
            check.add(tmp);
        }

//      初始化令所有时间片状态为waiting
        status = new ArrayList<>();
        for (int i = 0; i < Size; i++) {
            ArrayList<Integer> tmp = new ArrayList<>();
            for(int j=0;j<len;j++)
            {
                tmp.add(1);
            }
            status.add(tmp);
        }

//      如果是周一那么，初始化工作时间
        if(Day==1){
            workingTime = new ArrayList<>();
            for (int i = 0; i < Size; i++) {
                ArrayList<Integer> tmp = new ArrayList<>();
                for(int j=0;j<3;j++)
                {
                    tmp.add(0);
                }
                workingTime.add(tmp);
            }
        }

    }

    //PreNum为上班前需要的人数，FreeNum为没人时至少需要几人值班，PassFlowNum为各个时间片需要的员工数量，pre为上班前的时间片数目，而later为下班后的时间片数目，day为一周中的第几天，loves_arr为员工的偏好
    public ArrayList<Chromo> GA(int PreNum, int FreeNum, int AftNum, ArrayList<Double> PassFlowNum, int pre, int later,int day,ArrayList<ArrayList<Integer>> loves_arr)//偏好数组和星期几，用于check
    {
//        group变量是包含了所有员工一天的时间片列表合集
        ArrayList<Chromo> groupTmp1=group;
        ArrayList<Chromo> groupTmp2=group;

        //  每一天的排班前都初始化所有员工的状态
        for(int i=0;i<group.size();i++)
        {
            Chromo tmp=group.get(i);
            for(int j=0;j<len;j++)
            {
                tmp.chromo.set(j,0);
            }
            group.set(i,tmp);
        }

        for(int i=0;i<Size;i++)
        {
            System.out.println("员工"+i);
            for(int j=0;j<len;j++)
            {
                System.out.print(group.get(i).chromo.get(j));
                System.out.print(" ");
            }
            System.out.println();
        }

        //开始对 记录所有员工情况的数组进行初始化
        init(day);

        System.out.println("请稍后，正在GA中......");

        Random r = new Random();

        // fitness里包含了循环，直到排完班才结束
        fitness(PreNum,FreeNum,AftNum,PassFlowNum,pre,later,loves_arr);

//       排完班后重置一天的工作时间
        resetDayTime();

        if(day==7)
            for(int j=0;j<Size;j++){
                System.out.print("第"+String.valueOf(j)+"个员工的周工作时间为："+String.valueOf(WeekTime(j)));
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
