package com.lwj.schedule.Schedule;

//import com.lwj.schedule.Schedule.智能排班系统.Stuff;
//import com.lwj.schedule.Schedule.智能排班系统.StuffOperator;
import com.lwj.schedule.Schedule.排班.核心算法.Chromo;
import com.lwj.schedule.entity.EmployeeSchedule;
import com.lwj.schedule.entity.Record;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Schedule_pb {
//    check和status的形状是Size x len， workingTime的形状是Size x len
    public ArrayList<ArrayList<Integer>> check;//check表示员工一天的那么多个时间片中是否在工作
    //check需要是二维数组来确认整个种群上的染色体点位
    public ArrayList<ArrayList<Integer>> status;//状态，最开始都为待机wait，除了偏好员工外
    public ArrayList<ArrayList<Integer>> workingTime;//0下标表示连续工作时间，1下标表示日工作时间 2下标表示周工作时间

    private double preWorkTime = 0.5;//0.5的倍数，开门前preWorkTime小时  a
    private int StoreSizeNeedBefore = 50;//    //建议输入时最大人数不超过工作人员的 1/2 + 1
    private double passengerFlowNeed = 3.8;//客流passengerFlowNeed  c
    private int free_population = 1;//没人的时候free_population值班  d
    private double aftWorkTime = 1.0;//0.5的倍数，关门后aftWork小时  e
    private int StoreSizeNeedAfter1 = 50;
    private int StoreSizeNeedAfter2 = 2;
    private double pm = 0.5;//变异概率，一般以上概率不能手动改变，只能程序员改代码
    private int pre = 1;
    private int later = 2;
    private int len =32;//染色体长度，需要传入上班前工作时间，下班后工作时间
    private int Size = 20;//可上班员工数目，种群规模

    private ArrayList<ArrayList<Double>> Premodel;
    private ArrayList<EmployeeSchedule> Stuffs;
    private Date start_time;

    private double size = 220;//面积
    private int PreNum = (int) Math.abs((size / StoreSizeNeedBefore));
    private int FreeNum = (int) (free_population);
    private int AftNum = ((int) (size / StoreSizeNeedAfter1) + StoreSizeNeedAfter2) + 1;
    public ArrayList<Chromo> group;//种群，染色体集
    private ArrayList<ArrayList<Record>> result = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> love_arrs;

    private int test1 = 0;
    private int test2 = 0;

    //  重置所有员工的日工作时间
    private void resetDayTime(){
        for(int j=0;j<Size;j++){
            ArrayList<Integer> tmp=workingTime.get(j);
            tmp.set(1,0);//持续时间重置0
            workingTime.set(j,tmp);
        }
    }

    //  下班后重置员工的连续工作时间
    private void resetPerTime(int index) {
        ArrayList<Integer> tmp=workingTime.get(index);
        tmp.set(0,0);//持续时间重置0
        workingTime.set(index,tmp);
    }

    //  某个班次的上班人数
    private int countPopulations(int index) {
        int res=0;
        for(int i=0;i<Size;i++) {
            if(group.get(i).chromo.get(index)==1) res++;
        }
        return res;
    }

    //  计算最后四个班次中需要的最大人数
    private Double count4max(ArrayList<Double> PassFlowNum){
        Double max = 0.0;
        for(int num=PassFlowNum.size()-(4-later);num<PassFlowNum.size();num++){
            if(max<PassFlowNum.get(num)){
                max = PassFlowNum.get(num);
            }
        }
        return max;
    }

    //  持续上班时间
    private int persistentTime(int index) {
        return workingTime.get(index).get(0);
    }

    //  日工作时间
    private int DayTime(int index) {
        return workingTime.get(index).get(1);
    }

    //  周工作时间
    private int WeekTime(int index){
        return workingTime.get(index).get(2);
    }

    //  所有员工的平均周工作时间
    private int MeanWeekTime(){
        int sum = 0;
        for(ArrayList<Integer> perweektime : workingTime){
            sum += perweektime.get(2);
        }
        return sum / Size;
    }

    //  某个时间片全部变异
    private void mutate(int pos) {
        //取反即可
        Random r = new Random();
        for(int i=0;i<Size;i++) {
            //System.out.println(i+" "+Size+" "+check.size()+" "+status.size());
//            status=1代表waiting即准备上班，check代表每个时间片中是否在工作
            if(Math.abs(r.nextInt()%10000/10000.0)<pm&&check.get(i).get(pos)==0&&status.get(i).get(pos)==1&&(workingTime.get(i).get(2)<=76)){//未确认，在waiting才能变异上班,并且满足概率
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

    //对倒数第四个时间片进行排班
    private void mutate_last(int pos, int num_need){
        Random r = new Random();
        int num = 0;
        while(num!=num_need){
            int i = r.nextInt(Size);//随机产生一个员工编号
            if(Math.abs(r.nextInt()%10000/10000.0)<pm&&check.get(i).get(pos)==0&&status.get(i).get(pos)==1&&(workingTime.get(i).get(2)<=76)&&group.get(i).chromo.get(pos-1)==0&&(workingTime.get(i).get(1)<=12)){//未确认，在waiting才能变异上班,并且满足概率
                if(group.get(i).chromo.get(pos)==0) {//这个分支设成要上班
                    num++;
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

    //  check,status,workingtime初始化
    private void init(int Day) {
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
//        workingTime = new ArrayList<>();
//        for (int i = 0; i < Size; i++) {
//            ArrayList<Integer> tmp = new ArrayList<>();
//            for(int j=0;j<3;j++)
//            {
//                tmp.add(0);
//            }
//            workingTime.add(tmp);
//        }
    }

    //  对员工做员工偏好数组的初始化
    private ArrayList<Integer> CreateLove(EmployeeSchedule stuff) {
        ArrayList<Integer> love_arr=new ArrayList<>();

        //        首先假设所有员工是没有偏好的
        for (int i = 0; i < 33; i++) {
            love_arr.add(0);
        }
//        如果有员工偏好，那么就把对应的偏好值设好
        if(stuff.getEmployeepreferencesType().charAt(2)=='日'){
            love_arr.set(0,1);//love_arr数组第一个位置（0下标）为1，代表设为工作日偏好
            for(int i=0;i<stuff.getEmployeepreferencesValue().length();i++) {//如果是日工作时间偏好，那么就把对应的时间设为可工作时间
                char day = stuff.getEmployeepreferencesValue().charAt(i);
                if(day=='1') love_arr.set(1,1);//0则check为休息
                if(day=='2') love_arr.set(2,1);//0则check为休息
                if(day=='3') love_arr.set(3,1);//0则check为休息
                if(day=='4') love_arr.set(4,1);//0则check为休息
                if(day=='5') love_arr.set(5,1);//0则check为休息
                if(day=='6') love_arr.set(6,1);//0则check为休息
                if(day=='7') love_arr.set(7,1);//0则check为休息
            }
        }

        //工作时间偏好 10:00-21:00(范围)
        else if(stuff.getEmployeepreferencesType().charAt(2)=='时') {
            love_arr.set(0,2);//设为工作时间偏好

//            这里的两个时间应该工作时间偏好的开始时间
            char ch1 = stuff.getEmployeepreferencesValue().charAt(0);
            char ch2 = stuff.getEmployeepreferencesValue().charAt(1);
            int i = ch1-'0';
            int j = ch2-'0';
            int res = i*10+j;// 如果是偏好是12：00的话就是，（“1”-“0” ）*10 + “2”—“0”
            if(stuff.getEmployeepreferencesValue().charAt(3)=='3') {//如果是点半这种，比如12：30
                love_arr.set((res - 9) * 2+9, 1);
                System.out.println();
            }
            else love_arr.set((res-9)*2+8,1);

//          这里的两个时间是结束时间
            ch1 = stuff.getEmployeepreferencesValue().charAt(6);
            ch2 = stuff.getEmployeepreferencesValue().charAt(7);
            i = ch1-'0';
            j = ch2-'0';
            res = i*10+j;
            if(stuff.getEmployeepreferencesValue().charAt(9)=='3') love_arr.set((res-9)*2+9,1);
            else love_arr.set((res-9)*2+8,1);

            for(int k=8;k<=31;k++)
            {
                if(love_arr.get(k)==1)
                {
                    while(love_arr.get(k)==1&&k<=31) k++;
                    while(love_arr.get(k)==0&&k<=31) {
                        love_arr.set(k,1);
                        k++;
                    }
                    break;
                }
            }
        }

        // 日工作时间偏好，7小时
        else if(stuff.getEmployeepreferencesType().charAt(2)=='作') {
            love_arr.set(0,3);//设为日工作时间偏好
            int z = stuff.getEmployeepreferencesValue().charAt(0)-'0';//不能超过8小时
            love_arr.set(32,z*2);
        }

        System.out.print("该员工偏好数组：");
        for(int i=0;i<33;i++){
            System.out.print(love_arr.get(i));
        }
        System.out.println();
        return love_arr;
//        chro=new Chromo();
    }

    //  有参构造方法
    public Schedule_pb(ArrayList<ArrayList<Double>> Premodel, ArrayList<EmployeeSchedule> Stuffs, Date start_time){
        this.Premodel = Premodel;
        this.Stuffs = Stuffs;
        this.start_time = start_time;

        int pre = (int) (preWorkTime / 0.5);
        int later = (int) (aftWorkTime / 0.5);
        int len = pre + later + 24;

        this.len = len;
        this.pre = pre;
        this.later = later;
        this.Size = Stuffs.size();

        ArrayList<Chromo> group = new ArrayList<>();

        for(int j=0;j<Stuffs.size();j++){
            Chromo chromo = new Chromo(len);
            group.add(chromo);
        }
        this.group = group;
    }

    public ArrayList<ArrayList<Record>> Schedule() throws ParseException {

        //根据员工具体信息来设置对应的偏好数组
        love_arrs = new ArrayList<>();
        for(EmployeeSchedule stuff : Stuffs){
            love_arrs.add(CreateLove(stuff));
        }

        //设置好一周的客流量
        ArrayList<ArrayList<Double>> PassFlowNum = new ArrayList<>();
        for (int x=0;x<Premodel.size();x++) {// 对各个时间段的客流量进行需要人数的计算并把人数放到数组PassFlowNum里
            ArrayList<Double> tmp = new ArrayList<>();
            for (int y = 0; y < Premodel.get(x).size(); y++) {
                tmp.add((Premodel.get(x).get(y)) / passengerFlowNeed);
            }
            PassFlowNum.add(tmp);
        }


        for(int Day=1;Day<=7;Day++){
//            ArrayList<Chromo> chromo = new ArrayList<>();
            //返回结果是个ArrayList<Chromo>对象
            group = GA(PassFlowNum.get(Day-1), Day, love_arrs);

            //将排完班的结果解码成ArrayList<Record>,然后返回
            result.add(decode(group, Day));
        }
        System.out.println("进来第一个分支的次数为"+test1);
        System.out.println("进来第二个分支的次数为"+test2);
        return result;
    }

    public ArrayList<Record> decode(ArrayList<Chromo> chromos, int Day) throws ParseException {
        ArrayList<Record> Records = new ArrayList<>();
        EmployeeSchedule employeeSchedule;
        Record record;
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        Date working_start = new Date();
        Date working_end = new Date();
        Date newDate;
        long time;
        long stime;
        long etime;
        if(Day<=5){//如果是在周中
            //早上开始上班的时间
            working_start = sdf.parse("09:00:00");
            working_end = sdf.parse("09:30:00");
        }else{
            working_start = sdf.parse("10:00:00");
            working_end = sdf.parse("10:00:00");
        }

//        System.out.println(working_start);

        for(Chromo chromo : chromos){
            //对每一个员工而言，由于排班是按半小时计算的，现有条件每个员工一天会有27个时间片来记载自己在每个时间段是否上班
            for(int j=0;j<chromo.chromo.size();j++){
                //如果这个人要被安排打扫卫生，那么就开始设置工作开始时间和工作结束时间
                if(j==0){
                    if(chromo.chromo.get(j)==1){
                        record = new Record();
                        int x = chromos.indexOf(chromo);
                        employeeSchedule = Stuffs.get(x);//取出这个员工的信息
                        record.setEmployeeId(employeeSchedule.getEmployeeId());
                        record.setEmployeeName(employeeSchedule.getEmployeeName());
                        record.setEmployeePosition(employeeSchedule.getEmployeePosition());
                        record.setShopId(employeeSchedule.getShopId());
                        record.setShopName(employeeSchedule.getShopName());

                        //设置员工工作日期字段
                        time = start_time.getTime();
                        newDate = new Date();
                        newDate.setTime(time + 1000*60*60*24*(Day-1));
                        record.setWorddate(newDate);

                        //设置好开始工作时间,如果上班前一个时间片，那就需要从八点半开始工作
                        stime = working_start.getTime();
                        newDate = new Date();
                        newDate.setTime(stime + 1000*60*30*(j-pre));
                        record.setStartTime(newDate);


                        while((j<chromo.chromo.size())&&(chromo.chromo.get(j)==1)){//开始遍历
                            j++;
                        }
                        //如果是越界超出
                        if(j>=chromo.chromo.size()){
                            j--;
                            etime = working_end.getTime();
                            newDate = new Date();
                            newDate.setTime(etime + 1000*60*30*(j-pre));
                            record.setEndTime(newDate);
                        }else{//如果是休息退出上面的循环
                            j--;
                            etime = working_end.getTime();
                            newDate = new Date();
                            newDate.setTime(etime + 1000*60*30*(j-pre));
                            record.setEndTime(newDate);
                        }
                        Records.add(record);
                    }

                }
                //对于每一个时间片,如果当前时间片要工作且前一个时间片不要工作，那么这个时候可以开始设置开始工作时间
                else if(chromo.chromo.get(j)==1 && chromo.chromo.get(j-1)==0){
                    //表示这个员工要工作，那么在此员工的此时间片会有一个Record对象
                    record = new Record();
                    int x = chromos.indexOf(chromo);
                    employeeSchedule = Stuffs.get(x);//取出这个员工的信息
                    record.setEmployeeId(employeeSchedule.getEmployeeId());
                    record.setEmployeeName(employeeSchedule.getEmployeeName());
                    record.setEmployeePosition(employeeSchedule.getEmployeePosition());
                    record.setShopId(employeeSchedule.getShopId());
                    record.setShopName(employeeSchedule.getShopName());

                    //设置员工工作日期字段
                    time = start_time.getTime();
                    newDate = new Date();
                    newDate.setTime(time + 1000*60*60*24*(Day-1));
                    record.setWorddate(newDate);

                    //设置好开始工作时间,如果上班前一个时间片，那就需要从八点半开始工作
                    stime = working_start.getTime();
                    newDate = new Date();
                    newDate.setTime(stime + 1000*60*30*(j-pre));
                    record.setStartTime(newDate);


                    while((j<chromo.chromo.size())&&(chromo.chromo.get(j)==1)){//开始遍历
                        j++;
                    }
                    //如果是越界超出
                    if(j>=chromo.chromo.size()){
                        j--;
                        etime = working_end.getTime();
                        newDate = new Date();
                        newDate.setTime(etime + 1000*60*30*(j-pre));
                        record.setEndTime(newDate);
                    }else{//如果是休息退出上面的循环
                        j--;
                        etime = working_end.getTime();
                        newDate = new Date();
                        newDate.setTime(etime + 1000*60*30*(j-pre));
                        record.setEndTime(newDate);
                    }
                    Records.add(record);
                }
            }
        }
        return Records;
    }

    //PassFlowNum为各个时间片需要的员工数量，pre为上班前的时间片数目，而later为下班后的时间片数目，day为一周中的第几天，loves_arr为员工的偏好
    private void fitness(ArrayList<Double> PassFlowNum,int pre,int later,ArrayList<ArrayList<Integer>> loves_arr, int day) {
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
        }


        //确认变异的第一列后进行check，status，workingTime的修改
        for(int i=0;i<pre;i++) {
            for(int j=0;j<Size;j++) {
                if (group.get(j).chromo.get(i) == 1) {//如果第一班次被选中
                    ArrayList<Integer> tmp = check.get(j);
                    tmp.set(i, 1);//check 1代表确认
                    check.set(j, tmp);

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
        for(int i=pre;i<len-4;i++) {

//            首先确定需要上班的人数
            double PopulationsNeed = PassFlowNum.get(i-pre);
            if((int)PopulationsNeed==0) PopulationsNeed =FreeNum;//没有人时至少需要1人


            //根据规则将超出工作时间的员工设为休息状态
//            i代表当前是哪个时间片
            for (int j = 0; j < Size; j++) {
                //如果员工上一个时间片没在上班
                if(group.get(j).chromo.get(i-1)==0){
                    //满足小于40小时
                    if ((WeekTime(j) >= 80) || (DayTime(j) >= 16)) {//如果一个员工连续工作时间超过了40小时，由于每个小时两个时间片，那么四小时就是8个时间片
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
                    if ((WeekTime(j)<80 && WeekTime(j)>76)||((DayTime(j)>12)&&(DayTime(j)<16))||(loves_arr.get(j).get(0)==3&&DayTime(j)==loves_arr.get(j).get(32))) {
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
                else{
                    //如果前一个时间片在上班
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
                    else if(persistentTime(j)>=4){
                        //如果连续工作时间最低要求满足了，周工作最高要求满足了，那么就得强制休息了
                        if ((WeekTime(j) >= 80)||(DayTime(j) >= 16)) {//如果一个员工连续工作时间超过了40小时，由于每个小时两个时间片，那么四小时就是8个时间片
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

//                        if ((WeekTime(j)<80 && WeekTime(j)>76)||((DayTime(j)>12)&&(DayTime(j)<16))||(DayTime(j) >= 16)||(loves_arr.get(j).get(0)==3&&DayTime(j)==loves_arr.get(j).get(32))) {
//                            for (int k = i; k < len; k++) {
//                                resetPerTime(j);
//                                ArrayList<Integer> tmp = check.get(j);
//                                tmp.set(k, 1);//确认
//                                check.set(j, tmp);
//
//                                tmp = status.get(j);
//                                tmp.set(k, 3);//休息中
//                                status.set(j, tmp);
//                            }
//                        }
                    }
//                    else{//如果连续工作时间小于两小时
//                        if(WeekTime(j) >= 80){
//
//                        }
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
                if(i>=pre&&i<len-later){//工作时间偏好的取值范围中
                    for(int j=0;j<Size;j++){
                        if((loves_arr.get(j).get(0)==2)||(loves_arr.get(j).get(0)==1)){
                            if(((loves_arr.get(j).get(day)==1)||(loves_arr.get(j).get(i+(8-pre))==1))&&(r.nextDouble()<0.5)&&(persistentTime(j)>=0&&persistentTime(j)<8)&&(status.get(j).get(i)==1)&&(check.get(j).get(i)==0)){//等待上班，并且可以上
//                        将第j个人的工作状态设为上班

//                            System.out.println();
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
                                tmp.set(2, WeekTime(j)+1);
                                workingTime.set(j,tmp);
                            }
                        }
                    }
                }

//              增加工作人员第二个措施，如果前一个时间片的人在工作，那么这个时间片可以给他大概率的工作机会
                for(int j=0;j<Size;j++)
                {
//                  如果员工连续工作时间不到4小时，且前一个时间片在工作，且status当前状态为可以上班
                    if((r.nextDouble() < 0.3)&&persistentTime(j)>=0&&persistentTime(j)<8&&group.get(j).chromo.get(i-1)==1&&status.get(j).get(i)==1&&check.get(j).get(i)==0){//等待上班，并且可以上
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
//              给平均工作时间小于平均
                while((int)Math.ceil(PopulationsNeed)>countPopulations(i)) {//人不够,随机选在wait的人上班,每次选一个，直到满足为止
//                    System.out.println(countPopulations(i)+" "+(int)Math.ceil(PopulationsNeed)+" "+1);
                    int index = Math.abs(r.nextInt()%Size);
                    int mean = MeanWeekTime();

//                    check为0表示未确认，status为1表示waiting，Daytime表示工作时间小于8小时
                    if(check.get(index).get(i)==0&&status.get(index).get(i)==1&&DayTime(index)<16)
                    {//满足要求，上班
//                        对应时间片设为上班

                        if(workingTime.get(index).get(2)<mean){//如果工作时间小于所有人的周平均工作时间
                            if(r.nextDouble()<0.8){
                                test1++;
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
                        }else{
                            if(r.nextDouble()<0.2){
                                test2++;
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
                    }
                }

                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            }
            for(int j=0;j<Size;j++) {
//                  下面是看所有员工的check是否有为0的，也就是没被确认过的，如果有一定是没被排班，那么重置连续工作时间
//                        if(check.get(j).get(i)==0)
                if(group.get(j).chromo.get(i)==0)
                {
                    resetPerTime(j);
                }
            }
        }

        for(int i=len-4;i<len;i++){
            if(i==len-4){
                double PopulationsNeed = count4max(PassFlowNum);
                //这里的变异直接产生后面四个班次需要的人数，和前面已经上班的人无关
                mutate_last(len-4, (int)Math.ceil(PopulationsNeed));
            }

            for(int j=0;j<Size;j++){
                //如果是是在倒数第四个时间片之前的一个时间片就在上班
                //如果工作时间>=4个时间片，那么就不用上班了
                if(group.get(j).chromo.get(i-1)==1){
                    if(persistentTime(j)>=4){
                        ArrayList<Integer> tmp = check.get(j);
                        tmp.set(i, 1);//check 1代表确认
                        check.set(j, tmp);

                        tmp = status.get(j);
                        tmp.set(i, 3);//status 3代表休息
                        status.set(j, tmp);
                        resetPerTime(j);
                    }
                    else{
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
                }else{//如果前一个时间片不在上班
                    //如果当前时间片在上班，那么是在第len-4个时间片被mutete_last选上了
                    if(group.get(j).chromo.get(i)==1){
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
                    //当前时间片也不需要上班
                    else{
                        ArrayList<Integer> tmp = check.get(j);
                        tmp.set(i, 1);//check 1代表确认
                        check.set(j, tmp);

                        tmp = status.get(j);
                        tmp.set(i, 3);//status 3代表休息
                        status.set(j, tmp);
                        resetPerTime(j);
                    }
                }
            }
            if(i==len-1){//如果是最后一个时间片，那么需要把本次排班的连续工作时间、日工作时间给清掉
                for(int j=0;j<Size;j++) {
                    //                  下面是看所有员工的check是否有为0的，也就是没被确认过的，如果有一定是没被排班，那么重置连续工作时间
//                        if(check.get(j).get(i)==0)
                    resetPerTime(j);
                }
                resetDayTime();
            }
        }
    }

    //PassFlowNum为各个时间片需要的员工数量，pre为上班前的时间片数目，而later为下班后的时间片数目，day为一周中的第几天，loves_arr为员工的偏好
    public ArrayList<Chromo> GA(ArrayList<Double> PassFlowNum, int day,ArrayList<ArrayList<Integer>> loves_arr)//偏好数组和星期几，用于check
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

//        //输出员工偏好数组的初始化情况
//        System.out.println();
//        System.out.println("Day"+day+"员工偏好数组初始化如下：");
//        for(int i=0;i<Size;i++)
//        {
//            System.out.println("员工"+i);
//            for(int j=0;j<len;j++)
//            {
//                System.out.print(group.get(i).chromo.get(j));
//                System.out.print(" ");
//            }
//            System.out.println();
//        }

        //开始对 记录所有员工情况的数组进行初始化
        init(day);

//        System.out.println("请稍后，正在排班中......");

        Random r = new Random();

        // fitness里包含了循环，直到排完班才结束
        fitness(PassFlowNum, pre, later, loves_arr, day);

        //完成GA，显示结果
        System.out.println("Day"+day+"已完成排班！！！！，结果如下：");

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

        if(day==7)
            for(int j=0;j<Size;j++){
                System.out.println("第"+String.valueOf(j)+"个员工的周工作时间为："+String.valueOf(WeekTime(j)));
            }
//        System.out.println("结果显示完成!!!");
        System.out.println();
//        下面这两次几句代码就是将排完班的结果返回并将排班结果覆盖成没排班之前的状态
        groupTmp1=group;
        group=groupTmp2;
        return groupTmp1;
    }
}
