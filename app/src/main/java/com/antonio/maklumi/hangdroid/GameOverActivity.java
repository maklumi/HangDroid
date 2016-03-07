package com.antonio.maklumi.hangdroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Maklumi on 29-02-16.
 */
public class GameOverActivity extends AppCompatActivity {
    int markah;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_game_over_toolbar);
        setSupportActionBar(toolbar);

        markah = getIntent().getIntExtra("POINT_EXTRA", 0);

        TextView textView = (TextView) findViewById(R.id.activity_game_over_score);
        textView.setText(String.valueOf(markah));



    }

    public void saveScore(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("SCOREPREF", Context.MODE_PRIVATE);

        EditText editText = (EditText) findViewById(R.id.activity_game_over_nama_editText);
        String name = editText.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();

        String previousScore = sharedPreferences.getString("SCORES", "");

        editor.putString("SCORES", name + " " + markah + " POIN\n" + previousScore);
        editor.commit();

        showScore();

    }

    public void showScore() {
        SharedPreferences preferences = getSharedPreferences("SCOREPREF", MODE_PRIVATE);
        String scores = preferences.getString("SCORES", "Tiada");

        TextView textView = (TextView) findViewById(R.id.activity_game_over_textview_showscore);
        textView.setText(scores);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.refresh:

                finish();
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);

    }
}
