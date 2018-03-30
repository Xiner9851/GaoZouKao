package com.example.administrator.gaozoukao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Main2Activity extends AppCompatActivity {

    private WebView webv;
    private Toolbar tuoba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        tuoba.setTitle("详情");
        tuoba.setNavigationIcon(R.mipmap.ic_launcher_round);
        tuoba.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        String s = (String) intent.getSerializableExtra("xin");
        webv.loadUrl(s);
        webv.setWebViewClient(new WebViewClient());
    }

    private void initView() {
        webv = (WebView) findViewById(R.id.webv);
        tuoba = (Toolbar) findViewById(R.id.tuoba);

    }
}
