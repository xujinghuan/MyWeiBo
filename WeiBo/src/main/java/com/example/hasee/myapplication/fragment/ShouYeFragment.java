package com.example.hasee.myapplication.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.hasee.myapplication.R;
import com.example.hasee.myapplication.activity.MainActivity;
import com.example.hasee.myapplication.model.BoWen;
import com.example.hasee.myapplication.sqlite.WeiBoDataBase;
import com.example.hasee.myapplication.util.BoWenAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShouYeFragment extends Fragment {

    private ListView listView;
    private List<BoWen> list=new ArrayList<BoWen>();
    private BoWenAdapter adapter;
    private WeiBoDataBase weiBoDataBase=WeiBoDataBase.getInstance(getContext());

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(R.layout.shouye_view,container,false);

        listView= (ListView) view.findViewById(R.id.bowen_list);

        return view;
    }
    public List<BoWen> getBoWen(){
        List<BoWen> list1=new ArrayList<BoWen>();
        List<String> bPhone=weiBoDataBase.getBPhone(MainActivity.getYonghuming());
        for (String bphone: bPhone){
            for(BoWen boWen:weiBoDataBase.getBoWen(bphone)){
                list1.add(0,boWen);
            }
        }
        return list1;
    }
    public class ShouYeBroadCastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            list.clear();
            list=getBoWen();
            adapter=new BoWenAdapter(getContext(),R.layout.bowen_view,list);
            listView.setAdapter(adapter);
        }
    }
}
