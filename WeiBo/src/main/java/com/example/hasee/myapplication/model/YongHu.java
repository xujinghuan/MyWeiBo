package com.example.hasee.myapplication.model;

public class YongHu {
    private  String phone=null;
    private  String name=null;
    private  String password=null;
    private  String sex=null;
    private  int image=0;

    public  String getPhone(){
        return phone;
    }
    public  String getName(){
        return name;
    }
    public  String getPassword(){
        return password;
    }
    public  String getSex(){
        return sex;
    }
    public  int getImage(){return image;}

    public  void setPhone(String phone1){
        phone=phone1;
    }
    public  void setName(String name1){
        name=name1;
    }
    public  void setPassword(String password1){
        password=password1;
    }
    public  void setSex(String sex1){
        sex=sex1;
    }
    public  void setImage(int image1){image=image1;}
}
