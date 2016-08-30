package com.example.hasee.myapplication.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.hasee.myapplication.R;
import com.example.hasee.myapplication.model.BoWen;
import com.example.hasee.myapplication.model.YongHu;

/**
 * Created by hasee on 2016/8/30.
 */
public class WeiBoDataBase {
     private static WeiBoDataBase weiBoDataBase;
     private SQLiteDatabase db;

    public WeiBoDataBase(Context context){
        db=new DatabaseHelper(context,"weibo_database",null,1).getWritableDatabase();
    }

    public synchronized static WeiBoDataBase getInstance(Context context){
        if (weiBoDataBase==null) {
            weiBoDataBase=new WeiBoDataBase(context);
        }
        return weiBoDataBase;
    }
     public void saveYonghu(YongHu yongHu){
         ContentValues values=new ContentValues();
         values.put("phoneNum",yongHu.getPhone());
         values.put("name",yongHu.getName());
         values.put("password",yongHu.getPassword());
         values.put("sex",yongHu.getSex());
         values.put("touxiang", R.mipmap.ic_launcher);
         db.insert("Yonghu",null,values);
     }
    private void saveBoWen(BoWen boWen){
        ContentValues values=new ContentValues();
        values.put("phoneNum",boWen.getPhone());
        values.put("name", boWen.getName());
        values.put("information", boWen.getText());
        values.put("inTime", boWen.getTime());
        db.insert("Information",null,values);
    }
}
