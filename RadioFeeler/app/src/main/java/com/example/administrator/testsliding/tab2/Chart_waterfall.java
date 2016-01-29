package com.example.administrator.testsliding.tab2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.testsliding.GlobalConstants.Constants;
import com.example.administrator.testsliding.R;
import com.example.administrator.testsliding.view.MyTopBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.Inflater;

import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.view.ColumnChartView;


/**
 * Created by Administrator on 2015/7/23.
 */

public class Chart_waterfall extends Activity {
    private static int[] colors = new int[]{0xFF0000A2, 0xFF0000BD, 0xFF0000DB, 0xFF1717FF,
            0xFF005BFF, 0xFF0376FF, 0xFF0895FF, 0xFF08AAFF,
            0xFF02CEFF, 0xFF04E6DC, 0xFF04FDC5, 0xFF35FCC8,
            0xFF5EFE9F, 0xFF7FFE7D, 0xFFB0FF4E, 0xFFDBFE00,
            0xFFFFD600, 0xFFFFB600, 0xFFFA5000, 0xFFE10006};
    private static List<AxisValue> Lvalues;
    private static final int DEFAULT_DATA = 0;
    private static final int SUBCOLUMNS_DATA = 1;
    private static final int STACKED_DATA = 2;
    private static final int NEGATIVE_SUBCOLUMNS_DATA = 3;
    private static final int NEGATIVE_STACKED_DATA = 4;

    private ColumnChartView chart;
    private ColumnChartData data;
    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private boolean hasLabels = false;
    private boolean hasLabelForSelected = false;
    private int dataType = DEFAULT_DATA;

    private Timer timer = new Timer();
    private TimerTask task;
//         ColumnChartRenderer ren=new ColumnChartRenderer();

