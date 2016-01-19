package com.example.administrator.testsliding.tab1;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.administrator.testsliding.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/4.
 */
public class                                                                                                 Interact_work1 extends Fragment implements RadioGroup.OnCheckedChangeListener,
        AdapterView.OnItemSelectedListener{
    //四种参数设置条
    private EditText et_ID, et_testRecv, et_testSend, et_testGate;

    private RadioGroup rg;//检测门限系数的两个按钮

    //检测门限系数两种设置面板
    private LinearLayout reLay01;
    private RelativeLayout reLay02;

    //自适应门限用的下拉条
    private Spinner spin;
    private List<String> list1;
    private ArrayAdapter<String> adapter1;

    //文件上传模式按钮
    private RadioGroup rg_sendMode;
    private  RadioGroup rg_sweep;
    //
    private Spinner  sp_autoSend;
    private List<String> list2;
    private ArrayAdapter<String> adapter2;
    private EditText et_select;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.interact_work1, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initSetting();
        initspinnerSetting();
        InitEvent();

    }

    /**
     * 初始化设置
     */
    private void initSetting() {
        et_ID = (EditText) getActivity().findViewById(R.id.et_work1_ID);
        et_testRecv = (EditText) getActivity().findViewById(R.id.et_testRecv);
        et_testSend = (EditText) getActivity().findViewById(R.id.et_testSend);
        et_testGate = (EditText) getActivity().findViewById(R.id.et_testGate1);
        //绑定两个门限设置面板
        reLay01 = (LinearLayout) getActivity().findViewById(R.id.reLayout_testGate1);
        reLay02 = (RelativeLayout) getActivity().findViewById(R.id.reLayout_testGate2);
        //绑定监听按钮
        rg = (RadioGroup) getActivity().findViewById(R.id.radioGroup1);

        spin = (Spinner) getActivity().findViewById(R.id.spinner);

        rg_sendMode=(RadioGroup)getActivity().findViewById(R.id.rg_sendMode);
        rg_sweep=(RadioGroup)getActivity().findViewById(R.id.rg_sweep);
        sp_autoSend= (Spinner)getActivity().findViewById(R.id.spinner_autoSend);

        et_select=(EditText)getActivity().findViewById(R.id.et_select);

        et_testRecv.setText("7");
        et_testSend.setText("0");

    }

    /**
     * spinner初始化
     */

    private void initspinnerSetting() {

        //1,设置数据源
        list1 = new ArrayList<String>();
        list1.add("3");
        list1.add("10");
        list1.add("20");
        list1.add("25");
        list1.add("30");
        list1.add("40");
        //2.新建数组适配器
        adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list1);

        //adapter设置一个下拉列表样式
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spin加载适配器
        spin.setAdapter(adapter1);

        //1,设置数据源
        list2 = new ArrayList<String>();
        list2.add("10");
        list2.add("20");
        //2.新建数组适配器
        adapter2=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,list2);

        //adapter设置一个下拉列表样式
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spin加载适配器
        sp_autoSend.setAdapter(adapter2);
        sp_autoSend.setSelection(0,true);
    }

    private void InitEvent() {
        rg.setOnCheckedChangeListener(this);
        //spin设置监听器
        spin.setOnItemSelectedListener(this);
        rg_sendMode.setOnCheckedChangeListener(this);
        rg_sweep.setOnCheckedChangeListener(this);
        //spin设置监听器
        sp_autoSend.setOnItemSelectedListener(this);

    }
    private void showDialog_Save1() {

        AlertDialog.Builder bulider = new AlertDialog.Builder(getActivity());
        bulider.setTitle("您是否确认保存以下参数：");
        bulider.setIcon(R.drawable.wo2);
        bulider.setMessage("确认设置扫频范围为70MHz-6GHz");
        bulider.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "保存成功", Toast.LENGTH_SHORT).show();
            }
        });

        bulider.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "保存失败", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = bulider.create();
        dialog.show();
    }

    private void showDialog_Save2() {


        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.mydialogzhidingpinduan, null);

        AlertDialog.Builder bulider = new AlertDialog.Builder(getActivity());
        bulider.setTitle("请输入扫频范围70MHz-6GHz");
        bulider.setIcon(R.drawable.wo2);

        bulider.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "保存成功", Toast.LENGTH_SHORT).show();
            }
        });

        bulider.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "保存失败", Toast.LENGTH_SHORT).show();
            }
        });
        bulider.setView(view);


        AlertDialog dialog = bulider.create();
        dialog.show();
    }


    private void showDialog_Save3() {


        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.mydialogduopinduan, null);

        AlertDialog.Builder bulider = new AlertDialog.Builder(getActivity());
        bulider.setTitle("请输入所选频段70MHz-6GHz");
        bulider.setIcon(R.drawable.wo2);

        bulider.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "保存成功", Toast.LENGTH_SHORT).show();
            }
        });

        bulider.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "保存失败", Toast.LENGTH_SHORT).show();
            }
        });
        bulider.setView(view);


        AlertDialog dialog = bulider.create();
        dialog.show();
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rbtn_guding:
                /**
                 * 固定门限显示
                 */
                reLay01.setVisibility(View.VISIBLE);
                reLay02.setVisibility(View.GONE);
                break;
            case R.id.rbtn_zidingyi:
                /**
                 * 自定义门限显示
                 */
                reLay01.setVisibility(View.GONE);
                reLay02.setVisibility(View.VISIBLE);
                // initspinnerSetting();
                break;
            case R.id.rbtn_sendHand:
                sp_autoSend.setEnabled(false);
                et_select.setEnabled(false);
                break;
            case R.id.rbtn_sendAuto:
                sp_autoSend.setEnabled(true);
                et_select.setEnabled(false);
                break;
            case R.id.rbtn_sendSelect:
                sp_autoSend.setEnabled(false);
                et_select.setEnabled(true);
                et_select.setText("63");
                break;
            case R.id.rbtn_whole:
                showDialog_Save1();
                break;
            case R.id.rbtn_specify:
                showDialog_Save2();
                break;
            case R.id.rbtn_many:
                showDialog_Save3();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




}
