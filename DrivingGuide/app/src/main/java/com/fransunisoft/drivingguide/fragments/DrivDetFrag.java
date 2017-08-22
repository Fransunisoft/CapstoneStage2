package com.fransunisoft.drivingguide.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fransunisoft.drivingguide.About;
import com.fransunisoft.drivingguide.Data.DrivingGuideDBHelper;
import com.fransunisoft.drivingguide.R;
import com.fransunisoft.drivingguide.SharedPreference;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class DrivDetFrag extends Fragment {
    SharedPreference pref;
    TextView manual_txt, manualtitle_txt;
    public static String strmanual, manual_title;
    Context context;
    int firstpos;
    boolean istwoPane = false;
    String tit,cont;
    View v;
    private DrivingGuideDBHelper DrivingGuideDBHelper;


    public DrivDetFrag() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getContext();
         v = inflater.inflate(R.layout.fragment_driv_det, container, false);
        DrivingGuideDBHelper = new DrivingGuideDBHelper(context);

       // setHasOptionsMenu(true);
       if( getArguments()!= null ){
          Bundle b = getArguments();
         istwoPane = b.getBoolean("two",false);
               tit = b.getString("title","N/A");
           if(!(b.getString("content","0")).equalsIgnoreCase("0")){
               cont = b.getString("content","N/A");
           }

           if(tit.contentEquals("N/A") || cont.contentEquals("N/A")){
               tit = DrivingGuideDBHelper.SignalingTittle();
               cont = DrivingGuideDBHelper.Signaling();
           }

       }

        pref = new SharedPreference(context);
        manual_txt = (TextView) v.findViewById(R.id.tvManual);
        manualtitle_txt = (TextView) v.findViewById(R.id.tvManualTittle);

        //hashmap to get use detailsmanual
        HashMap<String, String> manual = pref.getManualDetails();
        strmanual = manual.get(pref.KEY_MANUAL);
        manual_title = manual.get(pref.KEY_TITLE);
        if(istwoPane){
            manual_txt.setText(cont);
            manualtitle_txt.setText(tit);
            String just ;
        }else {
            manual_txt.setText(strmanual);
            manualtitle_txt.setText(manual_title);
        }

        return v;
    }

    public void Update(String title, String content){
        manual_txt = (TextView) v.findViewById(R.id.tvManual);
        manualtitle_txt = (TextView) v.findViewById(R.id.tvManualTittle);

        manual_txt.setText(title);
        manualtitle_txt.setText(content);

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
       inflater.inflate(R.menu.menu_driving_guide, menu);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_about) {
            Intent about = new Intent(context, About.class);
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
