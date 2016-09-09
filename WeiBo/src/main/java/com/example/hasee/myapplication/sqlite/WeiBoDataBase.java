package com.example.hasee.myapplication.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.hasee.myapplication.R;
import com.example.hasee.myapplication.activity.MainActivity;
import com.example.hasee.myapplication.model.BoWen;
import com.example.hasee.myapplication.model.YongHu;

import java.util.ArrayList;
import java.util.List;

public class WeiBoDataBase {
     private static WeiBoDataBase weiBoDataBase;
     private  SQLiteDatabase db;

    public WeiBoDataBase(Context context){
        db=new DatabaseHelper(context,"weibo_database",null,1).getWritableDatabase();
    }

    public synchronized static WeiBoDataBase getInstance(Context context){
        if (weiBoDataBase==null) {
            weiBoDataBase=new WeiBoDataBase(context);
        }
        return weiBoDataBase;
    }
     public  void saveYonghu(YongHu yongHu){
         ContentValues values=new ContentValues();
         values.put("phoneNum",yongHu.getPhone());
         values.put("name",yongHu.getName());
         values.put("password",yongHu.getPassword());
         values.put("sex",yongHu.getSex());
         values.put("touxiang", R.mipmap.ic_launcher);
         db.insert("Yonghu",null,values);
         ContentValues values1=new ContentValues();
         values1.put("phoneNum",yongHu.getPhone());
         values1.put("bPhoneNum",yongHu.getPhone());
         db.insert(" Guanzhu",null,values1);
     }
    public  void saveBoWen(BoWen boWen){
        ContentValues values=new ContentValues();
        values.put("phoneNum",boWen.getPhone());
        values.put("name", boWen.getName());
        values.put("information", boWen.getText());
        values.put("inTime", boWen.getTime());
        db.insert("Information",null,values);
    }
    public void saveGuanzhu(String phone,String bPhone){
        ContentValues values=new ContentValues();
        values.put("phoneNum",phone);
        values.put("bPhoneNum",bPhone);
        db.insert(" Guanzhu",null,values);
    }

    public  YongHu getYongHu(String yongHuMing){
        YongHu yongHu=new YongHu();
        Cursor cursor=db.query("Yonghu",null,"phoneNum=?",new String[]{yongHuMing},null,null,null);
        if(cursor.moveToFirst()){
            do{
                yongHu.setPhone(cursor.getString(cursor.getColumnIndex("phoneNum")));
                yongHu.setName(cursor.getString(cursor.getColumnIndex("name")));
                yongHu.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                yongHu.setSex(cursor.getString(cursor.getColumnIndex("sex")));
                yongHu.setImage(cursor.getInt(cursor.getColumnIndex("touxiang")));
            }while(cursor.moveToNext());
        }
        if(cursor!=null){
            cursor.close();
        }
        return yongHu;
    }
    public List<BoWen> getBoWen(String yongHuMing){
        List<BoWen> list=new ArrayList<BoWen>();
        Cursor cursor=db.query("Information",null,"phoneNum=?",new String[]{yongHuMing},null,null,null);
        if(cursor.moveToFirst()){
            do{
                BoWen boWen=new BoWen();
                boWen.setPhone(cursor.getString(cursor.getColumnIndex("phoneNum")));
                boWen.setName(cursor.getString(cursor.getColumnIndex("name")));
                boWen.setText(cursor.getString(cursor.getColumnIndex("information")));
                boWen.setTime(cursor.getString(cursor.getColumnIndex("inTime")));
                list.add(boWen);
            }while(cursor.moveToNext());
        }
        if(cursor!=null){
            cursor.close();
        }
        return list;
    }

    public List<String> getPhone(){
        List<String> list=new ArrayList<String>();
        Cursor cursor=db.query("Yonghu",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                list.add(cursor.getString(cursor.getColumnIndex("phoneNum")));
            }while(cursor.moveToNext());
        }
        if(cursor!=null) {
            cursor.close();
        }
        return list;
    }

    public List<String> getBPhone(String phone){
        List<String> list=new ArrayList<String>();
        Cursor cursor=db.query("Guanzhu",null,"phoneNum=?",new String[]{phone},null,null,null);
        if(cursor.moveToFirst()){
            do{
                list.add(cursor.getString(cursor.getColumnIndex("bPhoneNum")));
            }while(cursor.moveToNext());
        }
        if(cursor!=null) {
            cursor.close();
        }
        return list;
    }
    public List<String> getfensi(String phone){
        List<String> list=new ArrayList<String>();
        Cursor cursor=db.query("Guanzhu",null,"bPhoneNum=?",new String[]{phone},null,null,null);
        if(cursor.moveToFirst()){
            do{
                list.add(cursor.getString(cursor.getColumnIndex("phoneNum")));
            }while(cursor.moveToNext());
        }
        if(cursor!=null) {
            cursor.close();
        }
        return list;
    }

    public void xiugainicheng(String nicheng){
        ContentValues values=new ContentValues();
        values.put("name",nicheng);
        db.update("Yonghu",values,"phoneNum=?",new String[]{MainActivity.getYonghuming()});
    }
    public void xiugaitouxiang(int touxiang){
        ContentValues values=new ContentValues();
        values.put("touxiang",touxiang);
        db.update("Yonghu",values,"phoneNum=?",new String[]{MainActivity.getYonghuming()});
    }
}
