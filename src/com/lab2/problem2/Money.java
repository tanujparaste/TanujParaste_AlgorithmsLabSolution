package com.lab2.problem2;

import java.util.Scanner;

public class Money {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.println("Enter the size of currency denominations");
		int numDenominations = in.nextInt();

		int[] denominations = new int[numDenominations];

		System.out.println("Enter the currency denominations value");
		for (int i = 0; i < numDenominations; i++) {
			denominations[i] = in.nextInt();
		}

		sort(denominations, true); // sort([], desc) --- merge sort impl

		printArray(denominations);

		System.out.println("Enter the amount you want to pay");
		int amount = in.nextInt();

		payMinimumNotes(denominations, amount);

		in.close();
	}

	private static void payMinimumNotes(int[] denominations, int amount) {
		int[] denominationCounter = new int[denominations.length];

		try {
			if (amount <= 0) {
				throw new Exception("No need to pay the amount " + amount + ".");
			}
			for (int i = 0; i < denominations.length; i++) {
				if (amount >= denominations[i]) {
					denominationCounter[i] = amount / denominations[i];
					amount %= denominations[i];
					if (amount == 0) {
						break;
					}
				}
			}

			if (amount > 0) {
				System.out.println("The amount can't be paid with the given denominations.");
			} else {
				System.out.println("Your payment approach in order to give min no of notes will be");
				for (int i = 0; i < denominations.length; i++) {
					if (denominationCounter[i] != 0) {
						System.out.println(denominations[i] + ":" + denominationCounter[i]);
					}
				}
			}
		} catch (ArithmeticException ex) {
			System.out.println("Denomination of 0 is not valid.");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

//	private static void payMinimumNotes(int[] denominations, int amount) {
//		boolean isEligibleDenomination = false;
//		for (int i = 0; i < denominations.length; i++) {
//			int count = 0;
//			int denomination = denominations[i];
//			while (amount - denomination >= 0) {
//				isEligibleDenomination = true;
//				count++;
//				amount = amount - denomination;
//			}
//			if (isEligibleDenomination) {
//				System.out.println(denominations[i] + ":" + count);
//				isEligibleDenomination = false;
//			}
//		}
//	}

	private static void sort(int[] array, boolean desc) {
		sort(array, 0, array.length - 1, desc);
	}

	private static void sort(int[] array, int left, int right, boolean desc) {
		if (left < right) {
			int mid = left + (right - left) / 2;
			sort(array, left, mid, desc);
			sort(array, mid + 1, right, desc);
			merge(array, left, mid, right, desc);
		}
	}

	private static void merge(int[] array, int left, int mid, int right, boolean desc) {
		int n1 = mid - left + 1;
		int n2 = right - mid;
		int[] leftArray = new int[n1];
		int[] rightArray = new int[n2];

		for (int i = 0; i < n1; i++) {
			leftArray[i] = array[left + i];
		}
		for (int i = 0; i < n2; i++) {
			rightArray[i] = array[mid + 1 + i];
		}

		int i = 0, j = 0;
		int k = left;
		while (i < n1 && j < n2) {
			if (desc ? leftArray[i] >= rightArray[j] : leftArray[i] <= rightArray[j]) {
				array[k++] = leftArray[i++];
			} else {
				array[k++] = rightArray[j++];
			}
		}

		while (i < n1) {
			array[k++] = leftArray[i++];
		}
		while (i < n2) {
			array[k++] = rightArray[j++];
		}
	}

	private static void printArray(int[] array) {
		System.out.println("---");
		System.out.println("sorted array: ");
		for (int e : array) {
			System.out.print(e + " ");
		}
		System.out.println();
		System.out.println("---");
	}
}
