package com.example.administrator.testsliding.tab2;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.administrator.testsliding.GlobalConstants.Constants;
import com.example.administrator.testsliding.R;
import com.example.administrator.testsliding.bean2server.HistorySpectrumRequest;
import com.example.administrator.testsliding.compute.ComputePara;
import com.example.administrator.testsliding.view.DateTimePickDialogUtil;
import com.example.administrator.testsliding.view.MyTopBar;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Created by Administrator on 2015/7/23.
 */

public class Chart_spectrum extends Activity {
    //下拉条
    private Spinner spin;
    private List<String> list;
    private ArrayAdapter<String> adapter;

    private LinearLayout layout;
    private EditText startDateTime;
    private EditText endDateTime;
    private EditText et_ID;
    private LinearLayout lilay_spectrum;
    private ComputePara computePara = new ComputePara();

    private Timer timer = new Timer();
    private TimerTask task;
    private XYSeries series;
    private XYMultipleSeriesDataset mDataset;
    private GraphicalView chart;
    private XYMultipleSeriesRenderer renderer;
    private int color = Color.GREEN;
    private PointStyle style = PointStyle.CIRCLE;
    double[] xv = new double[1024];
    double[] yv = new double[1024];
    int count = 0;//段数计数器
    private  int totalseries=1;//画图线条总数

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
              if(msg.obj instanceof Info){
                  Info info= (Info) msg.obj;

                  InitView(info.start,info.end,info.total);
              }
            if(msg.what==1) {
                updateChart();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart_spectrum);
        initSetting();
        initspinnerSetting();
        InitEvent();
        layout = (LinearLayout)findViewById(R.id.linearLayout1);
        series = new XYSeries("");
        mDataset = buildDataset("",totalseries);
        renderer = buildRenderer(color,totalseries, style, true);
        // 设置好图表的样式
        setChartSettings(renderer, "频率值", "功率值", 95, 110, -150, 10, Color.WHITE, Color.WHITE);
        chart = ChartFactory.getLineChartView(Chart_spectrum.this, mDataset, renderer);
        layout.addView(chart, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        MyTopBar topBar= (MyTopBar) findViewById(R.id.topbar_chartspec);
        topBar.setOnTopBarClickListener(new MyTopBar.TopBarClickListener() {
            @Override
            public void leftclick() {
                Chart_spectrum.this.finish();
            }

            @Override
            public void rightclick() {

            }
        });
    }

//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.chart_spectrum, container, false);
//    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//
//    }


    private void initSetting() {
        spin = (Spinner) findViewById(R.id.spinner_spectrum);
        lilay_spectrum = (LinearLayout)findViewById(R.id.lilay_history);
        et_ID = (EditText)findViewById(R.id.et_ID);
        // 两个输入框
        startDateTime = (EditText)findViewById(R.id.inputDate);
        endDateTime = (EditText)findViewById(R.id.inputDate2);

    }

