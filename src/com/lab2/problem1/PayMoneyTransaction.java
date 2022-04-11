package com.lab2.problem1;

/*
 * Time complexity
 * Worst case: O(n^2) 
 */

import java.util.Scanner;

public class PayMoneyTransaction {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.println("Enter the size of transaction array");
		int numTransactions = in.nextInt();

		int[] transactions = new int[numTransactions];

		System.out.println("Enter the values of array");
		for (int i = 0; i < numTransactions; i++) {
			transactions[i] = in.nextInt();
		}

		System.out.println("Enter the total no of targets that needs to be achieved");
		int numTargets = in.nextInt();

		for (int i = 0; i < numTargets; i++) {
			System.out.println("Enter the value of target");
			int target = in.nextInt();

			int count = numOfTransctionsRequired(transactions, target);

			if (count == -1) {
				System.out.println("Given target is not achieved");
			} else {
				System.out.println("Target achieved after " + count + " transactions");
			}

			System.out.println();
		}

		in.close();
	}

	private static int numOfTransctionsRequired(int[] transactions, int target) {
		int sum = 0;
		for (int i = 0; i < transactions.length; i++) {
			sum += transactions[i];
			if (sum >= target) {
				return ++i;
			}
		}
		return -1;
	}
}
