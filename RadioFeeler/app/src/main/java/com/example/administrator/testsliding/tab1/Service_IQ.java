package com.example.administrator.testsliding.tab1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.testsliding.GlobalConstants.ConstantValues;
import com.example.administrator.testsliding.GlobalConstants.Constants;
import com.example.administrator.testsliding.Mina.Broadcast;
import com.example.administrator.testsliding.R;
import com.example.administrator.testsliding.bean2server.HistoryIQRequest;
import com.example.administrator.testsliding.compute.ComputePara;
import com.example.administrator.testsliding.view.DateTimePickDialogUtil;


/**
 * Created by Administrator on 2015/10/26.
 */
public class Service_IQ extends Activity {
    private EditText startDateTime;
    private EditText endDateTime;
    private EditText et_ID;
    private Button btn_set;
    private ComputePara compute=new ComputePara();

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
        btn_set= (Button) findViewById(R.id.btn_ID_time);
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

        btn_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HistoryIQRequest IQ=new HistoryIQRequest();
                try {
                    IQ.setEqiupmentID(Constants.ID);
                    if (!et_ID.getText().toString().equals("")) {
                        IQ.setIDcard(Integer.parseInt(et_ID.getText().toString()));
                    }
                    if (!startDateTime.getText().toString().equals("")) {
                        byte[] bytes = compute.Time2Bytes(startDateTime.getText().toString());
                        IQ.setStartTime(bytes);
                    }
                    if (!endDateTime.getText().toString().equals("")) {
                        byte[] bytes = compute.Time2Bytes(endDateTime.getText().toString());
                        IQ.setEndTime(bytes);
                    }
                    Broadcast.sendBroadCast(Service_IQ.this,
                            ConstantValues.SERVICE_IQ,"service_IQ",IQ);
                }catch (Exception e){

                }
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
