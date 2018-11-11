package client.view;

import client.controller.Controller;
import common.CommandParser;
import common.Commands;

public class CommandLineInterface implements Runnable {
    private static final String USER_POINTER= "> ";
    private Controller controller;
    private CommandLineIntegration commandLineIntegration;




    public CommandLineInterface(){
        this.controller = new Controller();
        this.commandLineIntegration = new CommandLineIntegration();
        new Thread(this, "CommandLineUI").start();

    }



    public void run(){
        //commandLineIntegration.safePrintln("Im Alive: "+ Thread.currentThread().getName());

        while(true){
            commandLineIntegration.safePrint(USER_POINTER);
            CommandParser nextCommand = new CommandParser(commandLineIntegration.readNextLine());
            String[] args = nextCommand.getArguments();
            switch(nextCommand.getCommand()){
                case CONNECT:
                    if((args != null) && (args.length > 1)){

                        String ipAddress = args[0];
                        int port = Integer.parseInt(args[1]);

                            controller.connect(port, ipAddress, (String s) -> {
                                        if (s.equals("1")) {
                                            commandLineIntegration.safePrintln(
                                                    "Connected to server with IP-Address " + ipAddress +
                                                            " and port " + port);
                                        }
                                        else if(s.equals("-1")) {
                                            commandLineIntegration.safePrintln("Connection failed");
                                        }
                                        else if(s.equals("-2")){
                                            commandLineIntegration.safePrintln("Connection to server lost!");
                                        }
                                        else if(s.equals(Commands.INVALID_COMMAND.toString())){
                                            commandLineIntegration.safePrintln("Command not recognized by server.");
                                        }
                                        else{
                                            String[] gameState = s.split(" ");
                                            if(gameState[1].equals("-1")){
                                                commandLineIntegration.safePrintln("Word: "+ gameState[0] + " Score: "+ gameState[2]);
                                            }
                                            else{
                                                commandLineIntegration.safePrintln("Word: "+ gameState[0] + " Remaining Failed Attempts: " + gameState[1] + " Score: "+ gameState[2]);
                                            }

                                        }
                                    });
                    }
                    else{
                        commandLineIntegration.safePrintln("Invalid arguments to Command CONNECT");
                    }

                    break;
                case NEWGAME:
                    controller.requestNewGame();
                    //commandLineIntegration.safePrintln("New game started!");
                    break;
                case QUIT:
                    controller.disconnect();
                    commandLineIntegration.safePrintln("Exited");
                    return;

                case GUESS:

                    if(args != null){
                        controller.guess(args[0]);
                    }
                    else{
                        commandLineIntegration.safePrintln("You didn't guess any word or letter!");
                    }

                    break;

                case EMPTY:
                    commandLineIntegration.safePrintln("no input");
                    break;

                default:
                    commandLineIntegration.safePrintln("Invalid command");
            }

        }

    }
}



