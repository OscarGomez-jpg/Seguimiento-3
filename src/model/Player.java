package model;

public class Player {
    private String userName;
    private int score;

    public Player(String userName) {
        this.userName = userName;
        this.score = 0;
    }

    public String getUserName() {
        return userName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
