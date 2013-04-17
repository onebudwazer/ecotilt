package fr.ecotilt.appui.util;

import java.util.Random;

public class RandomNumber {

	public static int getRandomInt(int mod) {
		Random rand = new Random();
		return Math.abs(rand.nextInt()) % mod + 1;
	}
	
	public static Double getRandomDouble(Double mod) {
		Random rand = new Random();
		Double value = (Math.abs(rand.nextDouble()) % mod * 100);
		return (double)Math.round(value * 10000000) / 10000000;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(getRandomDouble(100.0));
		}
	}
}
