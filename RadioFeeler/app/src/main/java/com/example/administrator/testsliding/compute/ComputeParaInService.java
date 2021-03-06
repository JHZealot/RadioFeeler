package com.example.administrator.testsliding.compute;

import com.example.administrator.testsliding.bean2server.File_ModifyAntenna;
import com.example.administrator.testsliding.bean2server.File_ModifyIngain;
import com.example.administrator.testsliding.bean2server.File_ServiceRadio;
import com.example.administrator.testsliding.bean2server.File_StationRegister;
import com.example.administrator.testsliding.bean2server.File_StationAll;
import com.example.administrator.testsliding.bean2server.File_TerminalOnline;
import com.example.administrator.testsliding.bean2server.File_TerminalRegister;
import com.example.administrator.testsliding.bean2server.ListMap;
import com.example.administrator.testsliding.bean2server.List_StationAll;
import com.example.administrator.testsliding.bean2server.List_TerminalOnline;
import com.example.administrator.testsliding.bean2server.ModifyIngainView;

import java.util.ArrayList;

/**在service中运用到的解码成列表方式
 * Created by Administrator on 2015/11/25.
 */
public class ComputeParaInService {
    private ComputePara computePara=new ComputePara();

    //==========================国家无线电规划用到的计算======================================
    public ArrayList<ListMap> Radio2ListItem(File_ServiceRadio radio) {
        ArrayList<ListMap> listItems;
        listItems = new ArrayList<>();
        //ListMap mlist=new ListMap();
        byte[] radioArray;
        radioArray = radio.getFileContent();
        int length=radioArray.length;
        // 将消息展现出来。
        if (radioArray != null) {
            //8个表示长度，4个不是主要内容
            for (int i = 4; i < length - 3; i = i + 16) {
                String start = computePara.Freq2float(radioArray[i], radioArray[i + 1], radioArray[i + 2], radioArray[i + 3]);
                String end = computePara.Freq2float(radioArray[i + 4], radioArray[i + 5], radioArray[i + 6], radioArray[i + 7]);
                String section=null;
                if(start!=null&&end!=null) {
                    section = start + "~" + end;
                }
                else if(start==null){
                    int startint=0;
                    section = String.valueOf(startint) + "~" + end;
                }
                String str = null;
                for (int j = 0; j < 8; j++) {
                    if (radioArray[i + 8+ j] != 0) {
                        if (str != null) {
                            str = str + computePara.ChartPlan((radioArray[i + 8 + j] & 0xff)) + " ";
                        } else {
                            str =computePara.ChartPlan((radioArray[i + 8 + j] & 0xff)) + " ";
                        }
                    }
                }
                int num=(i/16)+1;
                String strs=String.valueOf(num);
                if(strs!=null&section!=null&str != null)
                    listItems.add(new ListMap(strs, section, str));

                if (strs!=null&section!=null&str== null) {
                    listItems.add(new ListMap(strs, section,"未划分"));
                }

            }
        }
        return listItems;
    }




    //=========================================================================================

