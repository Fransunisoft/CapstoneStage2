package com.fransunisoft.drivingguide.signs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.fransunisoft.drivingguide.R;
import com.fransunisoft.drivingguide.adapters.RegulatoryAdapter;


public class RegulatorySigns extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regulatory_signs);

        GridView gridView = (GridView)findViewById(R.id.gridview);
        gridView.setAdapter(new RegulatoryAdapter(this));


    }

}
