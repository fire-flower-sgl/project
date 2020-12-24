package com.mobile.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class DoubleUtils {
    /**
     * 小数位数格式化 格式化类型默认#.##
     * @param value
     * @return
     */
    public static Double double_format(double value){
        return double_format(value,"");
    }
    /**
     * 小数位数格式化
     * @param value
     * @param str_format
     * @return
     */
    public static Double double_format(double value,String str_format){
        String result_value="";
        try {
            if(str_format==null||str_format.length()==0)
                str_format="#.##";
            DecimalFormat df = new DecimalFormat(str_format);
            df.setRoundingMode(RoundingMode.FLOOR);//设置四舍五入情况
            result_value=df.format(value);
        }catch (Exception e){
            e.getMessage();
            return value;
        }
        return Double.parseDouble(result_value);
    }
    /**
     * 小数位数格式化
     * @param value
     * @param str_format
     * @return
     */
    public static String double_format_String(double value,String str_format){
        String result_value="";
        try {
            if(str_format==null||str_format.length()==0)
                str_format="#.##";
            DecimalFormat df = new DecimalFormat(str_format);
            df.setRoundingMode(RoundingMode.FLOOR);//设置四舍五入情况
            result_value=df.format(value);
        }catch (Exception e){
            e.getMessage();
            return value+"";
        }
        return result_value;
    }

    /**
     * 小数位数格式化 可以传入四舍五入情况 1=四舍五入 2=不作处理 3=向上取值 4=向下取值
     * @param value
     * @param str_format
     * @return
     */
    public static Double double_format(double value,String str_format,int type){
        String result_value="";
        RoundingMode rm=RoundingMode.HALF_EVEN;
        if(type==1)
            rm=RoundingMode.HALF_EVEN;
        else if(type==2)
            rm= RoundingMode.FLOOR;
        else if(type==3)
            rm= RoundingMode.UP;
        else if(type==4)
            rm=RoundingMode.DOWN;

        try {
            if(str_format==null||str_format.length()==0)
                str_format="#.##";
            DecimalFormat df = new DecimalFormat(str_format);
            df.setRoundingMode(rm);//设置四舍五入情况
            result_value=df.format(value);
        }catch (Exception e){
            e.getMessage();
            return value;
        }
        return Double.parseDouble(result_value);
    }
    public static void main(String[] args){
        double value=2338.121;
        System.out.println(double_format_String(value,"#"));
    }
}
