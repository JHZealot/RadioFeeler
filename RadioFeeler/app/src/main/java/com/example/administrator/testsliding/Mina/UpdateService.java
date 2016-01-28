/**
 * @Company: Batways
 * @Project:Tnt
 * @Title: UpdateService.java
 * @Package com.batways.tnt.service
 * @Description: TODO
 * @author victor_freedom (x_freedom_reddevil@126.com)
 * @date 2014年9月6日 下午2:16:08
 * @version V1.0
 */

package com.example.administrator.testsliding.Mina;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import com.example.administrator.testsliding.GlobalConstants.Constants;
import com.example.administrator.testsliding.compute.ComputePara;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;

//import com.example.administrator.testsliding.bean2Transmit.FPGA2server.Reply_abnormalPoint;


/**
 * @ClassName: UpdateService
 * @Description: TODO
 */
public class UpdateService extends Service {

    private String IP = "27.17.8.142";
    private int PORT = 8888;
    private DataHandler dataHandler;
    private IoSession session;
    private String TAG = "UpdateService";
   // private ComputeParaInService compute=new ComputeParaInService();
    private ComputePara computePara=new ComputePara();


    private BroadcastReceiver sendMessage = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                String action = intent.getAction();
                Log.d("action",action);

                switch(action){
//                    case ConstantValues.REQUSTNETWORK:
//                        RequstNetwork net = intent.getParcelableExtra("network");
//                        session.write(net);
//                        break;
//                    case ConstantValues.MAPRADIO:
//                        MapRadio radio=intent.getParcelableExtra("map_radio");
//                        session.write(radio);
//                        break;
//                    case ConstantValues.MAPROUTE:
//                        MapRoute route=intent.getParcelableExtra("map_route");
//                        session.write(route);
//                        break;
//                    case ConstantValues.ABNORMAL_LOCATION:
//                        LocationAbnormalRequest locate = intent.getParcelableExtra("abnormal_location");
//                        session.write(locate);
//                        break;
//                    case ConstantValues.STATION_REGISTER:
//                        Station_RegisterRequst reg = intent.getParcelableExtra("station_register");
//                        session.write(reg);
//                        break;
//                    case ConstantValues.STATION_CURRENT:
//                        Station_CurrentRequst cur = intent.getParcelableExtra("station_current");
//                        session.write(cur);
//                        break;
//
//                    case ConstantValues.WIRLESSPLAN:
//                        Send_ServiceRadio data = intent.getParcelableExtra("wirlessplan");
//                        session.write(data);
//                        break;
//                    case ConstantValues.TERMINAL_ALL:
//                        TerminalAttributes_All ter = intent.getParcelableExtra("terminal_all");
//                        session.write(ter);
//                        break;
//                    case ConstantValues.TERMINAL_ONLINE:
//                        Terminal_Online online = intent.getParcelableExtra("terminal_online");
//                        session.write(online);
//                        break;
//                    case ConstantValues.TERMINAL_REGISTER:
//                        Terminal_Register register = intent.getParcelableExtra("terminal_register");
//                        session.write(register);
//                        break;
//                    case ConstantValues.SERVICE_SPECTRUM:
//                        HistorySpectrumRequest spec = intent.getParcelableExtra("service_spectrum");
//                        session.write(spec);
//                        break;
//                    case ConstantValues.SERVICE_IQ:
//                        HistoryIQRequest IQ = intent.getParcelableExtra("service_IQ");
//                        session.write(IQ);
//                        break;
//                    case ConstantValues.INTERACTION_WORKMODEL01:
//                        InteractionSweepModeRequest sweep = intent.getParcelableExtra("interaction_workmodel01");
//                        session.write(sweep);
//                        break;
//                    case ConstantValues.INTERACTION_WORKMODEL02:
//                        InteractionFixmodeRequest fix = intent.getParcelableExtra("interaction_workmodel02");
//                        session.write(fix);
//                        break;
//                    case ConstantValues.INTERACTION_WORKMODEL03:
//                        InteractionPressmodeRequest press = intent.getParcelableExtra("interaction_workmodel03");
//                        session.write(press);
//                        break;
//                    case ConstantValues.MODIFYINGAIN:
//                        ModifyInGain modify = intent.getParcelableExtra("modifyIngain");
//                        session.write(modify);
//                        break;
//                    case ConstantValues.MODIFYANTENNA:
//                        ModifyAntenna antenna = intent.getParcelableExtra("modifyAntenna");
//                        session.write(antenna);
//                        break;

                }

            } catch (Exception e) {

            //    Toast.makeText(getBaseContext(), "请检查网络", Toast.LENGTH_LONG).show();
            }