    //=======================全部台站登记属性查询的计算=======================================
    public ArrayList<List_StationAll> StationALL2ListItem(File_StationAll terminal){
        ArrayList<List_StationAll> listItems;
        listItems = new ArrayList<>();
        String organizer=null;
        String IDcard=null;
        String longstyle=null;
        String latstyle=null;
        int height=0;
        String section=null;
        float power=0;
        String modem=null;
        String attribute=null;

        byte[] bytes=terminal.getFileContent();
        for(int i=4;i<bytes.length-3;i=i+34){

            int number=i/34+1;//序号

            for(int j=0;j<8;j++){
                //查归属单位
            }
            byte[] og=new byte[8];
            System.arraycopy(bytes,i,og,0,8);
            organizer=new String(og);
            //判断位置
            if(bytes[i+11]==0x00)
                 longstyle="E";
            else if(bytes[i+11]==0x01){
                longstyle="W";
            }
           float longtitude=(bytes[i+12]&0xff)+computePara.BitDecimal2float(bytes[i+13],bytes[i+14]);

            if((bytes[i+15]>>7)==0x00)
                latstyle="N";
            else if((bytes[i+15]>>7)==0x01){
                latstyle="S";
            }
            float latitude=(bytes[i+15]&0x7f)+computePara.BitDecimal2float(bytes[i+16],bytes[i+17]);

            if((bytes[i+18]>>7)==0x00){
                height=((bytes[i+18]&0x7f)<<8)+(bytes[i+19]&0xff);
            }else{
                height=-(((bytes[i+18]&0x7f)<<8)+(bytes[i+19]&0xff));
            }
            //识别码
            if((bytes[i+8]==(byte)0xFF)&&(bytes[i+9]==(byte)0xFF)&&(bytes[i+10]==(byte)0xFF)){
                //辐射源
                IDcard="0xFFFFFF";
                double freq=((bytes[i + 20] & 0xff) << 6) + (bytes[i + 21] & 0x3f)+
                        computePara.TenbitDecimal2float(bytes[i + 21],bytes[i + 22]);
                section=String.valueOf(freq);
            }
            else {
                IDcard = String.valueOf(((bytes[i + 8] & 0xff) << 16) +
                        ((bytes[i + 9] & 0xff) << 8) + (bytes[i + 10] & 0xff));
                //中心频率范围
                int start = ((bytes[i + 20] & 0xff) << 8) + (bytes[i + 21] & 0xff);
                int end = ((bytes[i + 22] & 0xff) << 8) + (bytes[i + 23] & 0xff);
                section = String.valueOf(start) + "~" + String.valueOf(end);
            }

            //发射功率
            float f1= (float) (((bytes[i+24]&0x7f)<<5)+((bytes[i+25]>>3)&0x1f)+
                                ((bytes[i+25]>>2)&0x01)*0.5+((bytes[i+25]>>1)&0x01)*0.25+(bytes[i+25]&0x01)*0.125);
            if((bytes[i+24]>>7)==0x00)
                power=f1;
            else
                power=-f1;

            //带宽
            float band=(bytes[i+26]&0x3f)+computePara.TenbitDecimal2float(bytes[i+26],bytes[i+27]);
            //调制方式
            modem=computePara.QueryModem(bytes[i+28]);
            float f2=0;
            for(int j=1;j<9;j++){
                f2+=((bytes[i+30]>>(8-j))&0x01)*Math.pow(2,-j);
            }
            float modemPara=(bytes[i+29]&0xff)+f2;

            //业务属性
            attribute=computePara.ChartPlan((bytes[i+31]&0xff));
            int radius=bytes[i+32]&0xff;
            float radio= (float) ((bytes[i+33]&0xff)/100.0);

            listItems.add(new List_StationAll(number,organizer,IDcard,longstyle,longtitude,latstyle
                          ,latitude,height,section,power,band,modem,modemPara,attribute,
                           radius,radio));

        }
        return  listItems;

    }


    //===============================================================================================

   //==========================所有在网终端属性======================================================
   public ArrayList<List_TerminalOnline> TerminalOnline2ListItem(File_TerminalOnline terminal){
       ArrayList<List_TerminalOnline> listItems;
       listItems = new ArrayList<>();
       String longstyle=null;
       String latstyle=null;
       int height=0;

       byte[] bytes=terminal.getFileContent();
       //在网终端总数通过listItem的长度计算一样可得，可不用存入表
       for(int i=6;i<bytes.length-3;i=i+12){
           int num=i/12+1;
           //ID号
           int ID=((bytes[i]&0xff))+((bytes[i+1]&0xff)<<8);
           //终端类型
           int style=bytes[i+2]&0xff;
           //位置
           //判断位置
           if(bytes[i+3]==0x00)
               longstyle="E";
           else if(bytes[i+3]==0x01){
               longstyle="W";
           }
           float longtitude=(bytes[i+4]&0xff)+computePara.BitDecimal2float(bytes[i+5],bytes[i+6]);

           if((bytes[i+7]>>7)==0x00)
               latstyle="N";
           else if((bytes[i+7]>>7)==0x01){
               latstyle="S";
           }
           float latitude=(bytes[i+7]&0x7f)+computePara.BitDecimal2float(bytes[i+8],bytes[i+9]);

           if((bytes[i+10]>>7)==0x00){
               height=((bytes[i+10]&0x7f)<<8)+(bytes[i+11]&0xff);
           }else{
               height=-(((bytes[i+10]&0x7f)<<8)+(bytes[i+11]&0xff));
           }

           listItems.add(new List_TerminalOnline(num,ID,style,longstyle,
                   longtitude,latstyle,latitude,height));
       }
       return  listItems;
   }

