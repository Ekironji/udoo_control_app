package org.ekironjisolutions.udooboard.udooboard.libbelzedoo;

/**
 * Created by Mario on 31/08/2015.
 */
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Connection  {

    private Socket socket              = null;
    private int mServerPort            = 10000;
    private String mServerIp           = "";
    private OutputStream mOutputStream = null;
    private InputStream mInputStream   = null;
    private Context mContext           = null;


    public Connection(Context mContext) {
        this.mContext = mContext;
    }

    public void connect(int mServerPort, String mServerIp){
        this.mServerPort = mServerPort;
        this.mServerIp = mServerIp;

        //open socket
        new Thread(new ClientThread()).start();
    }

    public void disconnect(){
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.socket = null;
        this.mServerIp = "";
        this.mInputStream  = null;
        this.mOutputStream = null;
    }

    public void reconnect(){
        //open socket
        disconnect();
        new Thread(new ClientThread()).start();
    }

    public boolean areStreamsAvailable(){
        try {
            mOutputStream = socket.getOutputStream();
            mInputStream  = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mOutputStream != null && mInputStream != null;
    }

    public InputStream getInputStream() {
        return mInputStream;
    }

    public OutputStream getOutputStream() {
        return mOutputStream;
    }

    class ClientThread implements Runnable {
        @Override
        public void run() {
            try {
                InetAddress serverAddr = InetAddress.getByName(mServerIp);

                socket = new Socket(serverAddr, mServerPort);

                mOutputStream = socket.getOutputStream();
                mInputStream  = socket.getInputStream();

            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

    }
}