package client.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ExternalConnection implements Runnable {
    private int port;
    private String ipAdress;
    NetObserver netObserver;
    Socket connectionSocket;
    private PrintWriter send;
    private BufferedReader receive;

    public ExternalConnection(){

    }
    public void connect(int port, String ipAdress, NetObserver netObserver){
        this.port = port;
        this.ipAdress = ipAdress;
        this.netObserver = netObserver;
        this.connectionSocket = new Socket();
        try{
            this.connectionSocket.connect(new InetSocketAddress(ipAdress, port));
            boolean automaticFlush = true;
            this.send = new PrintWriter(connectionSocket.getOutputStream(),automaticFlush);
            this.receive = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            this.netObserver.receivedData("1");
            Thread handleIncomingMessages = new Thread(this);
            handleIncomingMessages.start();

        }
        catch (Exception e){
            this.netObserver.receivedData("-1");
        }


    }

    @Override
    public void run(){
        while (true){
            receiveMessage();
        }


    }

    public void sendMessage(String message){
        this.send.println(message);
    }

    private void receiveMessage(){
        try{
            String message = this.receive.readLine();
            this.netObserver.receivedData(message);
        }
        catch (Exception e){

            this.netObserver.receivedData("-2");
        }

    }


}
