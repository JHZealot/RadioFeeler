/**   
 * @Company: Batways
 * @Project:MINAtest 
 * @Title: ConstantValues.java
 * @Package com.example.minatest.gloable
 * @Description: TODO
 * @author victor_freedom (x_freedom_reddevil@126.com)
 * @date 2014-12-5 上午11:38:26
 * @version V1.0   
 */

package com.example.administrator.testsliding.GlobalConstants;



import org.apache.mina.core.session.IoSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @ClassName: ConstantValues
 * @Description: TODO
 * 
 */
public class Constants {
	public static  int ID=5555;
	public static String PCBIP=null;
	public static int PORTValue ;
	public static String IPValue ;

	public static IoSession FPGAsession=null;
	public static IoSession SERVERsession=null;
	public static IoSession FILEsession=null;

	public static Queue<List<byte[]>> Queue_RealtimeSpectrum=new LinkedList<>();//实时功率谱数据,供写文件
	public static Queue<List<float[]>> Queue_DrawRealtimeSpectrum= new LinkedList<>();
	public static Queue<List<float[]>> Queue_DrawRealtimewaterfall= new LinkedList<>();
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


//终端状态地图显示
	public static boolean IsTerminaleOnline=false;
	public static boolean IsTerminaleRegister=false;


	public static long startTime=0;//频谱数据帧起始时间
	public static boolean NotFill=false;//频谱数据没有收满
	public static boolean Success=false;//频谱数据接收成功
	public static Context ctx;//频谱数据
	public  static  int  sevCount;


	public  static  int  judgePower;//功率谱变化判断门限
	public  static  int  selectRate;//抽取倍率
	public  static  boolean IsDrawWaterfall=false;//画瀑布图触发事件
	public  static  boolean IsshowAbnormalList=false;//显示异常频点触发事件

}
