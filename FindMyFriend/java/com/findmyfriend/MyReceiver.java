package com.findmyfriend;

/**
 * Created by Harish on 18-09-2016.
 */
        import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;

public class MyReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getBundleExtra("bundle");
        String sms = bundle.getString("sms");
        String mob = bundle.getString("mob");
        sendSMS(sms, mob);

    }

    private void sendSMS(String sms, String mob) {
        SmsManager.getDefault().sendTextMessage(mob, null, sms+"\n-Sent via Find My Friend Application", null, null);
    }
}
