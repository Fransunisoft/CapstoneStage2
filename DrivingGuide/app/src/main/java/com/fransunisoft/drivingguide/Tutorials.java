package com.fransunisoft.drivingguide;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.fransunisoft.drivingguide.fragments.DrivDetFrag;
import com.fransunisoft.drivingguide.fragments.Fragmentutsnew;

public class Tutorials extends AppCompatActivity  implements Fragmentutsnew.OnItemClickListener{

    private ArrayAdapter<String> mForecastAdapter;
    private com.fransunisoft.drivingguide.Data.DrivingGuideDBHelper DrivingGuideDBHelper;
    SharedPreference pref;
    Context context = Tutorials.this;
    boolean isTwopane;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorials);


        if(findViewById(R.id.fragment_container_pane2 ) != null){
            isTwopane = true;
            Bundle twopane = new Bundle();
            twopane.putBoolean("two",true);
            Fragmentutsnew firstFragment = new Fragmentutsnew();
            firstFragment.setOnItemClickListener(this);
            firstFragment.setArguments(twopane);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();

            DrivDetFrag drivDetFrag = new DrivDetFrag();

            drivDetFrag.setArguments(twopane);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_pane2, drivDetFrag).commit();



        }else {
            isTwopane= false;
            Bundle twopane = new Bundle();
            twopane.putBoolean("two",false);
            Fragmentutsnew firstFragment = new Fragmentutsnew();
            firstFragment.setArguments(twopane);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();

        }


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
            Intent about = new Intent(Tutorials.this, About.class);
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




    @Override
    public void onItemClick(String title, String content) {


        if(isTwopane){
            Bundle b = new Bundle();
            b.putString("title",title);
            b.putBoolean("two",isTwopane);
            b.putString("content",content);
            DrivDetFrag drivDetFrag = new DrivDetFrag();
           drivDetFrag.setArguments(b);
           getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_pane2, drivDetFrag).commit();

            //drivDetFrag.Update(title, content);
        }

    }
}



