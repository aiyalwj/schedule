package com.lwj.schedule.Schedule.智能排班系统.遗传算法;


import java.util.ArrayList;
import java.util.Random;

/**
 * 单独的染色体类
 */

public class Chromov0 {
    public ArrayList<Integer> chromo= new ArrayList<>();//需要set染色体长度
    public int len=28;
    public Chromov0(int len){
        this.len = len;
        Random r = new Random();
        for(int i=0;i<len;i++) {
            chromo.add((Integer) (0));//全都没有，靠突变
        }
    }
}
