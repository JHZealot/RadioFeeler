package com.example.administrator.testsliding.SlideMenu;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.testsliding.Bean.Query;
import com.example.administrator.testsliding.Bean.StationState;
import com.example.administrator.testsliding.Broadcast.Broadcast;
import com.example.administrator.testsliding.GlobalConstants.ConstantValues;
import com.example.administrator.testsliding.R;

/**
 * Created by H on 2015/10/24.
 */
public class FinalStationState extends Activity {

    private Button mGetPCBinfo;

    private BroadcastReceiver StationStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ConstantValues.StationStateQuery)) {

                StationState data = intent.getParcelableExtra("data");
                if (data == null) {
                    return;
                }

                String s1net;

                String s2model=null;

                int onNet=data.getOnNet();
                int model=data.getModel();
                int eastORwest=data.getEastORwest();
                int northORsouth=data.getNorthORsouth();
                double longtitude=data.getLongtitude();
                double latitude=data.getLatitude();
                int isAboveHrizon=data.getIsAboveHrizon();
                int atitude=data.getAtitude();

                if(onNet==0x0f){
                    s1net="在网用户";
                }else{
                    s1net="不在网";
                }

                switch(model){
                    case 0x00:
                        s2model="专业用户终端";
                        break;
                    case 0x01:
                        s2model="普通用户终端";
                        break;
                    case 0x02:
                        s2model="专业查询终端";
                        break;
                    case 0x03:
                        s2model="普通查询终端";
                        break;
                }

                Toast toast=Toast.makeText(FinalStationState.this, "该终端是："
                        +s1net+"的"+s2model, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP , 0, 400);
                toast.show();


            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.station);
        IntentFilter filter=new IntentFilter();

        filter.addAction(ConstantValues.StationStateQuery);

        filter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(StationStateReceiver, filter);
        mGetPCBinfo = (Button) findViewById(R.id.bt_getpcbinfo);
        findViewById(R.id.title_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FinalStationState.this.finish();
            }
        });


        mGetPCBinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query query = new Query();
                query.setequipmentID(0);
                query.setFuncID((byte) 0x1C);

                if (query != null) {
                    Broadcast.sendBroadCast(FinalStationState.this,
                            ConstantValues.StationStateQuery, "StationStateQuery", query);
                }


            }
        });
//        final LinearLayout mLinearLayout= (LinearLayout) findViewById(R.id.pcbinfo);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(StationStateReceiver);
    }
}
