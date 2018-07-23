package com.findmyfriend;
/**
 * Created by Harish on 18-09-2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;

public class track extends Activity implements View.OnClickListener {
    EditText fmail;
    Button track,sched;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        fmail=(EditText)findViewById(R.id.fmail);
        track=(Button)findViewById(R.id.track);
        sched=(Button)findViewById(R.id.schedule);
        sched.setOnClickListener(this);
        track.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int r=v.getId();
        switch(r) {

            case R.id.track:
            String sf = fmail.getText().toString();
            boolean rks = true;
            int i = 0;
            Intent intent = new Intent();
            intent.setClass(this, Mapbus.class);
            if (sf.matches("")) Toast.makeText(this, "Empty String !", Toast.LENGTH_LONG).show();
            else {
                String[] arr = new String[100];
                StringTokenizer stx = new StringTokenizer(sf);
                if (stx.countTokens() > 0) {
                    while (stx.hasMoreTokens()) {
                        arr[i++] = stx.nextToken(",");
                    }
                    intent.putExtra("N", i);
                    for (int j = 0; j < i; j++) {
                        try {
                            String ffx = new Receive(this).execute(arr[j]).get();
                            if (!ffx.contains("Error")) {
                                intent.putExtra(String.valueOf(j) + "mail", arr[j]);
                                intent.putExtra(String.valueOf(j) + "gets", ffx);
                            } else {
                                rks = false;
                                Toast.makeText(this, "Error occured check network connection", Toast.LENGTH_LONG).show();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                    }

                }
                if (rks) startActivity(intent);

            }
                break;
            default :
                Intent intent1=new Intent();
                intent1.setClass(this,Plan.class);
                startActivity(intent1);
                break;

        }
    }



}
