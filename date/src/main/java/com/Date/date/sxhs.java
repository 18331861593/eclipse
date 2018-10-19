package com.Date.date;

/**
 * 水仙花数
 * 
 * @author Administrator
 *
 */
public class sxhs {
	public static void main(String[] args) {
		for (int i = 152; i < 1000; i++) {
			int f = i / 100;
			int s = i / 10 % 10;
			int t = i % 10;

			if (f * f * f + s * s * s + t * t * t == i) {
				System.out.println(i);
			}

		}
	}
}
