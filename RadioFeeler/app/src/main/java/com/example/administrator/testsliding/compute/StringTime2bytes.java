package com.example.administrator.testsliding.compute;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jinaghao on 15/11/25.
 */
public class StringTime2bytes {
    /**
     * 将文本框的String转换为时间字节
     * Created by Administrator on 2015/11/25.
     */


        public List<Integer> Time2Bytes(String str) {
            int[] result = new int[8];
            //从字符串中取出时间，分别是年，月，日，时，分,miao
            String regex = "\\d*";
            List<Integer> digitList = new ArrayList<>();
            Pattern p = Pattern.compile(regex);

            Matcher m = p.matcher(str);
            while (m.find()) {
                if (!"".equals(m.group())) {
                    digitList.add(Integer.valueOf(m.group()));
                }
            }
//            if (digitList.size() != 0) {
//                bytes[0] = (byte) ((digitList.get(1) >> 4) & 0xff);//年高八位
//                bytes[1] = (byte) (((digitList.get(1) & 0x0f) << 4) + (digitList.get(2) & 0x0f));//年+月
//                bytes[2] = (byte) (((digitList.get(3) & 0x1f) << 3) + ((digitList.get(4) >> 2) & 0x07));//日+时高三位
//                bytes[3] = (byte) (((digitList.get(5) & 0x3f) << 2) + (digitList.get(4) & 0x03));//分+时低二位
//                bytes[4] = (byte)(digitList.get(6)&0xff);
//                result[0]=((digitList.get(0)>>4)&0xff);
//                result[0]=(digitList.get(0)  << 4) + (digitList.get(1) >>4);
//                result[1]=digitList.get(1) & 0x0f;
//                result[2]=(digitList.get(2)&0xf8)>>3;
//                result[3]=((digitList.get(2)&0x07)<<2)+(digitList.get(3)&0x03);
//                result[4]=(digitList.get(3)&0xfc)>>2;
//                result[5]=digitList.get(4);


            return digitList;
        }



}
