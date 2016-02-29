package com.antonio.maklumi.hangdroid;

/**
 * Created by Maklumi on 29-02-16.
 */


import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;



public class PapanKetik extends Fragment implements OnClickListener {

    OnUsingPapanKetikListener mCallback;

    //Container activity must implement this interface
    public interface OnUsingPapanKetikListener{
         void hurufDitaip(String huruf);
    }

    private String huruf;

    private Button mB[] = new Button[26];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.keyboard, container, false);

    }

    @Override
    public void onResume() {
        super.onResume();
        hideDefaultKeyboard();
        setKeys();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnUsingPapanKetikListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    " must implement OnUsingPapanKetikListener.");
        }
    }

    public void disableKey(String key){
        for (int i = 0; i < mB.length; i++) {
           if ( mB[i].getTag() == key ) {
               int colorInt = getResources().getColor(R.color.sedikitGrey);
               mB[i].getBackground().setColorFilter(colorInt, PorterDuff.Mode.MULTIPLY);
               mB[i].setEnabled(false);

           }
        }
    }

    public void enableKey(){
        for (int i = 0; i < mB.length; i++) {
            mB[i].getBackground().setColorFilter(null);
            mB[i].setEnabled(true);
        }
    }

    private void addText(View v) {
        String b = "";
        b = (String) v.getTag();
        if (b != null) {
            huruf = b;
            //send to calling activity
            mCallback.hurufDitaip(huruf);
        }
    }


    private void hideDefaultKeyboard() {
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void setKeys() {

        // getting ids from xml files
        mB[0] = (Button) getView().findViewById(R.id.xa);
        mB[1] = (Button) getView().findViewById(R.id.xb);
        mB[2] = (Button) getView().findViewById(R.id.xc);
        mB[3] = (Button) getView().findViewById(R.id.xd);
        mB[4] = (Button) getView().findViewById(R.id.xe);
        mB[5] = (Button) getView().findViewById(R.id.xf);
        mB[6] = (Button) getView().findViewById(R.id.xg);
        mB[7] = (Button) getView().findViewById(R.id.xh);
        mB[8] = (Button) getView().findViewById(R.id.xi);
        mB[9] = (Button) getView().findViewById(R.id.xj);
        mB[10] = (Button) getView().findViewById(R.id.xk);
        mB[11] = (Button) getView().findViewById(R.id.xl);
        mB[12] = (Button) getView().findViewById(R.id.xm);
        mB[13] = (Button) getView().findViewById(R.id.xn);
        mB[14] = (Button) getView().findViewById(R.id.xo);
        mB[15] = (Button) getView().findViewById(R.id.xp);
        mB[16] = (Button) getView().findViewById(R.id.xq);
        mB[17] = (Button) getView().findViewById(R.id.xr);
        mB[18] = (Button) getView().findViewById(R.id.xs);
        mB[19] = (Button) getView().findViewById(R.id.xt);
        mB[20] = (Button) getView().findViewById(R.id.xu);
        mB[21] = (Button) getView().findViewById(R.id.xv);
        mB[22] = (Button) getView().findViewById(R.id.xw);
        mB[23] = (Button) getView().findViewById(R.id.xx);
        mB[24] = (Button) getView().findViewById(R.id.xy);
        mB[25] = (Button) getView().findViewById(R.id.xz);

        for (int i = 0; i < mB.length; i++)
            mB[i].setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        addText(v);
    }
}
