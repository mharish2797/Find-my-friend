package com.findmyfriend;

/**
 * Created by Harish on 18-09-2016.
 */
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Shareloc extends AppCompatActivity implements View.OnClickListener {
EditText etx;
    Button btn;
    double latit,longit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shareloc);
        etx=(EditText)findViewById(R.id.texter);
        btn=(Button)findViewById(R.id.share);
        btn.setOnClickListener(this);
        GPSTracker gps;
        gps=new GPSTracker(getApplicationContext());
        if (gps.canGetLocation()) {

            latit = gps.getLatitude();
            // latit+=0.0001;
            longit = gps.getLongitude();
            // longit+=0.0001;
        }


    }

    @Override
    public void onClick(View v) {
        String gml="8144522262",lat,lon,text="Hey check out my location:\n www.google.co.in/maps/@"+latit+","+longit+",15z?hl=en\n\n-Sent via Find my friend Application";;
        gml=etx.getText().toString();
        SmsManager smsManager=SmsManager.getDefault();
        smsManager.sendTextMessage(gml, null, text, null, null);
        Toast.makeText(this,"Location shared successfully !",Toast.LENGTH_LONG).show();

    }
}
