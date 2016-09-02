package com.example.hasee.myapplication.util;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hasee.myapplication.R;
import com.example.hasee.myapplication.model.BoWen;
import com.example.hasee.myapplication.sqlite.WeiBoDataBase;

import java.util.List;

public class BoWenAdapter extends ArrayAdapter<BoWen> {

    private int resourceId;
    private WeiBoDataBase weiBoDataBase;

    public BoWenAdapter(Context context, int resource,List<BoWen> objects) {
        super(context, resource, objects);
        resourceId=resource;
        weiBoDataBase=WeiBoDataBase.getInstance(getContext());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BoWen boWen=getItem(position);
        View view ;
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId, null);
        }else {
            view=convertView;
        }
        ImageView bowen_touxiang= (ImageView) view.findViewById(R.id.bowen_touxiang);
        TextView mingzi_text= (TextView) view.findViewById(R.id.mingzi_text);
        TextView time_text= (TextView) view.findViewById(R.id.time_text);
        TextView neirong= (TextView) view.findViewById(R.id.neirong_text);
        RadioGroup xiaoxi_radio= (RadioGroup) view.findViewById(R.id.xiaoxi_radio);

        if(!TextUtils.isEmpty(boWen.getPhone())){
        bowen_touxiang.setImageResource(weiBoDataBase.getYongHu(boWen.getPhone()).getImage());
        mingzi_text.setText(boWen.getName());
        time_text.setText(boWen.getTime());
        neirong.setText(boWen.getText());
        } else{
            bowen_touxiang.setVisibility(View.GONE);
            mingzi_text.setVisibility(View.GONE);
            time_text.setVisibility(View.GONE);
            neirong.setVisibility(View.GONE);
            xiaoxi_radio.setVisibility(View.GONE);
        }
        return view;
    }
}
