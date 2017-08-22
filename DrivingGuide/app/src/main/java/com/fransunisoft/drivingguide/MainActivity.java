package com.fransunisoft.drivingguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {
    Button tutorial, sign, guide, traffic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tutorial = (Button) findViewById(R.id.tutorialbtn);
        sign = (Button) findViewById(R.id.signbtn);
        guide = (Button) findViewById(R.id.guidebtn);
        traffic = (Button) findViewById(R.id.trafficbtn);


        AdView adView = (AdView) this.findViewById(R.id.adView);
        // Request for Ads
        AdRequest adRequest = new AdRequest.Builder()

                // Add a test device to show Test Ads
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("CD5F3A72H2B356BB60DD198")
                .build();

        // Load ads into Banner Ads
        adView.loadAd(adRequest);

        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tutorial = new Intent(MainActivity.this, Tutorials.class);

                startActivity(tutorial);
            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sign = new Intent(MainActivity.this, SignRules.class);

                startActivity(sign);
            }
        });

        guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent guide = new Intent(MainActivity.this, Guide.class);

                startActivity(guide);
            }
        });

        traffic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent traffic = new Intent(MainActivity.this, PlaceActivity.class);
                startActivity(traffic);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Intent about = new Intent(MainActivity.this, About.class);
            startActivity(about);
            return true;
        }

        if (id == R.id.action_email) {
            Intent mailto = new Intent(Intent.ACTION_SEND);
            mailto.setType("message/rfc822") ; // use from live device
            mailto.putExtra(Intent.EXTRA_EMAIL, new String[]{"oluwaseyiayodele18@gmail.com"});
            mailto.putExtra(Intent.EXTRA_SUBJECT,"Driving Guide v1.0 : Feedback");
            mailto.putExtra(Intent.EXTRA_TEXT,"");
            startActivity(Intent.createChooser(mailto, "Select email application."));
            return true;
        }

        if (id == R.id.action_report) {
            Intent mailto = new Intent(Intent.ACTION_SEND);
            mailto.setType("message/rfc822") ; // use from live device
            mailto.putExtra(Intent.EXTRA_EMAIL, new String[]{"oluwaseyiayodele18@gmail.com"});
            mailto.putExtra(Intent.EXTRA_SUBJECT,"Driving Guide v1.0 : Report a bug");
            mailto.putExtra(Intent.EXTRA_TEXT,"");
            startActivity(Intent.createChooser(mailto, "Select email application."));
            return true;
        }

        if (id == R.id.action_tell){
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Share Via");
            shareIntent.putExtra(Intent.EXTRA_TEXT,"I have just used Driving Guide App." + " " + "Download Driving Guide App at http://play.Google.com/store/apps/details?Id=com.fransunisoft.DrivingDetails");
            startActivity(Intent.createChooser(shareIntent, "Share via"));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
