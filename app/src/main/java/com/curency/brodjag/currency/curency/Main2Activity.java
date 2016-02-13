package com.curency.brodjag.currency.curency;


import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.curency.brodjag.currency.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Main2Activity extends AppCompatActivity {
    public Long time_ms=0l;
    public Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



                setContentView(R.layout.activity_main2);
        time_ms=getIntent().getLongExtra("time_ms",0l);

        calendar=Calendar.getInstance();
        calendar.setTimeInMillis(time_ms);

        new  mTask(this);
        findViewById(R.id.error).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new mTask(Main2Activity.this);
            }
        });
    }


    public void requestSucsses(String[] res){
    findViewById(R.id.success).setVisibility(View.VISIBLE);
    findViewById(R.id.error).setVisibility(View.GONE);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        ((TextView) findViewById(R.id.date1)).setText(dateFormat.format(calendar.getTime()));
        ((TextView) findViewById(R.id.eur1)).setText("1 Eur = "+res[1]+" Rub");
        ((TextView) findViewById(R.id.usd1)).setText("1 $ = " + res[0] + " Rub");

    }

    public void requestError(){
        findViewById(R.id.success).setVisibility(View.GONE);
        findViewById(R.id.error).setVisibility(View.VISIBLE);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.empty_menu, menu);
        return true;
    }






}
