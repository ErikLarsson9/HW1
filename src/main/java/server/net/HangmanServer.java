package server.net;

import server.controller.Controller;

import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class HangmanServer {
    //private Controller controller;
    //Do I need this list?
    private List<ClientHandler> clients = new ArrayList<ClientHandler>();
    private int listeningPort = 8080;




    public HangmanServer(){

    }



    public void start(){
        try{
            ServerSocket serverSocket = new ServerSocket(listeningPort);
            while(true){
                Socket client  = serverSocket.accept();
                handleClient(client);
            }
        }
        catch (Exception e){
            System.err.println("There was a problem with starting the server");

        }

    }

    public void handleClient(Socket client){
            ClientHandler clientHandler = new ClientHandler(client, this, new Controller());
            this.clients.add(clientHandler);
            Thread handlerThread = new Thread(clientHandler);
            handlerThread.setPriority(Thread.MAX_PRIORITY);
            handlerThread.start();

    }
    public void removeClientHandler(ClientHandler clientHandler){
        clients.remove(clientHandler);
    }

    private void parseArgs(String[] args) {
        if (args.length > 0) {
            try {
                int port = Integer.parseInt(args[0]);
                if (0 > port || port > 65535) {
                    throw new InvalidPortException();
                }
                this.listeningPort = port;
            } catch (Exception e) {
                System.err.println("Not a valid port number!");

            }
        }
    }


    public static void main(String[] args){


        //System.err.println(Paths.get(".").toAbsolutePath().normalize().toString());
        HangmanServer hangmanServer = new HangmanServer();
        hangmanServer.parseArgs(args);
        hangmanServer.start();
    }

}

