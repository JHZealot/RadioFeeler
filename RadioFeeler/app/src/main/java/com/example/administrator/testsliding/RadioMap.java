package com.example.administrator.testsliding;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

/**
 * 路径图
 * 
 */
public class RadioMap extends Activity {

    // 地图相关
    MapView mMapView;
    BaiduMap mBaiduMap;

    ImageButton  radio_to_heatmap ;
    ImageButton  radio_to_routemap;
    private static final LatLng GEO_WUHAN = new LatLng(30.515, 114.420);
    BitmapDescriptor stationMarker = null;
    private Marker mMarkerA;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_radiomap);
        // 初始化地图
        mMapView = (MapView) findViewById(R.id.bMapView_radio);
        mBaiduMap = mMapView.getMap();
        // UI初始化
        MapStatusUpdate u1 = MapStatusUpdateFactory.newLatLng(GEO_WUHAN);
        mBaiduMap.setMapStatus(u1);
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(14);
        mBaiduMap.setMapStatus(msu);
        radio_to_routemap = (ImageButton)findViewById(R.id.RouteMap_radio);
        radio_to_heatmap = (ImageButton)findViewById(R.id.HeatMap_radio);

        OnClickListener RouterListener = new OnClickListener() {
            public void onClick(View v) {
                //onDestroy();
                Intent intent=new Intent(RadioMap.this,Map_GeometryDemo.class);
                startActivity(intent);
            }
        };
        OnClickListener HeatListener = new OnClickListener() {
            public void onClick(View v) {
                //onDestroy();
                Intent intent=new Intent(RadioMap.this,Map_BaseMap.class);
                startActivity(intent);
            }
        };

        radio_to_routemap.setOnClickListener(RouterListener);
        radio_to_heatmap.setOnClickListener(HeatListener);

        stationMarker = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_gcoding);
        MarkerOptions ooA = new MarkerOptions().position(GEO_WUHAN).icon(stationMarker)
                .zIndex(9);

        mMarkerA = (Marker) (mBaiduMap.addOverlay(ooA));
        // 界面加载时添加绘制图层
        addRadioMap();
    }

    /**
     * 添加点、线、多边形、圆、文字
     */



        private void addRadioMap() {
            for (int i = 1; i < 10; i++) {
                MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(18.0f);
                mBaiduMap.setMapStatus(msu);
                LatLng llCircle = new LatLng(30.515, 114.420);

                OverlayOptions ooCircle = new CircleOptions().fillColor(0X33faa700)
                        .center(llCircle).radius(10 * i);
                mBaiduMap.addOverlay(ooCircle);
            }
        }
        





    public void clearClick() {
        // 清除所有图层
        mMapView.getMap().clear();
    }

    //设置显示的态势图的频率范围

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mMapView.onDestroy();


        super.onDestroy();
    }

}
