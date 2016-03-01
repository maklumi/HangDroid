package com.antonio.maklumi.hangdroid;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

/**
 * Created by Maklumi on 01-03-16.
 */
public class LoadFromAltLoc extends AppCompatActivity {

    private static final String REQUEST_PERKATAAN = "REQUEST_PERKATAAN";
    //a handle to the application's resources
        private Resources resources;

    public String getOutput() {
        return output;
    }

    //a string to output the contents of the files to LogCat
        private String output;
        private String aRandomWord;

        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.loadfromalternatelocation);

            //get the application's resources
            resources = getResources();
            aRandomWord="raaa";

            try
            {
                //Load the file from the raw folder - don't forget to OMIT the extension
                output = LoadFile("fourletterwords", true);
                //output to LogCat

                //setaRandomWord();
                Log.i("Loading", "File found in raw folder");
            }
            catch (IOException e)
            {
                //display an error toast message
                Toast toast = Toast.makeText(this, "File: not found in raw folder", Toast.LENGTH_LONG);
                toast.show();

            }

            try
            {
                //Load the file from assets folder - don't forget to INCLUDE the extension
                output = LoadFile("fourletterwords.txt", false);
                //output to LogCat
                Log.i("test", aRandomWord);
            }
            catch (IOException e)
            {
                //display an error toast message
              //  Toast toast = Toast.makeText(this, "File: not found in asset folder", Toast.LENGTH_LONG);
               // toast.show();
                Log.e("Loading", "File not found in asset folder");
            }

            //get the intent that start this activity
            Intent intent = getIntent();
            intent.putExtra(REQUEST_PERKATAAN, output );
            setResult(RESULT_OK, intent);
            finish();
        }

        private void setaRandomWord() {
            String wordlist = getOutput();
            String[] arrayWords = wordlist.split(" ");
            int totalWords = arrayWords.length;
            Random random = new Random();
            int arandomNumber = random.nextInt(totalWords - 1) + 1; // between 1 and (totalwod -1)
            aRandomWord =  arrayWords[arandomNumber].toLowerCase();
        }

        //load file from apps res/raw folder or Assets folder
        public String LoadFile(String fileName, boolean loadFromRawFolder) throws IOException
        {
            //Create a InputStream to read the file into
            InputStream iS;

            if (loadFromRawFolder)
            {
                //get the resource id from the file name
                int rID = resources.getIdentifier("com.antonio.maklumi.hangdroid:raw/"+fileName, null, null);
                //get the file as a stream
                iS = resources.openRawResource(rID);
            }
            else
            {
                //get the file as a stream
                iS = resources.getAssets().open(fileName);
            }

            //create a buffer that has the same size as the InputStream
            byte[] buffer = new byte[iS.available()];
            //read the text file as a stream, into the buffer
            iS.read(buffer);
            //create a output stream to write the buffer into
            ByteArrayOutputStream oS = new ByteArrayOutputStream();
            //write this buffer to the output stream
            oS.write(buffer);
            //Close the Input and Output streams
            oS.close();
            iS.close();

            //return the output stream as a String
            return oS.toString();
        }
    }
