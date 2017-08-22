package com.fransunisoft.drivingguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.fransunisoft.drivingguide.signs.InformationSigns;
import com.fransunisoft.drivingguide.signs.ProhibitorySigns;
import com.fransunisoft.drivingguide.signs.RegulatorySigns;
import com.fransunisoft.drivingguide.signs.TrafficLight;
import com.fransunisoft.drivingguide.signs.TrafficSigns;
import com.fransunisoft.drivingguide.signs.WarningSigns;

public class SignRules extends AppCompatActivity {

    Button traffic, pro, reg, inf, trafficlight, warn;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_rules);

        traffic = (Button)findViewById(R.id.buttontraffic);
        pro = (Button)findViewById(R.id.buttonpro);
        reg = (Button)findViewById(R.id.buttonreg);
        inf = (Button)findViewById(R.id.buttoninf);
        trafficlight = (Button)findViewById(R.id.buttontrafficlight);
        warn = (Button)findViewById(R.id.buttonwarnings);

        traffic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent traffic = new Intent(SignRules.this, TrafficSigns.class);

                startActivity(traffic);
            }
        });

        pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pro = new Intent(SignRules.this, ProhibitorySigns.class);

                startActivity(pro);
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg = new Intent(SignRules.this, RegulatorySigns.class);

                startActivity(reg);

            }
        });

        inf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inf = new Intent(SignRules.this, InformationSigns.class);

                startActivity(inf);
            }
        });

        trafficlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent trafficlight = new Intent(SignRules.this, TrafficLight.class);

                startActivity(trafficlight);
            }
        });

        warn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent warn = new Intent(SignRules.this, WarningSigns.class);

                startActivity(warn);
            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_rules, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
       if (id == R.id.action_about) {
            Intent about = new Intent(SignRules.this, About.class);
            startActivity(about);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
