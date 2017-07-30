package com.example.fbagheri.fulfill;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by F.Bagheri .
 */


public class SplashActivity extends Activity {
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    ImageView imgSplash;
    TextView  txtSplash;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash_layout);

        findViewById();
        handlerSplash();
    }


    public void findViewById() {
        imgSplash = (ImageView) findViewById(R.id.img_splash);
        imgSplash.setImageResource(R.drawable.splash);
        txtSplash = (TextView) findViewById(R.id.txt_splash);
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/splashfont.ttf");
        txtSplash.setTypeface(tf);

    }

    public void handlerSplash(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this, HomeActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
