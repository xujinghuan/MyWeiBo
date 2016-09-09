package com.example.hasee.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hasee.myapplication.R;
import com.example.hasee.myapplication.sqlite.WeiBoDataBase;

public class LoginActivity extends Activity implements View.OnClickListener{

    private ImageView touxiang;
    private EditText yonghuming_text;
    private EditText mima_text;
    private TextView wangjimima_text;
    private TextView wumimadenglu_text;
    private Button login_button;
    private TextView zhuce_text;
    private TextView QQdenglu_text;

    //保存登陆信息
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    private String yongHuMing;
    private String miMa;

    private WeiBoDataBase weiBoDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);

        init();


        //自主记住密码登录
        sharedPreferences=getSharedPreferences("jizhumima",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        yongHuMing=sharedPreferences.getString("yonghuming",null);
        miMa=sharedPreferences.getString("mima",null);
        if(yongHuMing!=null&&miMa!=null) {
            if (miMa.equals(weiBoDataBase.getYongHu(yongHuMing).getPassword())) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("yonghuming", yongHuMing);
                startActivity(intent);
            }
        }
    }

    private void init(){
        touxiang= (ImageView) findViewById(R.id.touxiang);
        yonghuming_text= (EditText) findViewById(R.id.yonghuming_Text);
        mima_text= (EditText) findViewById(R.id.mima_text);
        wangjimima_text= (TextView) findViewById(R.id.wangjimima_text);
        wumimadenglu_text=(TextView)findViewById(R.id.wumimadenglu);
        login_button=(Button)findViewById(R.id.login_button);
        zhuce_text= (TextView) findViewById(R.id.zhuce_text);
        QQdenglu_text= (TextView) findViewById(R.id.QQdenglu_text);
        weiBoDataBase=WeiBoDataBase.getInstance(this);



        login_button.setOnClickListener(this);
        zhuce_text.setOnClickListener(this);
        wumimadenglu_text.setOnClickListener(this);
        QQdenglu_text.setOnClickListener(this);
        wangjimima_text.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
         yongHuMing=yonghuming_text.getText().toString();
         miMa=mima_text.getText().toString();
        switch (view.getId()){
            case R.id.login_button:
                if(miMa.equals(weiBoDataBase.getYongHu(yongHuMing).getPassword())){
                editor.clear();
                editor.putString("yonghuming",yongHuMing);
                editor.putString("mima",miMa);
                editor.commit();

                Intent intent=new Intent(this,MainActivity.class);
                intent.putExtra("yonghuming",yongHuMing);
                startActivity(intent);
            }else {Toast.makeText(this,"你输入的用户名或者密码不正确",Toast.LENGTH_SHORT).show();}
            break;
            case R.id.zhuce_text:
            {   Intent intent=new Intent(this,ZhuCeActivity.class);
                startActivity(intent);}
            break;
        }
    }

}
