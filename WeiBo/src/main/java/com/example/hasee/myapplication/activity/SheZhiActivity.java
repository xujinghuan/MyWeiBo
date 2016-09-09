package com.example.hasee.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hasee.myapplication.R;
import com.example.hasee.myapplication.sqlite.WeiBoDataBase;

public class SheZhiActivity extends Activity implements View.OnClickListener{

    private Button xiugaitouxiang;
    private Button xiugainicheng;
    private Button zhuxiao;

    private EditText edit_nicheng;

    private WeiBoDataBase weiBoDataBase=WeiBoDataBase.getInstance(this);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shezhi_view);

        xiugainicheng=(Button)findViewById(R.id.xiugainicheng);
        xiugaitouxiang=(Button)findViewById(R.id.xiugaitouxiang);
        zhuxiao=(Button)findViewById(R.id.zhuxiao);
        edit_nicheng= (EditText) findViewById(R.id.edit_nicheng);

        zhuxiao.setOnClickListener(this);
        xiugainicheng.setOnClickListener(this);
        xiugaitouxiang.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.xiugainicheng:
                if(xiugainicheng.getText().equals("修改昵称")) {
                    xiugainicheng.setText("确定");
                    xiugaitouxiang.setVisibility(View.GONE);
                    zhuxiao.setVisibility(View.GONE);
                    edit_nicheng.setVisibility(View.VISIBLE);
                }else if(xiugainicheng.getText().equals("确定")&&!TextUtils.isEmpty(edit_nicheng.getText())){
                        weiBoDataBase.xiugainicheng(edit_nicheng.getText().toString());
                        Intent intent1=new Intent(this,MainActivity.class);
                        intent1.putExtra("yonghuming",MainActivity.getYonghuming());
                        startActivity(intent1);
                }
                break;
            case R.id.xiugaitouxiang:
                Intent intent1=new Intent(this,XiuGaiTouXiang.class);
                startActivity(intent1);
                break;
            case R.id.zhuxiao:
                SharedPreferences sharedPreferences=getSharedPreferences("jizhumima",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.remove("yonghuming");
                editor.remove("mima");
                editor.commit();

                Intent intent=new Intent(this,LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
