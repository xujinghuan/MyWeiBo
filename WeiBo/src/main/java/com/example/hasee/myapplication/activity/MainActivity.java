package com.example.hasee.myapplication.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.hasee.myapplication.R;
import com.example.hasee.myapplication.fragment.ShouYeFragment;
import com.example.hasee.myapplication.sqlite.WeiBoDataBase;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {

    private static String yonghuming=null;

    private ImageButton people_image;
    private TextView mingzi_text;
    private ImageButton leida_image;
    private RadioGroup radioGroup;
    private EditText sousuo_edit;


    private ShouYeFragment shouYeFragment;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private WeiBoDataBase weiBoDataBase=WeiBoDataBase.getInstance(this);

    private LocalBroadcastManager broadcastManager;
    private ShouYeFragment.ShouYeBroadCastReceiver shouYeBroadCastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);

        yonghuming=getIntent().getStringExtra("yonghuming");

        shouYeFragment=new ShouYeFragment();

        //使用本地广播来修改fragment的控件来刷新微博内容
        broadcastManager = LocalBroadcastManager.getInstance(this);
        shouYeBroadCastReceiver=shouYeFragment.new ShouYeBroadCastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.ShouYe_BROADCAST");
        broadcastManager.registerReceiver(shouYeBroadCastReceiver, intentFilter);

        manager=getSupportFragmentManager();
        transaction=manager.beginTransaction();
        transaction.replace(R.id.zhuyaoneirong_layout,shouYeFragment);
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
        sousuo_edit= (EditText) findViewById(R.id.sousuo_edit);

        leida_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,LeiDaActivity.class);
                startActivity(intent);
            }
        });


        mingzi_text.setText(weiBoDataBase.getYongHu(yonghuming).getName());

        radioGroup.setOnCheckedChangeListener(this);
    }
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.shouye:
                Intent intent1=new Intent("android.intent.action.ShouYe_BROADCAST");
                broadcastManager.sendBroadcast(intent1);
                break;
            case R.id.xiaoxi:
                break;
            case R.id.jiahao:
                Intent intent=new Intent(MainActivity.this,FaBoWenActivity.class);
                intent.putExtra("yonghuming",yonghuming);
                startActivity(intent);
                break;
            case R.id.faxian:
                Intent intent2=new Intent(MainActivity.this,FaXianActivity.class);
                startActivity(intent2);
                break;
            case R.id.wo:
                Intent intent3=new Intent(MainActivity.this,WoActivity.class);
                startActivity(intent3);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        broadcastManager.unregisterReceiver(shouYeBroadCastReceiver);
    }
}
