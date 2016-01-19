package com.example.administrator.testsliding.tab1;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.administrator.testsliding.Map_BaseMap;
import com.example.administrator.testsliding.R;
import com.example.administrator.testsliding.view.MyTopBar;

import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2015/10/27.
 */
public class Service_locationResult extends Activity {
    private List<Map<String, Object>> listItems;
    private ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_locationresult);

        MyTopBar topBar = (MyTopBar)findViewById(R.id.topbar_locationResult);  //id是在acitivity_main中定义的
        topBar.setOnTopBarClickListener(new MyTopBar.TopBarClickListener() {
            @Override
            public void leftclick() {
                Service_locationResult.this.finish();
            }

            @Override
            public void rightclick() {
                Intent intent = new Intent(Service_locationResult.this, Map_BaseMap.class);
                startActivity(intent);
            }
        });


//        mListView = (ListView) findViewById(R.id.service_location_listview);
//        listItems = new ArrayList<>();
//
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("name", "异常频点中心频率（MHz）");
//            map.put("location", "");
//            listItems.add (map);
//        map.clear();
//        map.put("name", "带宽（MHz）");
//        map.put("location", "");
//        listItems.add(map);
//        map.clear();
//        map.put("name", "带宽（MHz）");
//        map.put("location", "");
//        listItems.add(map);
//        map.clear();
//
//
//
//
//
//
//        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
//                R.layout.service_location_item, new String[]{"name", "location"},
//                new int[]{R.id.name, R.id.location});
//
//        mListView.setAdapter(simpleAdapter);
    }
}