    //==========================所有注册终端========================================================
    public ArrayList<List_TerminalOnline> TerminalRegister2ListItem(File_TerminalRegister terminal){
        ArrayList<List_TerminalOnline> listItems;
        listItems = new ArrayList<>();
        String longstyle=null;
        String latstyle=null;
        int height=0;

        byte[] bytes=terminal.getFileContent();
        //在网终端总数通过listItem的长度计算一样可得，可不用存入表
        for(int i=6;i<bytes.length-3;i=i+12){
            int num=i/12+1;
            //ID号
            int ID=((bytes[i]&0xff))+((bytes[i+1]&0xff)<<8);
            //终端类型
            int style=bytes[i+2]&0xff;
            //位置
            //判断位置
            if(bytes[i+3]==0x00)
                longstyle="E";
            else if(bytes[i+3]==0x01){
                longstyle="W";
            }
            float longtitude=(bytes[i+4]&0xff)+computePara.BitDecimal2float(bytes[i+5],bytes[i+6]);

            if((bytes[i+7]>>7)==0x00)
                latstyle="N";
            else if((bytes[i+7]>>7)==0x01){
                latstyle="S";
            }
            float latitude=(bytes[i+7]&0x7f)+computePara.BitDecimal2float(bytes[i+8],bytes[i+9]);

            if((bytes[i+10]>>7)==0x00){
                height=((bytes[i+10]&0x7f)<<8)+(bytes[i+11]&0xff);
            }else{
                height=-(((bytes[i+10]&0x7f)<<8)+(bytes[i+11]&0xff));
            }

            listItems.add(new List_TerminalOnline(num,ID,style,longstyle,
                    longtitude,latstyle,latitude,height));
        }
        return  listItems;
    }

