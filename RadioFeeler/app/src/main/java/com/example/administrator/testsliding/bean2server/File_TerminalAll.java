package com.example.administrator.testsliding.bean2server;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2015/11/16.
 */
public class File_TerminalAll implements Parcelable {
    private byte[] fileContent;
    public File_TerminalAll(){

    }

    protected File_TerminalAll(Parcel in) {
        fileContent = in.createByteArray();
    }

    public static final Creator<File_TerminalAll> CREATOR = new Creator<File_TerminalAll>() {
        @Override
        public File_TerminalAll createFromParcel(Parcel in) {
            return new File_TerminalAll(in);
        }

        @Override
        public File_TerminalAll[] newArray(int size) {
            return new File_TerminalAll[size];
        }
    };

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByteArray(fileContent);
    }
}
