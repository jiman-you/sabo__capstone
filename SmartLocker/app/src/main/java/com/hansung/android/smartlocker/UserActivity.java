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
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserActivity extends AppCompatActivity {

    private TextView tv_id,tv_pass,tv_name,tv_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        GridView menu = findViewById(R.id.homemenu);
        final String[] home = new String[]{"\n\n홈","\n\n사용자","\n\n지문","\n\n원격제어"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.menu,
                home);
        menu.setAdapter(adapter);

        tv_id = findViewById(R.id.tv_id);
        tv_pass = findViewById(R.id.tv_pass);
        tv_age = findViewById(R.id.tv_age);
        tv_name = findViewById(R.id.tv_name);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        String userPass = intent.getStringExtra("userPassword");
        String userName = intent.getStringExtra("userName");
        String userAge = intent.getStringExtra("userAge");

        tv_id.setText(userID);
        tv_pass.setText(userPass);
        tv_name.setText(userName);
        tv_age.setText(userAge);

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