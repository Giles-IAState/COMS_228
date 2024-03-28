package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 *  
 * @author Cole Giles
 *
 */

/**
 * 
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{
	// Other private instance variables if needed
	
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		super(pts);
		algorithm = "merge sort";
		// TODO  
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 */
	@Override 
	public void sort()
	{
		mergeSortRec(points);
		roundTime = System.nanoTime();
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts)
	{
		if (pts.length <= 1) {
			return;
		}

		int midpoint = pts.length / 2;

		Point[] leftPart = Arrays.copyOf(pts, midpoint);
		Point[] rightPart = Arrays.copyOfRange(pts, midpoint, pts.length);

		mergeSortRec(leftPart);
		mergeSortRec(rightPart);

		merge(leftPart, rightPart, pts);
	}

	private static void merge(Point[] a, Point[] b, Point[] c) {

		int leftPos = 0;
		int rightPos = 0;
		int mergePos = 0;

		while(leftPos < a.length && rightPos < b.length) {
			if (a[leftPos].compareTo(b[rightPos]) < 0) {
				c[mergePos] = a[leftPos];
				leftPos++;
				mergePos++;
			}
			else {
				c[mergePos] = b[rightPos];
				rightPos++;
				mergePos++;
			}
		}

 		while (leftPos < a.length) {

			c[mergePos] = a[leftPos];
			leftPos++;
			mergePos++;
		}

		while (rightPos < b.length) {
			c[mergePos] = b[rightPos];
			rightPos++;
			mergePos++;
		}

	}


}
