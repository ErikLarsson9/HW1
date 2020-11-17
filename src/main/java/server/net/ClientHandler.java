package server.net;
import common.CommandParser;
import server.controller.Controller;
import server.model.GameState;
import java.io.*;
import java.net.Socket;
import java.nio.file.Paths;

public class ClientHandler implements Runnable {
    private Socket client;
    private HangmanServer server;
    private Controller controller;
    private boolean connection = true;


    public ClientHandler(Socket client, HangmanServer server, Controller controller){
        this.client = client;
        this.server = server;
        this.controller = controller;
    }



    public void run() {
        //System.err.println(Paths.get(".").toAbsolutePath().normalize().toString());
        try{
            boolean automaticalFlush = true;
            BufferedReader recieve = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter send = new PrintWriter(client.getOutputStream(),automaticalFlush);

            while (connection){
                String message = recieve.readLine();
                CommandParser nextCommand = new CommandParser(message);
                String[] args = nextCommand.getArguments();
                //System.err.println(nextCommand.getCommand());
                GameState gameState;
                String returnMessage;
                switch(nextCommand.getCommand()){

                    case NEWGAME:
                        gameState = controller.startNewGame();
                        returnMessage =  gameStateMessage(gameState);
                        send.println(returnMessage);
                        break;
                    case DISCONNECT:
                        disconnect();


                    case GUESS:
                        if(args != null){
                             gameState = controller.guess(args[0].toLowerCase());
                            returnMessage =  gameStateMessage(gameState);
                            send.println(returnMessage);

                        }
                        else{
                            send.println( "INVALID_COMMAND");
                        }
                        break;

                    default:
                        send.println( "INVALID_COMMAND");
                }

            }
        }
        catch (Exception e){

        }


    }
    private String gameStateMessage(GameState gameState){
        System.err.println(gameState.getGuessState() + " " + gameState.getNumberOfGuesses() + " " + gameState.getScore()+ " " + gameState.getGameStatus());
        return gameState.getGuessState() + " " + gameState.getNumberOfGuesses() + " " + gameState.getScore()+ " " + gameState.getGameStatus();
    }
    private void disconnect(){
        try{
            client.close();
        }
        catch (Exception e){
            System.err.println("There was a problem when closing the client");
        }
        connection = false;
        server.removeClientHandler(this);

    }

}
