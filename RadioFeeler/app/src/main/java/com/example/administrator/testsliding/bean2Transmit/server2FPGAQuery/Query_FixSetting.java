package com.example.administrator.testsliding.bean2Transmit.server2FPGAQuery;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2015/12/1.
 */
public class Query_FixSetting implements Parcelable{
    private byte[] content;

    public Query_FixSetting(){

    }

    protected Query_FixSetting(Parcel in) {
        content = in.createByteArray();
    }

    public static final Creator<Query_FixSetting> CREATOR = new Creator<Query_FixSetting>() {
        @Override
        public Query_FixSetting createFromParcel(Parcel in) {
            return new Query_FixSetting(in);
        }

        @Override
        public Query_FixSetting[] newArray(int size) {
            return new Query_FixSetting[size];
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
