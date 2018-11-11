package server.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HangmanGame {
    private static final int numberOfWords = 51477;
    private static final Path pathTofile = Paths.get(URI.create("file:///Users/Likecoke/Desktop/networkProgramming/homework1/HW1/src/main/resources/words.txt"));
    private String word;
    private char[] guessState;
    private int score;
    private int guessesLeft;
    private int wordLength;

    //private numberOfguessesLeft
    public HangmanGame(){
        this.score = 0;
    }

    public GameState newGame(){
        this.word = chooseWordFromFile();
        this.wordLength = this.word.length();
        this.guessesLeft = this.wordLength;
        initializeGuessState(this.wordLength);
        return getGameState();

    }
    public GameState getGameState(){
        return new GameState(this.guessesLeft, this.score, new String(this.guessState));
    }


    private void initializeGuessState(int wordLength){
       this.guessState =  new char[wordLength];
       for(int i = 0; i<wordLength; i++){
           this.guessState[i] = '-';

       }

    }


    private String chooseWordFromFile(){
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathTofile.toString()));
            int currentline = 0;
            int wordlineNumber = (int) Math.ceil(numberOfWords*Math.random());
            while(currentline < (wordlineNumber -1)){
                bufferedReader.readLine();
                currentline++;
            }
            //System.err.println("Line number: "+currentline);
            String word = bufferedReader.readLine();
            //System.err.println("Word: "+ word);
            return word.toLowerCase();
            //System.err.println("Line number: "+lineNumberReader.getLineNumber());
            //System.err.println("Line number: "+lineNumberReader.getLineNumber());
            //System.err.println("Word: "+lineNumberReader.readLine());

            //System.err.println(bufferedReader.readLine());

        }
        catch(Exception e){
            System.err.println("File not found!");
            return null;
        }


    }
    public GameState guess(String guess){
        if(this.guessesLeft == 0){
            return getGameState();
        }
        else{
            if(guess.length() == 1){
                guessLetter(guess.charAt(0));
            }
            else{
                guessWord(guess);
            }
            return getGameState();

        }


    }
    private void guessLetter(char letter){
        boolean correctletter = false;
        //boolean []  correctLetters  = new boolean[this.wordLength];
        for(int i = 0; i<this.wordLength; i++){
            if(this.word.charAt(i) == letter){
                this.guessState[i] = letter;
                correctletter = true;
            }
        }
        if(this.word.equals(new String(guessState))) {
            this.guessesLeft = -1;
        }
        else if(!correctletter){
            this.guessesLeft--;
        }


    }
    private void guessWord(String word){
        if(this.word.equals(word)){
            this.guessState = this.word.toCharArray();
            this.guessesLeft = -1;
        }
        else {
            this.guessesLeft--;
        }


    }





}
