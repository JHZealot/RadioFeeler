package com.example.administrator.testsliding.tab1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.administrator.testsliding.R;
import com.example.administrator.testsliding.view.DateTimePickDialogUtil;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/4.
 */
public class Interact_work2 extends Fragment {
    private EditText et_ID;
    private SeekBar seekBar01;
    private  TextView textView;

    private CheckBox cb_frequency01;
    private CheckBox cb_frequency02;
    private CheckBox cb_frequency03;

    private EditText et_frequency01;
    private EditText et_frequency02;
    private EditText et_frequency03;

    private Spinner spinner_IQ;
    private List<String> list;
    private ArrayAdapter<String> adapter;

    private EditText et_IQblock;
    private EditText inputDate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.interact_work2,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        InitSetting();
        initspinnerSetting();
        InitEvent();
    }

    private void InitSetting(){
        et_ID= (EditText) getActivity().findViewById(R.id.et_work2_ID);
        seekBar01= (SeekBar) getActivity().findViewById(R.id.seekBar_recv_interact);
        textView= (TextView) getActivity().findViewById(R.id.tv_recvGain_interact);

        et_frequency01=(EditText)getActivity().findViewById(R.id.et_frequency01);
        et_frequency02=(EditText)getActivity().findViewById(R.id.et_frequency02);
        et_frequency03=(EditText)getActivity().findViewById(R.id.et_frequency03);

        spinner_IQ=(Spinner)getActivity().findViewById(R.id.spinner_IQ);
        inputDate= (EditText) getActivity().findViewById(R.id.inputDate);

        et_IQblock=(EditText)getActivity().findViewById(R.id.et_IQblock);



    }
    /**
     * spinner初始化
     *
     */

    private  void initspinnerSetting(){

        //1,设置数据源
        list = new ArrayList<String>();
        list.add("5/5");
        list.add("2.5/2.5");
        list.add("1/1");
        list.add("0.5/0.5");
        list.add("0.1/0.1");

        //2.新建数组适配器
        adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,list);

        //adapter设置一个下拉列表样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spin加载适配器
        spinner_IQ.setAdapter(adapter);
        spinner_IQ.setSelection(0,true);
    }
    private void InitEvent(){
        seekBar01.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                       textView.setText("当前值："+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        inputDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(getActivity());
                dateTimePicKDialog.dateTimePicKDialog(inputDate);

            }
        });
    }
}
