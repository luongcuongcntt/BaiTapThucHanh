package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.our_1.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnLoad = findViewById<Button>(R.id.btnLoad)
        val edtPhone = findViewById<EditText>(R.id.edtPhone)
        val txtInfo = findViewById<TextView>(R.id.txtInfo)

        btnSave.setOnClickListener {
            val phone = edtPhone.text.toString()
            val sharedPreferences = getSharedPreferences("ANHYEUEM", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("PHONE", phone)
            editor.apply()
        }

        btnLoad.setOnClickListener {
            val sharedPreferences = getSharedPreferences("ANHYEUEM", MODE_PRIVATE)
            val phone = sharedPreferences.getString("PHONE", "")
            txtInfo.text = phone
        }
    }
}