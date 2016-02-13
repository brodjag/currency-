package com.curency.brodjag.currency.curency;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.curency.brodjag.currency.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by brodjag on 13.02.16.
 */
public class mTask {
    Main2Activity activity;

    public mTask(Main2Activity c){
        activity=c;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String q_date= dateFormat.format(activity.calendar.getTime());
        Log.d("sss2", q_date);
        (new task()).execute(q_date);
        ((TextView) activity.findViewById(R.id.error)).setText("загрузка...");
    }


    class task extends AsyncTask<String,Void,String[]> {
        @Override
        protected String[] doInBackground(String... params) {

            final String url = "http://www.cbr.ru/scripts/XML_daily.asp?date_req="+params[0];
            ajax a=new ajax();
            String[] val= a.getUrl(url);
            return val;
        }


        @Override
        protected void onPostExecute(String[] result) {
            super.onPostExecute(result);
            if (result==null){
                activity.requestError();
                ((TextView) activity.findViewById(R.id.error)).setText("Ошибка! Кликните чтобы повторить запрос...");
            }else {
                activity.requestSucsses(result);
            }

          //  Toast.makeText(activity, result[0], Toast.LENGTH_LONG).show();
        }
    }
}
