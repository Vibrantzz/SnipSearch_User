package com.example.vibrantzz3.snipsearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ViewImageActivity extends AppCompatActivity {

    private TextView username, salonname, likecount,commentcount;
    private ImageView vimage;
    public String uname, sname, uid,sid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewimage);


            username=(TextView)findViewById(R.id.imgname);
            salonname=(TextView)findViewById(R.id.imgsalon);

            vimage=(ImageView)findViewById(R.id.viewimg);

            Intent intent = getIntent();
            uname=intent.getExtras().getString("uname");
            sname=intent.getExtras().getString("sname");
        uid=intent.getExtras().getString("uid");
        sid=intent.getExtras().getString("sid");
            String image = intent.getExtras().getString("Image") ;
            username.setText(uname);
            salonname.setText(sname);
            Picasso.get()
                    .load(image)
                    .placeholder(R.drawable.logo2) // optional
                    .into(vimage);

        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewImageActivity.this, User.class);
                intent.putExtra("id",uid);
                startActivity(intent);

            }
        });

        salonname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewImageActivity.this, SalonProfile.class);
                intent.putExtra("id",sid);
                startActivity(intent);

            }
        });


        }

    }

