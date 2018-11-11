package server.controller;

import server.model.GameState;
import server.model.HangmanGame;

public class Controller {
    private HangmanGame hangmanGame;

    public Controller(){
        hangmanGame = new HangmanGame();


    }


    public GameState startNewGame(){
        //What to do if game is already in progress?
        return hangmanGame.newGame();

    }
    public GameState guess(String guess){
        return hangmanGame.guess(guess);

    }

}
