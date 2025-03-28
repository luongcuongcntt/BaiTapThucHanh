package com.example.sharedprefapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var edtUsername: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnSave: Button
    private lateinit var btnDelete: Button
    private lateinit var btnShow: Button
    private lateinit var tvResult: TextView

    private lateinit var preferenceHelper: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ UI
        edtUsername = findViewById(R.id.edtUsername)
        edtPassword = findViewById(R.id.edtPassword)
        btnSave = findViewById(R.id.btnSave)
        btnDelete = findViewById(R.id.btnDelete)
        btnShow = findViewById(R.id.btnShow)
        tvResult = findViewById(R.id.tvResult)

        // Khởi tạo Shared Preferences Helper
        preferenceHelper = PreferenceHelper(this)

        // Xử lý lưu dữ liệu
        btnSave.setOnClickListener {
            val username = edtUsername.text.toString()
            val password = edtPassword.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            preferenceHelper.saveUser(username, password)
            Toast.makeText(this, "Đã lưu dữ liệu", Toast.LENGTH_SHORT).show()
        }

        // Xử lý hiển thị dữ liệu
        btnShow.setOnClickListener {
            val (username, password) = preferenceHelper.getUser()
            if (username != null && password != null) {
                tvResult.text = "Tên: $username\nMật khẩu: $password"
            } else {
                tvResult.text = "Không có dữ liệu"
            }
        }

        // Xử lý xóa dữ liệu
        btnDelete.setOnClickListener {
            preferenceHelper.clearUser()
            tvResult.text = "Dữ liệu đã bị xóa"
            Toast.makeText(this, "Đã xóa dữ liệu", Toast.LENGTH_SHORT).show()
        }
    }
}
