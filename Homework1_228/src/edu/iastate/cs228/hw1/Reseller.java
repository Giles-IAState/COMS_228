package edu.iastate.cs228.hw1;

public class Reseller extends TownCell{

    public Reseller(Town p, int row, int col){
        super(p, row, col);
    }
    public State who() {
        return State.RESELLER;
    }

    public TownCell next(Town tNew) {

        //TODO
        return null;
    }
}
