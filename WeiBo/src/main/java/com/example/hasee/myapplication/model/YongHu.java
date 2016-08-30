package com.example.hasee.myapplication.model;

public class YongHu {
    private static String phone;
    private static String name;
    private static String password;
    private static String sex;

    public static String getPhone(){
        return phone;
    }
    public static String getName(){
        return name;
    }
    public static  String getPassword(){
        return password;
    }
    public static String getSex(){
        return sex;
    }

    public static void setPhone(String phone1){
        phone=phone1;
    }
    public static void setName(String name1){
        name=name1;
    }
    public static void setPassword(String password1){
        password=password1;
    }
    public static void setSex(String sex1){
        sex=sex1;
    }

}
