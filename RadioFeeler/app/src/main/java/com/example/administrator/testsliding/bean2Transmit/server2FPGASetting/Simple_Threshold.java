package com.example.administrator.testsliding.bean2Transmit.server2FPGASetting;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2015/12/1.
 */
public class Simple_Threshold implements Parcelable{
    private byte[] content;

    public Simple_Threshold(){

    }

    protected Simple_Threshold(Parcel in) {
        content = in.createByteArray();
    }

    public static final Creator<Simple_Threshold> CREATOR = new Creator<Simple_Threshold>() {
        @Override
        public Simple_Threshold createFromParcel(Parcel in) {
            return new Simple_Threshold(in);
        }

        @Override
        public Simple_Threshold[] newArray(int size) {
            return new Simple_Threshold[size];
        }
    };

    public void setContent(byte[] content) {
        this.content = content;
    }

    public byte[] getContent() {

        return content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByteArray(content);
    }
}
