package com.company;

import java.util.Scanner;

public class SolutionB {

    // Search in the array method
    public static int midValSearch(int arr[], int left, int right, int key) {
        if (right >= left) {
            int mid = left + (right - left) / 2; // get the mid value
            if (arr[mid] == key) 
                return mid;
            if (arr[mid] > key)
                return midValSearch(arr, left, mid - 1, key);
            return midValSearch(arr, mid + 1, right, key);
        }
        return -1; // if value not found return -1
    }

    // index value finding method
    public static int findIndex(int arr[], int key) {
        int start = 0, end = 1; // initialisation of 
        int val = arr[0];
        // to avoid out of bound
        try {
            while (val <= key) { // updated start and end value            
                start = end;      //store previous end
                end = 2 * end;   //double end index to increase the index size
                val = arr[end]; //update new elements in the array 
            }
            return midValSearch(arr, start, end, key); // execute if the double end is not out of bound
        } catch (ArrayIndexOutOfBoundsException e) {
            //if end is out of bound execute following
            while (val <= key) {
                val = arr[start];
                if (val == key) {
                    break;
                }
                start++;
            }
            return start;
        }
    }

    // Driver method
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // Asking for user input
        int numberOfUserID = input.nextInt();
        int key = input.nextInt();
        int userId[] = new int[numberOfUserID + 1]; // indexing from 1

        // insert element to the empty array
        for (int i = 1; i <= numberOfUserID; i++) {
            userId[i] = input.nextInt();
        }
        input.close();

        int M = findIndex(userId, key); // in the user searched value finding the user id position from the array.

        if (M == -1) {
            System.out.println("NOT_FOUND");
        } else {
            System.out.println(M);
        }
    }
}