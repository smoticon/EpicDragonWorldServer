package com.epicdragonworld.util;

/**
 * String utilities optimized for the best performance.<br>
 * @author fordfrog
 */
public final class StringUtil
{
	/**
	 * Concatenates strings.
	 * @param strings strings to be concatenated
	 * @return concatenated string
	 */
	public static String concat(String... strings)
	{
		final StringBuilder sbString = new StringBuilder();
		for (String string : strings)
		{
			sbString.append(string);
		}
		return sbString.toString();
	}
	
	/**
	 * Appends strings to existing string builder.
	 * @param sbString string builder
	 * @param strings strings to be appended
	 */
	public static void append(StringBuilder sbString, String... strings)
	{
		sbString.ensureCapacity(sbString.length() + getLength(strings));
		
		for (String string : strings)
		{
			sbString.append(string);
		}
	}
	
	public static int getLength(Iterable<String> strings)
	{
		int length = 0;
		for (String string : strings)
		{
			length += (string == null) ? 4 : string.length();
		}
		return length;
	}
	
	/**
	 * Counts total length of all the strings.
	 * @param strings array of strings
	 * @return total length of all the strings
	 */
	public static int getLength(String[] strings)
	{
		int length = 0;
		for (String string : strings)
		{
			length += (string == null) ? 4 : string.length();
		}
		return length;
	}
}