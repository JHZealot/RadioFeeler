package com.example.administrator.testsliding.mina_transmit.server2FPGAEncoder;

import com.example.administrator.testsliding.bean2Transmit.server2FPGASetting.Simple_SweepRange;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

/**
 * Created by Administrator on 2015/12/1.
 */
public class Simple_SweepRangeEncoder implements MessageEncoder<Simple_SweepRange> {
    @Override
    public void encode(IoSession ioSession, Simple_SweepRange sweep,
                       ProtocolEncoderOutput out) throws Exception {

        if(sweep!=null){
            IoBuffer buffer=IoBuffer.allocate(17,true);
            byte[] bytes=sweep.getContent();
            buffer.put(bytes);
            buffer.flip();
            out.write(buffer);
        }

    }
}
