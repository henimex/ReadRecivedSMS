package com.devhen.broadcastsms

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.telephony.SmsMessage
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class SMSYakalayici : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        val b = intent?.extras
        if (b != null) {
            val pdusObj = b.get("pdus") as Array<Any>
            for (i in pdusObj.indices){
                val guncelMsg:SmsMessage
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    val format = b.getString("format")
                    guncelMsg = SmsMessage.createFromPdu(pdusObj[i] as ByteArray, format)
                }else{
                    guncelMsg = SmsMessage.createFromPdu(pdusObj[i] as ByteArray)
                }

                val telNo = guncelMsg.displayOriginatingAddress
                val mesaj = guncelMsg.displayMessageBody

                Toast.makeText(context, "Tel : $telNo Mesaj : $mesaj", Toast.LENGTH_SHORT).show()

                if (mesaj.contains("test")){
                    Toast.makeText(context, "içeriyor", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}