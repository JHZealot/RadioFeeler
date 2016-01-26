package com.example.administrator.testsliding.Mina;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.testsliding.Bean.Connect;
import com.example.administrator.testsliding.Bean.FixCentralFreq;
import com.example.administrator.testsliding.Bean.FixSetting;
import com.example.administrator.testsliding.Bean.IQwave;
import com.example.administrator.testsliding.Bean.InGain;
import com.example.administrator.testsliding.Bean.OutGain;
import com.example.administrator.testsliding.Bean.PowerSpectrumAndAbnormalPonit;
import com.example.administrator.testsliding.Bean.Press;
import com.example.administrator.testsliding.Bean.PressSetting;
import com.example.administrator.testsliding.Bean.Query;
import com.example.administrator.testsliding.Bean.ReceiveRight;
import com.example.administrator.testsliding.Bean.ReceiveWrong;
import com.example.administrator.testsliding.Bean.StationState;
import com.example.administrator.testsliding.Bean.SweepRange;
import com.example.administrator.testsliding.Bean.Threshold;
import com.example.administrator.testsliding.Bean.UploadData;
import com.example.administrator.testsliding.Broadcast.Broadcast;
import com.example.administrator.testsliding.Database.DatabaseHelper;
import com.example.administrator.testsliding.GlobalConstants.ConstantValues;
import com.example.administrator.testsliding.GlobalConstants.Constants;
import com.example.administrator.testsliding.GlobalConstants.MyApplication;
import com.example.administrator.testsliding.compute.ComputePara;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jinaghao on 15/11/18.
 */
public class MinaClientService extends Service {

    private SQLiteDatabase db=null;
    private DatabaseHelper dbHelper=null;
    private IoSession session;
    private MyApplication myApplication;
    ComputePara computePara = new ComputePara();
    private Boolean Ispsfull = false;//queshao

    List<byte[]> temp_IQwave = new ArrayList<>();
    int SweepParaList_length;

    private int total;
    private int h;
    private int y;
    private int z;
    private int fileIsChanged;

    public static final String PSFILE_PATH = Environment.getExternalStorageDirectory().
            getAbsolutePath() + "/PowerSpectrumFile/";


    private FileOutputStream fos;
    private DataOutputStream dos;

