package edu.iastate.cs228.hw1;

public class Outage extends TownCell{

    public Outage(Town p, int row, int col){
        super(p, row, col);
    }

    public State who() {
        return State.OUTAGE;
    }

    public TownCell next(Town tNew) {
        //TODO
        return null;
    }
}
