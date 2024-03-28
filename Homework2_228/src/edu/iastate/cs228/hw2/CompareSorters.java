package edu.iastate.cs228.hw2;

/**
 *  
 * @author Cole Giles
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random; 


public class CompareSorters 
{
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Use them as coordinates to construct points.  Scan these points with respect to their 
	 * median coordinate point four times, each time using a different sorting algorithm.  
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws IOException {

		// 
		// Conducts multiple rounds of comparison of four sorting algorithms.  Within each round, 
		// set up scanning as follows: 
		// 
		//    a) If asked to scan random points, calls generateRandomPoints() to initialize an array 
		//       of random points. 
		// 
		//    b) Reassigns to the array scanners[] (declared below) the references to four new 
		//       PointScanner objects, which are created using four different values  
		//       of the Algorithm type:  SelectionSort, InsertionSort, MergeSort and QuickSort. 
		// 
		//
		PointScanner[] scanners = new PointScanner[4];

		Scanner in = new Scanner(System.in);
		System.out.print("Keys: 1 (random integers)  2 (file input)  3 (exit)");
		while (in.nextInt() != 3) {
			int trial = 1;
			System.out.print("Trial: " + trial + "\n ");
			if (in.nextInt() == 1) {
				Random rand = new Random();
				System.out.print("Enter a number of random points: ");

				Point[] points = generateRandomPoints(in.nextInt(), rand);

				scanners[0] = new PointScanner(points, Algorithm.SelectionSort);
				scanners[1] = new PointScanner(points, Algorithm.InsertionSort);
				scanners[2] = new PointScanner(points, Algorithm.MergeSort);
				scanners[3] = new PointScanner(points, Algorithm.QuickSort);
			}
			else {
				System.out.print("Points from a file: ");
				System.out.print("File name: ");
				String input = in.next();

				scanners[0] = new PointScanner(input, Algorithm.SelectionSort);
				scanners[1] = new PointScanner(input, Algorithm.InsertionSort);
				scanners[2] = new PointScanner(input, Algorithm.MergeSort);
				scanners[3] = new PointScanner(input, Algorithm.QuickSort);
			}

			for (int i = 0; i < scanners.length; i++) {
				scanners[i].scan();
			}

			System.out.print("algorithm   size    time (ns) \n----------------------------------\n");
			for (int j = 0; j < scanners.length; j++) {
				System.out.println(scanners[j].stats());
			}
			System.out.print("----------------------------------");

			trial++;
		}


		
		// For each input of points, do the following. 
		// 
		//     a) Initialize the array scanners[].  
		//
		//     b) Iterate through the array scanners[], and have every scanner call the scan() 
		//        method in the PointScanner class.  
		//
		//     c) After all four scans are done for the input, print out the statistics table from
		//		  section 2.
		//
		// A sample scenario is given in Section 2 of the project description. 
		
	}
	
	
	/**
	 * This method generates a given number of random points.
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] × [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{
		Point[] temp = new Point[numPts];
		for(int i = 0; i < temp.length; i++) {
			temp[i] = new Point(rand.nextInt(101) - 50, rand.nextInt(101) - 50);
		}
		return temp;

	}
	
}
