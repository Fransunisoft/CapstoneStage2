package com.fransunisoft.drivingguide.Data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.fransunisoft.drivingguide.DrivingDetails;

public class DGprovider extends ContentProvider {

    DrivingGuideDBHelper dbHelper;
    SQLiteDatabase helper;


    static final int TUTORIALS = 100;
    static final int GUIDES = 101;

    static final int TUTORIAL_WITH_ID = 110;
    static final int GUIDE_WITH_ID = 111;


    private  UriMatcher buildUriMatcher(Uri uri){
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        final  String AUTHORITY = DGcontract.CONTENT_AUTHORITY;

        uriMatcher.addURI(AUTHORITY,DGcontract.TutorialsEntry.PATH_TUTORIALS,TUTORIALS);
        uriMatcher.addURI(AUTHORITY,DGcontract.TutorialsEntry.PATH_TUTORIALS +"/*",TUTORIAL_WITH_ID);

        uriMatcher.addURI(AUTHORITY,DGcontract.GuideEntry.PATH_GUIDES,GUIDES);
        uriMatcher.addURI(AUTHORITY,DGcontract.GuideEntry.PATH_GUIDES +"/*",GUIDE_WITH_ID);

        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DrivingGuideDBHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        helper = dbHelper.getWritableDatabase();
        UriMatcher matcher = buildUriMatcher(uri);
        Cursor retCursor = null;
        String[] stringselection = new String[1];

        switch(matcher.match(uri)){
            case TUTORIALS:
                retCursor = helper.query(DGcontract.TutorialsEntry.TABLE_NAME,null,null,null,null,null,sortOrder);
                return retCursor;
            case TUTORIAL_WITH_ID:
                stringselection[0] = String.valueOf(DGcontract.TutorialsEntry.getIdFromUri(uri));
                retCursor = helper.query(DGcontract.TutorialsEntry.TABLE_NAME,
                        null,
                        DGcontract.TutorialsEntry._ID + "=", stringselection, null, null, sortOrder);
                return retCursor;
            case GUIDES:
                retCursor = helper.query(DGcontract.GuideEntry.TABLE_NAME,null,null,null,null,null,sortOrder);
                return retCursor;
            case GUIDE_WITH_ID:
                stringselection[0] = String.valueOf(DGcontract.GuideEntry.getIdFromUri(uri));
                retCursor = helper.query(DGcontract.GuideEntry.TABLE_NAME,
                        null,
                        DGcontract.GuideEntry._ID + "=", stringselection, null, null, sortOrder);
                return retCursor;
            default:
                throw new UnsupportedOperationException ("Unknown uri: " + uri);
        }
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
