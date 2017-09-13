package jaihind.gobblessamerica.mywidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Created by nande on 9/12/2017.
 */

public class DiceWidget extends AppWidgetProvider {
    
    
    private int imageIds[] = new int[]{R.drawable.die_1,R.drawable.die_2,R.drawable.die_3,
                            R.drawable.die_4, R.drawable.die_5, R.drawable.die_6};
    
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        ComponentName componentName = new ComponentName(context, DiceWidget.class);
        appWidgetManager.updateAppWidget(componentName, buildRemoteView(context, appWidgetIds));
    }
    
    public RemoteViews buildRemoteView(Context context, int[] appWidgetIds) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.dice_widget);
    
        Intent i = new Intent(context, DiceWidget.class);
        i.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        i.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,appWidgetIds);
        PendingIntent pi =  PendingIntent.getBroadcast(context, 0, i, PendingIntent.FLAG_UPDATE_CURRENT) ;
        remoteViews.setImageViewResource(R.id.left_iv,imageIds[(int)(((Math.random()*6)+ (Math.random()*6))%6)]);
        remoteViews.setImageViewResource(R.id.right_iv, imageIds[(int)(Math.random()*6)]);
        remoteViews.setOnClickPendingIntent(R.id.left_iv,pi);
        remoteViews.setOnClickPendingIntent(R.id.right_iv, pi);
        remoteViews.setOnClickPendingIntent(R.id.background, pi);
        return remoteViews;
    }
}
