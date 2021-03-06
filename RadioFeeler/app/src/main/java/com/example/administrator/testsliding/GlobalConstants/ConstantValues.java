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

/**
 * @ClassName: ConstantValues
 * @Description: TODO
 * 
 */
public interface ConstantValues {

	public String REQUSTNETWORK="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.REQUSTNETWORK";//申请入网

	public String MAPRADIO="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.MAPRADIO";//电磁分布态势
	public String MAPROUTE="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.MAPROUTE";//电磁路径分布

	public String ABNORMAL_LOCATION="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.ABNORMAL_LOCATION";//异常频点定位

    public String STATION_REGISTER="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.STATION_REGISTER";//登记台站属性
	public String STATION_CURRENT="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.STATION_CURRENT";//登记台站当前属性

	public String WIRLESSPLAN= "COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.WIRLESSPLAN";//国家无线电
	public String SERVICE_IQ="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.SERVICE_IQ";//历史IQ波
	public String SERVICE_SPECTRUM="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.SERVICE_SPECTRUM";//历史功率谱
	public String TERMINAL_ALL="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.TERMINAL_ALL";//全部台站记录属性
	public String TERMINAL_ONLINE="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.TERMINAL_ONLINE";//所有在网终端
	public String TERMINAL_REGISTER="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.TERMINAL_REGISTER";//所有注册终端

	public String INTERACTION_WORKMODEL01="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.INTERACTION_WORKMODEL01";//站点互动扫频模式
	public String INTERACTION_WORKMODEL02="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.INTERACTION_WORKMODEL02";//站点互动定频模式
	public String INTERACTION_WORKMODEL03="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.INTERACTION_WORKMODEL03";//站点互动压制模式
	public String MODIFYINGAIN="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.MODIFYINGAIN";//接收通道增益修正表
	public String MODIFYANTENNA="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.MODIFYANTENNA";//天线增益修正表

	//======================================回复===================
	public String RREQUSTNETWORK="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.RREQUSTNETWORK";//申请入网
	public String RMAPRADIO="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.RMAPRADIO";//电磁分布态势
	public String RMAPROUTE="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.RMAPROUTE";//电磁路径分布

	public String RABNORMAL_LOCATION="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.RABNORMAL_LOCATION";//异常频点定位

	public String RSTATION_REGISTER="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.RSTATION_REGISTER";//登记台站属性
	public String RSTATION_CURRENT="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.RSTATION_CURRENT";//登记台站当前属性

	public String RWIRLESSPLAN= "COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.RWIRLESSPLAN";//国家无线电
	public String RSERVICE_IQ="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.RSERVICE_IQ";//历史IQ波

	public String RTERMINAL_ALL="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.RTERMINAL_ALL";//全部台站记录属性
	public String RTERMINAL_ONLINE="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.RTERMINAL_ONLINE";//所有在网终端
	public String RTERMINAL_REGISTER="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.RTERMINAL_REGISTER";//所有注册终端

	public String RINTERACTION_WORKMODEL01="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.INTERACTION_WORKMODEL01";//站点互动扫频模式
	public String RINTERACTION_WORKMODEL02="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.INTERACTION_WORKMODEL02";//站点互动定频模式
	public String RINTERACTION_WORKMODEL03="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.INTERACTION_WORKMODEL03";//站点互动压制模式
	public String RMODIFYINGAIN="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.RMODIFYINGAIN";//接收通道增益修正表
	public String RMODIFYANTENNA="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.RMODIFYANTENNA";//天线增益修正表

	//================================转发的变量===============================
	public String CONNECT="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.CONNECT";
	public String FixCentralFreq="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.FixCentralFreq";
	public String FixSetting="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.FixSetting";
	public String InGain ="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.InGain";
	public String OutGain="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.OutGain";
	public String Press="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.Press";
	public String PressSetting="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.PressSetting";
	public String Query="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.Query";
	public String StationState="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.FinalStationState";
	public String SweepRange="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.SweepRange";
	public String Threshold="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.Threshold";
	public String UploadData="COM.EXAMPLE.ADMINISTRATOR.TESTSLIDING.UploadData";


	//手机与FPGA
	public String SweepRangeQuery= "COM.EXAMPLE.SweepRange";
	public String SweepRangeSet= "COM.EXAMPLE.SweepRangeSet";

	public String InGainQuery= "COM.EXAMPLE.InGainQuery";
	public String InGainSet= "COM.EXAMPLE.InGainSet";

	public String OutGainSet= "COM.EXAMPLE.OutGainSet";
	public String OutGainQuery= "COM.EXAMPLE.OutGainQuery";

	public String ThresholdSet= "COM.EXAMPLE.ThresholdSet";
	public String ThresholdQuery= "COM.EXAMPLE.ThresholdQuery";

	public String FixCentralFreqSet= "COM.EXAMPLE.FixCentralFreqSet";
	public String FixCentralFreqQuery= "COM.EXAMPLE.FixCentralFreqQuery";

	public String FixSettingSet= "COM.EXAMPLE.FixSettingSet";
	public String FixSettingQuery= "COM.EXAMPLE.FixSettingQuery";

	public String PressSet= "COM.EXAMPLE.PressSet";
	public String PressQuery= "COM.EXAMPLE.PressQuery";

	public String PressSettingSet= "COM.EXAMPLE.PressSettingSet";
	public String PressSettingQuery= "COM.EXAMPLE.PressSettingQuery";

	public String StationStateQuery= "COM.EXAMPLE.StationStateQuery";

	public String uploadDataSet= "COM.EXAMPLE.uploadDataSet";
	public String uploadQuery= "COM.EXAMPLE.uploadQuery";

	public String ConnectPCBQuery= "COM.EXAMPLE.ConnectPCBQuery";
}
