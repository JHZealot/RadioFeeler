package com.example.administrator.testsliding.tab1;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


import com.example.administrator.testsliding.R;
import com.example.administrator.testsliding.view.DateTimePickDialogUtil;
import com.example.administrator.testsliding.view.MyTopBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/26.
 */
public class Service_abnormal extends Activity {

    private EditText et_locationpoint;

    private Spinner spinner_location,spinner_IQ;
    private List<String> list1,list2;
    private ArrayAdapter<String> adapter1,adapter2;

    private EditText et_IQblock,et_inputtime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.service_abnormal);

        InitSetting();
        initspinnerSetting();
        MyTopBar topbar= (MyTopBar) findViewById(R.id.topBar);
        topbar.setOnTopBarClickListener(new MyTopBar.TopBarClickListener() {
            @Override
            public void leftclick() {
                Service_abnormal.this.finish();
            }

            @Override
            public void rightclick() {
                Intent intent = new Intent(Service_abnormal.this, Service_locationResult.class);
                startActivity(intent);
            }
        });

        et_inputtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(Service_abnormal.this);
                dateTimePicKDialog.dateTimePicKDialog(et_inputtime);
            }
        });
    }

    private void InitSetting(){

        et_locationpoint=(EditText)findViewById(R.id.et_radioPoint);

        spinner_location=(Spinner)findViewById(R.id.spinner_location);
        spinner_IQ=(Spinner)findViewById(R.id.spinner_IQ);

        et_IQblock=(EditText)findViewById(R.id.et_IQblock);

        et_inputtime= (EditText) findViewById(R.id.inputDate_abnormal);
    }

    private  void initspinnerSetting() {

        //1,设置数据源
        list2 = new ArrayList<String>();
        list2.add("5/5");
        list2.add("2.5/2.5");
        list2.add("1/1");
        list2.add("0.5/0.5");
        list2.add("0.1/0.1");

        //2.新建数组适配器
        adapter2 = new ArrayAdapter<String>(Service_abnormal.this, android.R.layout.simple_spinner_item, list2);

        //adapter设置一个下拉列表样式
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spin加载适配器
        spinner_IQ.setAdapter(adapter2);
        spinner_IQ.setSelection(0, true);

        //1,设置数据源
        list1 = new ArrayList<String>();
        list1.add("POA");
        list1.add("TDOA/POA");


        //2.新建数组适配器
        adapter1 = new ArrayAdapter<String>(Service_abnormal.this, android.R.layout.simple_spinner_item, list1);

        //adapter设置一个下拉列表样式
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spin加载适配器
        spinner_location.setAdapter(adapter1);

    }

}
