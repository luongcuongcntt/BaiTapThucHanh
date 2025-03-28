package com.example.contentproviders

import android.Manifest
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit var listViewContacts: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Liên kết ListView
        listViewContacts = findViewById(R.id.listViewContacts)

        // Kiểm tra quyền truy cập danh bạ
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            loadContacts()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), 1)
        }
    }

    private fun loadContacts() {
        val contactList = ArrayList<String>()

        // Truy vấn danh bạ lấy ID và tên
        val cursor: Cursor? = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            arrayOf(ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME_PRIMARY),
            null, null, ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " ASC"
        )

        cursor?.use {
            while (it.moveToNext()) {
                val contactId = it.getString(0)
                val name = it.getString(1)

                // Truy vấn số điện thoại theo Contact ID
                val phoneCursor: Cursor? = contentResolver.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER),
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                    arrayOf(contactId),
                    null
                )

                phoneCursor?.use { phoneIt ->
                    while (phoneIt.moveToNext()) {
                        val phoneNumber = phoneIt.getString(0)
                        contactList.add("$name: $phoneNumber")
                    }
                }
            }
        }

        // Hiển thị danh bạ lên ListView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, contactList)
        listViewContacts.adapter = adapter
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            loadContacts()
        }
    }
}
