package com.example.hasee.myapplication.model;

/**
 * Created by hasee on 2016/8/30.
 */
public class BoWen {
    private  String phone;
    private  String name;
    private  String text;
    private  String time;

    public  String getPhone(){
        return phone;
    }
    public  String getName(){
        return name;
    }
    public  String getText(){
        return text;
    }
    public  String getTime(){
        return time;
    }

    public  void setPhone(String phone1){
        phone=phone1;
    }
    public  void setName(String name1){
        name=name1;
    }
    public  void setText(String text1){
        text=text1;
    }
    public  void setTime(String time1){time=time1; }
}
