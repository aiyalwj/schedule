package com.lwj.schedule.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ReadExcelFileToList {
    public ArrayList<ArrayList<Double>> PreModel;

    public ReadExcelFileToList(){
        this.PreModel = new ArrayList<>();
    }

    public ArrayList<ArrayList<Double>> ReadExcel(String FilePath){
        try {
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(FilePath));
            //获取工作簿下sheet的个数
            int sheetNum = hssfWorkbook.getNumberOfSheets();
            System.out.println("该excel文件中总共有："+sheetNum+"个sheet");
            //遍历工作簿中的所有数据
            for(int i = 0;i<sheetNum;i++) {
                //读取第i个工作表
                System.out.println("读取第"+(i+1)+"个sheet");
                HSSFSheet sheet = hssfWorkbook.getSheetAt(i);
                //获取最后一行的num，即总行数。此处从0开始
                int maxCol = sheet.getRow(0).getLastCellNum();
                for (int col = 0; col < maxCol; col++) {

                    ArrayList<Double> tmp = new ArrayList<>();

                    //获取最后单元格num，即总单元格数 ***注意：此处从1开始计数***
                    int maxRol = sheet.getLastRowNum();
//                    System.out.println("--------第" + row + "行的数据如下--------");
                    for (int rol = 0; rol <= maxRol; rol++){
//                        System.out.print(sheet.getRow(row).getCell(rol) + "  ");
                        tmp.add(sheet.getRow(rol).getCell(col).getNumericCellValue());
                    }
                    PreModel.add(tmp);
//                    System.out.println();
                }
            }
//          传入的未来以后客流量
            System.out.println("传入的未来一周的客流量如下：");
            for(int x=0; x<PreModel.size(); x++) {
                for(int y=0; y<PreModel.get(x).size(); y++) {
                    System.out.print(PreModel.get(x).get(y)+" ");
                }
                System.out.println();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return PreModel;
    }

//    public static void main(String[] args) {
//        ReadExcelFileToList readExcelFileToList = new ReadExcelFileToList();
//        String str = "D:/Code/Competition/Service outsourcing/yxf_branch1/src/main/java/com/lwj/schedule/utils/abc.xls";
//        readExcelFileToList.ReadExcel(str);
//    }

}

