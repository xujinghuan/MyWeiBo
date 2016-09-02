package com.example.hasee.myapplication.activity;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.hasee.myapplication.R;
import com.example.hasee.myapplication.fragment.ShouYeFragment;

public class MainActivity extends FragmentActivity {

    private static String yonghuming=null;

    private ImageButton people_image;
    private TextView mingzi_text;
    private ImageButton leida_image;
    private RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);

        yonghuming=getIntent().getStringExtra("yonghuming");

        ShouYeFragment shouYeFragment=new ShouYeFragment();
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.add(R.id.zhuyaoneirong_layout,shouYeFragment);
        transaction.addToBackStack(null);
        transaction.commit();

        init();

    }
    public static String getYonghuming(){
        return yonghuming;
    }

    private void init(){
        people_image= (ImageButton) findViewById(R.id.people_image);
        mingzi_text= (TextView) findViewById(R.id.mingzi_text);
        leida_image=(ImageButton)findViewById(R.id.leida_image);
        radioGroup= (RadioGroup) findViewById(R.id.radioGroup1);

        leida_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,LeiDaActivity.class);
                startActivity(intent);
            }
        });

        mingzi_text.setText(yonghuming);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.shouye:
                        ShouYeFragment shouYeFragment=new ShouYeFragment();
                        FragmentManager manager=getSupportFragmentManager();
                        FragmentTransaction transaction=manager.beginTransaction();
                        transaction.add(R.id.zhuyaoneirong_layout,shouYeFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        break;
                    case R.id.xiaoxi:
                        break;
                    case R.id.jiahao:
                        Intent intent=new Intent(MainActivity.this,FaBoWenActivity.class);
                        intent.putExtra("yonghuming",yonghuming);
                        startActivity(intent);
                        break;
                    case R.id.faxian:
                        break;
                    case R.id.wo:
                        break;
                }
            }
        });
    }
}
