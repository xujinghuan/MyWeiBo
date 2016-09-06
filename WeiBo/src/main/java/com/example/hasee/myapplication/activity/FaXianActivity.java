package com.example.hasee.myapplication.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hasee.myapplication.R;
import com.example.hasee.myapplication.model.BoWen;
import com.example.hasee.myapplication.sqlite.WeiBoDataBase;
import com.example.hasee.myapplication.util.BoWenAdapter;
import com.example.hasee.myapplication.view.YongHuButton;

import java.util.ArrayList;
import java.util.List;

public class FaXianActivity extends Activity {

    private String yonghuming = MainActivity.getYonghuming();
    private EditText editText;
    private YongHuButton yongHuButton;
    private Button faxian_button;
    private WeiBoDataBase weiBoDataBase;

    private ListView listView;
    private List<BoWen> list=new ArrayList<BoWen>();
    private BoWenAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faxian_view);

        editText=(EditText)findViewById(R.id.faxian_edit);
        yongHuButton= (YongHuButton) findViewById(R.id.yonghu_button);
        listView= (ListView) findViewById(R.id.bowen_list_faxian);
        faxian_button=(Button)findViewById(R.id.faxian_button);
        weiBoDataBase=WeiBoDataBase.getInstance(this);

        faxian_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(weiBoDataBase.getYongHu(editText.getText().toString())!=null){
                    yongHuButton.setImage(weiBoDataBase.getYongHu(editText.getText().toString()).getImage());
                    yongHuButton.setMingzitext(weiBoDataBase.getYongHu(editText.getText().toString()).getName());
                    yongHuButton.setYonghumingtext(weiBoDataBase.getYongHu(editText.getText().toString()).getPhone());

                    list=getBoWen();
                    adapter=new BoWenAdapter(FaXianActivity.this,R.layout.bowen_view,list);
                    listView.setAdapter(adapter);
                }else{
                    Toast.makeText(FaXianActivity.this,"找不到此用户",Toast.LENGTH_SHORT).show();
                }
            }
        });

        yongHuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(FaXianActivity.this);

                if(weiBoDataBase.getBPhone(yonghuming).indexOf(editText.getText().toString())<0){
                    dialog.setMessage("关注此人吗？");
                    dialog.setPositiveButton("确定",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            weiBoDataBase.saveGuanzhu(yonghuming,editText.getText().toString());
                        }
                    });
                    dialog.setNegativeButton("取消",new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    dialog.setCancelable(false);
                }else{
                    dialog.setMessage("你已关注此人");
                    dialog.setPositiveButton("确定",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    dialog.setCancelable(false);
                }
                dialog.show();
            }
        });
    }

    public List<BoWen> getBoWen(){
        List<BoWen> list1=new ArrayList<BoWen>();
            for(BoWen boWen:weiBoDataBase.getBoWen(editText.getText().toString())){
                list1.add(0,boWen);
            }
        return list1;
    }

}
