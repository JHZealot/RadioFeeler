package com.example.administrator.testsliding.bean2server;

/**
 * Created by Administrator on 2015/12/21.
 */
public class MapRadioPointInfo {
    private int year;
    private int month;
    private int date;
    private int hour;
    private int min;

    private String longtitudeStyle;//东西经
    private float longitude;
    private String latitudeStyle;//南北纬
    private float latitude;//纬度
    private int height;

    private float equalPower;//等效发射功率
    private float rPara;//损耗指数

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDate() {
        return date;
    }

    public int getHour() {
        return hour;
    }

    public int getMin() {
        return min;
    }

    public String getLongtitudeStyle() {
        return longtitudeStyle;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getLatitudeStyle() {
        return latitudeStyle;
    }

    public float getLatitude() {
        return latitude;
    }

    public int getHeight() {
        return height;
    }

    public float getEqualPower() {
        return equalPower;
    }

    public float getrPara() {
        return rPara;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMin(int min) {
        this.min = min;
    }



    public void setLongtitudeStyle(String longtitudeStyle) {
        this.longtitudeStyle = longtitudeStyle;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setLatitudeStyle(String latitudeStyle) {
        this.latitudeStyle = latitudeStyle;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setEqualPower(float equalPower) {
        this.equalPower = equalPower;
    }

    public void setrPara(float rPara) {
        this.rPara = rPara;
    }
}
