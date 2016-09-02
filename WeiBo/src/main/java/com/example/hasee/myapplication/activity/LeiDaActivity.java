package com.example.hasee.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.example.hasee.myapplication.R;

public class LeiDaActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.leida_view);
    }
}
