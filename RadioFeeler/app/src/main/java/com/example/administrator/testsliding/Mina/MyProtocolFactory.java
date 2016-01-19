package com.example.administrator.testsliding.Mina;

import com.example.administrator.testsliding.Bean.FixCentralFreq;
import com.example.administrator.testsliding.Bean.FixSetting;
import com.example.administrator.testsliding.Bean.InGain;
import com.example.administrator.testsliding.Bean.OutGain;
import com.example.administrator.testsliding.Bean.Press;
import com.example.administrator.testsliding.Bean.PressSetting;
import com.example.administrator.testsliding.Bean.Query;
import com.example.administrator.testsliding.Bean.ReceiveRight;
import com.example.administrator.testsliding.Bean.ReceiveWrong;
import com.example.administrator.testsliding.Bean.SweepRange;
import com.example.administrator.testsliding.Bean.Threshold;
import com.example.administrator.testsliding.Bean.ToServerPowerSpectrumAndAbnormalPoint;
import com.example.administrator.testsliding.Bean.UploadData;
import com.example.administrator.testsliding.Decode.ConnectDecoder;
import com.example.administrator.testsliding.Decode.FixCentralFreqDecoder;
import com.example.administrator.testsliding.Decode.FixSettingDecoder;
import com.example.administrator.testsliding.Decode.IQwaveDecoder;
import com.example.administrator.testsliding.Decode.InGainDecoder;
import com.example.administrator.testsliding.Decode.OutGainDecoder;
import com.example.administrator.testsliding.Decode.PowerSpectrumAndAbnormalPonitDecoder;
import com.example.administrator.testsliding.Decode.PressDecoder;
import com.example.administrator.testsliding.Decode.PressSettingDecoder;
import com.example.administrator.testsliding.Decode.StationStateDecoder;
import com.example.administrator.testsliding.Decode.SweepRangeDecoder;
import com.example.administrator.testsliding.Decode.ThresholdDecoder;
import com.example.administrator.testsliding.Decode.UploadDataDecoder;
import com.example.administrator.testsliding.Encode.FixCentralFreqEncoder;
import com.example.administrator.testsliding.Encode.FixSettingEncoder;
import com.example.administrator.testsliding.Encode.InGainEncoder;
import com.example.administrator.testsliding.Encode.OutGainEncoder;
import com.example.administrator.testsliding.Encode.PressEncoder;
import com.example.administrator.testsliding.Encode.PressSettingEncoder;
import com.example.administrator.testsliding.Encode.QueryEncoder;
import com.example.administrator.testsliding.Encode.ReceiveRightEncoder;
import com.example.administrator.testsliding.Encode.ReceiveWrongEncoder;
import com.example.administrator.testsliding.Encode.SweepRangeEncoder;
import com.example.administrator.testsliding.Encode.ThresholdEncoder;
import com.example.administrator.testsliding.Encode.ToServerPowerSpectrumAndAbnormalPointEncoder;
import com.example.administrator.testsliding.Encode.UploadDataEncoder;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

/**
 * Created by Administrator on 2015/11/16.
 */
public class MyProtocolFactory extends DemuxingProtocolCodecFactory {
    public MyProtocolFactory() {


        super.addMessageEncoder(InGain.class, InGainEncoder.class);
        super.addMessageEncoder(OutGain.class, OutGainEncoder.class);
        super.addMessageEncoder(Query.class, QueryEncoder.class);
        super.addMessageEncoder(FixSetting.class, FixSettingEncoder.class);
        super.addMessageEncoder(FixCentralFreq.class, FixCentralFreqEncoder.class);
        super.addMessageEncoder(SweepRange.class, SweepRangeEncoder.class);
        super.addMessageEncoder(Threshold.class, ThresholdEncoder.class);
        super.addMessageEncoder(Press.class, PressEncoder.class);
        super.addMessageEncoder(PressSetting.class, PressSettingEncoder.class);
        super.addMessageEncoder(UploadData.class, UploadDataEncoder.class);

        super.addMessageEncoder(ReceiveRight.class, ReceiveRightEncoder.class);
        super.addMessageEncoder(ReceiveWrong.class, ReceiveWrongEncoder.class);

        //上传服务器的功率谱文件编码器
        super.addMessageEncoder(ToServerPowerSpectrumAndAbnormalPoint.class, ToServerPowerSpectrumAndAbnormalPointEncoder.class);


        super.addMessageDecoder(InGainDecoder.class);
        super.addMessageDecoder(OutGainDecoder.class);
        super.addMessageDecoder(FixSettingDecoder.class);
        super.addMessageDecoder(FixCentralFreqDecoder.class);
        super.addMessageDecoder(SweepRangeDecoder.class);
        super.addMessageDecoder(ThresholdDecoder.class);
        super.addMessageDecoder(PressDecoder.class);
        super.addMessageDecoder(PressSettingDecoder.class);
        super.addMessageDecoder(UploadDataDecoder.class);
        super.addMessageDecoder(StationStateDecoder.class);
        super.addMessageDecoder(ConnectDecoder.class);
//        super.addMessageDecoder(PowerSpectrumDecoder.class);
        super.addMessageDecoder(IQwaveDecoder.class);
        super.addMessageDecoder(PowerSpectrumAndAbnormalPonitDecoder.class);//功率谱异常频点

//        super.addMessageDecoder(AbnormalFreqDecoder.class);




    }
}
