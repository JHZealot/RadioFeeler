package com.example.administrator.testsliding.mina_transmit.server2FPGAEncoder;

import com.example.administrator.testsliding.bean2Transmit.server2FPGAQuery.Query_InGain;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

/**
 * Created by Administrator on 2015/12/1.
 */
public class Query_InGainEncoder implements MessageEncoder<Query_InGain> {
    @Override
    public void encode(IoSession ioSession, Query_InGain gain,
                       ProtocolEncoderOutput out) throws Exception {

        if(gain!=null){
            IoBuffer buffer=IoBuffer.allocate(7,true);
            byte[] bytes=gain.getContent();
            buffer.put(bytes);
            buffer.flip();
            out.write(buffer);
        }

    }
}
