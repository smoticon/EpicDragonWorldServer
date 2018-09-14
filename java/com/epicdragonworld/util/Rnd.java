package com.epicdragonworld.util;

import java.util.Random;

/**
 * @author Pantelis Andrianakis
 */
public final class Rnd
{
	/**
	 * @return a pseudorandom boolean value.
	 */
	public static boolean nextBoolean()
	{
		final Random random = new Random();
		return random.nextBoolean();
	}
	
	/**
	 * Generates random bytes and places them into a user-supplied byte array. The number of random bytes produced is equal to the length of the byte array.
	 * @param bytes the byte array to fill with random bytes.
	 */
	public static void nextBytes(byte[] bytes)
	{
		final Random random = new Random();
		random.nextBytes(bytes);
	}
	
	/**
	 * @param bound (int)
	 * @return a pseudorandom int value between zero (inclusive) and the specified bound (exclusive).
	 */
	public static int get(int bound)
	{
		final Random random = new Random();
		return random.nextInt(bound);
	}
	
	/**
	 * @param origin (int)
	 * @param bound (int)
	 * @return a pseudorandom int value between the specified origin (inclusive) and the specified bound (exclusive).
	 */
	public static int get(int origin, int bound)
	{
		final Random random = new Random();
		return origin + random.nextInt((bound - origin) + 1);
	}
	
	/**
	 * @return a pseudorandom int value.
	 */
	public static int nextInt()
	{
		final Random random = new Random();
		return random.nextInt();
	}
	
	/**
	 * @param bound (long)
	 * @return a pseudorandom long value between zero (inclusive) and the specified bound (exclusive).
	 */
	public static long get(long bound)
	{
		final Random random = new Random();
		return (long) (random.nextDouble() * bound);
	}
	
	/**
	 * @param origin (long)
	 * @param bound (long)
	 * @return a pseudorandom long value between the specified origin (inclusive) and the specified bound (exclusive).
	 */
	public static long get(long origin, long bound)
	{
		final Random random = new Random();
		return origin + (long) ((bound - origin) * random.nextDouble());
	}
	
	/**
	 * @return a pseudorandom long value.
	 */
	public static long nextLong()
	{
		final Random random = new Random();
		return random.nextLong();
	}
	
	/**
	 * @param bound (double)
	 * @return a pseudorandom double value between zero (inclusive) and the specified bound (exclusive).
	 */
	public static double get(double bound)
	{
		final Random random = new Random();
		return random.nextDouble() * bound;
	}
	
	/**
	 * @param origin (double)
	 * @param bound (double)
	 * @return a pseudorandom double value between the specified origin (inclusive) and the specified bound (exclusive).
	 */
	public static double get(double origin, double bound)
	{
		final Random random = new Random();
		return origin + ((bound - origin) * random.nextDouble());
	}
	
	/**
	 * @return a pseudorandom double value between zero (inclusive) and one (exclusive).
	 */
	public static double nextDouble()
	{
		final Random random = new Random();
		return random.nextDouble();
	}
	
	/**
	 * @return the next pseudorandom, Gaussian ("normally") distributed double value with mean 0.0 and standard deviation 1.0 from this random number generator's sequence.
	 */
	public static double nextGaussian()
	{
		final Random random = new Random();
		return random.nextGaussian();
	}
}
