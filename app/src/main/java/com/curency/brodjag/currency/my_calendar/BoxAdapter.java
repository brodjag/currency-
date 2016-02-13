package com.curency.brodjag.currency.my_calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.curency.brodjag.currency.R;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by brodjag on 12.02.16.
 */
public class BoxAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Long> objects;

    BoxAdapter(Context context, ArrayList<Long> m_dates) {
        ctx = context;
        objects =   m_dates;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {return objects.size();}


    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.item, null, false);
        }

        Long t_ms = objects.get(position);
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(t_ms);

        int day_of_week=calendar.get(Calendar.DAY_OF_WEEK);
        ((TextView) view.findViewById(R.id.week)).setText(getWeekDayName(day_of_week));
        ((TextView) view.findViewById(R.id.week1)).setText(getWeekDayName(day_of_week));

        if( day_of_week==Calendar.SUNDAY || day_of_week==Calendar.SATURDAY){
            ((TextView) view.findViewById(R.id.week)).setTextColor(ctx.getResources().getColor(R.color.m_text_red));
            ((TextView) view.findViewById(R.id.number)).setTextColor(ctx.getResources().getColor(R.color.m_text_red));
        }
        else {
            ((TextView) view.findViewById(R.id.week)).setTextColor(ctx.getResources().getColor(R.color.m_text));
            ((TextView) view.findViewById(R.id.number)).setTextColor(ctx.getResources().getColor(R.color.m_text));
        }

        ((TextView) view.findViewById(R.id.number)).setText(""+calendar.get(Calendar.DAY_OF_MONTH));
        ((TextView) view.findViewById(R.id.number1)).setText(""+calendar.get(Calendar.DAY_OF_MONTH));
        //((ImageView) view.findViewById(R.id.ivImage)).setImageResource(p.image);
        ((TextView) view.findViewById(R.id.year_month)).setText(calendar.get(Calendar.YEAR)+" "+getMonthName( calendar.get(Calendar.MONTH)));

//        parent.addView(view);
        return view;
    }

    String getWeekDayName(int n){
        DateFormatSymbols symbols = new DateFormatSymbols();
        String[] dayNames = symbols.getShortWeekdays();
        if(n>dayNames.length)return "??";
        return dayNames[n];
    }
    String getMonthName(int n){
        DateFormatSymbols symbols = new DateFormatSymbols();
        String[] dayNames = symbols.getShortMonths();
        if(n>dayNames.length)return "??";
        return dayNames[n];
    }


}
