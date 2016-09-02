package com.example.hasee.myapplication.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hasee.myapplication.R;
import com.example.hasee.myapplication.model.BoWen;
import com.example.hasee.myapplication.sqlite.WeiBoDataBase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FaBoWenActivity extends Activity{
    private TextView quxiao;
    private Button fasong;
    private EditText bowen_text;
    private WeiBoDataBase weiBoDataBase;

    private String yonghuming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faweibo_view);

        quxiao= (TextView) findViewById(R.id.quxiaofaweibo);
        fasong= (Button) findViewById(R.id.fasong_button);
        bowen_text= (EditText) findViewById(R.id.bowen_text);
        weiBoDataBase=WeiBoDataBase.getInstance(this);

        yonghuming=getIntent().getStringExtra("yonghuming");

        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder dialog=new  AlertDialog.Builder(FaBoWenActivity.this);
                dialog.setMessage("确定取消吗？");
                dialog.setPositiveButton("确定",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                dialog.setNegativeButton("取消",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialog.setCancelable(false);
                dialog.show();
            }
        });

        fasong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(bowen_text.getText().toString())){
                    Toast.makeText(FaBoWenActivity.this,"请填写博文",Toast.LENGTH_SHORT).show();
                }else{
                BoWen boWen=new BoWen();
                SimpleDateFormat formatter = new SimpleDateFormat ("yyyy年MM月dd日 HH:mm:ss ");
                Date curDate = new Date(System.currentTimeMillis());
                String str = formatter.format(curDate);

                boWen.setPhone(yonghuming);
                boWen.setName(weiBoDataBase.getYongHu(yonghuming).getName());
                boWen.setText(bowen_text.getText().toString());
                boWen.setTime(str);
                weiBoDataBase.saveBoWen(boWen);

                Toast.makeText(FaBoWenActivity.this,"已发布",Toast.LENGTH_SHORT).show();
                finish();
                }
            }
        });
    }
}
