package com.example.vibrantzz3.snipsearch;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChangePassword extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    ImageView img,touser,savebtn;
    String id,fname,upic,oldpwd,confirmpwd,newpwd, password;
    EditText txtold,txtconfirm,txtnew;
    TextView uname;
    private static final String url_pass = "http://test.epoqueapparels.com/Salon/Salon_App/setpass.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PROFILE = "data";
    private static final String TAG_ID = "id";
    private static final String TAG_EMAIL = "password";

    int success;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Change Password");
        mToolbar.setNavigationIcon(R.drawable.backarrow1);
        setSupportActionBar(mToolbar);



        SharedPreferences pref = getSharedPreferences("loginData", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        id = pref.getString("userid", null);
        fname = pref.getString("name", null);
        upic = pref.getString("profilepic", null);
        password = pref.getString("password", null);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.setDrawerIndicatorEnabled(false);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        View headview=navigationView.getHeaderView(0);
        img=headview.findViewById(R.id.imageView);
        uname=headview.findViewById(R.id.uname);
        touser=headview.findViewById(R.id.imgto);
        uname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ChangePassword.this , User.class);
                intent.putExtra("id",id)
                ;                startActivity(intent);
                //InsertLocation(UName, GetCityName);
            }
        });
        Picasso.get()
                .load(upic)
                .into(img);
        uname.setText(fname);



        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        txtold=(EditText)findViewById(R.id.etold);
        txtconfirm=(EditText)findViewById(R.id.etconfirm);
        txtnew=(EditText)findViewById(R.id.etnew);
        savebtn=(ImageView)findViewById(R.id.btnsave);



        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oldpwd=txtold.getText().toString();
                newpwd=txtnew.getText().toString();
                confirmpwd=txtconfirm.getText().toString();
               if(password.compareTo(oldpwd)==0)
               {
                    updatepwd();
                }
                else
               {
                   txtold.setError("Invalid. Please enter current password.");
               }

            }
        });



    }
    public void updatepwd()
    {
        if(confirmpwd.compareTo(newpwd)==0)
        {
            new UpdateAsyncTask().execute();
        }
        else
        {
            txtnew.setText("");
            txtnew.setError("Passwords don't match. Please try again");
            txtconfirm.setText("");
        }


    }

    class UpdateAsyncTask extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Display progress bar
        }

        @Override
        protected String doInBackground(String... params) {
            HttpJsonParser httpJsonParser = new HttpJsonParser();
            Map<String, String> httpParams = new HashMap<>();
            //Populating request parameters

            httpParams.put(TAG_EMAIL, newpwd);

            httpParams.put(TAG_ID, id);
            JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                    url_pass, "POST", httpParams);
            try {
                success = jsonObject.getInt(TAG_SUCCESS);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result) {
            runOnUiThread(new Runnable() {
                public void run() {
                    if (success == 1) {
                        SharedPreferences preferences =getSharedPreferences("loginData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.clear();
                        editor.commit();
                        Intent intent = new Intent(ChangePassword.this , MainActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(ChangePassword.this,
                                "Some error occurred while updating user",
                                Toast.LENGTH_LONG).show();

                    }
                }
            });
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_appointment) {
            Intent intent = new Intent(ChangePassword.this , ViewAppointments.class);
            intent.putExtra("id",id);
            startActivity(intent);
        } else if (id == R.id.nav_bm) {

            Intent intent = new Intent(ChangePassword.this , ViewBookmarksActivity.class);
            intent.putExtra("id",id);
            startActivity(intent);

        } else if (id == R.id.nav_fave) {
            Intent intent = new Intent(ChangePassword.this , ViewFavouritesActivity.class);
            intent.putExtra("id",id);
            startActivity(intent);


        } else if (id == R.id.nav_notif) {
            Intent intent = new Intent(ChangePassword.this , ViewOffers.class);
            startActivity(intent);

        }else if (id == R.id.nav_settings) {
            Intent intent = new Intent(ChangePassword.this , Settings.class);
            startActivity(intent);

        }else if (id == R.id.nav_signout) {
            SharedPreferences preferences =getSharedPreferences("loginData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(ChangePassword.this , MainActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_feedback) {

        } else if (id == R.id.nav_playstore) {

        }




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
