package org.ekironjisolutions.udooboard.udooboard;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.ekironjisolutions.udooboard.udooboard.libbelzedoo.Belzedoo;
import org.ekironjisolutions.udooboard.udooboard.libbelzedoo.Connection;


public class UBMainActivity extends ActionBarActivity {

    private Connection mConnection = null;
    private Belzedoo mBelzedoo     = null;

    private String ipAddress = "192.168.0.109";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubmain);
        mConnection = new Connection(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ubmain, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            ipSettings(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    EditText ipEditText = null;

    public void ipSettings(Context context) {
        // custom dialog
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.ip_alert);
        dialog.setTitle("Title...");

        Button okButton     = (Button)   dialog.findViewById(R.id.okButton);
        Button cancelButton = (Button)   dialog.findViewById(R.id.cancelButton);
        ipEditText = (EditText) dialog.findViewById(R.id.ipEditText);
        ipEditText.setText(ipAddress);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ipAddress = ipEditText.getText().toString();
                mConnection.connect(10000, ipAddress);
                mBelzedoo = new Belzedoo(mConnection);

                dialog.dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    public void onButtonD0clicked(View v){
        digitalButtonClick(this, "0");
    }

    public void onButtonD1clicked(View v){
        digitalButtonClick(this, "1");
    }

    public void onButtonD2clicked(View v){
        digitalButtonClick(this, "2");
    }

    public void onButtonD3clicked(View v){
        digitalButtonClick(this, "3");
    }

    public void onButtonD4clicked(View v){
        digitalButtonClick(this, "4");
    }

    public void onButtonD5clicked(View v){
        digitalButtonClick(this, "5");
    }

    public void onButtonD6clicked(View v){
        digitalButtonClick(this, "6");
    }

    public void onButtonD7clicked(View v){
        digitalButtonClick(this, "7");
    }



    public void digitalButtonClick(Context context, String pinNumber) {
        // custom dialog
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.action_alert);
        dialog.setTitle("Title...");

        final String pin = pinNumber;


        Button inputButton  = (Button) dialog.findViewById(R.id.inputButton);
        Button outputButton = (Button) dialog.findViewById(R.id.outputButton);
        Button lowButton    = (Button) dialog.findViewById(R.id.lowButton);
        Button highButton   = (Button) dialog.findViewById(R.id.highButton);
        Button readButton   = (Button) dialog.findViewById(R.id.readButton);

        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBelzedoo.pinMode(pin, "INPUT");
                dialog.dismiss();
            }
        });

        outputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBelzedoo.pinMode(pin, "OUTPUT");
                dialog.dismiss();
            }
        });

        lowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBelzedoo.digitalWrite(pin, "LOW");
                dialog.dismiss();
            }
        });

        highButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBelzedoo.digitalWrite(pin, "HIGH");
                dialog.dismiss();
            }
        });

        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mBelzedoo.digitalRead(pin);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    public void onButtonA0clicked(View v){

    }

    public void onButtonA1clicked(View v){

    }

    public void onButtonA2clicked(View v){

    }

    public void onButtonA3clicked(View v){

    }

    public void onButtonA4clicked(View v){

    }

    public void onButtonA5clicked(View v){

    }
}
