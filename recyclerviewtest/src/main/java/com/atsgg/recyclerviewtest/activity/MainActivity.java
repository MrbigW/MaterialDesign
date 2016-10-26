package com.atsgg.recyclerviewtest.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.atsgg.recyclerviewtest.R;

public class




MainActivity extends Activity implements View.OnClickListener{

    private Button btn_main_linear;
    private Button btn_main_grid;
    private Button btn_main_staggerd;
    private Button btn_main_random;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        btn_main_grid = (Button) findViewById(R.id.btn_main_grid);
        btn_main_linear = (Button) findViewById(R.id.btn_main_linear);
        btn_main_staggerd = (Button) findViewById(R.id.btn_main_staggered);
        btn_main_random = (Button) findViewById(R.id.btn_main_random);

        btn_main_staggerd.setOnClickListener(this);
        btn_main_linear.setOnClickListener(this);
        btn_main_grid.setOnClickListener(this);
        btn_main_random.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_main_linear:
                Intent linearIntent = new Intent(MainActivity.this,LinearLayoutActivity.class);
                startActivity(linearIntent);
                break;
            case R.id.btn_main_grid:
                Intent gridIntent = new Intent(MainActivity.this,GridLayoutActivity.class);
                startActivity(gridIntent);
                break;
            case R.id.btn_main_staggered:
                Intent staggerdIntent = new Intent(MainActivity.this,StaggeredGridLayoutActivity.class);
                startActivity(staggerdIntent);
                break;
            case R.id.btn_main_random:
                Intent randomIntent = new Intent(MainActivity.this,RandomStaggeredLayoutActivity.class);
                startActivity(randomIntent);
                break;
        }

    }
}
















