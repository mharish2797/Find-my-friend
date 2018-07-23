package com.findmyfriend;
/**
 * Created by Harish on 18-09-2016.
 */

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;


import java.util.Calendar;

public class Scheduler extends AppCompatActivity implements View.OnClickListener {
String mobl="",msg="";
    EditText contnet;
    Button btn;
    DatePicker dp;
    TimePicker tp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler);
        Bundle bundle=getIntent().getExtras();
        mobl=bundle.getString("Mobile");
        contnet=(EditText)findViewById(R.id.content);
        btn=(Button)findViewById(R.id.send);
        btn.setOnClickListener(this);
        dp=(DatePicker)findViewById(R.id.datePicker);
        tp=(TimePicker)findViewById(R.id.timePicker);

    }

    @Override
    public void onClick(View v) {

        Calendar cal = Calendar.getInstance();
        cal.set(5, dp.getDayOfMonth());
        cal.set(2, dp.getMonth());
        cal.set(1, dp.getYear());
        cal.set(11, tp.getCurrentHour().intValue());
        cal.set(12, tp.getCurrentMinute().intValue());
        cal.set(13, 0);
        Intent intent = new Intent(this, MyReceiver.class);
        Bundle bundle = new Bundle();
        bundle.putString("sms",mobl);
        bundle.putString("mob", msg);
        intent.putExtra("bundle", bundle);
      //  ((AlarmManager) this.getSystemService(NotificationCompatApi21.CATEGORY_ALARM)).set(0, cal.getTimeInMillis(), PendingIntent.getBroadcast(this, 1234, intent, 134217728));
        //this.dismiss();

    }
}
