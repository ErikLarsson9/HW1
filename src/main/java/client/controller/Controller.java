package client.controller;

import client.net.ExternalConnection;
import client.net.NetObserver;
import client.view.CommandLineIntegration;

import java.util.concurrent.CompletableFuture;

public class Controller {
    private ExternalConnection server;

    public Controller(){
        this.server = new ExternalConnection();

    }

    public void connect(int port, String ipAdress, NetObserver netObserver){
//        try{
//            return  Integer.parseInt( (String) new CompletableFuture().thenRunAsync(() -> this.server.connect(port,ipAdress,netObserver)).
//                    get());
//        }
//        catch(Exception e){
//            return -1;
//        }
        //System.err.println("BLA BLA");
        CompletableFuture.runAsync(() ->{
            try{
                this.server.connect(port,ipAdress,netObserver);
            }
            catch (Exception e){
                    netObserver.receivedData("-1");
            }
        });



    }
    private void sendMessage(String message){
        CompletableFuture.runAsync(() ->
        this.server.sendMessage(message)
        );

    }
    public void requestNewGame(){
        CompletableFuture.runAsync(() ->
                this.server.sendMessage("NEWGAME")
        );
    }

    public void guess(String message){
        sendMessage("GUESS "+ message);

    }
    public void disconnect(){
        sendMessage("DISCONNECT");
    }


}

