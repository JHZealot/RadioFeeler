package com.example.administrator.testsliding.tab1;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.testsliding.GlobalConstants.ConstantValues;
import com.example.administrator.testsliding.R;
import com.example.administrator.testsliding.bean2server.List_StationAll;
import com.example.administrator.testsliding.map.Stations_registerResult_map;
import com.example.administrator.testsliding.view.CHScrollView_StationRegisterResult;
import com.example.administrator.testsliding.view.MyTopBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2015/10/27.
 */
public class Stations_registerResult extends Activity {
    private ListView mListView;
    private CHScrollView_StationRegisterResult headerScroll;
    ArrayList<Map<String, String>> datas;
    ArrayList<List_StationAll> mlist = new ArrayList<>();
    public final static String Stations_registerResult_KEY = "com.example.administrator.testsliding.terminalall_map";
    public HorizontalScrollView mTouchView;
    //装入所有的HScrollView
    protected List<CHScrollView_StationRegisterResult> mHScrollViews = new ArrayList<CHScrollView_StationRegisterResult>();
    // 接受消息广播
    private BroadcastReceiver contentRecevier = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ConstantValues.RSTATION_REGISTER)) {
                mlist = intent.getParcelableArrayListExtra("station_register");
                datas = ListArray2ListItems(mlist);
                if (datas != null) {

                    SimpleAdapter adapter = new ScrollAdapter(Stations_registerResult.this, datas,
                            R.layout.item_stationregister
                            , new String[]{"title", "data_1", "data_2", "data_3", "data_4", "data_5", "data_6",
                            "data_7", "data_8", "data_9", "data_10", "data_11"}
                            , new int[]{R.id.item_title
                            , R.id.item_data1
                            , R.id.item_data2
                            , R.id.item_data3
                            , R.id.item_data4
                            , R.id.item_data5
                            , R.id.item_data6
                            , R.id.item_data7
                            , R.id.item_data8
                            , R.id.item_data9
                            , R.id.item_data10
                            , R.id.item_data11});
                    mListView.setAdapter(adapter);
                }

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staions_registerresult);

        IntentFilter filter = new IntentFilter();
        filter.addAction(ConstantValues.RSTATION_REGISTER);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(contentRecevier, filter);

        mListView = (ListView) findViewById(R.id.scroll_list);
        headerScroll = (CHScrollView_StationRegisterResult) findViewById(R.id.item_scroll_title);
        datas = new ArrayList<Map<String, String>>();

        MyTopBar topBar = (MyTopBar) findViewById(R.id.topbar_registerResult);
        topBar.setOnTopBarClickListener(new MyTopBar.TopBarClickListener() {
            @Override
            public void leftclick() {
                Stations_registerResult.this.finish();
            }

            @Override
            public void rightclick() {
                Intent intent = new Intent(Stations_registerResult.this, Stations_registerResult_map.class);
                Bundle mBundle = new Bundle();
                if (mlist != null) {
                    mBundle.putParcelableArrayList(Stations_registerResult_KEY, mlist);
                }
                intent.putExtras(mBundle);
                startActivity(intent);

            }
        });


    }

    private ArrayList<Map<String, String>> ListArray2ListItems(ArrayList<List_StationAll> mlist) {
        ArrayList<Map<String, String>> datas = new ArrayList<Map<String, String>>();
        Map<String, String> data = null;
//        CHScrollView_TerminalAll headerScroll = (CHScrollView_TerminalAll) findViewById(R.id.item_scroll_title);
        //添加头滑动事件
        mHScrollViews.add(headerScroll);
        for (List_StationAll mlistAll : mlist) {
            data = new HashMap<String, String>();
            data.put("title", String.valueOf(mlistAll.getNum()));
            data.put("data_" + 1, mlistAll.getASICII());
            data.put("data_" + 2, mlistAll.getIDcard());
            //位置
            String str1 = mlistAll.getLongtitudeStyle() + String.valueOf(mlistAll.getLongitude());
            String str2 = mlistAll.getLatitudeStyle() + String.valueOf(mlistAll.getLatitude());
            String str3 = String.valueOf(mlistAll.getHeight());
            data.put("data_" + 3, str1 + "," + str2 + "," + str3);

            data.put("data_" + 4, mlistAll.getSection());
            data.put("data_" + 5, String.valueOf(mlistAll.getMaxPower()));
            data.put("data_" + 6, String.valueOf(mlistAll.getaBand()));
            data.put("data_" + 7, mlistAll.getModem());
            data.put("data_" + 8, String.valueOf(mlistAll.getModemPara()));
            data.put("data_" + 9, mlistAll.getWork());
            data.put("data_" + 10, String.valueOf(mlistAll.getRuleRadius()));
            data.put("data_" + 11, String.valueOf(mlistAll.getLiveness()));
            datas.add(data);
        }

        return datas;
    }

    public void addHViews(final CHScrollView_StationRegisterResult hScrollView) {
        if (!mHScrollViews.isEmpty()) {
            int size = mHScrollViews.size();
            CHScrollView_StationRegisterResult scrollView = mHScrollViews.get(size - 1);
            final int scrollX = scrollView.getScrollX();
            //第一次满屏后，向下滑动，有一条数据在开始时未加入
            if (scrollX != 0) {
                mListView.post(new Runnable() {
                    @Override
                    public void run() {
                        //当listView刷新完成之后，把该条移动到最终位置
                        hScrollView.scrollTo(scrollX, 0);
                    }
                });
            }
        }
        mHScrollViews.add(hScrollView);
    }

    public void onScrollChanged(int l, int t, int oldl, int oldt) {
        for (CHScrollView_StationRegisterResult scrollView : mHScrollViews) {
            //防止重复滑动
            if (mTouchView != scrollView)
                scrollView.smoothScrollTo(l, t);
        }
    }

    class ScrollAdapter extends SimpleAdapter {

        private List<? extends Map<String, ?>> datas;
        private int res;
        private String[] from;
        private int[] to;
        private Context context;

        public ScrollAdapter(Context context,
                             List<? extends Map<String, ?>> data, int resource,
                             String[] from, int[] to) {
            super(context, data, resource, from, to);
            this.context = context;
            this.datas = data;
            this.res = resource;
            this.from = from;
            this.to = to;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                v = LayoutInflater.from(context).inflate(res, null);
                //第一次初始化的时候装进来
                addHViews((CHScrollView_StationRegisterResult) v.findViewById(R.id.item_scroll));
                View[] views = new View[to.length];
                for (int i = 0; i < to.length; i++) {
                    View tv = v.findViewById(to[i]);
                    ;
                    tv.setOnClickListener(clickListener);
                    views[i] = tv;
                }
                v.setTag(views);
            }
            View[] holders = (View[]) v.getTag();
            int len = holders.length;
            for (int i = 0; i < len; i++) {
                ((TextView) holders[i]).setText(this.datas.get(position).get(from[i]).toString());
            }
            return v;
        }
    }

    //测试点击的事件
    protected View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(Stations_registerResult.this, ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onDestroy() {
        unregisterReceiver(contentRecevier);
        contentRecevier = null;
        super.onDestroy();
    }
}
