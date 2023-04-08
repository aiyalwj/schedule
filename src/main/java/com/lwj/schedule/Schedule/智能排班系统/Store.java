package com.lwj.schedule.Schedule.智能排班系统;

import com.lwj.schedule.Schedule.智能排班系统.遗传算法.Chromo;
import com.lwj.schedule.Schedule.智能排班系统.遗传算法.GA_changev1;
import com.lwj.schedule.Schedule.智能排班系统.遗传算法.GA_group;
import com.lwj.schedule.utils.ReadExcelFileToList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * 1.自己设置规则：通过store_add构建
 * 2.默认规则，通过new store构建
 */
public class Store {
    private String name;//名称
    private String address;//地址
    private double size = 220;//面积
    private HashMap<String, Stuff> stuffs;//员工集


    //预计客流模型
    private ArrayList<ArrayList<Double>> PreModle;

    // group数组的作用是记录每个员工在每个时间片是否上班
    ArrayList<Chromo> group;

    //黑箱数据设定字段，这些规则由添加店的时候设置
    //建议输入时最大人数不超过工作人员的 1/2 + 1
    private double preWorkTime = 0.5;//0.5的倍数，开门前preWorkTime小时  a
    private int StoreSizeNeedBefore = 50;// b
    private double passengerFlowNeed = 3.8;//客流passengerFlowNeed  c
    private int free_population = 1;//没人的时候free_population值班  d
    private double aftWorkTime = 1.0;//0.5的倍数，关门后aftWork小时  e
    private int StoreSizeNeedAfter1 = 50;//f
    private int StoreSizeNeedAfter2 = 2;//g

    //add商店时已被设置


    private GA_group ga_group;
    private GA_changev1 ga_changev1;

    //字段解释
    //规则是用来固定len和限制适应度函数fitness的
    //固定规则：
    //①周一~周五 9-21  12小时
    //②周末    10-22  12小时
    //③每周最多40 每天最多8
    //④每班最少2，最多4 可以多个连续班次，也就是说可以连续两个2
    //⑤最长连续工作时间4，必须休息
    //⑥工作时间完全覆盖饭点的员工安排吃饭时间
    //⑦午餐：12：00~13：00  晚餐：18：00~19：00  休息时间1小时
    //以上字段通过程序员设置在适应度函数当中

    //用户定义：
    //①开店前a小时准备 门店面积/b = 需要人数
    //②每天至少安排不超过4小时的班次 客流/c = 需要人数
    //③没有客流的时候 至少d人员值班
    //④关门后需要e小时收尾 门店买诺记/f + g = 人数

    //添加店铺时的规则设置
    public void setPreWorkTime(double preWorkTime) {
        this.preWorkTime = preWorkTime;
    }

    public void setStoreSizeNeedBefore(int storeSizeNeedBefore) {
        StoreSizeNeedBefore = storeSizeNeedBefore;
    }

    public void setPassengerFlowNeed(double passengerFlowNeed) {
        this.passengerFlowNeed = passengerFlowNeed;
    }

    public void setFree_population(int free_population) {
        this.free_population = free_population;
    }

    public void setAftWorkTime(double aftWorkTime) {
        this.aftWorkTime = aftWorkTime;
    }

    public void setStoreSizeNeedAfter1(int storeSizeNeedAfter1) {
        StoreSizeNeedAfter1 = storeSizeNeedAfter1;
    }

    public void setStoreSizeNeedAfter2(int storeSizeNeedAfter2) {
        StoreSizeNeedAfter2 = storeSizeNeedAfter2;
    }


    //构造方法

    public Store() {
        this.name = "";
        this.address = "";
        this.size = 0;
        this.stuffs = new HashMap<>();

        int pre = (int) (preWorkTime / 0.5);
        int later = (int) (aftWorkTime / 0.5);
        int len = pre + later + 24;

        ArrayList<Chromo> chro = new ArrayList<>();
        for (Stuff stu : stuffs.values()) {
            chro.add(stu.chro);
        }
        ga_changev1 = new GA_changev1(chro);


        ga_changev1.setLen(len);
        ga_changev1.setSize(stuffs.size());
    }

    public Store(String name, String address, double size, HashMap<String, Stuff> stuffs) {
        this.name = name;
        this.address = address;
        this.size = size;
        this.stuffs = stuffs;

        int pre = (int) (preWorkTime / 0.5);
        int later = (int) (aftWorkTime / 0.5);
        int len = pre + later + 24;

        ArrayList<Chromo> chro = new ArrayList<>();
        for (Stuff stu : stuffs.values()) {
            chro.add(stu.chro);
        }
        ga_changev1 = new GA_changev1(chro);


        ga_changev1.setLen(len);
//        这里的有参构造函数的setSize会把公司里的所有员工数量设为可上班员工数量
        ga_changev1.setSize(stuffs.size());
    }

