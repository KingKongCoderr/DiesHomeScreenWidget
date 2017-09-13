package jaihind.gobblessamerica.mywidget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

/**
 * Created by nande on 9/13/2017.
 */

public class DiceWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
       return new DiceWidgetFactory(getApplicationContext());
    }
}
class DiceWidgetFactory implements RemoteViewsService.RemoteViewsFactory{
    private int imageIds[] = new int[]{R.drawable.die_1,R.drawable.die_2,R.drawable.die_3,
            R.drawable.die_4, R.drawable.die_5, R.drawable.die_6};
    private Context context;
    
    public DiceWidgetFactory(Context context) {
        this.context = context;
    }
    
    @Override
    public void onCreate() {
        
    }
    
    @Override
    public void onDataSetChanged() {
        
    }
    
    @Override
    public void onDestroy() {
        
    }
    
    @Override
    public int getCount() {
        return imageIds.length;
    }
    
    @Override
    public RemoteViews getViewAt(int i) {
        
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_item);
        String label_text = String.valueOf(i+1)+":";
        rv.setTextViewText(R.id.item_label, label_text );
        rv.setImageViewResource(R.id.item_iv, imageIds[i]);
        // Next, set a fill-intent, which will be used to fill in the pending intent template
        // that is set on the collection view in StackWidgetProvider.
        Bundle extras = new Bundle();
        extras.putInt(DiceWidget.EXTRA_IMAGE_ID, imageIds[i]);
        extras.putString(DiceWidget.EXTRA_TEXT, label_text );
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);
        rv.setOnClickFillInIntent(R.id.item_relativeLayout, fillInIntent);
        return rv;
    }
    
    //displayed by system when loading something if given null it system default view
    @Override
    public RemoteViews getLoadingView() {
        return null;
    }
    
    @Override
    public int getViewTypeCount() {
        return 1;
    }
    
    @Override
    public long getItemId(int i) {
        return i;
    }
    
    @Override
    public boolean hasStableIds() {
        return true;
    }
}
