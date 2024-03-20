package edu.iastate.cs228.hw1;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author <<Cole Giles>>
 *
 * The edu.iastate.cs228.hw1.ISPBusiness class performs simulation over a grid
 * plain with cells occupied by different edu.iastate.cs228.hw1.TownCell types.
 *
 */
public class ISPBusiness {
	
	/**
	 * Returns a new edu.iastate.cs228.hw1.Town object with updated grid value for next billing cycle.
	 * @param tOld: old/current edu.iastate.cs228.hw1.Town object.
	 * @return: New town object.
	 */
	public static Town updatePlain(Town tOld) {
		Town tNew = new Town(tOld.getLength(), tOld.getWidth());
		//TODO: Write your code here.
		return tNew;
	}
	
	/**
	 * Returns the profit for the current state in the town grid.
	 * @param town
	 * @return
	 */
	public static int getProfit(Town town) {
		//TODO: Write/update your code here.
		return 0;
	}
	

	/**
	 *  Main method. Interact with the user and ask if user wants to specify elements of grid
	 *  via an input file (option: 1) or wants to generate it randomly (option: 2).
	 *  
	 *  Depending on the user choice, create the edu.iastate.cs228.hw1.Town object using respective constructor and
	 *  if user choice is to populate it randomly, then populate the grid here.
	 *  
	 *  Finally: For 12 billing cycle calculate the profit and update town object (for each cycle).
	 *  Print the final profit in terms of %. You should print the profit percentage
	 *  with two digits after the decimal point:  Example if profit is 35.5600004, your output
	 *  should be:
	 *
	 *	35.56%
	 *  
	 * Note that this method does not throw any exception, so you need to handle all the exceptions
	 * in it.
	 * 
	 * @param args
	 * 
	 */
	public static void main(String[] args) throws FileNotFoundException {

		Scanner in = new Scanner(System.in);
		String filePath;
		int choice;

		System.out.println("How to populate grid (type 1 or 2): 1: from a file. 2: randomly with seed");
		choice = in.nextInt();

		if(choice == 1) {
			System.out.println("Please enter file path: ");
			filePath = in.next();
			Town test = new Town(filePath);
			System.out.println(test.toString());
		}
		else{

			System.out.println("Please provide rows, cols, and seed integer separated by spaces:");
			int rows = in.nextInt();
			int cols = in.nextInt();
			int seed = in.nextInt();
			Town test = new Town(rows, cols);
			test.randomInit(seed);
			System.out.println(test.toString());
		}
		//TODO
	}
}
