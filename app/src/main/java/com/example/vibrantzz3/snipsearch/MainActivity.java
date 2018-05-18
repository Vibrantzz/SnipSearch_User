package com.example.vibrantzz3.snipsearch;

import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener {

    ImageView title,pin,cell,btn;
    Animation bounce,slideUp ,slideDown,fadein;

    String emailStored = "", passwordStored = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        btn=(ImageView)findViewById(R.id.imgbtn);
        cell=(ImageView)findViewById(R.id.imgcell);
        pin=(ImageView)findViewById(R.id.imgpin);
        title=(ImageView)findViewById(R.id.imgtitle);


        //---------------animation code--------------

        slideUp = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_up);
        slideUp.setAnimationListener(this);

        bounce = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
        bounce.setAnimationListener(this);

        //slideDown = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);
        //slideDown.setAnimationListener(this);


        slideDown = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);
        slideDown.setAnimationListener(this);

        fadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        fadein.setAnimationListener(this);
        //--------------------------------------------

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent in = new Intent(getApplicationContext(), login.class);
                startActivity(in);
            }});

    }


    @Override
    protected void onStart() {
        super.onStart();
        //salon_title.setVisibility(View.VISIBLE);
        //salon_title.startAnimation(slideDown);
        title.setVisibility(View.VISIBLE);
        title.startAnimation(fadein);
        pin.setVisibility(View.VISIBLE);
        pin.startAnimation(bounce);
        btn.setVisibility(View.VISIBLE);
        btn.startAnimation(slideUp);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}