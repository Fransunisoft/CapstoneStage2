package com.fransunisoft.drivingguide.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.RemoteViews;

import com.fransunisoft.drivingguide.Data.DGcontract;
import com.fransunisoft.drivingguide.DrivingDetails;
import com.fransunisoft.drivingguide.R;
import com.fransunisoft.drivingguide.Tutorials;
import com.fransunisoft.drivingguide.fragments.DrivDetFrag;

import java.util.Random;

public class WidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int count = appWidgetIds.length;
        Cursor c = null;

        for (int i = 0; i < count; i++) {
            int widgetId = appWidgetIds[i];

            c  = context.getContentResolver().query(DGcontract.TutorialsEntry.CONTENT_URI,null,null,null,null);
            if(c.moveToFirst()){
            Random r = new Random();
            int rand = r.nextInt(c.getCount());
            c.moveToPosition(rand);

            String title = c.getString(c.getColumnIndex(DGcontract.TutorialsEntry.COLUMN_NAME));
            String content = c.getString(c.getColumnIndex(DGcontract.TutorialsEntry.COLUMN_TUTORIAL));

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.widget_today);
            remoteViews.setTextViewText(R.id.widget_title, title);
            remoteViews.setTextViewText(R.id.widget_content,content);
            Intent intent = new Intent(context, Tutorials.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,
                    0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.widget_content, pendingIntent);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);
            }
        }
        c.close();
    }
}