    private BroadcastReceiver ActivityReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (action.equals(ConstantValues.InGainSet)) {
                InGain data = intent.getParcelableExtra("InGainSet");
                /**
                 * 在这里把activity的消息转发给服务器
                 */
                if (data == null) {
                    return;
                }
                try {
                    session.write(data);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "请连接硬件", Toast.LENGTH_SHORT).show();
                }
                return;

            }
            if (action.equals(ConstantValues.InGainQuery)) {
                Query data = intent.getParcelableExtra("InGainQuery");
                /**
                 * 在这里把activity的消息转发给服务器
                 */
                if (data == null) {
                    return;
                }
                try {
                    session.write(data);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "请连接硬件", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            if (action.equals(ConstantValues.SweepRangeQuery)) {
                Query data = intent.getParcelableExtra("SweepRangeQuery");
                /**
                 * 在这里把activity的消息转发给服务器
                 */
                if (data == null) {
                    return;
                }
                try {
                    session.write(data);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "请连接硬件", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            if (action.equals(ConstantValues.SweepRangeSet)) {
                SweepRange data = intent.getParcelableExtra("SweepRangeSet");
                /**
                 * 在这里把activity的消息转发给服务器
                 */
                if (data == null) {
                    return;
                }
                try {

                    session.write(data);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "请连接硬件", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            if (action.equals(ConstantValues.OutGainSet)) {
                OutGain data = intent.getParcelableExtra("OutGainSet");
                /**
                 * 在这里把activity的消息转发给服务器
                 */
                if (data == null) {
                    return;
                }
                try {
                    session.write(data);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "请连接硬件", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            if (action.equals(ConstantValues.OutGainQuery)) {
                Query data = intent.getParcelableExtra("OutGainQuery");
                /**
                 * 在这里把activity的消息转发给服务器
                 */
                if (data == null) {
                    return;
                }
                try {
                    session.write(data);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "请连接硬件", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            if (action.equals(ConstantValues.ThresholdSet)) {
                Threshold data = intent.getParcelableExtra("ThresholdSet");
                /**
                 * 在这里把activity的消息转发给服务器
                 */
                if (data == null) {
                    return;
                }
                try {

                    session.write(data);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "请连接硬件", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            if (action.equals(ConstantValues.ThresholdQuery)) {
                Query data = intent.getParcelableExtra("ThresholdQuery");
                /**
                 * 在这里把activity的消息转发给服务器
                 */
                if (data == null) {
                    return;
                }
                try {

                    session.write(data);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "请连接硬件", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            if (action.equals(ConstantValues.FixCentralFreqSet)) {
                FixCentralFreq data = intent.getParcelableExtra("FixCentralFreqSet");
                /**
                 * 在这里把activity的消息转发给服务器
                 */
                if (data == null) {
                    return;
                }
                try {

                    session.write(data);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "请连接硬件", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            if (action.equals(ConstantValues.FixCentralFreqQuery)) {
                Query data = intent.getParcelableExtra("FixCentralFreqQuery");
                /**
                 * 在这里把activity的消息转发给服务器
                 */
                if (data == null) {
                    return;
                }
                try {

                    session.write(data);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "请连接硬件", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            if (action.equals(ConstantValues.FixSettingSet)) {
                FixSetting data = intent.getParcelableExtra("FixSettingSet");
                /**
                 * 在这里把activity的消息转发给服务器
                 */
                if (data == null) {
                    return;
                }
                try {

                    session.write(data);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "请连接硬件", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            if (action.equals(ConstantValues.FixSettingQuery)) {
                Query data = intent.getParcelableExtra("FixSettingQuery");
                /**
                 * 在这里把activity的消息转发给服务器
                 */
                if (data == null) {
                    return;
                }
                try {

                    session.write(data);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "请连接硬件", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            if (action.equals(ConstantValues.PressSet)) {
                Press data = intent.getParcelableExtra("PressSet");
                /**
                 * 在这里把activity的消息转发给服务器
                 */
                if (data == null) {
                    return;
                }
                try {

                    session.write(data);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "请连接硬件", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            if (action.equals(ConstantValues.PressQuery)) {
                Query data = intent.getParcelableExtra("PressQuery");
                /**
                 * 在这里把activity的消息转发给服务器
                 */
                if (data == null) {
                    return;
                }
                try {

                    session.write(data);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "请连接硬件", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            if (action.equals(ConstantValues.PressSettingSet)) {
                PressSetting data = intent.getParcelableExtra("PressSettingSet");
                /**
                 * 在这里把activity的消息转发给服务器
                 */
                if (data == null) {
                    return;
                }
                try {

                    session.write(data);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "请连接硬件", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            if (action.equals(ConstantValues.PressSettingQuery)) {
                Query data = intent.getParcelableExtra("PressSettingQuery");
                /**
                 * 在这里把activity的消息转发给服务器
                 */
                if (data == null) {
                    return;
                }
                try {

                    session.write(data);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "请连接硬件", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            if (action.equals(ConstantValues.StationStateQuery)) {
                Query data = intent.getParcelableExtra("StationStateQuery");
                /**
                 * 在这里把activity的消息转发给服务器
                 */
                if (data == null) {
                    return;
                }
                try {

                    session.write(data);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "请连接硬件", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            if (action.equals(ConstantValues.uploadQuery)) {
                Query data = intent.getParcelableExtra("uploadQuery");
                /**
                 * 在这里把activity的消息转发给服务器
                 */
                if (data == null) {
                    return;
                }
                try {

                    session.write(data);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "请连接硬件", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            if (action.equals(ConstantValues.uploadDataSet)) {
                UploadData data = intent.getParcelableExtra("uploadDataSet");
                /**
                 * 在这里把activity的消息转发给服务器
                 */
                if (data == null) {
                    return;
                }
                try {

                    session.write(data);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "请连接硬件", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            if (action.equals(ConstantValues.ConnectPCBQuery)) {
                Query data = intent.getParcelableExtra("ConnectPCBQuery");
                /**
                 * 在这里把activity的消息转发给服务器
                 */
                if (data == null) {
                    return;
                }
                try {

                    session.write(data);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "请连接硬件", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    };


    @Override
    public void onCreate() {
        dbHelper=new DatabaseHelper(this);
        db=dbHelper.getWritableDatabase();
        Constants.sevCount++;
        Log.d("service", "service运行次数" + Constants.sevCount);
        myApplication = (MyApplication) getApplication();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConstantValues.InGainSet);
        filter.addAction(ConstantValues.InGainQuery);

        filter.addAction(ConstantValues.OutGainSet);
        filter.addAction(ConstantValues.OutGainQuery);

        filter.addAction(ConstantValues.FixCentralFreqSet);
        filter.addAction(ConstantValues.FixCentralFreqQuery);

        filter.addAction(ConstantValues.FixSettingSet);
        filter.addAction(ConstantValues.FixSettingQuery);

        filter.addAction(ConstantValues.SweepRangeSet);
        filter.addAction(ConstantValues.SweepRangeQuery);

        filter.addAction(ConstantValues.ThresholdSet);
        filter.addAction(ConstantValues.ThresholdQuery);

        filter.addAction(ConstantValues.PressSet);
        filter.addAction(ConstantValues.PressQuery);

        filter.addAction(ConstantValues.PressSettingSet);
        filter.addAction(ConstantValues.PressSettingQuery);

        filter.addAction(ConstantValues.StationStateQuery);

        filter.addAction(ConstantValues.uploadDataSet);
        filter.addAction(ConstantValues.uploadQuery);

        filter.addAction(ConstantValues.ConnectPCBQuery);

        filter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(ActivityReceiver, filter);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //异常频点和频谱
//                    int size=Constants.Queue_SpectrumVSAbnormal.size();
//                    Log.d("qwr",String.valueOf(size));

                    IoConnector connector = new NioSocketConnector();

                    connector.setHandler(new MyClientHandler());

                    connector.getFilterChain().addLast("codec",
                            new ProtocolCodecFilter(new MyProtocolFactory()));

                    connector.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 1);
                    // connector.getSessionConfig().setReadBufferSize(1024);
//
//                    /**
//                     * 我的电脑的IP
//                     */
//                    ConnectFuture future=connector.connect
//                            (new InetSocketAddress("115.156.209.124",8080));

                    /**
                     * Fpga的IP
                     */
                    ConnectFuture future = connector.connect
                            (new InetSocketAddress("192.168.43.157", 8080));
                    /**
                     * Fpga的IP
                     */
//                ConnectFuture future=connector.connect
//                        (new InetSocketAddress(Constants.PCBIP,8080));

                    future.awaitUninterruptibly();// 等待连接创建完成

                    session = future.getSession();

                    Constants.FPGAsession = session;

                    session.getCloseFuture().awaitUninterruptibly();//等待连接断开

                    connector.dispose();


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(ActivityReceiver);
        ActivityReceiver = null;
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public class MyClientHandler extends IoHandlerAdapter {
        @Override
        public void messageReceived(IoSession session, final Object message) throws Exception {
            //处理从服务端接收到的消息，这个消息已经经过decode解码器把消息从字节流转化为对象

            //==========================================功率谱解析
            if (message instanceof PowerSpectrumAndAbnormalPonit) {

                Log.d("abcd", "写文件开始时间：" + String.valueOf(System.currentTimeMillis()));
                SweepParaList_length = Constants.SweepParaList.size();
                final PowerSpectrumAndAbnormalPonit PSAP = (PowerSpectrumAndAbnormalPonit) message;
                if (PSAP != null) {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //相对于前一帧功率谱文件是否有变化信息位
                            List<byte[]> temp_powerSpectrum = new ArrayList<>();
                            List<byte[]> temp_abnormalPoint = new ArrayList<>();
                            List<float[]> temp_drawSpectrum = new ArrayList<float[]>();
                            byte[] b3 = null;
                            total = PSAP.getTotalBand();
                            int firstart = Constants.SweepParaList.get(0).getStartNum();//输入扫频范围第一组的起点对应的段号
                            int lastend = Constants.SweepParaList.get(SweepParaList_length - 1).getEndNum();//输入扫频范围最后一组的终点对应的段号
                            if (PSAP.getFunctionID() == 0x0D) {//区分功率谱数据类型
                                //判断是否为起始段
                                if (firstart == PSAP.getPSbandNum()) {
                                    //判断相对前一帧是否有变化
                                    if (PSAP.getIsChange() == 0x0f) {
                                        fileIsChanged = 1;
                                    }
                                    if (Constants.spectrumCount != 0) {
//                                        temp_powerSpectrum.clear();
//                                        temp_abnormalPoint.clear();
                                        Constants.spectrumCount = 0;
                                        fileIsChanged = 0;
                                    }

                                    Constants.spectrumCount++;

                                    //频谱数据
                                    byte[] byte1 = new byte[1554];//
                                    byte[] b1 = PSAP.getLocationandTime();
                                    System.arraycopy(b1, 0, byte1, 0, 14);//填入位置和时间
                                    byte[] b2 = PSAP.getSweepModel2bandNum();
                                    System.arraycopy(b2, 0, byte1, 14, 3);//填入扫频模式，文件上传模式的信息
                                    byte1[17] = (byte) PSAP.getPSbandNum();//输入段序号
                                    b3 = PSAP.getPSpower();
                                    System.arraycopy(b3, 0, byte1, 18, 1536);//填入功率谱值
                                    if (byte1 != null)
                                        temp_powerSpectrum.add(byte1);
                                    //异常频点
                                    byte[] byteAb1 = new byte[32];
                                    byteAb1[0] = (byte) PSAP.getAPbandNum();
                                    byteAb1[1] = (byte) PSAP.getAPnum();
                                    byte[] ap = PSAP.getAPpower();
                                    System.arraycopy(ap, 0, byteAb1, 2, 30);
                                    if (byteAb1 != null)
                                        temp_abnormalPoint.add(byteAb1);

                                    //存入画图
                                    float[] pow = new float[1026];
                                    pow[0] = PSAP.getTotalBand();//填入总段数
                                    pow[1] = PSAP.getPSbandNum();//输入段序号
                                    float[] f1 = computePara.Bytes2Power(b3);
                                    System.arraycopy(f1, 0, pow, 2, 1024);//填入功率谱值
                                    temp_drawSpectrum.add(pow);

                                } else if (Constants.SweepParaList.get(SweepParaList_length - 1).getEndNum() == PSAP.getPSbandNum()) {
//                            //结束
                                    if (Constants.SweepParaList.get(SweepParaList_length - 1).getEndNum() !=
                                            Constants.SweepParaList.get(0).getStartNum()) {
                                        Constants.spectrumCount++;
                                        byte[] byte2 = new byte[1537];
                                        byte2[0] = (byte) PSAP.getPSbandNum();
                                        b3 = PSAP.getPSpower();
                                        System.arraycopy(b3, 0, byte2, 1, 1536);//填入功率谱值
                                        if (byte2 != null)
                                            temp_powerSpectrum.add(byte2);

                                        byte[] byteAb2 = new byte[32];
                                        byteAb2[0] = (byte) PSAP.getAPbandNum();
                                        byteAb2[1] = (byte) PSAP.getAPnum();
                                        byte[] bb1 = PSAP.getAPpower();
                                        System.arraycopy(bb1, 0, byteAb2, 2, 30);
                                        temp_abnormalPoint.add(byteAb2);

//                                        float[] pow = new float[1025];
//                                        pow[0] = PSAP.getPSbandNum();//输入段序号
                                        float[] f1 = computePara.Bytes2Power(b3);
//                                        System.arraycopy(f1, 0, pow, 1, 1024);//填入功率谱值
                                        temp_drawSpectrum.add(f1);
                                    }
                                    if (Constants.spectrumCount == PSAP.getTotalBand()) {

                                        //判断相对前一帧是否有变化
                                        if (PSAP.getIsChange() == 0x0f) {
                                            fileIsChanged = 1;
                                        }

                                        File PSdir = new File(PSFILE_PATH);
                                        if (!PSdir.exists()) {
                                            PSdir.mkdir();
                                        }
                                        //取出时间
                                        byte[] byte6 = PSAP.getLocationandTime();
                                        int year = getYear(byte6);
                                        int month = getMonth(byte6);
                                        int day = getDay(byte6);
                                        int hour = getHour(byte6);
                                        int min = getMin(byte6);
                                        int sec = getSecond(byte6);
                                        //创建文件

                                        /**
                                         * 获取文件名
                                         */

                                        String name = String.format("%d-%d-%d-%d-%d-%d-%d-%d-%s.%s", year, month, day, hour, min, sec,
                                                0, Constants.ID, "fine", "pwr");

                                        //判断是否是一秒内的文件，如果是，需要加上1s序号
                                        File[] PSFile = PSdir.listFiles();
                                        if (PSFile.length > 0) {
                                            for (int j = 0; j < PSFile.length; j++) {
                                                if (name == PSFile[j].getName()) {
                                                    int count = 0;
                                                    count++;
                                                    name = String.format("%d-%d-%d-%d-%d-%d-%d-%d-%s.%s", year, month, day,
                                                            hour, min, sec, count, Constants.ID, "fine", "pwr");
                                                }
                                            }
                                        }

                                        File file = new File(PSdir, name);
//
                                        //获取文件写入流
                                        try {
                                            dos = new DataOutputStream(new FileOutputStream(file));
//                                                fos = new FileOutputStream(file);
                                            dos.write((byte) 0x00);
                                            for (int j = 0; j < temp_powerSpectrum.size(); j++) {
                                                dos.write(temp_powerSpectrum.get(j));
                                            }
                                            dos.write(0xff);
                                            for (int k = 0; k < temp_abnormalPoint.size(); k++) {
                                                dos.write(temp_abnormalPoint.get(k));
                                            }
                                            dos.write(0x00);
                                            dos.close();
                                            y++;
                                            Log.d("abcde", "写文件个数：" + y);

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        } finally {
                                            try {
                                                dos.close();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        //在此将文件的信息插入数据库===================
                                        ContentValues cv=new ContentValues();
                                        cv.put("filename",name);
                                        cv.put("start",myApplication.getSweepStart());
                                        cv.put("end",myApplication.getSweepEnd());
                                        cv.put("isChanged",fileIsChanged);
                                        db.insert("localFile",null,cv);
                                    }
                                    Lock lock = new ReentrantLock(); //锁对象
                                    lock.lock();
                                    try {
                                        Constants.Queue_DrawRealtimeSpectrum.offer(temp_drawSpectrum);
                                    } catch (Exception e) {

                                    } finally {
                                        lock.unlock();
                                    }
                                    Constants.spectrumCount = 0;


                                    //一个文件写完后将其重置
                                    fileIsChanged = 0;

                                } else {
                                    //中间正常
                                    Constants.spectrumCount++;
                                    //判断相对前一帧是否有变化
                                    if (PSAP.getIsChange() == 0x0f) {
                                        fileIsChanged = 1;
                                    }
                                    byte[] byte3 = new byte[1537];
                                    byte3[0] = (byte) PSAP.getPSbandNum();
                                    b3 = PSAP.getPSpower();
                                    System.arraycopy(b3, 0, byte3, 1, 1536);//填入功率谱值
                                    temp_powerSpectrum.add(byte3);

                                    byte[] byteAb3 = new byte[32];
                                    byteAb3[0] = (byte) PSAP.getAPbandNum();
                                    byteAb3[1] = (byte) PSAP.getAPnum();
                                    byte[] bbb1 = PSAP.getAPpower();
                                    System.arraycopy(bbb1, 0, byteAb3, 2, 30);
                                    temp_abnormalPoint.add(byteAb3);

                                    //float[] pow = new float[1024];
                                    float[] f1 = computePara.Bytes2Power(b3);
                                    //System.arraycopy(f1, 0, pow, 2, 1024);//填入功率谱值
                                    temp_drawSpectrum.add(f1);
                                }


                                //存入画图
                                float[] pow = new float[1026];
                                pow[0] = PSAP.getTotalBand();//填入总段数
                                pow[1] = PSAP.getPSbandNum();//输入段序号
                                float[] f1 = computePara.Bytes2Power(b3);
                                System.arraycopy(f1, 0, pow, 2, 1024);//填入功率谱值

                                Lock lock = new ReentrantLock(); //锁对象
                                lock.lock();
                                try {
                                    // Constants.Queue_DrawRealtimeSpectrum.offer(pow);
                                } catch (Exception e) {

                                } finally {
                                    lock.unlock();
                                }

                            } else {//背景功率只画图
                                float[] pow = new float[1025];
                                pow[0] = PSAP.getPSbandNum();//输入段序号
                                float[] f1 = computePara.Bytes2Power(b3);
                                System.arraycopy(f1, 0, pow, 1, 1024);//填入功率谱值
                                Constants.Queue_BackgroundSpectrum.offer(pow);
                            }

                            //=============================异常频点=================================================
                            ////////////////存入显示列表
                            int length = PSAP.getAPnum() * 3;
                            byte[] abnormalList = new byte[length + 1];
                            abnormalList[0] = (byte) PSAP.getAPbandNum();
                            byte[] allPow = PSAP.getAPpower();
                            System.arraycopy(allPow, 0, abnormalList, 1, length);
                            Constants.Queue_AbnormalFreq_List.offer(abnormalList);
                        }

                    }).start();
                }
                Log.d("abcd", "写文件结束时间：" + String.valueOf(System.currentTimeMillis()));


            }

            if (message instanceof InGain) {
                InGain data = (InGain) message;
                Broadcast.sendBroadCast(getBaseContext(),
                        ConstantValues.InGainQuery, "data", data);
            }

            if (message instanceof SweepRange) {
                SweepRange data = (SweepRange) message;
                Broadcast.sendBroadCast(getBaseContext(), ConstantValues.SweepRangeQuery, "data", data);

            }

            if (message instanceof OutGain) {
                OutGain data = (OutGain) message;
                Broadcast.sendBroadCast(getBaseContext(), ConstantValues.OutGainQuery, "data", data);

            }

            if (message instanceof Threshold) {
                Threshold data = (Threshold) message;
                Broadcast.sendBroadCast(getBaseContext(), ConstantValues.ThresholdQuery, "data", data);

            }

            if (message instanceof FixCentralFreq) {
                FixCentralFreq data = (FixCentralFreq) message;
                Broadcast.sendBroadCast(getBaseContext(), ConstantValues.FixCentralFreqQuery, "data", data);

            }

            if (message instanceof FixSetting) {
                FixSetting data = (FixSetting) message;
                Broadcast.sendBroadCast(getBaseContext(), ConstantValues.FixSettingQuery, "data", data);

            }
            if (message instanceof Press) {
                Press data = (Press) message;
                Broadcast.sendBroadCast(getBaseContext(), ConstantValues.PressQuery, "data", data);

            }
            if (message instanceof PressSetting) {
                PressSetting data = (PressSetting) message;
                Broadcast.sendBroadCast(getBaseContext(), ConstantValues.PressSettingQuery, "data", data);

            }
            if (message instanceof StationState) {
                StationState data = (StationState) message;
                Broadcast.sendBroadCast(getBaseContext(), ConstantValues.StationStateQuery, "data", data);

            }

            if (message instanceof UploadData) {
                UploadData data = (UploadData) message;
                Broadcast.sendBroadCast(getBaseContext(), ConstantValues.uploadQuery, "data", data);

            }

            if (message instanceof Connect) {
                Connect data = (Connect) message;
                Broadcast.sendBroadCast(getBaseContext(), ConstantValues.ConnectPCBQuery, "data", data);

            }


//============================IQ波形文件生成==================================================

            if (message instanceof IQwave) {
                IQwave iQwave = new IQwave();
                iQwave = (IQwave) message;
                if (iQwave != null) {
                    if (iQwave.getNowNum() == 1) {
                        Constants.IQCount++;
                        byte[] byte1 = new byte[6020];
                        System.arraycopy(iQwave.getLocation(), 0, byte1, 0, 10);
                        System.arraycopy(iQwave.getIQpara(), 0, byte1, 11, 5);
                        System.arraycopy(iQwave.getIQwave(), 0, byte1, 16, 6001);
                        temp_IQwave.add(byte1);

                    } else if (Constants.IQCount == iQwave.getNowNum()) {
                        Constants.IQCount++;
                        byte[] byte1 = new byte[6001];
                        System.arraycopy(iQwave.getIQwave(), 0, byte1, 0, 6001);
                        temp_IQwave.add(byte1);

                    } else if (Constants.IQCount != iQwave.getNowNum()) {
                        int need = iQwave.getTotalBands() - Constants.IQCount;
                        for (int i = 0; i < need; i++) {
                            Constants.IQCount++;
                            byte[] byte1 = new byte[6001];
                            System.arraycopy(iQwave.getIQwave(), 0, byte1, 0, 6001);
                            temp_IQwave.add(byte1);
                        }
                    } else if (iQwave.getNowNum() == Constants.IQCount && iQwave.getNowNum() == iQwave.getTotalBands()) {
                        byte[] byte1 = new byte[6001];
                        System.arraycopy(iQwave.getIQwave(), 0, byte1, 0, 6001);
                        temp_IQwave.add(byte1);
                        Constants.Queue_IQwave.offer(temp_IQwave);
                        temp_IQwave.clear();
                        Constants.IQCount = 0;
                    }
                }
            }
        }

        @Override
        public void sessionCreated(IoSession session) throws Exception {
            super.sessionCreated(session);
        }

        @Override
        public void sessionOpened(IoSession session) throws Exception {
            super.sessionOpened(session);
        }

        @Override
        public void sessionClosed(IoSession session) throws Exception {
            super.sessionClosed(session);
        }

        @Override
        public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
            super.sessionIdle(session, status);
            ReceiveWrong mReceiveWrong = new ReceiveWrong();
            ReceiveRight mReceiveRight = new ReceiveRight();
            //频谱数据超时重传
//            if (((System.currentTimeMillis() - Constants.startTime) > 300)) {
            if (Constants.NotFill) {
                Constants.FPGAsession.write(mReceiveWrong);
                Constants.NotFill = false;
                Constants.ctx.reset();
                h++;
                Log.d("abcd", "重传次数：" + h);


            }
        }

        @Override
        public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
            super.exceptionCaught(session, cause);

        }
    }


    private int getYear(byte[] bytes) {
        int year;
        year = ((bytes[9] & 0xff) << 4) + ((bytes[10] >> 4) & 0xff);
        return year;
    }

    private int getMonth(byte[] bytes) {
        int month;
        month = (bytes[10] & 0x0f);
        return month;
    }

    private int getDay(byte[] bytes) {
        int day;
        day = ((bytes[11] >> 3) & 0xff);
        return day;
    }

    private int getHour(byte[] bytes) {
        int hour;
        hour = (((bytes[11] & 0x07) << 2) & 0xff) + ((bytes[12] & 0x03) & 0xff);
        return hour;
    }

    private int getMin(byte[] bytes) {
        int min;
        min = (bytes[12] >> 2) & 0xff;
        return min;
    }

    private int getSecond(byte[] bytes) {
        int second;
        second = (bytes[13]) & 0xff;
        return second;
    }

    private String getFileName(String path) {

        int start = path.lastIndexOf("/");
        int end = path.lastIndexOf(".");
        if (start != -1 && end != -1) {
            return path.substring(start + 1, end);
        } else {
            return null;
        }

    }

}



