package com.example.administrator.testsliding;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.administrator.testsliding.view.DateTimePickDialogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/6.
 */
public class Map_Geometry_Setting extends Activity implements AdapterView.OnItemSelectedListener {
    private Button btn_geometry_setting;
    private Spinner mySpinner;
    private ArrayAdapter<String> Spinner_adapter;
    private List<String> FreqSelectList = new ArrayList<String>();

    private EditText iuputtime1,inputtime2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_geometry_setting);
        mySpinner = (Spinner)findViewById(R.id.Spinner);
        iuputtime1= (EditText) findViewById(R.id.inputTime1);
        inputtime2= (EditText) findViewById(R.id.inputTime2);

        InitSpinnerSetting();
        mySpinner.setOnItemSelectedListener(Map_Geometry_Setting.this);

        InitEvent();
    }
    private void InitSpinnerSetting(){
        FreqSelectList.add("FM调频广播频段（88-108MHz）");
        FreqSelectList.add("GSM下行频段I（35-960MHz）");
        FreqSelectList.add("GSM下行频段II（1804-1880MHz）");
        FreqSelectList.add("IS95CDMA下行频段（2555-25750MHz）");
        FreqSelectList.add("TD 3G频段（1880-1900MHz）");
        FreqSelectList.add("TD LTE频段I（2320-2370MHz）");
        FreqSelectList.add("TD LTE频段II（2575-2635MHz）");
        FreqSelectList.add("WCDMA下行频段（2130-2145MHz）");
        FreqSelectList.add("联通TD LTE频段I（2300-2320MHz）");
        FreqSelectList.add("联通TD LTE频段II（2555-2575MHz）");
        FreqSelectList.add("电信TD LTE频段I（2370-2390MHz）");
        FreqSelectList.add("电信TD LTE频段II（2635-26550MHz）");
        FreqSelectList.add("cdma2000 下行频段（2110-2125MHz）");
        FreqSelectList.add("LTE FDD频段I（1850-1880MHz）");
        FreqSelectList.add("LTE FDD频段II（2145-2170MHz）");
        FreqSelectList.add("ISM 433M频段（433.05-434.790MHz）");
        FreqSelectList.add("ISM 工业频段（902-9280MHz）");
        FreqSelectList.add("ISM 科学研究频段（2420-2483.50MHz）");
        FreqSelectList.add("ISM 医疗频段（5725-5850MHz）");
        //第二步：为下拉列表定义一个适配器，这里就用到里前面定义的list。
        Spinner_adapter = new ArrayAdapter<String>(this,R.layout.simple_spinner_item_custom, FreqSelectList);
        //第三步：为适配器设置下拉列表下拉时的菜单样式。
        Spinner_adapter.setDropDownViewResource(R.layout.simple_spinner_item_custom);
        //第四步：将适配器添加到下拉列表上
        mySpinner.setAdapter(Spinner_adapter);
        //第五步：为下拉列表设置各种事件的响应，这个事响应菜单被选中

    }

    private void InitEvent(){
        iuputtime1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(Map_Geometry_Setting.this);
                dateTimePicKDialog.dateTimePicKDialog(iuputtime1);

            }
        });

        inputtime2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(Map_Geometry_Setting.this);
                dateTimePicKDialog.dateTimePicKDialog(inputtime2);
            }
        });

        findViewById(R.id.btn_mapbase_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map_Geometry_Setting.this.finish();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

