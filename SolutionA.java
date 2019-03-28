package com.company;

import java.util.Scanner;

public class SolutionA {

    //Find the index position method
    public static int findIndex(int arr[], int start, int end) {
        if (end >= start) {
            // Find the mid value of the array
            int mid = (start + end) / 2;

            if (mid == arr[mid]) {
                return mid;
            } else if (mid > arr[mid]) {
                return findIndex(arr, (mid + 1), end);
            } else {
                return findIndex(arr, start, (mid - 1));
            }
        }

        // Return -1 if there is no index matching element value found
        return -1;
    }

    //Drive method
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        // asking for user input for length of the array
        int numberOfUserID = input.nextInt();
        int userId[] = new int[numberOfUserID + 1];

        // insert elements to the empty array by user
        for (int i = 1; i <= numberOfUserID; i++) {
            userId[i] = input.nextInt();
        }
        input.close();

        //output
        int N = userId.length; // size of the array
        int M = findIndex(userId, 1, N - 1); // position of element in the array

        //Display the result
        if (M == -1) {
            System.out.println("NOT_FOUND");
        } else {
            System.out.println(M);
        }
    }
}