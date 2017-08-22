package com.fransunisoft.drivingguide.fragments;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fransunisoft.drivingguide.Data.DGcontract;
import com.fransunisoft.drivingguide.DrivingDetails;
import com.fransunisoft.drivingguide.Data.DrivingGuideDBHelper;
import com.fransunisoft.drivingguide.R;
import com.fransunisoft.drivingguide.SharedPreference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragmentutsnew extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private ArrayAdapter<String> mForecastAdapter;
    private DrivingGuideDBHelper DrivingGuideDBHelper;

    Context context ;
    boolean istwoPane;
    public ArrayList<Integer> IDs;
    public ArrayList<Cursor> cursors;
    Cursor c ,cSingle;
    String[] forecastArray;
    private static final int LOADER_ID = 0;
    View v;


    private OnFragmentInteractionListener mListener;
    private static OnItemClickListener listener;

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(context,DGcontract.TutorialsEntry.CONTENT_URI,null,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        c = data;
        if(c.moveToFirst()){
            forecastArray = new String[c.getCount()];
            for(int i = 0; i < c.getCount(); i ++){
                c.moveToPosition(i);
              //  cursors.add(c);

                forecastArray[i] = c.getString(c.getColumnIndex(DGcontract.TutorialsEntry.COLUMN_NAME));
            }
            startForReal();
        }



    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        c= null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String title, String content);
    }

    public interface OnItemClickListener {
        void onItemClick(String title, String content);
    }

    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////


    public Fragmentutsnew() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.fragment_fragmentutsnew, container, false);
        context = getContext();

        return  v;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(LOADER_ID, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    public void startForReal(){


        DrivingGuideDBHelper = new DrivingGuideDBHelper(context);
        IDs = new ArrayList<>();
        cursors = new ArrayList<>();
        istwoPane = getArguments().getBoolean("two");

        List<String> weekForecast;
        weekForecast = new ArrayList<String>(
                Arrays.asList(forecastArray));

        mForecastAdapter =
                new ArrayAdapter<String>(
                        context,
                        R.layout.list_item_forecast,
                        R.id.list_item_forecast_textview,
                        weekForecast);

        final ListView listView = (ListView) v.findViewById(
                R.id.list_tutorials);
        listView.setAdapter(mForecastAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                cSingle = getActivity().getContentResolver().query(DGcontract.TutorialsEntry.CONTENT_URI,
                        null,
                        null,
                        null,
                        null);

                cSingle.moveToPosition(position);

                String Title = cSingle.getString(cSingle.getColumnIndex(DGcontract.TutorialsEntry.COLUMN_NAME));
                String Content = cSingle.getString(cSingle.getColumnIndex(DGcontract.TutorialsEntry.COLUMN_TUTORIAL));
                if (listener != null) {
                    listener.onItemClick(Title, Content);
                    listView.setItemChecked(position,true);
                }

                if(!istwoPane) {
                    Intent intent = new Intent(context, DrivingDetails.class);
                    SharedPreference pref;
                    pref = new SharedPreference(context);
                    pref.saveManual(Title, Content);
                    startActivity(intent);
                }
            }
        });

    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}
