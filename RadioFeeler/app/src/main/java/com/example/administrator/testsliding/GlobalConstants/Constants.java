package com.example.administrator.testsliding.GlobalConstants;

import org.apache.mina.core.session.IoSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Created by jinaghao on 15/12/1.
 */
public class Constants {
    //public static IoSession FPGAsession=null;
    public static String PCBIP=null;

    public static  int ID=0;

    public static int PORTValue ;
    public static String IPValue ;

    public static IoSession FPGAsession=null;
    public static IoSession SERVERsession=null;

    public static Queue<List<byte[]>> Queue_RealtimeSpectrum=new LinkedList<>();//实时功率谱数据,供写文件
    public static Queue<float[]> Queue_DrawRealtimeSpectrum=new LinkedList<float[]>();
    public static Queue<float[]> Queue_BackgroundSpectrum=new LinkedList<>();//背景功率谱
    public static Queue<List<byte[]> >Queue_AbnormalFreq=new LinkedList<>();//异常频点数据供写文件
    public static Queue<byte[]> Queue_AbnormalFreq_List=new LinkedList<>();//异常频点数据供列表显示
    public static Queue<List<byte[]>> Queue_IQwave=new LinkedList<>();//IQ波形文件

    public static Queue<byte[]> Queue_SpectrumVSAbnormal=new LinkedList<>();//功率谱和异常频点


    //存储扫频范围参数及对应的起始点
    public static List<SweepRangeInfo> SweepParaList=new ArrayList<>();
    public static int spectrumCount=0;//段数计数器
    public static int IQCount=0;//段数计数器
    public static int BackgroundCount=0;//背景pinpu段数计数器
    public static int AbnormalCount=0;//异常频点计数器


    public static long startTime=0;//频谱数据帧起始时间
    public static boolean NotFill=false;//频谱数据没有收满
    public static boolean Success=false;//频谱数据接收成功
    public static Context ctx;//频谱数据
    public  static  int  sevCount;


}
