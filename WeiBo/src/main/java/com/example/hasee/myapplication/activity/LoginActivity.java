package com.example.hasee.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hasee.myapplication.R;

public class LoginActivity extends Activity implements View.OnClickListener{

    private ImageView touxiang;
    private EditText yonghuming_text;
    private EditText mima_text;
    private TextView wangjimima_text;
    private TextView wumimadenglu_text;
    private Button login_button;
    private TextView zhuce_text;
    private TextView QQdenglu_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);

        touxiang= (ImageView) findViewById(R.id.touxiang);
        yonghuming_text= (EditText) findViewById(R.id.yonghuming_Text);
        mima_text= (EditText) findViewById(R.id.mima_text);
        wangjimima_text= (TextView) findViewById(R.id.wangjimima_text);
        wumimadenglu_text=(TextView)findViewById(R.id.wumimadenglu);
        login_button=(Button)findViewById(R.id.login_button);
        zhuce_text= (TextView) findViewById(R.id.zhuce_text);
        QQdenglu_text= (TextView) findViewById(R.id.QQdenglu_text);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }
}
