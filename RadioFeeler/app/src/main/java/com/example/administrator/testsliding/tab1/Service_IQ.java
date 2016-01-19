package com.example.administrator.testsliding.tab1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.example.administrator.testsliding.R;
import com.example.administrator.testsliding.view.DateTimePickDialogUtil;


/**
 * Created by Administrator on 2015/10/26.
 */
public class Service_IQ extends Activity {
    private EditText startDateTime;
    private EditText endDateTime;
    private EditText et_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_iq);
//        MyTopBar topBar= (MyTopBar) findViewById(R.id.topbar_servicestation);
//        topBar.setOnTopBarClickListener(new MyTopBar.TopBarClickListener() {
//            @Override
//            public void leftclick() {
//                Service_IQ.this.finish();
//            }
//
//            @Override
//            public void rightclick() {
//
//            }
//        });

        initSetting();
        InitEvent();
    }
    private void initSetting() {

        et_ID = (EditText)findViewById(R.id.et_ID);
        // 两个输入框
        startDateTime = (EditText)findViewById(R.id.inputDate);
        endDateTime = (EditText)findViewById(R.id.inputDate2);
    }

    private void InitEvent() {
        startDateTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil( Service_IQ.this);
                dateTimePicKDialog.dateTimePicKDialog(startDateTime);

            }
        });

        endDateTime.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        Service_IQ.this);
                dateTimePicKDialog.dateTimePicKDialog(endDateTime);
            }
        });
    }

    //ActionBar菜单栏 模块
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_iq, menu);
        return true;
    }
}
