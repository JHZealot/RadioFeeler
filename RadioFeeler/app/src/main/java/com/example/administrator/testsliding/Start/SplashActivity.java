package com.example.administrator.testsliding.Start;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.administrator.testsliding.Mina.MinaClientService;
import com.example.administrator.testsliding.Mina.UpdateService;
import com.example.administrator.testsliding.R;


/**
 * Created by H on 2015/9/15.
 */
public class SplashActivity extends Activity {
private TextView tv01,tv02;

    /**
     * Called when the activity is first created.
     */

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Intent startServiceIntent=new Intent(SplashActivity.this, MinaClientService.class);
        startService(startServiceIntent);

        Intent startUpdateService=new Intent(SplashActivity.this, UpdateService.class);
        startService(startUpdateService);



        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //AssetManager mgr=getAssets();//得到AssetManager

        setContentView(R.layout.splash);
        Typeface tf= Typeface.createFromAsset(getAssets(), "fonts/STHUPO.TTF");//根据路径得到Typeface
        tv01= (TextView) findViewById(R.id.chinese);
        tv02= (TextView) findViewById(R.id.english);
        tv01.setTypeface(tf);
        tv02.setTypeface(tf);


        new Handler().postDelayed(new Runnable() {//新建一个handler实现演示跳转


            @Override

            public void run() {

// TODO Auto-generated method stub

                Intent i = new Intent(SplashActivity.this, LoginActivity.class);


                startActivity(i);
                finish();

            }

        }, 5000);


    }

}



