package com.example.teacher.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

// Teacher was here
public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button buttonAbout;
    private int clicks = 0;
    private static final String TAG = "MAINACTIVITY";
    public static final String APP_PREFERENCES = "myclicks"; // Название файла настроек
    public static final String APP_PREFERENCES_CLICKS = "clicks"; // Поле для хранения кликов
    SharedPreferences mClicks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mClicks = getSharedPreferences(APP_PREFERENCES, getApplicationContext().MODE_PRIVATE);
        textView = (TextView) findViewById(R.id.textView);
        buttonAbout = (Button) findViewById(R.id.button_about);

        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = mClicks.edit();
        editor.putInt(APP_PREFERENCES_CLICKS, clicks);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mClicks.contains(APP_PREFERENCES_CLICKS)) {
            textView.setText(Integer.toString(mClicks.getInt(APP_PREFERENCES_CLICKS, 0)));
            clicks = mClicks.getInt(APP_PREFERENCES_CLICKS, 0);
        }
    }

    public void click(View view) {
        textView.setText(Integer.toString(++clicks));
        Log.d(TAG, ""+clicks);
    }
}
