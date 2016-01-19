package com.example.administrator.testsliding.tab1;

import android.app.Activity;
import android.os.Bundle;

import com.example.administrator.testsliding.R;
import com.example.administrator.testsliding.view.MyTopBar;


/**
 * Created by Administrator on 2015/10/28.
 */
public class Service_radioResult extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_radioresult);

        MyTopBar topBar= (MyTopBar) findViewById(R.id.topbar_radioResult);
        topBar.setOnTopBarClickListener(new MyTopBar.TopBarClickListener() {
            @Override
            public void leftclick() {
               Service_radioResult.this.finish();
            }

            @Override
            public void rightclick() {

            }
        });
    }
}
