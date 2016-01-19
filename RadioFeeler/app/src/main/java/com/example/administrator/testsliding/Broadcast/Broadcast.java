package com.example.administrator.testsliding.Broadcast;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

/**
 * Created by jinaghao on 15/11/18.
 */
public class Broadcast {

    public static void sendBroadCast(Context context, String action,
                                     String key, Parcelable value) {
        Intent intent = new Intent();
        intent.setAction(action);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra(key,value);
        context.sendBroadcast(intent);
    }


    public static void sendBroadCast2(Context context, String action,
                                      String key, int value) {
        Intent intent = new Intent();
        intent.setAction(action);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra(key, value);
        context.sendBroadcast(intent);
    }
}
