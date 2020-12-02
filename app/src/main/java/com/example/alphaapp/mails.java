package com.example.alphaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class mails extends AppCompatActivity {
    Button sendbt;
    EditText toet, subet, msget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mails);

        toet = (EditText) findViewById(R.id.toet);
        subet = (EditText) findViewById(R.id.subet);
        msget = (EditText) findViewById(R.id.msget);
        sendbt = (Button) findViewById(R.id.sendbt);

        sendbt.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                sendMail();
            }


        });

    }
    private void sendMail(){
        String recipientList = toet.getText().toString();
        String[] recipients = recipientList.split(",");
        String subject = subet.getText().toString();
        String message = msget.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT,  message);





























        try {
        startActivity(Intent.createChooser(intent, "choose an email client"));}
        catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(mails.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
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

        if (st.equals("authentication")) {
            Intent si = new Intent(this, mails.class);
            startActivity(si);
        }

        return true;
    }

}