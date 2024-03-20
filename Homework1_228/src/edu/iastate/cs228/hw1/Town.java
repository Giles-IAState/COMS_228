package edu.iastate.cs228.hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.Random;
import java.util.Scanner;


/**
 *  @author <<Cole Giles>>
 *
 */
public class Town {
	
	private int length, width;  //Row and col (first and second indices)
	public TownCell[][] grid;
	
	/**
	 * Constructor to be used when user wants to generate grid randomly, with the given seed.
	 * This constructor does not populate each cell of the grid (but should assign a 2D array to it).
	 * @param length The specified length of grid
	 * @param width The specified width of grid
	 */
	public Town(int length, int width) {
		this.length = length;
		this.width = width;
		grid = new TownCell[length][width];
	}
	
	/**
	 * Constructor to be used when user wants to populate grid based on a file.
	 * Please see that it simply throws FileNotFoundException exception instead of catching it.
	 * Ensure that you close any resources (like file or scanner) which is opened in this function.
	 * @param inputFileName the file name to be read
	 * @throws FileNotFoundException
	 */
	public Town(String inputFileName) throws FileNotFoundException {

		Scanner fileReader = new Scanner(new File(inputFileName));
		String line = fileReader.nextLine();
		Scanner readLine = new Scanner(line);
		length = readLine.nextInt();
		width = readLine.nextInt();
		grid = new TownCell[length][width];


		for(int i = 0; i < length; i++) {
			line = fileReader.nextLine();
			readLine = new Scanner(line);
			for(int j = 0; j < width; j++) {
				String input = readLine.next();
				if(input.equals("R")) {
					grid[i][j] = new Reseller(this, i, j);
				}
				else if(input.equals("E")) {
					grid[i][j] = new Empty(this, i, j);
				}
				else if(input.equals("C")) {
					grid[i][j] = new Casual(this, i, j);
				}
				else if(input.equals("O")) {
					grid[i][j] = new Outage(this, i, j);
				}
				else if(input.equals("S")) {
					grid[i][j] = new Streamer(this, i, j);
				}
			}
		}
		readLine.close();
		fileReader.close();
	}
	
	/**
	 * Returns width of the grid.
	 * @return width
	 */
	public int getWidth() {

		return this.width;
	}
	
	/**
	 * Returns length of the grid.
	 * @return length
	 */
	public int getLength() {

		return this.length;
	}

	/**
	 * Initialize the grid by randomly assigning cell with one of the following class object:
	 * Casual, Empty, Outage, Reseller OR Streamer
	 */
	public void randomInit(int seed) {
		Random rand = new Random(seed);
		int random;
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < width; j++) {
				random = rand.nextInt(5);
				if(random == 0) {
					grid[i][j] = new Reseller(this, i, j);
				}
				else if(random == 1) {
					grid[i][j] = new Empty(this, i, j);
				}
				else if(random == 2) {
					grid[i][j] = new Casual(this, i, j);
				}
				else if(random == 3) {
					grid[i][j] = new Outage(this, i, j);
				}
				else if(random == 4){
					grid[i][j] = new Streamer(this, i, j);
				}
			}
		}
	}
	
	/**
	 * Output the town grid. For each square, output the first letter of the cell type.
	 * Each letter should be separated either by a single space or a tab.
	 * And each row should be in a new line. There should not be any extra line between 
	 * the rows.
	 */
	@Override
	public String toString() {
		String s = "";
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < width; j++) {
				if(grid[i][j].who() == State.RESELLER) {
					 s += "R ";
				}
				else if(grid[i][j].who() == State.EMPTY) {
					s += "E ";
				}
				else if(grid[i][j].who() == State.CASUAL) {
					s += "C ";
				}
				else if(grid[i][j].who() == State.OUTAGE) {
					s += "O ";
				}
				else if (grid[i][j].who() == State.STREAMER) {
					s += "S ";
				}
			}
			s += "\n";
		}
		return s;
	}
}
