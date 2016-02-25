package com.antonio.maklumi.hangdroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void bilaButangOKDiTekan(View view) {
        EditText editText = (EditText) findViewById(R.id.editText);

        String hurufDiberi = editText.getText().toString();

        Log.d(TAG, hurufDiberi);

    }
}
