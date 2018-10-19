package com.Date.date;

public class Sort {
	public static void main(String[] args) {
		int[] arr = { 18, 15, 10, 20, 5, 88, 99, 21, -5 };

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if(arr[j] > arr[j+1]){
					int temp = arr[j];
	                arr[j] = arr[j+1];
	                arr[j+1] = temp;
				}
			}
		}
		
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}

	}

}
