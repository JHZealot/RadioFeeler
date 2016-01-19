package com.example.administrator.testsliding.tab3;

import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.testsliding.Bean.ToServerPowerSpectrumAndAbnormalPoint;
import com.example.administrator.testsliding.GlobalConstants.Constants;
import com.example.administrator.testsliding.R;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/7/21.
 */
public class Share_fragment extends Fragment {
    private Button mUpload;
    private Button mDownload;
    private Button mCreatePS;
    private Button mCreateIQ;
    private List mPowerSpectrum;
    private List mAbnormalPoint;

    private List mIQ;
    private FileOutputStream fos;

    public static final String PSFILE_PATH = Environment.getExternalStorageDirectory().
            getAbsolutePath() + "/PowerSpectrumFile/";

    public static final String IQFILE_PATH = Environment.getExternalStorageDirectory().
            getAbsolutePath() + "/IQwaveFile/";


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        InitSetting();
        InitEvent();
    }

    private void InitSetting() {
        mUpload = (Button) getActivity().findViewById(R.id.upload);
        mDownload = (Button) getActivity().findViewById(R.id.download);
        mCreatePS = (Button) getActivity().findViewById(R.id.ps_localsave);
        mCreateIQ = (Button) getActivity().findViewById(R.id.iq_localsave);


    }

    public void InitEvent() {

        /**
         * 上传文件按钮
         */
        mUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();

                        int h = 0;
                        ArrayList fileName = GetFileName(PSFILE_PATH);
                        while(true){
                                File file = new File(PSFILE_PATH,
                                        String.valueOf(fileName.get(0)));
                                FileInputStream fis=null;
                                try {
                                    fis = new FileInputStream(file);
//                                    BufferedInputStream bis=new BufferedInputStream(fis);
//                                    int readcount=0;
//                                    int count=4000;
                                    // 一次读一个字节
//                                byte[] content = new byte[fis.available()];
                                    byte[] content = new byte[4000];
                                    int temp;
                                    byte[] buffer=new byte[4000] ;
                                    while ((temp = fis.read(buffer)) != -1) {
                                        content = buffer;
                                        h++;
                                    }
                                    //缓冲字节流
//                                    while(readcount<count){
//                                        readcount+=bis.read(content,readcount,count-readcount);
//                                    }
                                    h = 0;
                                    ToServerPowerSpectrumAndAbnormalPoint ToPS = new ToServerPowerSpectrumAndAbnormalPoint();
                                    ToPS.setContent(content);
                                    ToPS.setContentLength(content.length);
                                    ToPS.setFileName(String.valueOf(fileName.get(0)));
                                    ToPS.setFileNameLength((short) String.valueOf(fileName.get(0)).getBytes(Charset.forName("UTF-8")).length);

                                    Constants.SERVERsession.write(ToPS);


                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();

                                } catch (IOException e) {

                                    e.printStackTrace();
                                    Toast.makeText(getActivity(), "请连接服务器", Toast.LENGTH_SHORT).show();
                                    Looper.loop();// 进入loop中的循环，查看消息队列
                                }catch (Exception e) {

                                    e.printStackTrace();
                                    Toast.makeText(getActivity(), "请连接服务器", Toast.LENGTH_SHORT).show();
                                    Looper.loop();// 进入loop中的循环，查看消息队列
                                }finally {
                                    try {
                                        fis.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }


//                        for (int i = 0; i < fileName.size(); i++) {
//                            File file = new File(PSFILE_PATH,
//                                    String.valueOf(fileName.get(i)));
//                            try {
//                                FileInputStream fis = new FileInputStream(file);
//                                // 一次读一个字节
////                                byte[] content = new byte[fis.available()];
//                                byte[] content = new byte[4000];
//                                int temp;
//                                byte[] buffer=new byte[4000] ;
//                                while ((temp = fis.read(buffer)) != -1) {
//                                    content = buffer;
//                                    h++;
//                                }
//                                h = 0;
//                                ToServerPowerSpectrumAndAbnormalPoint ToPS = new ToServerPowerSpectrumAndAbnormalPoint();
//                                ToPS.setContent(content);
//                                ToPS.setContentLength(content.length);
//                                ToPS.setFileName(String.valueOf(fileName.get(i)));
//                                ToPS.setFileNameLength((short) String.valueOf(fileName.get(i)).getBytes(Charset.forName("UTF-8")).length);
//
//                                Constants.SERVERsession.write(ToPS);
//
//                            } catch (FileNotFoundException e) {
//                                e.printStackTrace();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//
//                                Toast.makeText(getActivity(), "请连接服务器", Toast.LENGTH_SHORT).show();
//                                Looper.loop();// 进入loop中的循环，查看消息队列
//                            }catch (Exception e) {
//                                e.printStackTrace();
//                                Toast.makeText(getActivity(), "请连接服务器", Toast.LENGTH_SHORT).show();
//                                Looper.loop();// 进入loop中的循环，查看消息队列
//                            }
//                        }
                    }
                }).start();
