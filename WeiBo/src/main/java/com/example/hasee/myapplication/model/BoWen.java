package com.example.hasee.myapplication.model;

/**
 * Created by hasee on 2016/8/30.
 */
public class BoWen {
    private static String phone;
    private static String name;
    private static String text;
    private static String time;

    public static String getPhone(){
        return phone;
    }
    public static String getName(){
        return name;
    }
    public static  String getText(){
        return text;
    }
    public static String getTime(){
        return time;
    }

    public static void setPhone(String phone1){
        phone=phone1;
    }
    public static void setName(String name1){
        name=name1;
    }
    public static void setText(String text1){
        text=text1;
    }
    public static void setTime(String time1){time=time1; }
}
