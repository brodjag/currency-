package com.curency.brodjag.currency.curency;

import android.content.Context;
import android.util.Log;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.*;
import java.nio.charset.Charset;


/**
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 07.02.11
 * Time: 20:25
 * To change this template use File | Settings | File Templates.
 */
public class ajax {
    private Context context;

    //public ajax(Context con){context=con;}
    /*  public Element start(String url, Context con) {
          context = con;
          Element ret = (Element) new TaskAjax().execute(url);
          return ret;
      }
    */
    public InputStream is;
    public String text = "null";
    public Document document;

    public String[] getUrl(final String myurl) {

        try {
            URL url = new URL(myurl);
            url.getAuthority();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.connect();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                //InputStream
                is = conn.getInputStream();
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new InputStreamReader(is, "windows-1251"));
                    for (String line; (line = reader.readLine()) != null; ) {
                        line = line.replaceAll("[\\x80-\\xBF]|", "");
                        text=text+line;

                    }
                }catch (Exception e){return null;}

                int usdPos=text.indexOf("<CharCode>USD</CharCode>");
                int valPos=text.indexOf("<Value>",usdPos)+"<Value>".length();
                int endValPos=text.indexOf("</Value>", valPos);
                String  usd=text.substring(valPos, endValPos);

                 usdPos=text.indexOf("<CharCode>EUR</CharCode>");
                 valPos=text.indexOf("<Value>",usdPos)+"<Value>".length();
                 endValPos=text.indexOf("</Value>", valPos);
                String  eur=text.substring(valPos, endValPos);
                String[] res={usd,eur};
                //text=usd+"|"+eur;

                Log.d("sss1", "text="+text);
                is.close();
                return res;
            } else {
                text = " code 3" + conn.getResponseCode();

                return null;
            }


        } catch (Exception e) {
            //  text="catch exeption ";
            Log.d("sss1","is_redi 4 "+e.toString());
            return null;
        }

    }




}
