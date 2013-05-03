package fr.ecotilt.appui.util;

import java.util.Random;

public class RandomNumber {

	protected static Random	random	= new Random();

	public static int getRandomInt(int mod) {
		Random rand = new Random();
		return Math.abs(rand.nextInt()) % mod + 1;
	}

	public static Double getRandomDouble(Double mod) {
		Random rand = new Random();
		Double value = (Math.abs(rand.nextDouble()) % mod * 100);
		return (double) Math.round(value * 10000000) / 10000000;
	}

	public static double inRangeDouble(double min, double max) {
		double range = max - min;
		double scaled = random.nextDouble() * range;
		double shifted = scaled + min;
		return shifted; // == (rand.nextDouble() * (max-min)) + min;
	}

	public static void main(String[] args) {
		// for (int i = 0; i < 10; i++) {
		// System.out.println(getRandomDouble(100.0));
		// }

		// for (int i = 0; i < 20; i++) {
		// System.out.println(inRangeDouble(7.05165165, 18.05156561));
		// }

		System.out.println(randomAreaMarseilleLatitude());
		System.out.println(randomAreaMarseilleLongitude());
	}

	public static double randomAreaMarseilleLatitude() {
		double max = StaticZoneVar.areaNorthWest.getLatitude();
		double min = StaticZoneVar.areaSouthEst.getLatitude();

		// System.out.println("max: " + max);
		// System.out.println("min: " + min);

		double value = inRangeDouble(min, max);
		return value;
	}

	public static double randomAreaMarseilleLongitude() {
		double max = StaticZoneVar.areaNorthWest.getLongitude();
		double min = StaticZoneVar.areaSouthEst.getLongitude();

		// System.out.println("max: " + max);
		// System.out.println("min: " + min);

		double value = inRangeDouble(min, max);
		return value;
	}

}
