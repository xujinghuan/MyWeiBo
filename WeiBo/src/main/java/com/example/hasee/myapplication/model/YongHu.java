package com.example.hasee.myapplication.model;

public class YongHu {
    private static String phone=null;
    private static String name=null;
    private static String password=null;
    private static String sex=null;
    private static int image=0;

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
    public static int getImage(){return image;}

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
    public static void setImage(int image1){image=image1;}
}
