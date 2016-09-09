package com.example.hasee.myapplication.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hasee.myapplication.R;
import com.example.hasee.myapplication.model.YongHu;
import com.example.hasee.myapplication.sqlite.WeiBoDataBase;


public class ZhuCeActivity extends Activity {

    private TextView quxiao;
    private EditText zhuce_phone;
    private EditText zhuce_mima;
    private EditText zhuce_mima1;
    private Button zhuce_button;
    private CheckBox check_xieyi;

    private WeiBoDataBase weiBoDatabase;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuce_view);

        quxiao= (TextView) findViewById(R.id.quxiaozhuce);
        zhuce_phone= (EditText) findViewById(R.id.zhuce_phone);
        zhuce_mima= (EditText) findViewById(R.id.zhuce_mima);
        zhuce_mima1= (EditText) findViewById(R.id.zhuce_mima1);
        zhuce_button= (Button) findViewById(R.id.zhuce_button);
        check_xieyi= (CheckBox) findViewById(R.id.check_xieyi);

        weiBoDatabase=WeiBoDataBase.getInstance(this);

        check_xieyi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(check_xieyi.isChecked()){
                    zhuce_button.setEnabled(true);
                }else{
                    zhuce_button.setEnabled(false);
                }
            }
        });

        zhuce_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mima=zhuce_mima.getText().toString();
                String mima1=zhuce_mima1.getText().toString();
                if(!TextUtils.isEmpty(zhuce_phone.getText())){
                    if(weiBoDatabase.getPhone().indexOf(zhuce_phone.getText().toString())<0) {
                        if (mima.equals(mima1)) {
                            YongHu yonghu = new YongHu();
                            yonghu.setName(zhuce_phone.getText().toString());
                            yonghu.setPhone(zhuce_phone.getText().toString());
                            yonghu.setPassword(zhuce_mima.getText().toString());
                            weiBoDatabase.saveYonghu(yonghu);
                            Toast.makeText(ZhuCeActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(ZhuCeActivity.this,LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(ZhuCeActivity.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                        }
                    }else {  Toast.makeText(ZhuCeActivity.this,"你输入的电话号码已经存在",Toast.LENGTH_SHORT).show(); }
                }else {
                    Toast.makeText(ZhuCeActivity.this,"请输入电话号码",Toast.LENGTH_SHORT).show();
                }
            }
        });

        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ZhuCeActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
