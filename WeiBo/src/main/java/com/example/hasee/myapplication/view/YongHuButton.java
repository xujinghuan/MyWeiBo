package com.example.hasee.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hasee.myapplication.R;

public class YongHuButton extends LinearLayout{
    private ImageView imageView;
    private TextView mingzi_text;
    private TextView yonghuming_text;
    public YongHuButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.yonghu_view,this);

        imageView= (ImageView) findViewById(R.id.yonghu_touxiang_faxian);
        mingzi_text= (TextView) findViewById(R.id.mingzi_text_faxian);
        yonghuming_text= (TextView) findViewById(R.id.yonghuming_text_faxian);
    }
    public void setImage(int image){
        imageView.setImageResource(image);
    }
    public void setMingzitext(String mingzitext){
        mingzi_text.setText(mingzitext);
    }
    public void setYonghumingtext(String yonghumingtext){
        yonghuming_text.setText(yonghumingtext);
    }
}
