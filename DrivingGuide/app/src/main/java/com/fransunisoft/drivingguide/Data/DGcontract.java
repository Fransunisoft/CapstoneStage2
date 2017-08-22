package com.fransunisoft.drivingguide.Data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class DGcontract {
        public static String DATABASE_NAME = "DrivingGuide.db";
    public static final String CONTENT_AUTHORITY = "com.fransunisoft.drivingguide";
    public static final Uri BASE_URI = Uri.parse("content://"+ CONTENT_AUTHORITY);

    public static class TutorialsEntry implements BaseColumns{
        public static final String PATH_TUTORIALS = "Tutorials";
        public static final Uri CONTENT_URI = BASE_URI.buildUpon().appendPath(PATH_TUTORIALS).build();

        public static String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TUTORIALS;

        public static Uri buildUriForTutorialWithID(long id){
            return CONTENT_URI.buildUpon().appendPath(Long.toString(id)).build();
        }

        public static long getIdFromUri(Uri uri){
            return Long.parseLong(uri.getPathSegments().get(1));
        }


        public static String TABLE_NAME = "Tutorials";

        public static String COLUMN_NAME = "Name";
        public static String COLUMN_TUTORIAL = "Tutorial";

    }
    public static class GuideEntry implements BaseColumns{

        public static final String PATH_GUIDES = "Guide";
        public static final Uri CONTENT_URI = BASE_URI.buildUpon().appendPath(PATH_GUIDES).build();

        public static String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GUIDES;

        public Uri buildUriForGuidesWithID(long id){
            return CONTENT_URI.buildUpon().appendPath(Long.toString(id)).build();
        }

        public static long getIdFromUri(Uri uri){
            return Long.parseLong(uri.getPathSegments().get(1));
        }


        public static String TABLE_NAME = "Guide";

        public static String COLUMN_NAME = "Name";
        public static String COLUMN_TUTORIAL = "Tutorial";
    }
}
