package com.tao;

import java.text.NumberFormat;

public class NumberUtil {

    public static String getPercent(int x,int total){
        int num1 = x;
        int num2 = total == 0 ? 1 : total;
        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        String result = numberFormat.format((float) num1 / (float) num2 * 100);
        result = result.replace(",","");
//        System.out.println("num1和num2的百分比为:" + result + "%");
        return result;
    }
}
