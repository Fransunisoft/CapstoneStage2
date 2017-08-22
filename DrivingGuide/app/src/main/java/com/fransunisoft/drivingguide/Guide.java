package com.fransunisoft.drivingguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fransunisoft.drivingguide.Data.DrivingGuideDBHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Guide extends AppCompatActivity {

    private ArrayAdapter<String> mForecastAdapter;
    private com.fransunisoft.drivingguide.Data.DrivingGuideDBHelper DrivingGuideDBHelper;
    SharedPreference pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        pref = new SharedPreference(Guide.this);
        DrivingGuideDBHelper = new DrivingGuideDBHelper(Guide.this);

        String[] forecastArray = {
                "STEERING FAILURE", "STUCK ACCELERATOR", "NIGHT DRIVING", "BRAKE FAILURE",
                "HEADLIGHT FAILURE", "HANDLING VEHICLE EMERGENCIES",
                "ANTI-LOCK BRAKING SYSTEM (ABS)", "SKIDDING", "HEADLIGHT USE", "WINTER DRIVING",
                "FOG", "STALLING ON RAILROAD TRACKS", "BLOCKED VISION", "PARKING",
                "VEHICLE APPROACHING HEAD-ON IN YOUR LANE", "RUNNING OFF THE PAVEMENT", "TIRE BLOWOUT"
        };

        List<String> weekForecast;
        weekForecast = new ArrayList<String>(
                Arrays.asList(forecastArray));

        mForecastAdapter =
                new ArrayAdapter<String>(
                        Guide.this,
                        R.layout.list_item_forecast,
                        R.id.list_item_forecast_textview,
                        weekForecast);

        ListView listView = (ListView) findViewById(
                R.id.list_guide);
        listView.setAdapter(mForecastAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        String steeringfailuretittle = DrivingGuideDBHelper.SteeringFailureTittle();
                        String steeringfailure = DrivingGuideDBHelper.SteeringFailure();
                        Intent intent = new Intent (Guide.this, DrivingDetails.class);
                        pref.saveManual(steeringfailuretittle, steeringfailure);
                        startActivity(intent);
                        break;
                    case 1:
                        String Stucktittle = DrivingGuideDBHelper.StuckTittle();
                        String Stuck = DrivingGuideDBHelper.Stuck();
                        Intent intent1 = new Intent (Guide.this, DrivingDetails.class);
                        pref.saveManual(Stucktittle, Stuck);
                        startActivity(intent1);
                        break;
                    case 2:
                        String NightDrivingtittle = DrivingGuideDBHelper.NightDrivingTittle();
                        String NightDriving = DrivingGuideDBHelper.NightDriving();
                        Intent intent2 = new Intent (Guide.this, DrivingDetails.class);
                        pref.saveManual(NightDrivingtittle, NightDriving);
                        startActivity(intent2);
                        break;
                    case 3:
                        String BrakeFailuretittle = DrivingGuideDBHelper.BrakeFailureTittle();
                        String BrakeFailure = DrivingGuideDBHelper.BrakeFailure();
                        Intent intent3 = new Intent (Guide.this, DrivingDetails.class);
                        pref.saveManual(BrakeFailuretittle, BrakeFailure);
                        startActivity(intent3);
                        break;
                    case 4:
                        String HeadlightFailuretittle = DrivingGuideDBHelper.HeadlightFailureTittle();
                        String HeadlightFailure = DrivingGuideDBHelper.HeadlightFailure();
                        Intent intent4 = new Intent (Guide.this, DrivingDetails.class);
                        pref.saveManual(HeadlightFailuretittle, HeadlightFailure);
                        startActivity(intent4);
                        break;
                    case 5:
                        String Handlingtittle = DrivingGuideDBHelper.HandlingTittle();
                        String Handling = DrivingGuideDBHelper.Handling();
                        Intent intent5 = new Intent (Guide.this, DrivingDetails.class);
                        pref.saveManual(Handlingtittle, Handling);
                        startActivity(intent5);
                        break;
                    case 6:
                        String Antilocktittle = DrivingGuideDBHelper.AntilockTittle();
                        String Antilock = DrivingGuideDBHelper.Antilock();
                        Intent intent6 = new Intent (Guide.this, DrivingDetails.class);
                        pref.saveManual(Antilocktittle, Antilock);
                        startActivity(intent6);
                        break;
                    case 7:
                        String Skiddingtittle = DrivingGuideDBHelper.SkiddingTittle();
                        String Skidding = DrivingGuideDBHelper.Skidding();
                        Intent intent7 = new Intent (Guide.this, DrivingDetails.class);
                        pref.saveManual(Skiddingtittle, Skidding);
                        startActivity(intent7);
                        break;
                    case 8:
                        String HeadLightUsetittle = DrivingGuideDBHelper.HeadLightUseTittle();
                        String HeadLightUse = DrivingGuideDBHelper.HeadLightUse();
                        Intent intent8 = new Intent (Guide.this, DrivingDetails.class);
                        pref.saveManual(HeadLightUsetittle, HeadLightUse);
                        startActivity(intent8);
                        break;
                    case 9:
                        String WinterDrivingtittle = DrivingGuideDBHelper.WinterDrivingTittle();
                        String WinterDriving = DrivingGuideDBHelper.WinterDriving();
                        Intent intent9 = new Intent (Guide.this, DrivingDetails.class);
                        pref.saveManual(WinterDrivingtittle, WinterDriving);
                        startActivity(intent9);
                        break;
                    case 10:
                        String Fogtittle = DrivingGuideDBHelper.FogTittle();
                        String Fog = DrivingGuideDBHelper.Fog();
                        Intent intent10 = new Intent (Guide.this, DrivingDetails.class);
                        pref.saveManual(Fogtittle, Fog);
                        startActivity(intent10);
                        break;
                    case 11:
                        String Stalltittle = DrivingGuideDBHelper.StallTittle();
                        String Stall = DrivingGuideDBHelper.Stall();
                        Intent intent11 = new Intent (Guide.this, DrivingDetails.class);
                        pref.saveManual(Stalltittle, Stall);
                        startActivity(intent11);
                        break;
                    case 12:
                        String BlockedVisiontittle = DrivingGuideDBHelper.BlockedVisionTittle();
                        String BlockedVision = DrivingGuideDBHelper.BlockedVision();
                        Intent intent12 = new Intent (Guide.this, DrivingDetails.class);
                        pref.saveManual(BlockedVisiontittle, BlockedVision);
                        startActivity(intent12);
                        break;
                    case 13:
                        String StallingOnRailRoadtittle = DrivingGuideDBHelper.StallingOnRailRoadTittle();
                        String StallingOnRailRoad = DrivingGuideDBHelper.StallingOnRailRoad();
                        Intent intent13 = new Intent (Guide.this, DrivingDetails.class);
                        pref.saveManual(StallingOnRailRoadtittle, StallingOnRailRoad);
                        startActivity(intent13);
                        break;
                    case 14:
                        String Vehicletittle = DrivingGuideDBHelper.VehicleTittle();
                        String Vehicle = DrivingGuideDBHelper.Vehicle();
                        Intent intent14 = new Intent (Guide.this, DrivingDetails.class);
                        pref.saveManual(Vehicletittle, Vehicle);
                        startActivity(intent14);
                        break;
                    case 15:
                        String Runningtittle = DrivingGuideDBHelper.RunningTittle();
                        String Running = DrivingGuideDBHelper.Running();
                        Intent intent15= new Intent (Guide.this, DrivingDetails.class);
                        pref.saveManual(Runningtittle, Running);
                        startActivity(intent15);
                        break;
                    case 16:
                        String TireBlowtittle = DrivingGuideDBHelper.TireBlowTittle();
                        String TireBlow = DrivingGuideDBHelper.TireBlow();
                        Intent intent16= new Intent (Guide.this, DrivingDetails.class);
                        pref.saveManual(TireBlowtittle, TireBlow);
                        startActivity(intent16);
                        break;
                }
            }
        });
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
            Intent about = new Intent(Guide.this, About.class);
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




