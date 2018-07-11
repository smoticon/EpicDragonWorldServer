package com.epicdragonworld.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Pantelis Andrianakis
 */
public final class Rnd
{
	/**
	 * @return a pseudorandom boolean value.
	 */
	public static boolean getBoolean()
	{
		return ThreadLocalRandom.current().nextBoolean();
	}
	
	/**
	 * @param bound (int)
	 * @return a pseudorandom int value between zero (inclusive) and the specified bound (exclusive).
	 */
	public static int get(int bound)
	{
		return ThreadLocalRandom.current().nextInt(bound);
	}
	
	/**
	 * @param origin (int)
	 * @param bound (int)
	 * @return a pseudorandom int value between the specified origin (inclusive) and the specified bound (exclusive).
	 */
	public static int get(int origin, int bound)
	{
		return ThreadLocalRandom.current().nextInt(origin, bound);
	}
	
	/**
	 * @param bound (long)
	 * @return a pseudorandom long value between zero (inclusive) and the specified bound (exclusive).
	 */
	public static long get(long bound)
	{
		return ThreadLocalRandom.current().nextLong(bound);
	}
	
	/**
	 * @param origin (long)
	 * @param bound (long)
	 * @return a pseudorandom long value between the specified origin (inclusive) and the specified bound (exclusive).
	 */
	public static long get(long origin, long bound)
	{
		return ThreadLocalRandom.current().nextLong(origin, bound);
	}
	
	/**
	 * @param bound (double)
	 * @return a pseudorandom double value between zero (inclusive) and the specified bound (exclusive).
	 */
	public static double get(double bound)
	{
		return ThreadLocalRandom.current().nextDouble(bound);
	}
	
	/**
	 * @param origin (double)
	 * @param bound (double)
	 * @return a pseudorandom double value between the specified origin (inclusive) and the specified bound (exclusive).
	 */
	public static double get(double origin, double bound)
	{
		return ThreadLocalRandom.current().nextDouble(origin, bound);
	}
}
