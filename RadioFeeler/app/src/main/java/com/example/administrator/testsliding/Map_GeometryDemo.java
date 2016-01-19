package com.example.administrator.testsliding;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * 路径图
 * 
 */
public class Map_GeometryDemo extends Activity {

    // 地图相关
    MapView mMapView;
    BaiduMap mBaiduMap;
    // UI相关
    Button resetBtn;
    Button clearBtn;

    private static final LatLng GEO_WUHAN = new LatLng(30.5170810000,114.4245410000);
    Polyline mColorfulPolyline;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_geometry);
        // 初始化地图
        mMapView = (MapView) findViewById(R.id.bMapView2);
        mBaiduMap = mMapView.getMap();
        // UI初始化
        MapStatusUpdate u1 = MapStatusUpdateFactory.newLatLng(GEO_WUHAN);
        mBaiduMap.setMapStatus(u1);
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15);
        mBaiduMap.setMapStatus(msu);
        clearBtn = (Button) findViewById(R.id.button1);
        resetBtn = (Button) findViewById(R.id.button2);

        OnClickListener clearListener = new OnClickListener() {
            public void onClick(View v) {
                clearClick();
            }
        };
        OnClickListener restListener = new OnClickListener() {
            public void onClick(View v) {
                resetClick();
            }
        };

        clearBtn.setOnClickListener(clearListener);
        resetBtn.setOnClickListener(restListener);

        // 界面加载时添加绘制图层
        addRoutine();
    }

    /**
     * 添加点、线、多边形、圆、文字
     */
    public void addRoutine() {

        
        // 添加多颜色分段的折线绘制
        LatLng p11 = new LatLng(30.5176200000,114.4213960000);
        LatLng p21 = new LatLng(30.5174200000,114.4203560000);
        LatLng p31 = new LatLng(30.5170810000,114.4245410000);
        LatLng p41 = new LatLng(30.5212030000,114.4280400000);
        LatLng p51 = new LatLng(30.5209540000,114.4304280000);
        List<LatLng> points1 = new ArrayList<LatLng>();
        points1.add(p11);
        points1.add(p21);
        points1.add(p31);
        points1.add(p41);
        points1.add(p51);
        List<Integer> colorValue = new ArrayList<Integer>();
        colorValue.add(0xAAFF0000);
        colorValue.add(0xAA00FF00);
        colorValue.add(0xAA0000FF);
        colorValue.add(0xAA0022FF);
        OverlayOptions ooPolyline1 = new PolylineOptions().width(5)
                .color(0xAAFF0000).points(points1).colorsValues(colorValue);
        mColorfulPolyline = (Polyline) mBaiduMap.addOverlay(ooPolyline1);
        


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.route_menu, menu);
        return true;
    }
    public void resetClick() {
        // 添加绘制元素
        addRoutine();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.id_map_route_settings) {
           // setRouteProperty();
            Intent intent=new Intent(Map_GeometryDemo.this,Map_Geometry_Setting.class);
            startActivity(intent);
            return true;
        }



        return super.onOptionsItemSelected(item);
    }

    public void clearClick() {
        // 清除所有图层
        mMapView.getMap().clear();
    }

    //设置显示的态势图的频率范围
    private void setRouteProperty() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.map_routesetting_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("参数设置");

        builder.setIcon(R.drawable.map_settings);

        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "保存失败", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

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
