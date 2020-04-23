package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static int SPLASH_TIMEOUT = 4500;
    //Vars
    Animation topanim, botanim;
    ImageView img;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        topanim = AnimationUtils.loadAnimation(this, R.anim.top_anim);
        botanim = AnimationUtils.loadAnimation(this, R.anim.bottm_anim);

        img = findViewById(R.id.img_spl);
        tv = findViewById(R.id.tv_splash);

        img.setAnimation(topanim);
        tv.setAnimation(botanim);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, Login.class));
                finish();
            }
        }, SPLASH_TIMEOUT);
    }
}
