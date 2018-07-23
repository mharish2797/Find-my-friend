package com.findmyfriend;
/**
 * Created by Harish on 18-09-2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Plan extends Activity implements View.OnClickListener {
EditText emob;
    Button bcall,bsched;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        emob=(EditText)findViewById(R.id.textin);
        bcall=(Button)findViewById(R.id.pcall);
        bsched=(Button)findViewById(R.id.scheds);
        bcall.setOnClickListener(this);
        bsched.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String txtmb="";
        txtmb=emob.getText().toString();
        if(v.getId()==R.id.pcall){
            Intent in = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+txtmb));
            try {

                startActivity(in);
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(this, "yourActivity is not founded", Toast.LENGTH_SHORT).show();
            }
        }
        else{
                Intent intent=new Intent();
            intent.putExtra("Mobile",txtmb);
            intent.setClass(this,Scheduler.class);
            startActivity(intent);
        }

    }
}
