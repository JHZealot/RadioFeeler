package com.example.administrator.testsliding.tab1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.administrator.testsliding.GlobalConstants.ConstantValues;
import com.example.administrator.testsliding.GlobalConstants.Constants;
import com.example.administrator.testsliding.Mina.Broadcast;
import com.example.administrator.testsliding.R;
import com.example.administrator.testsliding.bean2server.MapRadio;
import com.example.administrator.testsliding.compute.ComputePara;
import com.example.administrator.testsliding.view.DateTimePickDialogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/5.
 */
public class Map_Heat_Setting extends Fragment implements RadioGroup.OnCheckedChangeListener{

    private EditText et_bandwidth,et_centerfreq,et_radius,et_dieta,et_fresh;
    private Button btn_setting;
    private Spinner FreqSpinner;
    private ArrayAdapter<String> Spinner_adapter1;

    private List<String> Freq_List = new ArrayList<String>();
    private  RadioGroup select_mode;
    private EditText iuputtime1,inputtime2;
    ///组帧参数
    private int centralFreq=98,band=20;//中心频率和带宽(初始化界面同步)
    private boolean Ishand=false,Ischoose=false;
    private ComputePara computePara=new ComputePara();


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.map_base_setting, container, false);
    }


    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        InitSetting();
        InitFreqSpinner();
        InitEvent();
    }

    private void InitSetting(){
        FreqSpinner = (Spinner)getActivity().findViewById(R.id.Spinner_base_radio);
        et_bandwidth= (EditText)getActivity(). findViewById(R.id.bandwidth_radio);
        et_centerfreq= (EditText)getActivity(). findViewById(R.id.edit_center_radio);
        et_dieta = (EditText)getActivity(). findViewById(R.id.et_dieta_radio);
       et_radius=(EditText)getActivity(). findViewById(R.id.et_radius_radio);
        et_fresh=(EditText)getActivity(). findViewById(R.id.et_freshTime_radio);

        iuputtime1= (EditText)getActivity(). findViewById(R.id.inputTime1);
        inputtime2= (EditText)getActivity(). findViewById(R.id.inputTime2);
        select_mode = (RadioGroup)getActivity().findViewById(R.id.select_model_radio);
        btn_setting= (Button) getActivity().findViewById(R.id.btn_mapbase_setting);
    }


    private void InitFreqSpinner(){
        Freq_List.add("FM调频广播频段（88-108MHz）");
        Freq_List.add("GSM下行频段I（935-960MHz）");
        Freq_List.add("GSM下行频段II（1805-1880MHz）");
        Freq_List.add("IS95CDMA下行频段（870-880MHz）");
        Freq_List.add("TD 3G频段（1880-1900MHz）");
        Freq_List.add("TD LTE频段I（2320-2370MHz）");
        Freq_List.add("TD LTE频段II（2575-2635MHz）");
        Freq_List.add("WCDMA下行频段（2130-2145MHz）");
        Freq_List.add("联通TD LTE频段I（2300-2320MHz）");
        Freq_List.add("联通TD LTE频段II（2555-2575MHz）");
        Freq_List.add("电信TD LTE频段I（2370-2390MHz）");
        Freq_List.add("电信TD LTE频段II（2635-26550MHz）");
        Freq_List.add("cdma2000 下行频段（2110-2125MHz）");
        Freq_List.add("LTE FDD频段I（1850-1880MHz）");
        Freq_List.add("LTE FDD频段II（2145-2170MHz）");
        Freq_List.add("ISM 433M频段（433.05-434.790MHz）");
        Freq_List.add("ISM 工业频段（902-9280MHz）");
        Freq_List.add("ISM 科学研究频段（2420-2483.50MHz）");
        Freq_List.add("ISM 医疗频段（5725-5850MHz）");
        Spinner_adapter1 = new ArrayAdapter<String>(getActivity(), R.layout.simple_spinner_item_custom, Freq_List);
        Spinner_adapter1.setDropDownViewResource(R.layout.simple_spinner_item_custom);
        FreqSpinner.setAdapter(Spinner_adapter1);
    }


    private void InitEvent(){
        select_mode.setOnCheckedChangeListener(this);
        //////////////////////
        FreqSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                           case 0:
                               centralFreq=98;
                               band=20;
                               break;
                           case 1:
                               centralFreq= 947;//947.5
                               band=25;
                               break;
                           case 2:
                               centralFreq=1842;//1842.5
                               band=75;
                               break;
                           case 3:
                               centralFreq=875;
                               band=19;
                               break;
                           case 4:
                               centralFreq=1890;
                               band=20;
                               break;
                           case 5:
                               centralFreq=2345;
                               band=50;
                               break;
                           case 6:
                               centralFreq=2605;
                               band=60;
                               break;
                           case 7:
                               centralFreq=2137;//2137.5
                               band=15;
                               break;
                           case 8:
                               centralFreq=2310;
                               band=20;
                               break;
                           case 9:
                               centralFreq=2565;
                               band=20;
                               break;
                           case 10:
                               centralFreq=2117;//2117.5
                               band=15;
                               break;
                           case 11:
                               centralFreq=2380;
                               band=20;
                               break;
                           case 12:
                               centralFreq=2645;
                               band=20;
                               break;
                           case 13:
                               centralFreq=1865;
                               band=30;
                               break;
                           case 14:
                               centralFreq=2157;//2157.5
                               band=25;
                               break;
                           case 15:
                               centralFreq=434;//433.92
                               band=2;//1.74
                               break;
                           case 16:
                               centralFreq=915;
                               band=26;
                               break;
                           case 17:
                               centralFreq=2452;//2451.75
                               band=63;//63.5
                               break;
                           case 18:
                               centralFreq=5785;//5787.5
                               band=125;
                               break;
                       }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        iuputtime1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(getActivity());
                dateTimePicKDialog.dateTimePicKDialog(iuputtime1);

            }
        });
        inputtime2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(getActivity());
                dateTimePicKDialog.dateTimePicKDialog(inputtime2);
            }
        });
        /////////////////////////
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapRadio map=new MapRadio();
                map.setEquipmentID(Constants.ID);
                try {
                    if (Ishand && (!Ischoose)) {
                        //手动
                        if (!et_centerfreq.getText().toString().equals("")) {
                            map.setCentralFreq(Integer.parseInt(et_centerfreq.getText().toString()));
                        }
                        if (!et_bandwidth.getText().toString().equals("")) {
                            map.setBand(Integer.parseInt(et_bandwidth.getText().toString()));
                        }

                    } else if ((!Ishand) && Ischoose) {
                        map.setCentralFreq(centralFreq);
                        map.setBand(band);
                    } else {
                        Toast.makeText(getContext(), "请正确输入！", Toast.LENGTH_SHORT).show();
                    }
                    if (!et_radius.getText().toString().equals("")) {
                        map.setRadius(Integer.parseInt(et_radius.getText().toString()));
                    }
                    if (!et_dieta.getText().toString().equals("")) {
                        map.setDieta(Float.parseFloat(et_dieta.getText().toString()));
                    }
                    if (!et_fresh.getText().toString().equals("")) {
                        map.setFreshtime(Integer.parseInt(et_fresh.getText().toString()));
                    }
                    if (!iuputtime1.getText().toString().equals("")) {
                        byte[] bytes = computePara.Time2Bytes(iuputtime1.getText().toString());
                        map.setStartTime(bytes);
                    }
                    if (!inputtime2.getText().toString().equals("")) {
                        byte[] bytes = computePara.Time2Bytes(inputtime2.getText().toString());
                        map.setEndTime(bytes);
                    }

                    Broadcast.sendBroadCast(getActivity(),
                            ConstantValues.MAPRADIO, "map_radio",map);

                    Intent intent = new Intent();
                    intent.setClass(getActivity(), Map_Heat_Result.class);
                    startActivity(intent);

                }catch (Exception e){
                    Toast.makeText(getContext(), "请正确输入！", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch(checkedId){
            case R.id.munual_select_radio:
                FreqSpinner.setEnabled(false);
//                et_bandwidth.setEnabled(true);
//                et_centerfreq.setEnabled(true);
                et_bandwidth.setFocusable(true);
                et_bandwidth.setFocusableInTouchMode(true);
                et_bandwidth.requestFocus();
                et_centerfreq.setFocusable(true);
                et_centerfreq.setFocusableInTouchMode(true);
                et_centerfreq.requestFocus();
                Ishand=true;
                Ischoose=false;
                break;
            case R.id.straight_select_radio:
//                et_bandwidth.setEnabled(false);
//                et_centerfreq.setEnabled(false);
                et_bandwidth.setText("");
                et_centerfreq.setText("");
                et_bandwidth.setFocusableInTouchMode(false);
                et_centerfreq.setFocusableInTouchMode(false);
                et_bandwidth.setFocusable(false);
                et_centerfreq.setFocusable(false);
                FreqSpinner.setEnabled(true);


                Ishand=false;
                Ischoose=true;
                break;
            default:
                break;

        }
    }

}
