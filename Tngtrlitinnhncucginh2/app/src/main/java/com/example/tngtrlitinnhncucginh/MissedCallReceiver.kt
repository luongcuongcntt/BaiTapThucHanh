package com.example.tngtrlitinnhncucginh

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsManager
import android.telephony.TelephonyManager
import android.util.Log

class MissedCallReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == TelephonyManager.ACTION_PHONE_STATE_CHANGED) {
            val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
            val incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)

            if (state == TelephonyManager.EXTRA_STATE_RINGING) {
                Log.d("MissedCallReceiver", "Cuộc gọi đến từ: $incomingNumber")
            }

            if (state == TelephonyManager.EXTRA_STATE_IDLE && incomingNumber != null) {
                // Gửi tin nhắn khi cuộc gọi bị nhỡ
                sendAutoReplySMS(incomingNumber)
            }
        }
    }

    private fun sendAutoReplySMS(phoneNumber: String) {
        try {
            val smsManager = SmsManager.getDefault()
            val message = "Xin lỗi, tôi đang bận. Tôi sẽ gọi lại sau."
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
            Log.d("MissedCallReceiver", "Đã gửi tin nhắn đến: $phoneNumber")
        } catch (e: Exception) {
            Log.e("MissedCallReceiver", "Lỗi khi gửi tin nhắn: ${e.message}")
        }
    }
}
