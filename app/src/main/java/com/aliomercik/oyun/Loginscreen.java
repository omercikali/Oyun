package com.aliomercik.oyun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Loginscreen extends AppCompatActivity {
TextView textView;
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginscreen);

        textView=findViewById(R.id.textView);
        imageView=findViewById(R.id.imageView);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anime);
        textView.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Loginscreen.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        },5000);


    }
}