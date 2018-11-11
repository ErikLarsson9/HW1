package server.model;

public class GameState {
    private int numberOfGuesses;
    private int score;
    private String guessState;


    public GameState(int numberOfGuesses, int score, String guessState){
        this.numberOfGuesses = numberOfGuesses;
        this.score = score;
        this.guessState = guessState;

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
}