    //getter setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public HashMap<String, Stuff> getStuffs() {
        return stuffs;
    }

    public void setStuffs(HashMap<String, Stuff> stuffs) {
        this.stuffs = stuffs;
    }

    public void store_information() {
        System.out.println("店铺名 ：" + getName() + "    地址:" + getAddress() + "    店铺面积大小 " + getSize());
        System.out.println("员工信息如下：");
        for (Stuff stu : stuffs.values()) {
            stu.stuff_information();//调用stuff中的显示方法
        }
    }

    //调用GA算法排班，其实这个Store类的作用也是做一些数据准备，计算上班前上班中下班后需要的人数，
    // 计算每个时间片中需要的员工数目，重新设置一天中时间片的长度和实例化Chromo，并将加进来的员工偏好做成可以调用使用的数据结构
    public void use_GA(int Week) {//需要把客流预测也输入给Store
//员工chromo初始化
        int pre = (int) (preWorkTime / 0.5);
        int later = (int) (aftWorkTime / 0.5);
//        这里的len为一天中所分的时间片个数，工作时间12小时 x 2 + pre + later(半小时为一个时间片)
        int len = pre + later + 24;

//由于规则中每次定义的正式工作前的时间和下班后的时间也许会不一样，所以这里新实例化一个tmp然后更改一下len再赋值给stu进行覆盖
        for (Stuff stu : stuffs.values()) {
            Stuff tmp = new Stuff();
            tmp = stu;
            tmp.chro.len = len;
//          HashMap.replace(K key, V new value)
            stuffs.replace(stu.getName(), tmp);
        }
        //这里是将chro实例化赋给新实例化的对象并覆盖原来的对象（新加chro是因为main中实例化Stuff时没有给chro赋值）
        for (Stuff stu : stuffs.values()) {
            Stuff tmp = new Stuff();
            tmp = stu;
            Chromo chro = new Chromo();
            tmp.chro = chro;
            stuffs.replace(stu.getName(), tmp);
        }

        /**
         * 传客流模型
         */
        //客流模型初始化
//        PreModle = new ArrayList<>();
//        PreModle.add(0.0);
//        PreModle.add(1.3);
//        PreModle.add(5.7);
//        PreModle.add(11.1);
//        PreModle.add(13.4);
//        PreModle.add(13.3);
//        PreModle.add(17.3);
//        PreModle.add(22.8);
//        PreModle.add(26.9);
//        PreModle.add(21.6);
//        PreModle.add(23.4);
//        PreModle.add(28.7);
//        PreModle.add(29.3);
//        PreModle.add(26.4);
//        PreModle.add(22.1);
//        PreModle.add(17.2);
//        PreModle.add(15.2);
//        PreModle.add(13.3);
//        PreModle.add(11.6);
//        PreModle.add(7.2);
//        PreModle.add(5.6);
//        PreModle.add(2.1);
//        PreModle.add(1.3);
//        PreModle.add(1.0);

        ReadExcelFileToList readExcelFileToList = new ReadExcelFileToList();

        PreModle = new ArrayList<>();
        String str = "D:/Code/Competition/Service outsourcing/yxf_branch1/src/main/java/com/lwj/schedule/utils/abc.xls";
//        PreModle = readExcelFileToList.ReadExcel(str);

        //计算需要人数，PreNum为上班前需要打扫卫生的人数，FreeNum为没人时至少有几个员工要值班，AftNum为下班后有几个人要打扫卫生
        int PreNum = (int) Math.abs((size / StoreSizeNeedBefore));
        int FreeNum = (int) (free_population);
        int AftNum = ((int) (size / StoreSizeNeedAfter1) + StoreSizeNeedAfter2) + 1;

//        PreModle存储了一天中各个时间片的客流量
        ArrayList<ArrayList<Double>> PassFlowNum = new ArrayList<>();
        for (int x=0;x<PreModle.size();x++) {// 对各个时间段的客流量进行需要人数的计算并把人数放到数组PassFlowNum里
            ArrayList<Double> tmp = new ArrayList<>();
            for (int y = 0; y < PreModle.get(x).size(); y++) {
                tmp.add((PreModle.get(x).get(y)) / passengerFlowNeed);
            }
            PassFlowNum.add(tmp);
        }
//        PassFlowNum = Collections.reverse(PassFlowNum);
        /*
        System.out.println(PassFlowNum.size());
        System.out.println(PreNum);
        System.out.println(FreeNum);
        System.out.println(AftNum);
        System.exit(1);//24
        */

        /**
         * 需要记录员工的偏好集合，传入并check，还有星期几，也就是day
         */

        ArrayList<ArrayList<Integer>> loves_arr=new ArrayList<>();
        for(Stuff stu:stuffs.values()) {
            loves_arr.add(stu.love_arr);
        }

        //开始公式计算并传参进行GA,完成排班
//        最终每一天每个员工是否进行排班的记录存在group里面
        for(int Day=1;Day<=7;Day++){
            group = ga_changev1.GA(PreNum, FreeNum, AftNum, PassFlowNum.get(Day-1), pre, later, Day, loves_arr);
        }
    }


    /**
     * 用于显示时间
     */
//    搞不懂这个decode的作用是什么
    public void decode()
    {
        int pre = (int) (preWorkTime / 0.5);
        int later = (int) (aftWorkTime / 0.5);
        int len = pre + later + 24;
        //进行group排班的显示
        /**
         * pre为上班之前的打扫时间
         * later为下班之后的打扫时间
         * 24是正常上班时间（都是以半小时为单位，group数组为1就显示上班即可）
         */


    }



}
