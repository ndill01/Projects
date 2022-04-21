// Nicholas Dill
// Computer Science III
// 11/24/2020

package Project3;

import java.util.*;

public class SortingAlgorithmsPerformance {
	
	// Final instance variable for use in the quickSort method.
	private final static int CUTOFF = 10;

	// Main method of the program.
	public static void main(String[] args) {
		
		createTest();
		
	}
	
	// Method that creates the arrays of random numbers.
	private static void createTest() {
		
		Random random = new Random();
		int[] numAmount = {10000, 20000, 40000, 80000};
		Integer[] randNums;
		
		// For loop that creates an array with the size of each element in the numAmount array.
		for(int i = 0; i < numAmount.length; i++) {
			
			randNums = new Integer[numAmount[i]];
			
			// For loop that populates each array with random numbers.
			for(int j = 0; j < numAmount[i]; j++) {
				
				randNums[j] = random.nextInt(numAmount[i]);
				
			}
			
			System.out.println(numAmount[i] + " RANDOM INTEGERS");
			
			// Calls method to perform sort algorithms and time their performance on the array.
			testAlgorithmsPerformance(randNums);
			
		}
		
	}
	
	// Method that calls each sort method on the Integer array and tracks their runtime.
	private static void testAlgorithmsPerformance(Integer[] arr) {
		
		long startTime;
		long endTime;
		
		// Testing insertion sort.
		startTime = System.currentTimeMillis();
		insertionSort(arr);
		endTime = System.currentTimeMillis();
		System.out.println("Insertion Sort Runtime (s): " + (double)(endTime - startTime) / 1000);
		
		// Testing merge sort.
		startTime = System.currentTimeMillis();
		mergeSort(arr);
		endTime = System.currentTimeMillis();
		System.out.println("Merge Sort Runtime (s): " + (double)(endTime - startTime) / 1000);
		
		// Testing quick sort.
		startTime = System.currentTimeMillis();
		quickSort(arr);
		endTime = System.currentTimeMillis();
		System.out.println("Quick Sort Runtime (s): " + (double)(endTime - startTime) / 1000);
		
	}
	
	// Method that performs insertion sort on the passed array.
	private static <AnyType extends Comparable<? super AnyType>> void insertionSort(AnyType[] a) {
		
		int j;
		
		for(int p = 1; p < a.length; p++) {
			
			AnyType temp = a[p];
			
			for(j = p; j > 0 && temp.compareTo(a[j - 1]) < 0; j--) {
				
				a[j] = a[j - 1];
				
			}
			
			a[j] = temp;
			
		}
		
	}
	
	// Method that performs insertion sort on the passed array.
	private static <AnyType extends Comparable<? super AnyType>> void insertionSort(AnyType[] a, int left, 
			int right) {
		
		for(int p = left + 1; p <= right; p++) {
			
			AnyType temp = a[p];
			int j;
			
			for(j = p; j > left && temp.compareTo(a[j - 1]) < 0; j--) {
				
				a[j] = a[j - 1];
				
			}
			
			a[j] = temp;
			
		}
		
	}
	
	// Method that performs recursive calls on the passed array.
	private static <AnyType extends Comparable<? super AnyType>> void mergeSort(AnyType[] a, AnyType[] tempArray, 
			int left, int right) {
		
		if(left < right) {
			
			int center = (left + right) / 2;
			
			mergeSort(a, tempArray, left, center);
			mergeSort(a, tempArray, center + 1, right);
			merge(a, tempArray, left, center + 1, right);
			
		}
		
	}
	
	// Method that performs merge sort on the passed array.
	private static <AnyType extends Comparable<? super AnyType>> void mergeSort(AnyType[] a) {
		
		AnyType[] tempArray = (AnyType[]) new Comparable[a.length];
		
		mergeSort(a, tempArray, 0, a.length - 1);
		
	}
	
	// Method that performs merge sort on the passed array.
	private static <AnyType extends Comparable<? super AnyType>> void merge(AnyType[] a, AnyType[] tempArray, 
			int leftPos, int rightPos, int rightEnd) {
		
		int leftEnd = rightPos - 1;
		int tempPos = leftPos;
		int numElements = rightEnd - leftPos + 1;
		
		while(leftPos <= leftEnd && rightPos <= rightEnd) {
			
			if(a[leftPos].compareTo(a[rightPos]) <= 0) {
				
				tempArray[tempPos++] = a[leftPos++];
				
			}
			else {
				
				tempArray[tempPos++] = a[rightPos++];
				
			}
			
		}
		
		while(leftPos <= leftEnd) {
			
			tempArray[tempPos++] = a[leftPos++];
			
		}
		
		while(rightPos <= rightEnd) {
			
			tempArray[tempPos++] = a[rightPos++];
			
		}
		
		for(int i = 0; i < numElements; i++, rightEnd--) {
			
			a[rightEnd] = tempArray[rightEnd];
			
		}
		
	}
	
	// Method that calls the main quick sort algorithm.
	private static <AnyType extends Comparable<? super AnyType>> void quickSort(AnyType[] a) {
		
		quickSort(a, 0, a.length - 1);
		
	}
	
	// Method that returns the median of the left, right, and center of the array.
	private static <AnyType extends Comparable<? super AnyType>> AnyType median3(AnyType[] a, int left, int right) {
		
		int center = (left + right) / 2;
		
		if(a[center].compareTo(a[left]) < 0) {
			
			swapReferences(a, left, center);
			
		}
		if(a[right].compareTo(a[left]) < 0) {
			
			swapReferences(a, left, right);
			
		}
		if(a[right].compareTo(a[center]) < 0) {
			
			swapReferences(a, center, right);
			
		}
		
		swapReferences(a, center, right - 1);
		return a[right - 1];
		
	}
	
	// Method that performs quick sort on the passed array.
	private static <AnyType extends Comparable<? super AnyType>> void quickSort(AnyType[] a, int left, int right) {
		
		if(left + CUTOFF <= right) {
			
			AnyType pivot = median3(a, left, right);
			int i = left, j = right - 1;
			
			for(;;) {
				
				while(a[++i].compareTo(pivot) < 0) { }
				while(a[--j].compareTo(pivot) > 0) { }
				
				if(i < j) {
					
					swapReferences(a, i, j);
					
				}
				else {
					
					break;
					
				}
				
			}
			
			swapReferences(a, i, right - 1);
			
			quickSort(a, left, i - 1);
			quickSort(a, i + 1, right);
			
		}
		else {
			
			insertionSort(a, left, right);
			
		}
		
	}
	
	// Method that swaps the two index references in the passed array.
	private static <AnyType> void swapReferences(AnyType[] a, int index1, int index2) {
		
		AnyType temp = a[index1];
		a[index1] = a[index2];
		a[index2] = temp;
		
	}

}
