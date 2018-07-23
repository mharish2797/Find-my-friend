package com.findmyfriend;
/**
 * Created by Harish on 18-09-2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class MainActivity extends Activity implements View.OnClickListener {
    RadioGroup jmode;
    Button jproceed,jshare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jproceed=(Button)findViewById(R.id.proceed);
        jproceed.setOnClickListener(this);
        jshare=(Button)findViewById(R.id.sharer);
        jshare.setOnClickListener(this);
        jmode=(RadioGroup)findViewById(R.id.mode);
        jmode.check(R.id.user);
    }

    @Override
    public void onClick(View v) {
        int r=v.getId();
        if(r==R.id.proceed) {
            Intent intent = new Intent();
            int select = jmode.getCheckedRadioButtonId();
            if (select == R.id.user)
                intent.setClass(this, track.class);
            else intent.setClass(this, Getloc.class);
            startActivity(intent);
        }
        else{
            Intent intent=new Intent();
            intent.setClass(this,Shareloc.class);
            startActivity(intent);
        }

    }
}
