package com.antonio.maklumi.hangdroid;

/**
 * Created by Maklumi on 29-02-16.
 */


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;


public class PapanKetik extends AppCompatActivity implements OnClickListener {

    private String sL[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
            "x", "y", "z"};

    private Button mB[] = new Button[26];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideDefaultKeyboard();
        setKeys();
    }

    private void addText(View v) {

        String b = "";
        b = (String) v.getTag();
        if (b != null) {
            Log.d("Papan", b);
        }
    }


    private void hideDefaultKeyboard() {
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void setKeys() {

        // getting ids from xml files
        mB[0] = (Button) findViewById(R.id.xa);
        mB[1] = (Button) findViewById(R.id.xb);
        mB[2] = (Button) findViewById(R.id.xc);
        mB[3] = (Button) findViewById(R.id.xd);
        mB[4] = (Button) findViewById(R.id.xe);
        mB[5] = (Button) findViewById(R.id.xf);
        mB[6] = (Button) findViewById(R.id.xg);
        mB[7] = (Button) findViewById(R.id.xh);
        mB[8] = (Button) findViewById(R.id.xi);
        mB[9] = (Button) findViewById(R.id.xj);
        mB[10] = (Button) findViewById(R.id.xk);
        mB[11] = (Button) findViewById(R.id.xl);
        mB[12] = (Button) findViewById(R.id.xm);
        mB[13] = (Button) findViewById(R.id.xn);
        mB[14] = (Button) findViewById(R.id.xo);
        mB[15] = (Button) findViewById(R.id.xp);
        mB[16] = (Button) findViewById(R.id.xq);
        mB[17] = (Button) findViewById(R.id.xr);
        mB[18] = (Button) findViewById(R.id.xs);
        mB[19] = (Button) findViewById(R.id.xt);
        mB[20] = (Button) findViewById(R.id.xu);
        mB[21] = (Button) findViewById(R.id.xv);
        mB[22] = (Button) findViewById(R.id.xw);
        mB[23] = (Button) findViewById(R.id.xx);
        mB[24] = (Button) findViewById(R.id.xy);
        mB[25] = (Button) findViewById(R.id.xz);

        for (int i = 0; i < mB.length; i++)
            mB[i].setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        addText(v);
    }
}
