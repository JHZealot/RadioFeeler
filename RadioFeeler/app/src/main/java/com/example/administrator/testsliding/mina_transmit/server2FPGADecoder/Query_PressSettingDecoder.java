package com.example.administrator.testsliding.mina_transmit.server2FPGADecoder;

import com.example.administrator.testsliding.bean2Transmit.server2FPGAQuery.Query_PressSetting;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

/**
 * Created by Administrator on 2015/12/1.
 */
public class Query_PressSettingDecoder implements MessageDecoder {
    @Override
    public MessageDecoderResult decodable(IoSession ioSession, IoBuffer in) {
        if(in.remaining()<2){
            return MessageDecoderResult.NEED_DATA;
        }else {
            byte b1=in.get();
            byte b2=in.get();
            if(b1==0x66&&b2==0x18){
                return MessageDecoderResult.OK;
            }else
                return MessageDecoderResult.NOT_OK;
        }

    }

    @Override
    public MessageDecoderResult decode(IoSession ioSession, IoBuffer in,
                                       ProtocolDecoderOutput out) throws Exception {

        int hasCount=0;
        while (in.hasRemaining()){
            hasCount++;
           if(hasCount==7){
               byte[] bytes=new byte[7];
               in.get(bytes);
              Query_PressSetting press=new Query_PressSetting();
               press.setContent(bytes);
               out.write(press);
               return MessageDecoderResult.OK;
           }
        }
        return MessageDecoderResult.NEED_DATA;
    }

    @Override
    public void finishDecode(IoSession ioSession, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {

    }
}