//
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        IntentFilter filter = new IntentFilter();
//        filter.addAction(ConstantValues.REQUSTNETWORK);
//        filter.addAction(ConstantValues.MAPRADIO);
//        filter.addAction(ConstantValues.MAPROUTE);
//        filter.addAction(ConstantValues.ABNORMAL_LOCATION);
//        filter.addAction(ConstantValues.STATION_REGISTER);
//        filter.addAction(ConstantValues.STATION_CURRENT);
//        filter.addAction(ConstantValues.WIRLESSPLAN);
//        filter.addAction(ConstantValues.SERVICE_IQ);
//        filter.addAction(ConstantValues.SERVICE_SPECTRUM);
//
//        filter.addAction(ConstantValues.INTERACTION_WORKMODEL01);
//        filter.addAction(ConstantValues.INTERACTION_WORKMODEL02);
//        filter.addAction(ConstantValues.INTERACTION_WORKMODEL03);
//
//        filter.addAction(ConstantValues.TERMINAL_ALL);
//        filter.addAction(ConstantValues.TERMINAL_ONLINE);
//        filter.addAction(ConstantValues.TERMINAL_REGISTER);
//        filter.addAction(ConstantValues.MODIFYINGAIN);
//        filter.addAction(ConstantValues.MODIFYANTENNA);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        dataHandler = new DataHandler();
        registerReceiver(sendMessage, filter);
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    IoConnector connector = new NioSocketConnector();
                    connector.getFilterChain().addLast(
                            "codec",
                            new ProtocolCodecFilter(new ToServerProtocolCodecFactory()));

                    connector.getSessionConfig().setReadBufferSize(1024);
                    connector.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,
                            10);

                    connector.setHandler(dataHandler);
                    // 这里是异步操作 连接后立即返回"
			ConnectFuture future = connector.connect(new InetSocketAddress(
						"27.17.8.142",9988));
