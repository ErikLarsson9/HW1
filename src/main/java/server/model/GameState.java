package server.model;

public class GameState {
    private int numberOfGuesses;
    private int score;
    private String guessState;
    private boolean gameover;


    public GameState(int numberOfGuesses, int score, String guessState, boolean gameover){
        this.numberOfGuesses = numberOfGuesses;
        this.score = score;
        this.guessState = guessState;
        this.gameover = gameover;

    }

    public int getNumberOfGuesses() {
        return numberOfGuesses;
    }

    public int getScore() {
        return score;
    }

    public String getGuessState() {
        return guessState;
    }

    public boolean getGameStatus(){ return gameover; }
}
