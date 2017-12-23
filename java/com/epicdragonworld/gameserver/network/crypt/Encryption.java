/*
 * This file is part of the Epic Dragon World project.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.epicdragonworld.gameserver.network.crypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author Pantelis Andrianakis
 */
public final class Encryption
{
	private final static String KEY = "SECRET_KEYWORD";
	private final static String CHARSET = "UTF-8";
	private final static String ALGORITHM = "Blowfish";
	private static SecretKeySpec secretKeySpec;
	
	public Encryption()
	{
		try
		{
			secretKeySpec = new SecretKeySpec(KEY.getBytes(CHARSET), ALGORITHM);
		}
		catch (Exception e)
		{
		}
	}
	
	public static byte[] encrypt(byte[] data)
	{
		try
		{
			final Cipher encryptCipher = Cipher.getInstance(ALGORITHM);
			encryptCipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			return encryptCipher.doFinal(data);
		}
		catch (Exception e)
		{
		}
		return null;
	}
	
	public static byte[] decrypt(byte[] data)
	{
		try
		{
			final Cipher decryptCipher = Cipher.getInstance(ALGORITHM);
			decryptCipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
			return decryptCipher.doFinal(data);
		}
		catch (Exception e)
		{
		}
		return null;
	}
	
	public static Encryption getInstance()
	{
		return SingletonHolder.INSTANCE;
	}
	
	private static class SingletonHolder
	{
		protected static final Encryption INSTANCE = new Encryption();
	}
}
