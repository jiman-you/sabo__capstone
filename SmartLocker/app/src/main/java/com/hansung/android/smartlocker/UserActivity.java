package com.hansung.android.smartlocker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hansung.android.smartlocker.UI.UpdateShadow;
import com.hansung.android.smartlocker.UI.UserGetThingShadow;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class UserActivity extends AppCompatActivity {

    private TextView tv_id,tv_pass,tv_name,tv_age,tv_servo;
    String urlStr;
    final static String TAG = "AndroidAPITest";
    Timer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        urlStr = "https://gqazd8w3m4.execute-api.us-east-1.amazonaws.com/prod/devices/Sabo";
        timer = new Timer();
        timer.schedule(new TimerTask() {
                           @Override
                           public void run() {
                               new UserGetThingShadow(UserActivity.this, urlStr).execute();
                           }
                       },
                0, 2000);


        tv_id = findViewById(R.id.tv_id);
        tv_pass = findViewById(R.id.tv_pass);
        tv_age = findViewById(R.id.tv_age);
        tv_name = findViewById(R.id.tv_name);
        tv_servo = findViewById(R.id.servostate);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        String userPass = intent.getStringExtra("userPassword");
        String userName = intent.getStringExtra("userName");
        String userAge = intent.getStringExtra("userAge");

        tv_id.setText(userID);
        tv_pass.setText(userPass);
        tv_name.setText(userName);
        tv_age.setText(userAge);

        Button updateBtn1 = findViewById(R.id.updateBtn1);
        updateBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                JSONObject payload = new JSONObject();

                try {
                    JSONArray jsonArray = new JSONArray();
                    String servo_input = "ON";
                    if (servo_input != null && !servo_input.equals("")) {
                        JSONObject tag1 = new JSONObject();
                        tag1.put("tagName", "SERVO");
                        tag1.put("tagValue", servo_input);

                        jsonArray.put(tag1);
                    }

                    if (jsonArray.length() > 0)
                        payload.put("tags", jsonArray);
                } catch (JSONException e) {
                    Log.e(TAG, "JSONEXception");
                }
                Log.i(TAG, "payload=" + payload);
                if (payload.length() > 0)
                    new UpdateShadow(UserActivity.this, urlStr).execute(payload);
            }
        });
        Button updateBtn2 = findViewById(R.id.updateBtn2);
        updateBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                JSONObject payload = new JSONObject();

                try {
                    JSONArray jsonArray = new JSONArray();
                    String servo2_input = "OFF";
                    if (servo2_input != null && !servo2_input.equals("")) {
                        JSONObject tag2 = new JSONObject();
                        tag2.put("tagName", "SERVO");
                        tag2.put("tagValue", servo2_input);

                        jsonArray.put(tag2);
                    }

                    if (jsonArray.length() > 0)
                        payload.put("tags", jsonArray);
                } catch (JSONException e) {
                    Log.e(TAG, "JSONEXception");
                }
                Log.i(TAG, "payload=" + payload);
                if (payload.length() > 0)
                    new UpdateShadow(UserActivity.this, urlStr).execute(payload);
            }
        });


        Button homebtn = findViewById(R.id.homebtn);
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.putExtra("userID", userID);
                intent.putExtra("userPassword", userPass);
                intent.putExtra("userName", userName);
                intent.putExtra("userAge", userAge);
                startActivity(intent);
                finish();

            }
        });
        Button contlor = findViewById(R.id.controlbtn);
        contlor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                intent.putExtra("userID", userID);
                intent.putExtra("userPassword", userPass);
                intent.putExtra("userName", userName);
                intent.putExtra("userAge", userAge);
                startActivity(intent);
                finish();

            }
        });

    }

}