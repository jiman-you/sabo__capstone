package com.hansung.android.smartlocker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    private WebView webview;
    private String videoURL = "http://172.20.10.6:81/stream";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        GridView menu = findViewById(R.id.homemenu);
        final String[] dayOfTheWeek = new String[]{"\n\n홈","\n\n사용자","\n\n지문","\n\n원격제어"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.menu,
                dayOfTheWeek);
        menu.setAdapter(adapter);

        webview =findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(videoURL);
        webview.setWebChromeClient(new WebChromeClient());
        webview.setWebViewClient(new WebViewClientClass());

        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if (position == 0) { // 홈버튼
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else if (position == 1) {//사용자
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else if (position == 2) {//지문
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else if (position == 3) {//원격제어
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }


    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(videoURL);
            return true;
        }
    }


}