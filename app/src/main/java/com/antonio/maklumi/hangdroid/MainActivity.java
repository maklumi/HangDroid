package com.antonio.maklumi.hangdroid;

import android.content.Intent;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.PersistableBundle;
import android.support.v4.content.ContextCompat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements PapanKetik.OnUsingPapanKetikListener{
    public static final String TAG = "MainActivity";
    private static final String POINT_EXTRA = "POINT_EXTRA";

    private String perkataan;

    private boolean tekaHurufBetul;
    private int bilanganHuruf;
    private int bilanganTekaanSalah;
    private int bilanganTekaanBetul;
    private ArrayList<Drawable> lapisan = new ArrayList<>();
    private LinearLayout linearLayoutPetakTeka;


    private String semuaHurufYangPernahDiteka;
    private ImageView gambar;
    private boolean isPlaying;

    PapanKetik papanKetik;
    private int winCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            winCount = savedInstanceState.getInt(POINT_EXTRA);
        }
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        linearLayoutPetakTeka = (LinearLayout) findViewById(R.id.layoutLetters);
        gambar = (ImageView) findViewById(R.id.imageView);

        semuaHurufYangPernahDiteka = "";
        perkataan = "suku";
        bilanganHuruf = perkataan.length();
        isPlaying = true; //true : hide refresh menu, false: hide it

        papanKetik = (PapanKetik) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_keyboard);

        //setPerkataanRandom();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        //save current game score
        outState.putInt(POINT_EXTRA, winCount);
        super.onSaveInstanceState(outState, outPersistentState);

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        mulaSemula();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        if (isPlaying) {
            menu.getItem(0).setVisible(false);
        } else {
            menu.getItem(0).setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.refresh) {
            mulaSemula();
        }
        return super.onOptionsItemSelected(item);
    }

    public void setPerkataanRandom() {
        LoadFromAltLoc loadFromAltLoc = new LoadFromAltLoc();
        String words = loadFromAltLoc.getOutput();

        String[] arrayWords = words.split(" ");

        for (int i = 0; i < arrayWords.length; i++) {
            if (arrayWords[i].length() != 4)
                Log.d(TAG, "some words too long");
        }
        Log.d(TAG, "all words are 4 letter long");
    }

    public void tapisHuruf(String hurufDiberi){
        if (semuaHurufYangPernahDiteka.contains(hurufDiberi)) return;

        semuaHurufYangPernahDiteka += hurufDiberi;

        if (hurufDiberi.length() ==1) {
            periksaHuruf(hurufDiberi);
        }

    }

    /**
     * bandingkan huruf yang diberi dengan perkataan yang perlu diteka
     * @param hurufDiberi
     */
    private void periksaHuruf(String hurufDiberi) {
        char hurufTeka = hurufDiberi.charAt(0);

        tekaHurufBetul = false;
        for (int i = 0; i < bilanganHuruf; i++) {
            char hurufRujukan = perkataan.charAt(i);

            if (hurufTeka == hurufRujukan) {
                updatePetakTeka(i, hurufRujukan);
                tekaHurufBetul = true;
                bilanganTekaanBetul++;
            }
        }

        if (!tekaHurufBetul)  {
            bilanganTekaanSalah++;
            updateGambarGunaLayerDrawable(bilanganTekaanSalah);
        }

        periksaMenangKalah();
    }

    private void periksaMenangKalah() {
        if (bilanganTekaanBetul == bilanganHuruf) {
            Toast.makeText(this, "Menang", Toast.LENGTH_LONG).show();
            //TODO animation win
            isPlaying = false;
            supportInvalidateOptionsMenu();
            winCount++;
            Intent intent = new Intent(this, GameOverActivity.class);
            intent.putExtra(POINT_EXTRA, winCount);
            startActivity(intent);
        } else if (bilanganTekaanSalah == 6) {
            Toast.makeText(this, "Cuba lagi", Toast.LENGTH_LONG).show();
            gambar.setImageResource(R.drawable.android_dead);

            isPlaying = false;
            supportInvalidateOptionsMenu();
            Intent intent = new Intent(this, GameOverActivity.class);
            intent.putExtra(POINT_EXTRA, winCount);
            startActivity(intent);
        }
    }

    private void mulaSemula() {
        for (int i = 0; i < bilanganHuruf; i++) {
            TextView textView = (TextView) linearLayoutPetakTeka.getChildAt(i);
            textView.setText("_");
        }

        gambar.setImageResource(R.drawable.hangman);
        lapisan.clear();
        semuaHurufYangPernahDiteka = "";
        bilanganTekaanSalah = 0;
        bilanganTekaanBetul = 0;
        papanKetik.enableKey();

    }

     private void updateGambarGunaLayerDrawable(int bilanganTekaanSalah) {

         switch (bilanganTekaanSalah){
            case 1:
                lapisan.add(ContextCompat.getDrawable(this, R.drawable.androidhead));
                break;
            case 2:
                lapisan.add(ContextCompat.getDrawable(this, R.drawable.androidbody));
                break;
            case 3:
                lapisan.add(ContextCompat.getDrawable(this, R.drawable.androidrightarm));
                break;
            case 4:
                lapisan.add(ContextCompat.getDrawable(this, R.drawable.androidleftarm));
                break;
            case 5:
                lapisan.add(ContextCompat.getDrawable(this, R.drawable.androidrightleg));
                break;
            case 6:
                lapisan.add(ContextCompat.getDrawable(this, R.drawable.androidleftleg));
                break;
        }

        Drawable[] simpleArray = new Drawable[lapisan.size()];
        lapisan.toArray(simpleArray);
        LayerDrawable layerDrawable = new LayerDrawable(simpleArray);
        gambar.setImageDrawable(layerDrawable);
    }

    /**
     * Tunjukkan huruf ke dalam petak textview yang betul.
     * @param posisi
     * @param hurufRujukan
     */
    private void updatePetakTeka(int posisi, char hurufRujukan) {

        TextView textView = (TextView) linearLayoutPetakTeka.getChildAt(posisi);

        textView.setText(String.valueOf(hurufRujukan));

    }

    @Override
    public void hurufDitaip(String huruf) {
        tapisHuruf(huruf);

        papanKetik.disableKey(huruf);
    }
}



























