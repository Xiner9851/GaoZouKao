package com.example.administrator.gaozoukao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {

    private ImageView dong;
    private Button tiyan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initView();


    }

    private void initView() {
        dong = (ImageView) findViewById(R.id.dong);
        tiyan = (Button) findViewById(R.id.tiyan);

        tiyan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tiyan:

                Intent intent = new Intent(Main3Activity.this, MainActivity.class);

                startActivity(intent);
                break;
        }
    }
}
