package com.example.hasee.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hasee.myapplication.R;
import com.example.hasee.myapplication.model.BoWen;
import com.example.hasee.myapplication.sqlite.WeiBoDataBase;
import com.example.hasee.myapplication.util.BoWenAdapter;
import com.example.hasee.myapplication.view.YongHuButton;

import java.util.ArrayList;
import java.util.List;


public class WoActivity extends Activity implements View.OnClickListener{


    private int boWenShu=0;
    private int guanZhuShu=0;
    private int fenSiShu=0;

    private TextView tianjiahaoyou;
    private TextView shezhi_text;
    private YongHuButton wo_button;
    private Button weibo_button;
    private Button guanzhu_button;
    private Button fensi_button;
    private Button wo_fanhui;

    private ListView listView;
    private List<String> fensilist;
    private List<BoWen> boWenList;
    private List<String> guanzhulist;
    private WeiBoDataBase weiBoDataBase;

    private BoWenAdapter boWenAdapter;
    private ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wo_view);

        tianjiahaoyou=(TextView)findViewById(R.id.tianjiahaoyou);
        shezhi_text=(TextView)findViewById(R.id.shezhi_text);
        wo_button=(YongHuButton)findViewById(R.id.wo_button);
        weibo_button=(Button)findViewById(R.id.weibo_button);
        guanzhu_button=(Button)findViewById(R.id.guanzhu_button);
        fensi_button=(Button)findViewById(R.id.fensi_button);
        wo_fanhui=(Button)findViewById(R.id.wo_fanhui);
        weiBoDataBase=WeiBoDataBase.getInstance(this);

        //设置按钮文字
        getBoWen();
        weibo_button.setText("微博"+boWenShu);
        guanZhuShu=weiBoDataBase.getBPhone(MainActivity.getYonghuming()).size();
        guanzhu_button.setText("关注"+guanZhuShu);
        fenSiShu=weiBoDataBase.getfensi(MainActivity.getYonghuming()).size();
        fensi_button.setText("粉丝"+fenSiShu);

        //设置wo_button的显示
        wo_button.setImage(weiBoDataBase.getYongHu(MainActivity.getYonghuming()).getImage());
        wo_button.setMingzitext(weiBoDataBase.getYongHu(MainActivity.getYonghuming()).getName());
        wo_button.setYonghumingtext(MainActivity.getYonghuming());


        listView=(ListView)findViewById(R.id.wo_list);

        tianjiahaoyou.setOnClickListener(this);
        weibo_button.setOnClickListener(this);
        guanzhu_button.setOnClickListener(this);
        fensi_button.setOnClickListener(this);
        shezhi_text.setOnClickListener(this);
        wo_fanhui.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tianjiahaoyou:
                Intent intent=new Intent(this,FaXianActivity.class);
                startActivity(intent);
                break;
            case R.id.weibo_button:
                boWenList=new ArrayList<BoWen>();
                boWenList=getBoWen();
                boWenAdapter=new BoWenAdapter(this,R.layout.bowen_view,boWenList);
                listView.setAdapter(boWenAdapter);
                break;
            case R.id.guanzhu_button:
                guanzhulist=new ArrayList<String>();
                guanzhulist=weiBoDataBase.getBPhone(MainActivity.getYonghuming());
                arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,guanzhulist);
                listView.setAdapter(arrayAdapter);
                break;
            case R.id.fensi_button:
                fensilist=new ArrayList<String>();
                fensilist=weiBoDataBase.getfensi(MainActivity.getYonghuming());
                arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,fensilist);
                listView.setAdapter(arrayAdapter);
                break;
            case R.id.shezhi_text:
                Intent intent1=new Intent(this,SheZhiActivity.class);
                startActivity(intent1);
                break;
            case R.id.wo_fanhui:
                Intent intent2=new Intent(this,MainActivity.class);
                intent2.putExtra("yonghuming",MainActivity.getYonghuming());
                startActivity(intent2);
                break;
        }
    }

    public List<BoWen> getBoWen(){
        List<BoWen> list1=new ArrayList<BoWen>();
        for(BoWen boWen:weiBoDataBase.getBoWen(MainActivity.getYonghuming())){
            list1.add(0,boWen);
            boWenShu++;
        }
        return list1;
    }

}
