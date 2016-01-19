package com.example.administrator.testsliding.compute;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/12.
 */
public class ComputeMapPara {
    public static final double PI = 3.14159265358979323846;
    public static final double H0=6378137;//地球半径，单位米
    public static final double mConstant=21600;//计算的常数：360*60
    //传进来的参数
    public double mLatitude;//纬度
    public double mLongitude;//经度
    public double mHeight;//高度
    public double mPower;//功率值
    public double Rindex;//损耗指数
    public double mRadius; //半径
    public double mRatio;//分辨率

    public List<PiontInfo> poinList;

    private double DetaY,DetaX;
    private int Nx,Ny;
    private double d;//距离

    public ComputeMapPara(double mLatitude,double mLongitude,
                          double mHeight,double mPower,double Rindex,double mRadius,double mRatio){
        this.mLatitude=mLatitude;
        this.mLongitude=mLongitude;
        this.mHeight=mHeight;
        this.mPower=mPower;
        this.Rindex=Rindex;
        this.mRadius=mRadius;
        this.mRatio=mRatio;

        poinList=new ArrayList<>();
        //======计算网格数据==============================================================//
        DetaY=2*PI*(H0+mHeight)*mRatio/mConstant;
        DetaX=DetaY*Math.cos(mLongitude);
        Nx= (int) (mRadius/DetaX);
        int Ny= (int) (mRadius/DetaY);

        for(int i=-Nx;i<=Nx;i++)
            for(int j=-Ny;j<=Ny;j++){
                PiontInfo  mpointInfo=new PiontInfo();
                mpointInfo.latitude=mLatitude+i*mRatio/60.0;
                mpointInfo.longtitude=mLongitude+j*mRatio/60.0;
                mpointInfo.xheight=mHeight;

                //计算距离
                double dd=Math.pow(i*DetaX,2)+Math.pow(j*DetaY,2);
                d=Math.sqrt(dd);
                mpointInfo.power=mPower/Math.pow(d,Rindex);
                //第二种计算功率的方法
               // mpointInfo.power=mPower-5*mRatio*Math.log(dd);

                poinList.add(mpointInfo);

            }





    }

    /**
     * 计算Nx
     * @return
     */
    public int CalculateNx(){
        double DetaX=2*PI*(H0+mHeight)*Math.cos(mLongitude)*mRadius/mConstant;
        int Nx= (int) (mRadius/DetaX);
        return Nx;
    }
    /**
     * 计算Ny
     * @return
     */
    public int CalculateNy(){
        double DetaY=2*PI*(H0+mHeight)*mRadius/mConstant;
        int Ny= (int) (mRadius/DetaY);
        return Ny;
    }


}
