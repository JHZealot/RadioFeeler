package com.example.administrator.testsliding.tab1;

import android.app.Activity;
import android.os.Bundle;

import com.example.administrator.testsliding.R;
import com.example.administrator.testsliding.view.MyTopBar;


/**
 * Created by Administrator on 2015/10/27.
 */
public class Stations_registerResult extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staions_registerresult);


        MyTopBar topBar= (MyTopBar) findViewById(R.id.topbar_registerResult);
        topBar.setOnTopBarClickListener(new MyTopBar.TopBarClickListener() {
            @Override
            public void leftclick() {
                Stations_registerResult.this.finish();
            }

            @Override
            public void rightclick() {

            }
        });
    }
}