    private void initspinnerSetting() {
        list = new ArrayList<String>();
        list.add("请选择 ");
        list.add("实时数据");
        list.add("历史数据");
        adapter = new ArrayAdapter<String>(Chart_spectrum.this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

    }

    private void InitEvent() {
        //spinner
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (id == 2) {
                    lilay_spectrum.setVisibility(View.VISIBLE);
                    //历史数据需要设置内容
                    DateEvent();
                    HistorySpectrumRequest spec = new HistorySpectrumRequest();
                    try {
                        spec.setEqiupmentID(Constants.ID);
                        if (!et_ID.getText().toString().equals("")) {
                            spec.setIDcard(Integer.parseInt(et_ID.getText().toString()));
                        }
                        if (!startDateTime.getText().toString().equals("")) {
                            byte[] bytes = computePara.Time2Bytes(startDateTime.getText().toString());
                            spec.setStartTime(bytes);
                        }
                        if (!endDateTime.getText().toString().equals("")) {
                            byte[] bytes = computePara.Time2Bytes(endDateTime.getText().toString());
                            spec.setEndTime(bytes);
                        }
//                        BroadcastHelper.sendBroadCast(getActivity(),
//                                ConstantValues.SERVICE_SPECTRUM, "service_spectrum", spec);
                    } catch (Exception e) {

                    }
                } else if (id == 1) {
                    lilay_spectrum.setVisibility(View.GONE);
                    int firstart=0 ,end=0;
                    Constants.Queue_DrawRealtimeSpectrum.clear();
                    if (Constants.SweepParaList.size() != 0) {
                        firstart= Constants.SweepParaList.get(0).getStartNum();
                        end= Constants.SweepParaList.get(Constants.SweepParaList.size() - 1).getEndNum();
                        totalseries=end-firstart+1;
                    }
                    final int finalFirstart = firstart;
                    final int finalEnd = end;
                    new Thread(){
                        @Override
                        public void run() {
                                Message message=new Message();
                                Info info=new Info();
                                if((finalFirstart&finalEnd)!=0) {
                                    info.start = 70+(finalFirstart-1)*25;
                                    info.end =  70+finalEnd*25;
                                    info.total = totalseries;
                                    message.obj = info;
                                    handler.sendMessage(message);
                                }
                        }
                    }.start();
                    task = new TimerTask() {
                        @Override
                        public void run() {
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                        }
                    };
                    timer.schedule(task, 1000, 500);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void updateChart() {
        List<float[]> listdata = new ArrayList<>();
        Lock lock = new ReentrantLock(); //锁对象
        lock.lock();
        try {
            if (!Constants.Queue_DrawRealtimeSpectrum.isEmpty()) {
                listdata = Constants.Queue_DrawRealtimeSpectrum.poll();
            }
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
        if (listdata != null) {
            int flag = 0;
            for (int mj = 0; mj < listdata.size(); mj++) {
                float[] data =listdata.get(mj);
                int total = (int) data[0];

                int circle = 1024 / total;
                double dataX = (data[1] - 1) * 25 + 70;
                float max = data[2];
                //抽取
                for (int i = 0; i < circle; i++) {
                    max = data[i * total + 2];
                    for (int j = 0; j < total; j++) {
                        if (data[j + i * total + 2] >=max) {
                            max = data[j + i * total + 2];
                            flag = j + i * total + 2;
                        }
                    }
                    xv[i] = dataX + flag * 25.0 / 1024.0;
                    yv[i] = max;
                }

                    series = mDataset.getSeriesAt(mj);
                    mDataset.removeSeries(mj);


                // 点集先清空，为了做成新的点集而准备
                series.clear();
                for (int k = 0; k < circle; k++) {
                    series.add(xv[k], yv[k]);
                }
                // 在数据集中添加新的点集

                mDataset.addSeries(mj, series);
                chart.invalidate();
            }

        }

    }
    private void DateEvent() {
        startDateTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(Chart_spectrum.this);
                dateTimePicKDialog.dateTimePicKDialog(startDateTime);

            }
        });

        endDateTime.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        Chart_spectrum.this);
                dateTimePicKDialog.dateTimePicKDialog(endDateTime);
            }
        });
    }


    @Override
    public void onDestroy() {
        // 当结束程序时关掉Timer
        timer.cancel();
        super.onDestroy();
    }

    private void InitView( int start,int end,int total ){
        layout.removeAllViews();
        series = new XYSeries("");
        mDataset = buildDataset("",totalseries);
        renderer = buildRenderer(color,totalseries, style, true);
        setChartSettings(renderer, "频率值", "功率值",start, end , -150, 10, Color.WHITE, Color.WHITE);
        chart = ChartFactory.getLineChartView(Chart_spectrum.this, mDataset, renderer);
        layout.addView(chart, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

    }
    protected XYMultipleSeriesDataset buildDataset(String titles, int totalseries) {
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        int length = totalseries;            //有几条线
        for (int i = 0; i < length; i++) {
            XYSeries series = new XYSeries(titles);    //根据每条线的名称创建
            dataset.addSeries(series);
        }
        return dataset;
    }

    protected XYMultipleSeriesRenderer buildRenderer(int color, int totalseries,
                                                     PointStyle style, boolean fill) {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        // 设置图表中曲线本身的样式，包括颜色、点的大小以及线的粗细等
        int length = totalseries;
        for (int i = 0; i < length; i++) {
            XYSeriesRenderer r = new XYSeriesRenderer();
            r.setColor(color);
            r.setPointStyle(style);
            r.setFillPoints(fill);
            r.setLineWidth(5);
            renderer.addSeriesRenderer(r);
        }

        return renderer;
    }
    protected void setChartSettings(XYMultipleSeriesRenderer renderer,
                                    String xTitle, String yTitle, double xMin, double xMax,
                                    double yMin, double yMax, int axesColor, int labelsColor) {
        // 有关对图表的渲染可参看api文档
        renderer.setChartTitle("频谱图");//
        renderer.setXTitle(xTitle);
        renderer.setYTitle(yTitle);
        renderer.setAxisTitleTextSize(30);
        renderer.setChartTitleTextSize(30);
        renderer.setXAxisMin(xMin);
        renderer.setXAxisMax(xMax);
        renderer.setYAxisMin(yMin);
        renderer.setYAxisMax(yMax);
        renderer.setAxesColor(axesColor);
        renderer.setLabelsColor(labelsColor);
        renderer.setLabelsTextSize(30);
        renderer.setShowGrid(true);
        renderer.setGridColor(Color.GREEN);
        renderer.setXLabels(10);
        renderer.setYLabels(10);
        renderer.setYLabelsAlign(Paint.Align.RIGHT);
        renderer.setPointSize((float) 2);
        renderer.setShowLegend(false);
        renderer.setZoomEnabled(true);// 设置渲染器允许放大缩小
        renderer.setZoomButtonsVisible(true);
        /**以下为自己修改
         *
         */
        //设定背景颜色
        renderer.setApplyBackgroundColor(true);
        renderer.setBackgroundColor(Color.BLACK);
        //renderer.setBarSpacing(1);
    }


}

class Info{
    public int start=1;
    public int end=1;
    public int total=1;
}
































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































        



















































































































































































































































































