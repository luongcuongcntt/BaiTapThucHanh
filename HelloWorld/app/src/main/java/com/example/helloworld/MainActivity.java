package com.example.helloworld;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    // Biến lưu cửa sổ EditText để xử lý
    EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //Thiết lập giao diện
        setContentView(R.layout.activity_main);
        // Lấy cửa sổ EditText
        name = (EditText) findViewById(R.id.editName);
        // Xử lý sự kiện bấm nút thoát
        findViewById(R.id.btnThoat).setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){ finish();}
                });
        // Xử lý sự kiện bấm nút hello
        findViewById(R.id.btnHello).setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        String ten = name.getText().toString();
                        Toast.makeText(MainActivity.this, "Chào bạn "
                                + ten, Toast.LENGTH_LONG).show();
                    }
                });
    }
}