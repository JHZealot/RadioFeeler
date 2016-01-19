package com.example.administrator.testsliding.tab1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.administrator.testsliding.R;
import com.example.administrator.testsliding.view.DateTimePickDialogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/27.
 */
public class Stations_current extends Fragment {

    private EditText et_ID;
    private EditText et_frequecy;

    private Spinner spinner_location, spinner_IQ;
    private List<String> list1, list2;
    private ArrayAdapter<String> adapter1, adapter2;

    private EditText et_IQblock,et_inputtime;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.stations_current, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        InitSetting();
        initspinnerSetting();
        InitEvent();
    }


    private void InitSetting() {

        et_frequecy = (EditText) getActivity().findViewById(R.id.et_radioPoint);
        et_ID = (EditText) getActivity().findViewById(R.id.et_ID);

        spinner_location = (Spinner) getActivity().findViewById(R.id.spinner_location);
        spinner_IQ = (Spinner) getActivity().findViewById(R.id.spinner_IQ);

        et_IQblock = (EditText) getActivity().findViewById(R.id.et_IQblock);
        et_inputtime= (EditText) getActivity().findViewById(R.id.inputDate_current);

    }

    private void initspinnerSetting() {

        //1,设置数据源
        list2 = new ArrayList<String>();
        list2.add("5/5");
        list2.add("2.5/2.5");
        list2.add("1/1");
        list2.add("0.5/0.5");
        list2.add("0.1/0.1");

        //2.新建数组适配器
        adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list2);

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
        adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list1);

        //adapter设置一个下拉列表样式
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spin加载适配器
        spinner_location.setAdapter(adapter1);

    }

    private void InitEvent() {
        getActivity().findViewById(R.id.btn_querycurrent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Stations_currentResult.class);
                startActivity(intent);
            }
        });
        et_inputtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(getActivity());
                dateTimePicKDialog.dateTimePicKDialog(et_inputtime);
            }
        });
    }
}