    List<List<SubcolumnValue>> Xvalues;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 1) {

                updateChart();
            } else {
                generateStackedData(msg.arg1, msg.arg2);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart_waterfall);
        chart = (ColumnChartView) findViewById(R.id.chart);
        chart.setOnValueTouchListener(new ValueTouchListener());
        generateStackedData(70, 95);

        MyTopBar topBar = (MyTopBar) findViewById(R.id.topbar_chartwater);
        topBar.setOnTopBarClickListener(new MyTopBar.TopBarClickListener() {
            @Override
            public void leftclick() {
                Chart_waterfall.this.finish();
            }

            @Override
            public void rightclick() {

            }
        });
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        setHasOptionsMenu(true);
//        View rootView = inflater.inflate(R.layout.chart_waterfall, container, false);
//        chart = (ColumnChartView) rootView.findViewById(R.id.chart);
//        chart.setOnValueTouchListener(new ValueTouchListener());
//        generateStackedData(70, 95);
//        return rootView;
//    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//
//    }

    @Override
    public void onResume() {
        super.onResume();
        Constants.Queue_DrawRealtimewaterfall.clear();
        int firstart = 0, end = 0;
        if (Constants.SweepParaList.size() != 0) {
            firstart = Constants.SweepParaList.get(0).getStartNum();
            end = Constants.SweepParaList.get(Constants.SweepParaList.size() - 1).getEndNum();
        }
        final int finalFirstart = firstart;
        final int finalEnd = end;
        new Thread() {
            @Override
            public void run() {
                Message message = new Message();
                Info info = new Info();
                if ((finalFirstart & finalEnd) != 0) {
                    message.arg1 = 70 + (finalFirstart - 1) * 25;
                    message.arg2 = 70 + finalEnd * 25;
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

        timer.schedule(task, 1000, 1000);


    }

    @Override
    public void onStop() {
        //停止时关掉
        // timer.cancel();
        Constants.IsDrawWaterfall = false;
        super.onStop();
    }

    //
    @Override
    public void onDestroy() {
        // 当结束程序时关掉Timer
        timer.cancel();
        super.onDestroy();
    }
//


    private void updateChart() {
        int numSubcolumns = 30;
        int numColumns = 1024;
        List<float[]> flist = new ArrayList<>();
        if (!Constants.Queue_DrawRealtimewaterfall.isEmpty()) {
            flist = Constants.Queue_DrawRealtimewaterfall.poll();
            List<Column> columns = new ArrayList<Column>();
            List<SubcolumnValue> values;
            float[] ff1 = selectData(flist);
            if (Xvalues.size() > 0) {
                int size = Xvalues.size();
                for (int i = 0; i < size; i++) {
                    values = new ArrayList<SubcolumnValue>();
                    values = Xvalues.get(i);
                    for (int j = numSubcolumns - 1; j > 0; j--) {
                        values.set(j, values.get(j - 1));
                    }
                    // int color=ChartUtils.pickColor();
                    int color = colors[(int) ((ff1[i] + 150) / 8)];
                    values.set(0, new SubcolumnValue(1, color));

                    Column column = new Column(values);
                    columns.add(column);
                }
            }
            data = new ColumnChartData(columns);
            data.setStacked(true);
            data.setFillRatio(1);
            Axis axisX = new Axis(Lvalues);
            Axis axisY = Axis.generateAxisFromRange(0, 30, 2);
            axisY.setLineColor(Color.GREEN);
            axisX.setHasLines(false);
            axisX.setHasSeparationLine(false);
            axisY.setHasSeparationLine(false);
            axisY.setHasLines(false);

            axisX.setName("Axis X");
            axisY.setName("Axis Y");
            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
            chart.setColumnChartData(data);

        }
    }

    /**
     * Generates columns with stacked subcolumns.
     */
    private void generateStackedData(int start, int end) {
        int numSubcolumns = 30;
        int numColumns = 1024;

        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        Lvalues = new ArrayList<>();
        Xvalues = new ArrayList<List<SubcolumnValue>>();
        for (int i = 0; i < numColumns; ++i) {
            values = new ArrayList<SubcolumnValue>();
            if (i == 0) {
                Lvalues.add(new AxisValue(i).setLabel(String.valueOf(start)));
            } else if (i == numColumns - 20) {
                Lvalues.add(new AxisValue(i).setLabel(String.valueOf(end)));
            }
            for (int j = 1; j <= numSubcolumns; ++j) {
                values.add(new SubcolumnValue(1, Color.WHITE));
            }

            Column column = new Column(values);
            column.setHasLabelsOnlyForSelected(hasLabelForSelected);
            columns.add(column);

            Xvalues.add(values);
        }
        data = new ColumnChartData(columns);

        data.setStacked(true);
        data.setFillRatio(1);

        Axis axisX = new Axis(Lvalues);
        axisX.setHasSeparationLine(false);
        axisX.setHasLines(false);
        Axis axisY = Axis.generateAxisFromRange(0, 30, 1);
        axisY.setHasSeparationLine(false);
        axisY.setHasLines(false);
        axisY.setLineColor(Color.GREEN);

        axisX.setName("Axis X");
        axisY.setName("Axis Y");
        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);
        chart.setColumnChartData(data);

    }


    private class ValueTouchListener implements ColumnChartOnValueSelectListener {

        @Override
        public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {
            // Toast.makeText(getActivity(), "Selected: " + value, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

    }

    private float[] selectData(List<float[]> mlist) {
        float[] data = new float[1024];
        int size = mlist.size();
        float[] f1 = mlist.get(0);
        int total = (int) f1[0];//取出总段数
        int circle = 1024 / total;//一段25MHz数据抽取次数
        float max = f1[1];
        //抽取
        for (int i = 0; i < circle; i++) {
            max = f1[i * total + 1];
            for (int j = 0; j < total; j++) {
                if (f1[j + i * total + 1] >= max) {
                    max = f1[j + i * total + 1];
                }
            }
            data[i] = max;
        }
        if (size > 1) {
            for (int mm = 1; mm < size; mm++) {
                float[] f2 = mlist.get(mm);
                //抽取
                max = f2[0];
                for (int i = 0; i < circle; i++) {
                    max = f2[i * total];
                    for (int j = 0; j < total; j++) {
                        if (f2[j + i * total] >= max) {
                            max = f2[j + i * total];
                        }
                    }
                    data[i] = max;
                }
            }
        }

        return data;
    }
}



























































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































