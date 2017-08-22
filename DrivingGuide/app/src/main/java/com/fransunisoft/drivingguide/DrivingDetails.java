package com.fransunisoft.drivingguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.fransunisoft.drivingguide.fragments.DrivDetFrag;
import com.fransunisoft.drivingguide.fragments.Fragmentutsnew;

import java.util.HashMap;



public class DrivingDetails extends AppCompatActivity {
    TextView manual_txt, manualtitle_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driving_guide);
        DrivDetFrag firstFragment = new DrivDetFrag();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container_drive_det, firstFragment).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tutorials, menu);
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
            Intent about = new Intent(DrivingDetails.this, About.class);
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
