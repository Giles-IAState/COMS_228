package edu.iastate.cs228.hw1;

public class Empty extends TownCell{

    public Empty(Town p, int row, int col){
        super(p, row, col);
    }
    public State who() {
        return State.EMPTY;
    }

    public TownCell next(Town tNew) {
        //TODO
        return null;
    }
}
