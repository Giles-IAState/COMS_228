package edu.iastate.cs228.hw1;

public class Streamer extends TownCell{

    public Streamer(Town p, int row, int col){
        super(p, row, col);
    }
    public State who() {
        return State.STREAMER;
    }

    public TownCell next(Town tNew) {

        //TODO
        return null;
    }
}
