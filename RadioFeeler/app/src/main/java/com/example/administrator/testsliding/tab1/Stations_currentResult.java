package com.example.administrator.testsliding.tab1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.administrator.testsliding.Map_BaseMap;
import com.example.administrator.testsliding.R;
import com.example.administrator.testsliding.view.MyTopBar;


/**
 * Created by Administrator on 2015/10/27.
 */
public class Stations_currentResult  extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stations_currentresult);


        MyTopBar topBar= (MyTopBar) findViewById(R.id.topbar_currentResult);
        topBar.setOnTopBarClickListener(new MyTopBar.TopBarClickListener() {
            @Override
            public void leftclick() {
                Stations_currentResult.this.finish();
            }

            @Override
            public void rightclick() {
                Intent intent = new Intent(Stations_currentResult.this, Map_BaseMap.class);
                startActivity(intent);
            }
        });
    }
}
