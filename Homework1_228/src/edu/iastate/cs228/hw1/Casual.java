package edu.iastate.cs228.hw1;

public class Casual extends TownCell{

    public Casual(Town p, int row, int col){
        super(p, row, col);
    }
    public State who() {
        return State.CASUAL;
    }

    public TownCell next(Town tNew) {
        //TODO
        return null;
    }
}