    public static String Bytes2HexString(byte[] b) {
        String ret = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += hex.toUpperCase();
        }
        return ret;
    }
    //====================================登记台站属性查询的计算===============
    //表内容与全部台站属性一样
    public ArrayList<List_StationAll> TerminalALL2ListItem(File_StationRegister terminal){
        ArrayList<List_StationAll> listItems;
        listItems = new ArrayList<>();
        String organizer=null;
        String IDcard=null;
        String longstyle=null;
        String latstyle=null;
        int height=0;
        String section=null;
        float power=0;
        String modem=null;
        String attribute=null;

        byte[] bytes=terminal.getFileContent();
        for(int i=4;i<bytes.length-3;i=i+34){

            int number=i/34+1;//序号

//            for(int j=0;j<8;j++){
//                //查归属单位
//            }
            byte[] og=new byte[8];
            System.arraycopy(bytes,i,og,0,8);
                organizer=new String(og);


            //判断位置
            if(bytes[i+11]==0x00)
                longstyle="E";
            else if(bytes[i+11]==0x01){
                longstyle="W";
            }
            float longtitude=(bytes[i+12]&0xff)+computePara.BitDecimal2float(bytes[i+13],bytes[i+14]);

            if((bytes[i+15]>>7)==0x00)
                latstyle="N";
            else if((bytes[i+15]>>7)==0x01){
                latstyle="S";
            }
            float latitude=(bytes[i+15]&0x7f)+computePara.BitDecimal2float(bytes[i+16],bytes[i+17]);

            if((bytes[i+18]>>7)==0x00){
                height=((bytes[i+18]&0x7f)<<8)+(bytes[i+19]&0xff);
            }else{
                height=-(((bytes[i+18]&0x7f)<<8)+(bytes[i+19]&0xff));
            }
            //识别码
            if(bytes[i+8]==(byte)0xFF&&bytes[i+9]==(byte)0xFF&&bytes[i+10]==(byte)0xFF){
                //辐射源
                IDcard="0xFFFFFF";
                double freq=((bytes[i + 20] & 0xff) << 6) + (bytes[i + 21] & 0x3f)+
                        computePara.TenbitDecimal2float(bytes[i + 21],bytes[i + 22]);
                section=String.valueOf(freq);
            }
            else {
                IDcard = String.valueOf(((bytes[i + 8] & 0xff) << 16) +
                        ((bytes[i + 9] & 0xff) << 8) + (bytes[i + 10] & 0xff));
                //中心频率范围
                int start = ((bytes[i + 20] & 0xff) << 8) + (bytes[i + 21] & 0xff);
                int end = ((bytes[i + 22] & 0xff) << 8) + (bytes[i + 23] & 0xff);
                section = String.valueOf(start) + "~" + String.valueOf(end);
            }

            //发射功率
            float f1= (float) (((bytes[i+24]&0x7f)<<5)+((bytes[i+25]>>3)&0xff)+
                    ((bytes[i+25]>>2)&0x01)*0.5+((bytes[i+25]>>1)&0x01)*0.25+(bytes[i+25]&0x01)*0.125);
            if((bytes[i+24]>>7)==0x00)
                power=f1;
            else
                power=-f1;

            //带宽
            float band=(bytes[i+26]&0x1f)+computePara.TenbitDecimal2float(bytes[i+26],bytes[i+27]);
            //调制方式
            modem=computePara.QueryModem(bytes[i+28]);
            float f2=0;
            for(int j=1;j<9;j++){
                f2+=((bytes[i+30]>>(8-j))&0x01)*Math.pow(2,-j);
            }
            float modemPara=(bytes[i+29]&0xff)+f2;

            //业务属性
            attribute=computePara.ChartPlan((bytes[i+31]&0xff));
            int radius=(bytes[i+32]&0xff);
            float radio= (float) ((bytes[i+33]&0xff)/100.0);

            listItems.add(new List_StationAll(number,organizer,IDcard,longstyle,longtitude,latstyle
                    ,latitude,height,section,power,band,modem,modemPara,attribute,
                    radius,radio));

        }
        return  listItems;

    }

    //===================增益修正表==============
    public ModifyIngainView File2InGain(File_ModifyIngain file){
        byte[] b=file.getFileContent();
        ModifyIngainView modify=new ModifyIngainView();
        String[] strings=new String[3];
        String[] strings1=new String[237];
       // modify.setContent(b);
        modify.setFPGANum(String.valueOf((((b[4]&0xff)<<8)+b[5])&0xff));
        for(int i=0;i<3;i++){
            int start=(((b[6+i*4]&0xff)<<8)+b[7+i*4])&0xff;
            int end=(((b[8+i*4]&0xff)<<8)+b[9+i*4])&0xff;
            String str1=String.valueOf(start)+"~"+String.valueOf(end);
            strings[i]=str1;
        }
        modify.setSection(strings[0]+","+strings[1]+","+strings[2]);
        for(int j=0;j<237;j++){
            strings1[j]=Bit2String(b[18+j]) ;
        }
        modify.setValue(strings1);
        return  modify;
    }
    //===================增益修正表==============
    public ModifyIngainView File2Antenna(File_ModifyAntenna file){
        byte[] b=file.getFileContent();
        ModifyIngainView modify=new ModifyIngainView();
        String[] strings=new String[3];
        String[] strings1=new String[237];
        // modify.setContent(b);
        modify.setFPGANum(String.valueOf((((b[4]&0xff)<<8)+b[5])&0xff));
        for(int i=0;i<3;i++){
            int start=(((b[6+i*4]&0xff)<<8)+b[7+i*4])&0xff;
            int end=(((b[8+i*4]&0xff)<<8)+b[9+i*4])&0xff;
            String str1=String.valueOf(start)+"~"+String.valueOf(end);
            strings[i]=str1;
        }
        modify.setSection(strings[0]+","+strings[1]+","+strings[2]);
        for(int j=0;j<237;j++){
            strings1[j]=Bit2String(b[18+j]) ;
        }
        modify.setValue(strings1);
        return  modify;
    }


    private String Bit2String(byte b){
        String str=null;
        float f1= (float) (((b>>1)&0x3f)+0.5*(b&0x01));
        if(((b>>7)&0x01)==0){
            str=String.valueOf(f1);
        }else {
            str=String.valueOf(-f1);
        }
        return str;

    }
}
