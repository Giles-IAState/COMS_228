package edu.iastate.cs228.hw2;

import java.util.Random;
public class Test {

    public static void main(String[] args){

        Random rand = new Random();
        Point[] p = new Point[10];

        for (int i = 0; i < p.length; i++) {
            p[i] = new Point(rand.nextInt(-50,51),rand.nextInt(-50,51));
            System.out.print(p[i].toString() + " ");
        }

        PointScanner scan = new PointScanner(p, Algorithm.SelectionSort);

    }
}