//                Toast.makeText(getActivity(), "上传成功", Toast.LENGTH_SHORT).show();
            }
        });

        mDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {


                        File PSdir = new File(PSFILE_PATH);
                        if (!PSdir.exists()) {
                            PSdir.mkdir();
                        }
                        for (int j = 0; j < 400000; j++) {
                            //创建文件
                            String name = String.format("%d-%d-%d-%d-%d-%d-%d.%s", 2016, 7, 8, 9, 9, j,
                                    2, "pwr");
                            File file = new File(PSdir, name);

                            try {
                                fos = new FileOutputStream(file);
                                fos.write(0x00);
                                for (int i = 0; i < 3998; i++) {
                                    fos.write(0x02);
                                }
                                fos.write(0x00);
                                fos.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
                Toast.makeText(getActivity(), "下载成功", Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * 生成文件
         */
        mCreatePS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        File PSdir = new File(PSFILE_PATH);
                        if (!PSdir.exists()) {
                            PSdir.mkdir();
                        }
                        /**
                         生成功率谱文件
                         */
                        if (!Constants.Queue_RealtimeSpectrum.isEmpty()) {
                            for (int i = 0; i < Constants.Queue_RealtimeSpectrum.size(); i++) {
                                mPowerSpectrum = Constants.Queue_RealtimeSpectrum.poll();
                                mAbnormalPoint = Constants.Queue_AbnormalFreq.poll();
                                //取出时间
                                byte[] byte1 = (byte[]) mPowerSpectrum.get(0);
                                int year = getYear(byte1);
                                int month = getMonth(byte1);
                                int day = getDay(byte1);
                                int hour = getHour(byte1);
                                int min = getMin(byte1);
                                int sec = getSecond(byte1);
                                //创建文件
                                String name = String.format("%d-%d-%d-%d-%d-%d.%s", year, month, day, hour, min, sec,
                                        Constants.ID, "pwr");
//
                                File file = new File(PSdir, name);

                                if (!file.exists()) {
                                    try {
//                                        //在指定的文件夹中创建文件
//                                        file.createNewFile();
                                        //获取文件写入流
                                        fos = new FileOutputStream(file);
                                        fos.write((byte) 0x00);
                                        for (int j = 0; j < mPowerSpectrum.size(); j++) {
                                            fos.write((byte[]) mPowerSpectrum.get(j));
                                        }
                                        fos.write(0xff);
                                        for (int k = 0; k < mAbnormalPoint.size(); k++) {
                                            fos.write((byte[]) mAbnormalPoint.get(k));
                                        }
                                        fos.write(0x00);
                                        fos.close();


                                    } catch (IOException e) {
                                        e.printStackTrace();
                                        try {
                                            fos.close();
                                        } catch (IOException e1) {
                                            e1.printStackTrace();
                                        }
                                    }

                                }
                            }

                            Toast.makeText(getContext(), "功率谱文件写入完毕", Toast.LENGTH_SHORT).show();
                        }

                    }
                }).start();
            }
        });


        mCreateIQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        //创建IQ波形文件路径
                        File PSdir = new File(IQFILE_PATH);
                        if (!PSdir.exists()) {
                            PSdir.mkdir();
                        }

                        if (!Constants.Queue_IQwave.isEmpty()) {

                            for (int i = 0; i < Constants.Queue_IQwave.size(); i++) {
                                mIQ = Constants.Queue_IQwave.poll();

                                //取出时间
                                byte[] byte1 = (byte[]) mIQ.get(0);
                                int year = getYear(byte1);
                                int month = getMonth(byte1);
                                int day = getDay(byte1);
                                int hour = getHour(byte1);
                                int min = getMin(byte1);
                                int sec = getSecond(byte1);
                                //创建IQ文件
                                String IQname = String.format("%d_%d_%d_%d_%d_%d_%d.%s", year, month, day, hour, min, sec,
                                        Constants.ID, "iq");
                                File file = new File(IQFILE_PATH, IQname);
                                //向文件写入数据
                                try {
                                    //获取文件写入流
                                    fos = new FileOutputStream(file);
                                    fos.write((byte) 0x00);
                                    for (int j = 0; j < mIQ.size(); j++) {
                                        fos.write((byte[]) mPowerSpectrum.get(j));
                                    }
                                    fos.write(0x00);
                                    fos.close();
                                } catch (IOException e) {
                                    try {
                                        fos.close();
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                    e.printStackTrace();
                                }
                            }

                            Toast.makeText(getContext(), "IQ文件写入完毕", Toast.LENGTH_SHORT).show();


                        }

                    }
                }).start();
            }
        });

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.share_fragment, container, false);
    }

    private int getYear(byte[] bytes) {
        int year;
        year = (bytes[9] << 4) + (bytes[10] >> 4);
        return year;
    }

    private int getMonth(byte[] bytes) {
        int month;
        month = (bytes[10] & 0x0f);
        return month;
    }

    private int getDay(byte[] bytes) {
        int day;
        day = (bytes[11] & 0xf8);
        return day;
    }

    private int getHour(byte[] bytes) {
        int hour;
        hour = ((bytes[11] & 0x07) << 3) + (bytes[12] & 0x03);
        return hour;
    }

    private int getMin(byte[] bytes) {
        int min;
        min = (bytes[12] & 0xfc);
        return min;
    }

    private int getSecond(byte[] bytes) {
        int second;
        second = (bytes[13]) & 0xff;
        return second;
    }

    //获取当前目录下所有的功率谱文件
    public ArrayList<String> GetFileName(String fileAbsolutePath) {
        ArrayList<String> Filename = new ArrayList<>();
        File file = new File(fileAbsolutePath);
        File[] subFile = file.listFiles();

        for (int iFileLength = 0; iFileLength < subFile.length; iFileLength++) {
            // 判断是否为文件夹
            if (!subFile[iFileLength].isDirectory()) {
                String name = subFile[iFileLength].getName();
                // 判断是否为pwr结尾
                if (name.trim().toLowerCase().endsWith(".pwr")) {
                    Filename.add(name);
                }
            }
        }
        return Filename;
    }

}




