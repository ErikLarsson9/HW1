//package server.net;
//import common.Commands;
//
//
//public class CommandParser {
//    private Commands command;
//    private String[] arguments;
//
//    public CommandParser(String input){
//        this.command = parseCommand(input);
//        this.arguments = parseArguments(input);
//
//    }
//
//
//    public Commands parseCommand(String input){
//        if(input == null){
//            return Commands.INVALID_COMMAND;
//        }
//        input =  input.trim();
//        if(input.equals("")){
//            return Commands.EMPTY;
//        }
//        /* Split on any whitespace */
//        String[] textArray =  input.split("\\s+");
//        try{
//            Commands command = Commands.valueOf(textArray[0].toUpperCase());
//            return command;
//        }
//        catch(Exception exc){
//            return Commands.INVALID_COMMAND;
//        }
//
//    }
//
//    public String[] parseArguments(String input){
//        if(input == null){
//            return null;
//        }
//        input = input.trim();
//        if(input.equals("")){
//            return null;
//        }
//        String[] inputArray =  input.split("\\s+");
//        if(inputArray.length == 1){
//            return null;
//        }
//        else{
//            String[] argsArray = new String[inputArray.length-1];
//            for(int i = 0; i< argsArray.length; i++){
//                argsArray[i] = inputArray[i+1];
//            }
//            return argsArray;
//        }
//
//
//
//    }
//
//    public String[] getArguments() {
//        return arguments;
//    }
//
//    public Commands getCommand(){
//        return command;
//    }
//
//
//}
