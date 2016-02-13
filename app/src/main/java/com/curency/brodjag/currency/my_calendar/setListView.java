package com.curency.brodjag.currency.my_calendar;

import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.curency.brodjag.currency.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by brodjag on 12.02.16.
 */
public class setListView {
    MainActivity activity;
   public setListView(MainActivity activity){
       this.activity=activity;
       setAdapter();
   }

    private void setAdapter() {
        final ListView list = (ListView) activity.findViewById(R.id.list);
        final ArrayList<Long> dates=new ArrayList<Long>();

        Calendar calendar=Calendar.getInstance();
        // calendar.setTimeInMillis(calendar.getTimeInMillis()+(1000 * 60 * 60 * 24)*10);
        calendar.add(Calendar.DATE,10);
        for(int i=0; i<(365+10); i++ ){
            calendar.add(Calendar.DATE,-1);
            dates.add(calendar.getTimeInMillis());
        }

        BoxAdapter adapter = new BoxAdapter(activity,dates);
        list.setAdapter(adapter);

        //Listener
        list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {}

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                View child;
                for (int i=0; i<(totalItemCount);i++){
                    child=view.getChildAt(i);
                    if (child!=null) {
                        if (i == (visibleItemCount/2) ) {
                            activity.selectedTime=dates.get(firstVisibleItem+i);
                            //child.setBackgroundColor(Color.RED);
                            child.findViewById(R.id.item1).setVisibility(View.VISIBLE);
                            child.findViewById(R.id.item0).setVisibility(View.GONE);
                        } else {
                            child.findViewById(R.id.item1).setVisibility(View.GONE);
                            child.findViewById(R.id.item0).setVisibility(View.VISIBLE);
                        }
                    }
                }

            }
        });
    }
}
