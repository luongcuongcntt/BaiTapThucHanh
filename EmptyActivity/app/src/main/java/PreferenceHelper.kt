package com.example.sharedprefapp

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper(context: Context) {
    private val PREF_NAME = "user_prefs"
    private val USERNAME_KEY = "username"
    private val PASSWORD_KEY = "password"

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    // Lưu dữ liệu
    fun saveUser(username: String, password: String) {
        editor.putString(USERNAME_KEY, username)
        editor.putString(PASSWORD_KEY, password)
        editor.apply() // Lưu thay đổi bất đồng bộ
    }

    // Lấy dữ liệu
    fun getUser(): Pair<String?, String?> {
        val username = sharedPreferences.getString(USERNAME_KEY, null)
        val password = sharedPreferences.getString(PASSWORD_KEY, null)
        return Pair(username, password)
    }

    // Xóa dữ liệu
    fun clearUser() {
        editor.clear()
        editor.apply()
    }
}
