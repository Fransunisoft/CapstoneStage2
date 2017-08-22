package com.fransunisoft.drivingguide;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fransunisoft.drivingguide.map.DataParser;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.SphericalUtil;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class PlaceActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,OnMapReadyCallback {

    private GoogleApiClient mGoogleApiClient;
    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    int WHICH;
    TextView from,to,dist,toggle;
    private GoogleMap mMap;
    long latFrom, lngFrom, latTo,lngTo;
    LatLng From = null, To= null;
    String froom, tooo;
    Button find;
    LinearLayout mapwrapper;
    double distance;
    PolylineOptions lineOptions;
    boolean showingtraffic = true;
    CardView cv;

    CameraPosition cp = null;
    float zoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_activity);


        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();
        from = (TextView) findViewById(R.id.tv_places_acitivity_from);
        to = (TextView) findViewById(R.id.tv_places_acitivity_to);
        dist = (TextView) findViewById(R.id.tv_places_acitivity_distance);
        toggle = (TextView) findViewById(R.id.tv_places_acitivity_toggle);
        toggle.setVisibility(View.GONE);
        cv = (CardView) findViewById(R.id.card_info);
        //find = (Button) findViewById(R.id.button_find);
        mapwrapper = (LinearLayout ) findViewById(R.id.mapwrapper);
        cv.setVisibility(View.GONE);
        mapwrapper.setVisibility(View.GONE);
        dist.setVisibility(View.GONE);

        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleTraffic();
            }
        });

        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseLocationFrom();
            }
        });
        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseLocationTo();
            }
        });
//
//        find.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startMaP();
//            }
//        });

    }

    private void startMaP() {
        mapwrapper.setVisibility(View.VISIBLE);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(PlaceActivity.this);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public void chooseLocationFrom(){
        WHICH = 0;
        try {
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                            .build(this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException e) {
            // TODO: Handle the error.
        } catch (GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
        }

    }
    public void chooseLocationTo(){
        WHICH = 1;
        try {
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                            .build(this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException e) {
            // TODO: Handle the error.
        } catch (GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE && WHICH == 0) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                Log.e("Auto Compete", "Place: " + place.getName());
                from.setText(place.getName());
                froom = place.getName().toString();
                From = place.getLatLng();

            }
            else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.e("Auto Complete", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }else if(requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE && WHICH == 1){
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                Log.e("Auto Compete", "Place: " + place.getName());
                to.setText(place.getName());
                To = place.getLatLng();
                tooo = place.getName().toString();
                startMaP();

            }
            else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.e("Auto Complete", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.clear();

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng Frm = From;
        LatLng to = To;

        //distance = SphericalUtil.computeDistanceBetween(Frm,to);
        dist.setVisibility(View.VISIBLE);
        toggle.setVisibility(View.VISIBLE);

        double distRnd = Math.ceil(distance / 1000);
        dist.setText(distRnd+ "Km");
        mMap.addMarker(new MarkerOptions().position(Frm).title(froom));
        mMap.addMarker(new MarkerOptions().position(to).title(tooo));

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(Frm);
        builder.include(to);
        LatLngBounds bounds = builder.build();

        String url = getUrl(Frm, to);
        Log.d("onMapClick", url.toString());
        FetchUrl FetchUrl = new FetchUrl();

        // Start downloading json data from Google Directions API
        if(showingtraffic == true){
            mMap.setTrafficEnabled(false);
            FetchUrl.execute(url);
            showingtraffic= false;
            toggle.setText("Traffic");
            cv.setVisibility(View.GONE);

        }else {
            mMap.setTrafficEnabled(true);
            showingtraffic = true;
            toggle.setText("Route");

            cv.setVisibility(View.VISIBLE);
        }



        if(cp == null){
        int padding = 5;
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds,padding);

        //mMap.moveCamera(cu);
        mMap.animateCamera(cu);
        } else {
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
            mMap.moveCamera(CameraUpdateFactory.zoomTo(zoom));
        }


       // mMap.moveCamera(CameraUpdateFactory.zoomTo(Frm/ to));


    }
    private String getUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;


        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;


        return url;
    }

    /**
     * A method to download json data from url
     */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();
            Log.d("downloadUrl", data.toString());
            br.close();

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    // Fetches data from url passed
    private class FetchUrl extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try {
                // Fetching the data from web service
                data = downloadUrl(url[0]);
                Log.d("Background Task data", data.toString());
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);

        }
    }

    /**
     * A class to parse the Google Places in JSON format
     */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                Log.d("ParserTask",jsonData[0].toString());
                DataParser parser = new DataParser();
                Log.d("ParserTask", parser.toString());

                // Starts parsing data
                routes = parser.parse(jObject);
                Log.d("ParserTask","Executing routes");
                Log.d("ParserTask",routes.toString());

            } catch (Exception e) {
                Log.d("ParserTask",e.toString());
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points;
            lineOptions = null;

            // Traversing through all the routes
            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList<>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }
                distance = SphericalUtil.computeLength(points);
                lineOptions.addAll(points);
                lineOptions.width(10);

                lineOptions.color(getResources().getColor(R.color.colorBrightBlue));

                Log.d("onPostExecute","onPostExecute lineoptions decoded");

            }

            // Drawing polyline in the Google Map for the i-th route
            if(lineOptions != null) {
                mMap.addPolyline(lineOptions);
            }
            else {
                Log.d("onPostExecute","without Polylines drawn");
            }
        }
    }


    public void toggleTraffic(){
         cp = mMap.getCameraPosition();
       zoom = cp.zoom;
       startMaP();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_places, menu);
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
            Intent about = new Intent(PlaceActivity.this, About.class);
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
