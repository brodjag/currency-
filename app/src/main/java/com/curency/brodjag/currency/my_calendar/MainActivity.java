package com.curency.brodjag.currency.my_calendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.curency.brodjag.currency.R;
import com.curency.brodjag.currency.curency.Main2Activity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

   public Long selectedTime=0l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new setListView(this);

        findViewById(R.id.rate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                calendar.setTimeInMillis(selectedTime);
                Intent intent=new Intent(getBaseContext(), Main2Activity.class);
                intent.putExtra("time_ms",selectedTime);
                startActivity(intent);
               // Toast.makeText(getBaseContext(),""+selectedTime,Toast.LENGTH_LONG).show();
           //   Toast.makeText(getBaseContext(),""+calendar.get(Calendar.DAY_OF_MONTH),Toast.LENGTH_LONG).show();
            }
        });
    }








}
