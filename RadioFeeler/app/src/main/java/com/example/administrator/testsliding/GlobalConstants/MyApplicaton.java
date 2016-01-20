package com.example.administrator.testsliding.GlobalConstants;

import android.app.Application;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by jianghao on 16/1/19.
 */
public class MyApplicaton extends Application{
    public static Queue<List<byte[]>> AppQueue_RealtimeSpectrum;//实时功率谱数据,供写文件

    public static void setQueue_RealtimeSpectrum(Queue<List<byte[]>> queue_RealtimeSpectrum) {
        AppQueue_RealtimeSpectrum = queue_RealtimeSpectrum;
    }

    public static Queue<List<byte[]>> getQueue_RealtimeSpectrum() {
        return AppQueue_RealtimeSpectrum;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setQueue_RealtimeSpectrum(new LinkedList<List<byte[]>>());
    }
}
