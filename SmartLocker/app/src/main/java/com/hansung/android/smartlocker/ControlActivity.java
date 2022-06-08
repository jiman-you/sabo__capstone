package com.hansung.android.smartlocker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

public class ControlActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        GridView menu = findViewById(R.id.homemenu);
        final String[] home = new String[]{"\n\n홈","\n\n사용자","\n\n지문","\n\n원격제어"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.menu,
                home);
        menu.setAdapter(adapter);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        String userPass = intent.getStringExtra("userPassword");
        String userName = intent.getStringExtra("userName");
        String userAge = intent.getStringExtra("userAge");

        Button contlorbtn = findViewById(R.id.contlor);
        contlorbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if (position == 0) { // 홈버튼
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.putExtra("userID",userID);
                    intent.putExtra("userPassword",userPass);
                    intent.putExtra("userName",userName);
                    intent.putExtra("userAge",userAge);
                    startActivity(intent);
                    finish();
                } else if (position == 1) {//사용자
                    Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                    intent.putExtra("userID",userID);
                    intent.putExtra("userPassword",userPass);
                    intent.putExtra("userName",userName);
                    intent.putExtra("userAge",userAge);
                    startActivity(intent);
                    finish();
                } else if (position == 2) {//지문
                    Intent intent = new Intent(getApplicationContext(), FingerActivity.class);
                    intent.putExtra("userID",userID);
                    intent.putExtra("userPassword",userPass);
                    intent.putExtra("userName",userName);
                    intent.putExtra("userAge",userAge);
                    startActivity(intent);
                    finish();
                } else if (position == 3) {//원격제어
                    Intent intent = new Intent(getApplicationContext(), ControlActivity.class);
                    intent.putExtra("userID",userID);
                    intent.putExtra("userPassword",userPass);
                    intent.putExtra("userName",userName);
                    intent.putExtra("userAge",userAge);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}