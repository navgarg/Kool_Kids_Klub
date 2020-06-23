package com.test.koolkidsklub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Animation anim = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.splash_anim);

        ImageView logo = findViewById(R.id.logo);
        logo.startAnimation(anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
          startActivity(new Intent(SplashActivity.this, LoginActivity.class));
          finish();

            }
        }, 3000);
    }
}