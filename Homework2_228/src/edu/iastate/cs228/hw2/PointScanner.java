package edu.iastate.cs228.hw2;

/**
 * 
 * @author Cole Giles
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * 
 * This class sorts all the points in an array of 2D points to determine a reference point whose x and y 
 * coordinates are respectively the medians of the x and y coordinates of the original points. 
 * 
 * It records the employed sorting algorithm as well as the sorting time for comparison. 
 *
 */
public class PointScanner  
{
	private Point[] points; 
	
	private Point medianCoordinatePoint;  // point whose x and y coordinates are respectively the medians of 
	                                      // the x coordinates and y coordinates of those points in the array points[].
	private Algorithm sortingAlgorithm;    
	
		
	protected long scanTime; 	       // execution time in nanoseconds. 
	
	/**
	 * This constructor accepts an array of points and one of the four sorting algorithms as input. Copy 
	 * the points into the array points[].
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException
	{
		points = pts;
		sortingAlgorithm = algo;
	}

	
	/**
	 * This constructor reads points from a file. 
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException   if the input file contains an odd number of integers
	 */
	protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException
	{
		sortingAlgorithm = algo;

		File inputFile = new File(inputFileName);
		Scanner scanFile = new Scanner(inputFile);
		Scanner scanLines = new Scanner(scanFile.nextLine());
		points = new Point[0];
		int i = 0;

		while(scanFile.hasNextLine()) {

			int x;
			int y = 51; //used to guarantee that y will be initialized to a value.

			while(scanLines.hasNextInt()) {


				x = scanLines.nextInt();

				if(scanLines.hasNextInt()){
					y = scanLines.nextInt();
				}
				else {
					scanLines = new Scanner(scanFile.nextLine());
					y = scanLines.nextInt();
				}
				points = Arrays.copyOf(points, points.length + 1);
				this.points[i] = new Point(x,y);
				i++;

			}

			if(y != 51) {
				scanLines = new Scanner(scanFile.nextLine());
			}

		}

		scanFile.close();
		scanLines.close();

	}

	
	/**
	 * Carry out two rounds of sorting using the algorithm designated by sortingAlgorithm as follows:  
	 *    
	 *     a) Sort points[] by the x-coordinate to get the median x-coordinate. 
	 *     b) Sort points[] again by the y-coordinate to get the median y-coordinate.
	 *     c) Construct medianCoordinatePoint using the obtained median x- and y-coordinates.     
	 *  
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter, InsertionSorter, MergeSorter,
	 * or QuickSorter to carry out sorting.       
	 * @param algo
	 * @return
	 */
	public void scan() throws IOException {
		// create an object to be referenced by aSorter according to sortingAlgorithm. for each of the two
		// rounds of sorting, have aSorter do the following:
		//
		//     a) call setComparator() with an argument 0 or 1.
		//
		//     b) call sort().
		//
		//     c) use a new Point object to store the coordinates of the medianCoordinatePoint
		//
		//     d) set the medianCoordinatePoint reference to the object with the correct coordinates.
		//
		//     e) sum up the times spent on the two sorting rounds and set the instance variable scanTime.
		AbstractSorter aSorter;

		if (sortingAlgorithm == Algorithm.SelectionSort) {
			aSorter = new SelectionSorter(points);
		}
		else if (sortingAlgorithm == Algorithm.InsertionSort) {
			aSorter = new InsertionSorter(points);
		}
		else if (sortingAlgorithm == Algorithm.MergeSort) {
			aSorter = new MergeSorter(points);
		}
		else {
			aSorter = new QuickSorter(points);
		}

		aSorter.setComparator(0);
		aSorter.sort();
		scanTime += aSorter.roundTime;
		medianCoordinatePoint = aSorter.getMedian();
		writeMCPToFile();

		aSorter.setComparator(1);
		aSorter.sort();
		scanTime += aSorter.roundTime;
		medianCoordinatePoint = aSorter.getMedian();
		writeMCPToFile();

		points = aSorter.points;

		
	}
	
	
	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <sorting algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * selection sort   1000	  9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description. 
	 */
	public String stats()
	{
		String output = sortingAlgorithm + "   " + points.length + "      " + scanTime;
		return output;
	}
	
	
	/**
	 * Write MCP after a call to scan(),  in the format "MCP: (x, y)"   The x and y coordinates of the point are displayed on the same line with exactly one blank space 
	 * in between. 
	 */
	@Override
	public String toString()
	{
//		String output = "";
//        for (int i = 0; i < points.length; i++) {
//            output += points[i].toString();
//        }
//		return output;

		return "MCP: " + medianCoordinatePoint;
	}

	
	/**
	 *  
	 * This method, called after scanning, writes point data into a file by outputFileName. The format 
	 * of data in the file is the same as printed out from toString().  The file can help you verify 
	 * the full correctness of a sorting result and debug the underlying algorithm. 
	 * 
	 * @throws FileNotFoundException
	 */
	public void writeMCPToFile() throws FileNotFoundException, IOException
	{
		File outputFile = new File("output_file.txt");
		FileWriter writer = new FileWriter("output_file.txt");

		writer.write(toString());
		writer.close();

	}	

	

		
}
