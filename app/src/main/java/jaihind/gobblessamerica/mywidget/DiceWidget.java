package jaihind.gobblessamerica.mywidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;

/**
 * Created by nande on 9/12/2017.
 */

public class DiceWidget extends AppWidgetProvider {
    
    public static final String EXTRA_TEXT = "Result_text_view"
            ,EXTRA_IMAGE_ID = "Result_image_id";
    
    private int imageIds[] = new int[]{R.drawable.die_1,R.drawable.die_2,R.drawable.die_3,
                            R.drawable.die_4, R.drawable.die_5, R.drawable.die_6};
    
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
       
            ComponentName componentName = new ComponentName(context, DiceWidget.class);
            appWidgetManager.updateAppWidget(componentName, buildRemoteView(context, appWidgetIds));
        
    }
    
    //called when widget is added and resized
    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        ComponentName componentName = new ComponentName(context, DiceWidget.class);
        int[] widgetId  = {appWidgetId};
        appWidgetManager.updateAppWidget(componentName, buildRemoteView(context, widgetId));
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
    
        // Sets up the intent that points to the StackViewService that will
        // provide the views for this collection.
        Intent serviceIntent = new Intent(context.getApplicationContext(), DiceWidgetService.class);
        serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
        // When intents are compared, the extras are ignored, so we need to embed the extras
        // into the data so that the extras will not be ignored.
        serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));
        remoteViews.setRemoteAdapter(R.id.widget_lv, serviceIntent);
        //set up template for handling click events
        Intent templateIntent = new Intent(context, ToastActivity.class);
        PendingIntent templatePendingIntent
                = PendingIntent.getActivity(context, 0 , templateIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setPendingIntentTemplate(R.id.widget_lv, templatePendingIntent);
        return remoteViews;
    }
}
