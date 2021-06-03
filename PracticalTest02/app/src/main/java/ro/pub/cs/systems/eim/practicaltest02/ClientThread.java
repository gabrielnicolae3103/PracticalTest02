package ro.pub.cs.systems.eim.practicaltest02;

import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {

    private String address;
    private int port;
    private String currency;
    private TextView valutaTextView;

    private Socket socket;

    public ClientThread(String address, int port, String currency, TextView valutaTextView) {
        this.address = address;
        this.port = port;
        this.currency = currency;
        this.valutaTextView = valutaTextView;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(address, port);
            if (socket == null) {
                Log.e(Constants.TAG, "[CLIENT THREAD] Could not create socket!");
                return;
            }
            Log.e(Constants.TAG, "[CLIENT THREAD] Created socker on address!" + this.address + " with port " + this.port);
            BufferedReader bufferedReader = Utilities.getReader(socket);
            PrintWriter printWriter = Utilities.getWriter(socket);
            if (bufferedReader == null || printWriter == null) {
                Log.e(Constants.TAG, "[CLIENT THREAD] Buffered Reader / Print Writer are null!");
                return;
            }
            printWriter.println(currency);
            printWriter.flush();
            String valutaInformation;
            Log.e(Constants.TAG, "[CLIENT THREAD] Am citit ianinte de if");
            while ((valutaInformation = bufferedReader.readLine()) != null) {
                final String finalizedValutaInformation = valutaInformation;
                Log.e(Constants.TAG, "[CLIENT THREAD] Am citit");
                valutaTextView.post(new Runnable() {
                    @Override
                    public void run() {
                        valutaTextView.setText(finalizedValutaInformation);
                    }
                });
            }
        } catch (IOException ioException) {
            Log.e(Constants.TAG, "[CLIENT THREAD] An exception has occurred: " + ioException.getMessage());
            if (Constants.DEBUG) {
                ioException.printStackTrace();
            }
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException ioException) {
                    Log.e(Constants.TAG, "[CLIENT THREAD] An exception has occurred: " + ioException.getMessage());
                    if (Constants.DEBUG) {
                        ioException.printStackTrace();
                    }
                }
            }
        }
    }

}
