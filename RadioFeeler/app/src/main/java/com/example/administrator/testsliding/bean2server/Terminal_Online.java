package com.example.administrator.testsliding.bean2server;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2015/11/23.
 */
public class Terminal_Online implements Parcelable {
    private int equipmentID;

    public Terminal_Online(){

    }
    protected Terminal_Online(Parcel in) {
        equipmentID = in.readInt();
    }

    public static final Creator<Terminal_Online> CREATOR = new Creator<Terminal_Online>() {
        @Override
        public Terminal_Online createFromParcel(Parcel in) {
            return new Terminal_Online(in);
        }

        @Override
        public Terminal_Online[] newArray(int size) {
            return new Terminal_Online[size];
        }
    };

    public void setEquipmentID(int equipmentID) {
        this.equipmentID = equipmentID;
    }

    public int getEquipmentID() {

        return equipmentID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(equipmentID);
    }
}
