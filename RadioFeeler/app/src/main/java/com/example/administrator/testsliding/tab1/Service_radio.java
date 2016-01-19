package com.example.administrator.testsliding.tab1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.administrator.testsliding.Encode.Send_ServiceRadio;
import com.example.administrator.testsliding.R;
import com.example.administrator.testsliding.packet.RequestToServicePacket;
import com.example.administrator.testsliding.view.MyTopBar;


/**
 * Created by Administrator on 2015/10/26.
 */
public class Service_radio extends Activity {
    private EditText et_start,et_end;
    private MyTopBar topBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_radio);
        Init();
        InitEvent();

    }


    private void Init(){
       et_start= (EditText) findViewById(R.id.et_radioStart);
        et_end= (EditText) findViewById(R.id.et_radioEnd);
        topBar= (MyTopBar) findViewById(R.id.topbar_radio);
    }

    private void InitEvent(){
        topBar.setOnTopBarClickListener(new MyTopBar.TopBarClickListener() {
            @Override
            public void leftclick() {
                Service_radio.this.finish();
            }

            @Override
            public void rightclick() {
                Intent intent=new Intent(Service_radio.this,Service_radioResult.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_queryradio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //获取值并下发
                GetValue();
                Intent intent=new Intent(Service_radio.this,Service_radioResult.class);
                startActivity(intent);
            }
        });
    }

    private void GetValue(){
        Send_ServiceRadio radio=new Send_ServiceRadio();
        RequestToServicePacket packet=new RequestToServicePacket();
        radio.equipmentID=0;
        radio.startFrequency=Integer.valueOf(et_start.getText().toString());
        radio.endFrequency=Integer.valueOf(et_end.getText().toString());
        packet.RequestWielessPlan(radio);
    }
}
