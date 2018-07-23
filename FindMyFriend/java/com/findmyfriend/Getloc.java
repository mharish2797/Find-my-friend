package com.findmyfriend;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by HP on 18-09-2016.
 */
public class Getloc extends Activity implements View.OnClickListener {
    Button jsendcontent;
    private ProgressBar jupload;
    private Handler mHandler = new Handler();
    String lats="",longs="",jnum;
    EditText jbusnum;
    double latit=0,longit=0;
    int m=0; Context ctx=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getloc);
        jsendcontent=(Button)findViewById(R.id.sendcontent);
        jsendcontent.setOnClickListener(this);
        jupload=(ProgressBar)findViewById(R.id.upload);
        jupload.setVisibility(View.GONE);
        jbusnum=(EditText)findViewById(R.id.busnum);
        jbusnum.setText(null);
    }

    @Override
    public void onClick(View v) {
        jupload.setVisibility(View.VISIBLE);
        m++;
        GPSTracker gps;
        gps=new GPSTracker(getApplicationContext());
        if (gps.canGetLocation()) {

            latit = gps.getLatitude();
            latit+=0.001;
            longit = gps.getLongitude();
            longit-=0.001;
            lats = String.valueOf(latit);
            longs = String.valueOf(longit);


            jnum = jbusnum.getText().toString();
            if (jnum.matches("")) Toast.makeText(this, "Enter your ID", Toast.LENGTH_LONG).show();
            else {
                OK();
                if (latit > 0.0 && longit > 0.0) {

                    boolean f = send(jnum, lats, longs);

                        Toast.makeText(this, "Thankyou for the updation", Toast.LENGTH_LONG).show();
                }
            }
        }
            else Toast.makeText(this, "Unable to access location. Try again!", Toast.LENGTH_LONG).show();




    }
    void OK(){
       // Toast.makeText(this,lats+" "+longs,Toast.LENGTH_LONG).show();
        //jupload.setVisibility(View.GONE);
    }
    boolean send(String busnum,String latit,String longit){
        Background b=new Background();
        b.execute(busnum,latit,longit);
        return true;
    }

    class Background extends AsyncTask<String,String,String> {
        @Override
        protected void onPostExecute(String s) {
            String x="";
            x=s;
            //if(x.equals(""))
           // x="Data registered !";
            //Toast.makeText(ctx,x,Toast.LENGTH_LONG).show();
            Intent intent = new Intent();
           intent.setClass(ctx, MainActivity.class);
            startActivity(intent);
        }


        @Override
        public String doInBackground(String... params) {

            String bbus=params[0],data="";
            String blat=params[1],blong=params[2];
            int tp;
            try {
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd.MM.yy");
                SimpleDateFormat sdf5 = new SimpleDateFormat("HH:mm");
                String dat=sdf1.format(new Date());
                String tim=sdf5.format(new Date());
               // Toast.makeText(this,s+" "+s1,Toast.LENGTH_LONG).show();

                URL url= new URL("http://mitcommuterpass.net16.net/friendtrack.php");
                String urlparams="friend="+bbus+"&latitude="+blat+"&longitude="+blong+"&date="+dat+"&time="+tim;
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os=httpURLConnection.getOutputStream();
                os.write(urlparams.getBytes());
                os.flush();
                os.close();
                InputStream is=httpURLConnection.getInputStream();
                while((tp=is.read())!=-1){
                    data+=(char)tp;
                }

                is.close();
                httpURLConnection.disconnect();
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return null;
        }
    }

}
