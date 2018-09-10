package com.epicdragonworld.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Pantelis Andrianakis
 */
public final class Rnd
{
	final static ThreadLocalRandom RANDOM = ThreadLocalRandom.current();
	
	/**
	 * @return a pseudorandom boolean value.
	 */
	public static boolean getBoolean()
	{
		return RANDOM.nextBoolean();
	}
	
	/**
	 * @param bound (int)
	 * @return a pseudorandom int value between zero (inclusive) and the specified bound (exclusive).
	 */
	public static int get(int bound)
	{
		return RANDOM.nextInt(bound);
	}
	
	/**
	 * @param origin (int)
	 * @param bound (int)
	 * @return a pseudorandom int value between the specified origin (inclusive) and the specified bound (exclusive).
	 */
	public static int get(int origin, int bound)
	{
		return RANDOM.nextInt(origin, bound);
	}
	
	/**
	 * @param bound (long)
	 * @return a pseudorandom long value between zero (inclusive) and the specified bound (exclusive).
	 */
	public static long get(long bound)
	{
		return RANDOM.nextLong(bound);
	}
	
	/**
	 * @param origin (long)
	 * @param bound (long)
	 * @return a pseudorandom long value between the specified origin (inclusive) and the specified bound (exclusive).
	 */
	public static long get(long origin, long bound)
	{
		return RANDOM.nextLong(origin, bound);
	}
	
	/**
	 * @param bound (double)
	 * @return a pseudorandom double value between zero (inclusive) and the specified bound (exclusive).
	 */
	public static double get(double bound)
	{
		return RANDOM.nextDouble(bound);
	}
	
	/**
	 * @param origin (double)
	 * @param bound (double)
	 * @return a pseudorandom double value between the specified origin (inclusive) and the specified bound (exclusive).
	 */
	public static double get(double origin, double bound)
	{
		return RANDOM.nextDouble(origin, bound);
	}
	
	/**
	 * @return a pseudorandom boolean value.
	 */
	public static boolean nextBoolean()
	{
		return RANDOM.nextBoolean();
	}
	
	/**
	 * Generates random bytes and places them into a user-supplied byte array. The number of random bytes produced is equal to the length of the byte array.
	 * @param bytes the byte array to fill with random bytes.
	 */
	public static void nextBytes(byte[] bytes)
	{
		RANDOM.nextBytes(bytes);
	}
	
	/**
	 * @return a pseudorandom int value.
	 */
	public static int nextInt()
	{
		return RANDOM.nextInt();
	}
	
	/**
	 * @return a pseudorandom double value between zero (inclusive) and one (exclusive).
	 */
	public static double nextDouble()
	{
		return RANDOM.nextDouble();
	}
	
	/**
	 * @return the next pseudorandom, Gaussian ("normally") distributed double value with mean 0.0 and standard deviation 1.0 from this random number generator's sequence.
	 */
	public static double nextGaussian()
	{
		return RANDOM.nextGaussian();
	}
}