//                    ConnectFuture future = connector.connect(new InetSocketAddress(
//                            Constant.IPValue, Constant.PORTValue));
                    future.awaitUninterruptibly();// 等待连接创建完成
                    session = future.getSession();
                    Constants.SERVERsession=session;

                    session.getCloseFuture().awaitUninterruptibly();// 等待连接断开
                    connector.dispose();
                } catch (Exception e) {
                    Log.d("abcd","lianjieshibai");

                }
            }
        }).start();

        super.onCreate();
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(sendMessage);
        sendMessage = null;
        super.onDestroy();
    }

    private class DataHandler extends IoHandlerAdapter {

        @Override
        public void sessionCreated(IoSession session) throws Exception {
        }

        @Override
        public void sessionOpened(IoSession session) throws Exception {

        }

        @Override
        public void sessionClosed(IoSession session) throws Exception {
        }

        @Override
        public void sessionIdle(IoSession session, IdleStatus status)
                throws Exception {
        }

        @Override
        public void exceptionCaught(IoSession session, Throwable cause)
                throws Exception {
            cause.printStackTrace();
        }

        @Override
        public void messageReceived(IoSession session, Object message)
                throws Exception {
            //处理从服务端接收到的消息
//            if(message instanceof MapRadioResult){
//                MapRadioResult map= (MapRadioResult) message;
//                BroadcastHelper.sendBroadCast(getBaseContext(),
//                        ConstantValues.RMAPRADIO,"map_radio",map);
//            }
//            if(message instanceof MapRouteResult){
//                MapRouteResult map= (MapRouteResult) message;
//                BroadcastHelper.sendBroadCast(getBaseContext(),
//                        ConstantValues.RMAPROUTE,"map_route",map);
//            }
//            if (message instanceof File_ServiceRadio) {
//                ArrayList<ListMap> listItems = new ArrayList<>();
//                File_ServiceRadio radio = new File_ServiceRadio();
//                radio = (File_ServiceRadio) message;
//                listItems.clear();
//                listItems = compute.Radio2ListItem(radio);
//                BroadcastHelper.sendBroadCastRadioList(getBaseContext(),
//                        ConstantValues.RWIRLESSPLAN, "wirlessplan", listItems);
//            }
//
//            if (message instanceof File_TerminalAll) {
//                File_TerminalAll all = new File_TerminalAll();
//                ArrayList<List_TerminalAll> list_terminalAlls = new ArrayList<>();
//                all = (File_TerminalAll) message;
//                list_terminalAlls.clear();
//                list_terminalAlls = compute.TerminalALL2ListItem(all);
//                BroadcastHelper.sendBroadCastTerminalAllList(getBaseContext(),
//                        ConstantValues.RTERMINAL_ALL, "terminal_all", list_terminalAlls);
//            }
//
//
//            if (message instanceof File_TerminalOnline) {
//                File_TerminalOnline online = new File_TerminalOnline();
//                ArrayList<List_TerminalOnline> list_terminalOnlines = new ArrayList<>();
//                online = (File_TerminalOnline) message;
//                list_terminalOnlines.clear();
//                list_terminalOnlines = compute.TerminalOnline2ListItem(online);
//                BroadcastHelper.sendBroadCastTerminalOnlineList(getBaseContext(),
//                        ConstantValues.RTERMINAL_ONLINE, "terminal_online", list_terminalOnlines);
//            }
//
//
//            if (message instanceof File_TerminalOnline) {
//                File_TerminalRegister register = new File_TerminalRegister();
//                //与在线终端的表一样
//                ArrayList<List_TerminalOnline> list_terminalRegister = new ArrayList<>();
//                register = (File_TerminalRegister) message;
//                list_terminalRegister.clear();
//                list_terminalRegister = compute.TerminalRegister2ListItem(register);
//                BroadcastHelper.sendBroadCast(getBaseContext(),
//                        ConstantValues.RTERMINAL_REGISTER, "terminal_register", register);
//            }
//            //==============================================================================
//
//            if (message instanceof File_StationRegister) {
//                File_StationRegister register1 = new File_StationRegister();
//                //与全部台站属性的列表一样
//                ArrayList<List_TerminalAll> list_StayionRegister = new ArrayList<>();
//                register1 = (File_StationRegister) message;
//                list_StayionRegister.clear();
//                list_StayionRegister = compute.TerminalALL2ListItem(register1);
//                BroadcastHelper.sendBroadCastTerminalAllList(getBaseContext(),
//                        ConstantValues.RSTATION_REGISTER, "station_register", list_StayionRegister);
//            }
//
//            //=======================================================================
//
//            if (message instanceof StationCurrentReply) {
//                StationCurrentReply reply = new StationCurrentReply();
//                reply = (StationCurrentReply) message;
//                BroadcastHelper.sendBroadCast(getBaseContext(),
//                        ConstantValues.RSTATION_CURRENT, "station_current", reply);
//            }
//
//            //=======================================================================
//
//            if (message instanceof LocationAbnormalReply) {
//                LocationAbnormalReply abnormal = new LocationAbnormalReply();
//                abnormal = (LocationAbnormalReply) message;
//                BroadcastHelper.sendBroadCast(getBaseContext(),
//                        ConstantValues.RABNORMAL_LOCATION, "abnormal_location", abnormal);
//            }
//            //========================增益表==============================
//            if (message instanceof File_ModifyIngain) {
//                File_ModifyIngain modify = (File_ModifyIngain) message;
//                byte[] bytes=modify.getFileContent();
//                if(bytes[0]==0x55){
//                    ModifyIngainView view=compute.File2InGain(modify);
//                    BroadcastHelper.sendBroadCast(getBaseContext(),
//                            ConstantValues.RMODIFYINGAIN, "modifyIngian", view);
//                }
//               // Constant.FPGAsession.write(modify);
//            }
//            if (message instanceof File_ModifyAntenna) {
//                File_ModifyAntenna modify = (File_ModifyAntenna) message;
//                byte[] bytes=modify.getFileContent();
//                if(bytes[0]==0x55){
//                    //表相同
//                    ModifyIngainView view=compute.File2Antenna(modify);
//                    BroadcastHelper.sendBroadCast(getBaseContext(),
//                            ConstantValues.RMODIFYANTENNA, "modifyAntenna", view);
//                }
//                //Constant.FPGAsession.write(modify);
//            }
//            //==============================================================================
////=============================================数据转发(设置指令)=============================================
//
//            if (message instanceof Simple_Connect) {
//                Simple_Connect connect = new Simple_Connect();
//                connect = (Simple_Connect) message;
//               // Constant.FPGAsession.write(connect);
//                Log.d("xyz", Arrays.toString(connect.getContent()));
//            }
//
//            //================================================
//
//            if (message instanceof Simple_FixCentralFreq) {
//                Simple_FixCentralFreq fixCentral = new Simple_FixCentralFreq();
//                fixCentral = (Simple_FixCentralFreq) message;
//                Constant.FPGAsession.write(fixCentral);
//            }
//            /////////////////////////////////
//            if (message instanceof Simple_FixSetting) {
//                Simple_FixSetting fixSetting = new Simple_FixSetting();
//                fixSetting = (Simple_FixSetting) message;
//                Constant.FPGAsession.write(fixSetting);
//            }
//
//            ///////////////////////////////////////////////////
//
//            if (message instanceof Simple_InGain) {
//                Simple_InGain inGain = new Simple_InGain();
//                inGain = (Simple_InGain) message;
//                //Constant.FPGAsession.write(inGain);
//            }
//            /////////////////////////////////////////////////////
//            if (message instanceof Simple_OutGain) {
//                Simple_OutGain outGain = new Simple_OutGain();
//                outGain = (Simple_OutGain) message;
//               // Constant.FPGAsession.write(outGain);
//            }
//            //////////////////////////////////////
//            if (message instanceof Simple_Press) {
//                Simple_Press press = new Simple_Press();
//                press = (Simple_Press) message;
//               // Constant.FPGAsession.write(press);
//            }
//            ///////////////////////////////////////////
//
//            if (message instanceof Simple_PressSetting) {
//                Simple_PressSetting pressSetting = new Simple_PressSetting();
//                pressSetting = (Simple_PressSetting) message;
//              //  Constant.FPGAsession.write(pressSetting);
//            }
//            ////////////////////////////////////////////////
//
//            if (message instanceof Simple_StationState) {
//                Simple_StationState simple_stationState = new Simple_StationState();
//                simple_stationState = (Simple_StationState) message;
//               // Constant.FPGAsession.write(simple_stationState);
//            }
//            ///////////////////////////////////////////////////
//
//            if (message instanceof Simple_SweepRange) {
//                Simple_SweepRange sweep = new Simple_SweepRange();
//                sweep = (Simple_SweepRange) message;
//               // Constant.FPGAsession.write(sweep);
//            }
//            ////////////////////////////////////////////////
//
//            if (message instanceof Simple_Threshold) {
//                Simple_Threshold threshold = new Simple_Threshold();
//                threshold = (Simple_Threshold) message;
//               // Constant.FPGAsession.write(threshold);
//            }
//
//            ////////////////////////////////////////////////
//
//            if (message instanceof Simple_UploadDataStart) {
//                Simple_UploadDataStart data = new Simple_UploadDataStart();
//                data = (Simple_UploadDataStart) message;
//               // Constant.FPGAsession.write(data);
//            }
//            /////////////////////////////////////////////////////
//
//            if (message instanceof Simple_UploadDataEnd) {
//                Simple_UploadDataEnd dataend = new Simple_UploadDataEnd();
//                dataend = (Simple_UploadDataEnd) message;
//               // Constant.FPGAsession.write(dataend);
//            }
//
//            //======================================数据转发（查询）===================
//
//            if (message instanceof Query_Connect) {
//                Query_Connect query_connect = new Query_Connect();
//                query_connect = (Query_Connect) message;
//               // Constant.FPGAsession.write(query_connect);
//            }
//
//            ////////////////////////////
//
//            if (message instanceof Query_FixCentralFreq) {
//                Query_FixCentralFreq query_fixCentralFreq = new Query_FixCentralFreq();
//                query_fixCentralFreq = (Query_FixCentralFreq) message;
//                //Constant.FPGAsession.write(query_fixCentralFreq);
//            }
//            //////////////////////////
//
//            if (message instanceof Query_FixSetting) {
//                Query_FixSetting query_fixSetting = new Query_FixSetting();
//                query_fixSetting = (Query_FixSetting) message;
//               // Constant.FPGAsession.write(query_fixSetting);
//            }
//
//            ///////////////////
//
//            if (message instanceof Query_InGain) {
//                Query_InGain query_inGain = new Query_InGain();
//                query_inGain = (Query_InGain) message;
//               // Constant.FPGAsession.write(query_inGain);
//            }
//
//            ///////////////////////////////////
//
//            if (message instanceof Query_OutGain) {
//                Query_OutGain query_outGain = new Query_OutGain();
//                query_outGain = (Query_OutGain) message;
//               // Constant.FPGAsession.write(query_outGain);
//            }
//
//            ///////////////////////////////////////
//
//            if (message instanceof Query_IsTerminalOnline) {
//                Query_IsTerminalOnline query_isTerminalOnline = new Query_IsTerminalOnline();
//                query_isTerminalOnline = (Query_IsTerminalOnline) message;
//               // Constant.FPGAsession.write(query_isTerminalOnline);
//            }
//            //////////////////////////////////////////
//
//            if (message instanceof Query_Press) {
//                Query_Press query_press = new Query_Press();
//                query_press = (Query_Press) message;
//               // Constant.FPGAsession.write(query_press);
//            }
//            /////////////////////////////////////////////////////
//
//            if (message instanceof Query_PressSetting) {
//                Query_PressSetting query_pressSetting = new Query_PressSetting();
//                query_pressSetting = (Query_PressSetting) message;
//                //Constant.FPGAsession.write(query_pressSetting);
//            }
//
//            //////////////////////////////////////////
//
//            if (message instanceof Query_SweepRange) {
//                Query_SweepRange query_sweepRange = new Query_SweepRange();
//                query_sweepRange = (Query_SweepRange) message;
//                Constant.FPGAsession.write(query_sweepRange);
//            }
//
//            /////////////////////////////////////////
//
//            if (message instanceof Query_Threshold) {
//                Query_Threshold query_threshold = new Query_Threshold();
//                query_threshold = (Query_Threshold) message;
//               // Constant.FPGAsession.write(query_threshold);
//            }
//
//            ////////////////////////////////////////////
//
//            if (message instanceof Query_UploadDataStart) {
//                Query_UploadDataStart uploadData = new Query_UploadDataStart();
//                uploadData = (Query_UploadDataStart) message;
//               // Constant.FPGAsession.write(uploadData);
//            }
//            ////////////////////////////////////////////
//
//            if (message instanceof Query_UploadDataEnd) {
//                Query_UploadDataEnd query_uploadDataEnd = new Query_UploadDataEnd();
//                query_uploadDataEnd = (Query_UploadDataEnd) message;
//               // Constant.FPGAsession.write(query_uploadDataEnd);
//            }
//
//
//            /////////业务数据上传
//            Reply_Spectrum spectrum = new Reply_Spectrum();
//            if (message instanceof Reply_Spectrum) {
//                spectrum = (Reply_Spectrum) message;
//            }
//            if (spectrum != null) {
//                byte[] byte1 = null;
//                byte[] b3 = null;
//                if (spectrum.getFunctionID() == 0x0D) {//区分功率谱数据类型
//                    //判断是否为起始段
//                    if (Constant.spectrumCount == 0 && Constant.SweepParaList.get(0).getStartNum() == spectrum.getBandNum()) {
//                        Constant.spectrumCount++;
//                        //入供写文件的队列
//                        byte1 = new byte[1554];//
//                        byte[] b1 = spectrum.getLocation();
//                        System.arraycopy(b1, 0, byte1, 0, 14);//填入位置和时间
//                        byte[] b2 = spectrum.getSweepModel2bandNum();
//                        System.arraycopy(b2, 0, byte1, 14, 3);//填入扫频模式，文件上传模式的信息
//                        byte1[17] = (byte) spectrum.getBandNum();//输入段序号
//                        b3 = spectrum.getPower();
//                        System.arraycopy(b3, 0, byte1, 18, 1536);//填入功率谱值
//                        Constant.Queue_RealtimeSpectrum.offer(byte1);
//                    } else {
//                        if (Constant.spectrumCount == 0 && Constant.SweepParaList.get(0).getStartNum() != spectrum.getBandNum()) {
//                            //若第一段没收到
//                            int need = spectrum.getBandNum() - Constant.SweepParaList.get(0).getStartNum();//计算缺几段
//                            Constant.spectrumCount = need + 1;
//                            byte1 = new byte[1554];
//                            byte[] b1 = spectrum.getLocation();
//                            System.arraycopy(b1, 0, byte1, 0, 14);//填入位置
//                            byte[] b2 = spectrum.getSweepModel2bandNum();
//                            System.arraycopy(b2, 0, byte1, 14, 3);//填入扫频模式，文件上传模式的信息
//                            byte1[17] = (byte) Constant.SweepParaList.get(0).getStartNum();
//                            Constant.Queue_RealtimeSpectrum.offer(byte1);
//
//                            if (need > 1) {//缺多段
//                                byte[] byte2 = new byte[1537];
//                                for (int i = 0; i < need - 1; i++) {
//                                    byte2[0] = (byte) (Constant.SweepParaList.get(0).getStartNum() + i + 1);
//                                    Constant.Queue_RealtimeSpectrum.offer(byte2);
//                                }
//                                //存入画图
//                                float[] pow = new float[1025];
//                                for (int i = 0; i < need; i++) {
//                                    pow[0] = Constant.SweepParaList.get(0).getStartNum() + i;
//                                    Constant.Queue_DrawRealtimeSpectrum.offer(pow);
//                                }
//
//                            }
//                        } else if ((Constant.SweepParaList.get(0).getStartNum() + Constant.spectrumCount) != spectrum.getBandNum() &&
//                                Constant.SweepParaList.get(0).getEngNum() != spectrum.getBandNum()) {
//                            //没到段尾，中间缺
//                            int need = spectrum.getBandNum() - (Constant.SweepParaList.get(0).getStartNum() + Constant.spectrumCount);//计算缺几段
//
//                            byte[] byte2 = new byte[1537];
//                            float[] pow = new float[1025];//存入画图
//                            for (int i = 0; i < need; i++) {
//                                byte2[0] = (byte) (Constant.SweepParaList.get(0).getStartNum() + Constant.spectrumCount + i);
//                                pow[0] = Constant.SweepParaList.get(0).getStartNum() + Constant.spectrumCount + i;
//                                Constant.Queue_RealtimeSpectrum.offer(byte2);
//                                Constant.Queue_DrawRealtimeSpectrum.offer(pow);//存入画图
//                            }
//
//                            Constant.spectrumCount = need + 1 + Constant.spectrumCount;
//
//                        } else if (Constant.SweepParaList.get(0).getEngNum() == spectrum.getBandNum()) {
//                            //结束
//                            Constant.spectrumCount = 0;
//
//                        } else if ((Constant.SweepParaList.get(0).getStartNum() + Constant.spectrumCount) == spectrum.getBandNum()) {
//                            //中间正常
//                            Constant.spectrumCount++;
//                        }
//                        byte[] byte2 = new byte[1537];
//                        byte2[0] = (byte) spectrum.getBandNum();
//                        b3 = spectrum.getPower();
//                        System.arraycopy(b3, 0, byte2, 1, 1536);//填入功率谱值
//                        Constant.Queue_RealtimeSpectrum.offer(byte2);
//
//                    }
//                    //存入画图
//                    float[] pow = new float[1025];
//                    pow[0] = spectrum.getBandNum();//输入段序号
//                    float[] f1 = computePara.Bytes2Power(b3);
//                    System.arraycopy(f1, 0, pow, 1, 1024);//填入功率谱值
//                    Constant.Queue_DrawRealtimeSpectrum.offer(pow);
//
//
//                } else {//背景功率谱
//                    //判断是否为起始段
//                    if (Constant.BackgroundCount == 0 && Constant.SweepParaList.get(0).getStartNum() == spectrum.getBandNum()) {
//                        Constant.spectrumCount++;
//                    } else {
//                        if ((Constant.SweepParaList.get(0).getStartNum() + Constant.spectrumCount) != spectrum.getBandNum() &&
//                                Constant.SweepParaList.get(0).getEngNum() != spectrum.getBandNum()) {
//                            //中间缺，段头缺，没到段尾
//                            int need = spectrum.getBandNum() - (Constant.SweepParaList.get(0).getStartNum() + Constant.spectrumCount);
//                            //存入画图
//                            float[] pow = new float[1025];
//                            for (int i = 0; i < need; i++) {
//                                pow[0] = Constant.SweepParaList.get(0).getStartNum() + Constant.spectrumCount + i;
//                                Constant.Queue_BackgroundSpectrum.offer(pow);
//                            }
//                            Constant.spectrumCount = need + 1 + Constant.spectrumCount;
//                        } else if (Constant.SweepParaList.get(0).getEngNum() == spectrum.getBandNum()) {
//                            //段尾结束
//                            Constant.spectrumCount = 0;
//                        } else if ((Constant.SweepParaList.get(0).getStartNum() + Constant.spectrumCount) == spectrum.getBandNum()) {
//                            //中间正常
//                            Constant.spectrumCount++;
//                        }
//
//                    }
//                    //存入画图
//                    float[] pow = new float[1025];
//                    pow[0] = spectrum.getBandNum();//输入段序号
//                    float[] f1 = computePara.Bytes2Power(b3);
//                    System.arraycopy(f1, 0, pow, 1, 1024);//填入功率谱值
//                    Constant.Queue_BackgroundSpectrum.offer(pow);
//
//                }
//            }
//
//            ///////////////////////异常频点
//            Reply_abnormalPoint point = new Reply_abnormalPoint();
//            if (message instanceof Reply_abnormalPoint) {
//                point = (Reply_abnormalPoint) message;
//            }
//            if (point != null) {
//                ////////////////存入显示列表
//                int length = point.getNum() * 3;
//                byte[] abnormalList = new byte[length + 1];
//                abnormalList[0] = (byte) point.getBandNum();
//                byte[] allPow = point.getPower();
//                System.arraycopy(allPow, 0, abnormalList, 1, length);
//                Constant.Queue_AbnormalFreq_List.offer(abnormalList);
//                //==================================================================================
//                //判断是否为起始段
//                if (Constant.AbnormalCount == 0 && Constant.SweepParaList.get(0).getStartNum() == point.getBandNum()) {
//                    Constant.AbnormalCount++;
//                } else {
//                    if ((Constant.SweepParaList.get(0).getStartNum() + Constant.AbnormalCount) != point.getBandNum() &&
//                            Constant.SweepParaList.get(0).getEngNum() != point.getBandNum()) {
//                        //中间缺，段头缺，没到段尾
//                        int need = point.getBandNum() - (Constant.SweepParaList.get(0).getStartNum() + Constant.AbnormalCount);
//                        //存入供写文件
//                        byte[] bytes = new byte[32];
//                        for (int i = 0; i < need; i++) {//存入缺的，频点个数为0；
//                            bytes[0] = (byte) (Constant.SweepParaList.get(0).getStartNum() + Constant.AbnormalCount + i);
//                            Constant.Queue_AbnormalFreq.offer(bytes);
//                        }
//                        Constant.AbnormalCount = need + 1 + Constant.AbnormalCount;
//                    } else if ((Constant.SweepParaList.get(0).getStartNum() + Constant.AbnormalCount) == point.getBandNum()) {
//                        //中间正常
//                        Constant.AbnormalCount++;
//                    }
//                }
//                byte[] bytes = new byte[32];
//                bytes[0] = (byte) point.getBandNum();
//                bytes[1] = (byte) point.getNum();
//                byte[] b1 = point.getPower();
//                System.arraycopy(b1, 0, bytes, 2, 30);
//                Constant.Queue_AbnormalFreq.offer(bytes);
//
//                if (Constant.SweepParaList.get(0).getEngNum() == point.getBandNum()) {
//                    //段尾结束
//                    Constant.AbnormalCount = 0;
//                    int sweepNum = 0, count = 0;//总段数
//                    // int year,month,day,hour,min,miu;//取出时间命名
//                    FileOutputStream fos=null;
//                    String name=null;
////
//                    while (count <= sweepNum) {
//                        count++;
//                        if (!Constant.Queue_RealtimeSpectrum.isEmpty()) {
//                            byte[] realTime = Constant.Queue_RealtimeSpectrum.poll();
//                            if (realTime.length == 1554) {//判断是否为第一帧
//                                sweepNum = realTime[15];//取出总段数
//                                //取时间
//                                int year = ((realTime[9] & 0xff) << 4) + ((realTime[10] >> 4) & 0xff);
//                                int month = realTime[10] & 0x0f;
//                                int day = ((realTime[11] >> 3) & 0xff);
//                                int hour = (((realTime[11] & 0x07) << 2) + realTime[12] & 0x03) & 0xff;
//                                int min = ((realTime[12] >> 2) & 0xff);
//                                int mius = (realTime[13] & 0xff);
//                                //创建文件
//                                 name=String.format("%d-%d-%d-%d-%d-%d-%d.%s", year, month, day, hour, min, mius,
//                                         Constant.ID, "pwr");
//                               fos = openFileOutput(name, MODE_APPEND);
//                                byte[] location=new byte[9];
//                                System.arraycopy(realTime,0,location,0,9);
//                                byte[] power=new byte[1539];
//                                System.arraycopy(realTime, 14, power, 0, 1539);
//                                fos.write(0x00);//写入开始标示符
//                                fos.write(location);
//                                fos.write(power);
//                                //写文件开头第一帧
//                            } else {
//                                //写入数据
//                                fos.write(realTime);
//
//                            }
//                        }
//
//                    }
//
//                    count = 0;
//                    //写入数据
//                    fos.write((byte)0xff);//写入间隔符
//
//                    while (count <= sweepNum) {
//                        count++;
//                        if (!Constant.Queue_AbnormalFreq.isEmpty()) {
//                            //文件追加异常频点信息
//                            byte[] abnormalpoint=Constant.Queue_AbnormalFreq.poll();
//                            fos.write(abnormalpoint);
//                        }
//                    }
//                    fos.write((byte)0x00);//写文件结束标识符
//                    fos.close();
//                    name=null;
//
//
//                }
                //=======================================================================================================
            //}
        }

        @Override
        public void messageSent(IoSession session, Object message)
                throws Exception {

        }
    }


}
