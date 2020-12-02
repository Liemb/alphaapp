package com.example.alphaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;

public class csv extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csv);
    }

    /**
     * Generating data
     * Saving the file into device and exporting
     * @param view
     */
    public void csvclick(View view) {
        StringBuilder data = new StringBuilder();
        data.append("Time,Distance");
        for (int i=0; i<5; i++){
            data.append("\n"+String.valueOf(i) + "," + String.valueOf(i*i));} //random example to test te table

        try{
            FileOutputStream out = openFileOutput("data.csv", Context.MODE_PRIVATE);
            out.write((data.toString()).getBytes());
            out.close();

            Context context = getApplicationContext();
            File filelocation = new File(getFilesDir(), "data.csv");
            Uri path = FileProvider.getUriForFile(context,"com.example.alphaapp.fileprovider", filelocation);

            Intent fileIntent = new Intent (Intent.ACTION_SEND);
            fileIntent.setType("text/csv");
            fileIntent.putExtra(Intent.EXTRA_SUBJECT, "data");
            fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            fileIntent.putExtra(Intent.EXTRA_STREAM, path);

            //opening the mail screen with the file in it
            startActivity(Intent.createChooser(fileIntent, "send mail"));

        } catch (Exception e) { e.printStackTrace();
        }
    }
    /**
     * inflate the menu
     *
     * @param menu
     * @return
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected (MenuItem item){
        String st = item.getTitle().toString();

        if (st.equals("location")){
            Intent si = new Intent(this, locations.class);
            startActivity(si);
        }

        if (st.equals("mail")) {
            Intent si = new Intent(this, mails.class);
            startActivity(si);
        }

        if (st.equals("camera")) {
            Intent si = new Intent(this, scamera.class);
            startActivity(si);
        }

        if (st.equals("authentication")) {
            Intent si = new Intent(this, MainActivity.class);
            startActivity(si);
        }

        return true;
    }}