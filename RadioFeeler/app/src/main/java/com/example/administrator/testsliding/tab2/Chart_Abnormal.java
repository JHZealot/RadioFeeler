package com.example.administrator.testsliding.tab2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.administrator.testsliding.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/26.
 */
public class Chart_Abnormal extends Fragment {

    private List<Map<String, Object>> listItems;
    private ListView recordListView;
    static int[] seq_num = new int[30];
    static int[] freq = new int[30];
    static int[] PowerSpectrum = new int[30];

    @Override
    public void onResume() {
        super.onResume();

        /*屏蔽掉系统的actionbar*/
        //ActionBar actionBar =getSupportActionBar();//在API 11或者更高的API上，使用getActionBar方法
       // actionBar.hide();



    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recordListView = (ListView) getActivity().findViewById(R.id.abnormal_frequency_listview);
        listItems = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            seq_num[i] = freq[i] = PowerSpectrum[i] = i;
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("seq_num", i);
            map.put("freq", i);
            map.put("PowerSpectrum", i);
            listItems.add(map);

        }

        /*新建适配器，适配器加载数据源，同时在这里面让适配器加载自定义的布局xml,自定义的表格的每一行的样式*/
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), listItems,
                R.layout.abnormal_frequency_item, new String[]{"seq_num", "freq", "PowerSpectrum"},
                new int[]{R.id.seq_num, R.id.freq, R.id.PowerSpectrum});

        recordListView.setAdapter(simpleAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.chart_abnormal,container,false);
    }
}
