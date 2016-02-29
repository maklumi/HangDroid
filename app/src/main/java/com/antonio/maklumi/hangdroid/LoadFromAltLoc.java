package com.antonio.maklumi.hangdroid;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Maklumi on 01-03-16.
 */
public class LoadFromAltLoc extends AppCompatActivity {

        //a handle to the application's resources
        private Resources resources;

    public String getOutput() {
        return output;
    }

    //a string to output the contents of the files to LogCat
        private String output;

        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            //get the application's resources
            resources = getResources();

            try
            {
                //Load the file from the raw folder - don't forget to OMIT the extension
                output = LoadFile("fourletterwords", true);
                //output to LogCat
                Log.i("test", output);
            }
            catch (IOException e)
            {
                //display an error toast message
                Toast toast = Toast.makeText(this, "File: not found!", Toast.LENGTH_LONG);
                toast.show();
            }

            try
            {
                //Load the file from assets folder - don't forget to INCLUDE the extension
                output = LoadFile("from_assets_folder.txt", false);
                //output to LogCat
                Log.i("test", output);
            }
            catch (IOException e)
            {
                //display an error toast message
                Toast toast = Toast.makeText(this, "File: not found!", Toast.LENGTH_LONG);
                toast.show();
            }
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
