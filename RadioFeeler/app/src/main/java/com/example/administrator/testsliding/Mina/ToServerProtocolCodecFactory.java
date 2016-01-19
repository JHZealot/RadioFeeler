package com.example.administrator.testsliding.Mina;

import com.example.administrator.testsliding.Bean.ToServerPowerSpectrumAndAbnormalPoint;
import com.example.administrator.testsliding.Encode.ToServerPowerSpectrumAndAbnormalPointEncoder;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;


/**
 * Created by jinaghao on 16/1/13.
 */
public class ToServerProtocolCodecFactory extends DemuxingProtocolCodecFactory  {

    public ToServerProtocolCodecFactory(){
        super.addMessageEncoder(ToServerPowerSpectrumAndAbnormalPoint.class, ToServerPowerSpectrumAndAbnormalPointEncoder.class);

    }
}
