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
package com.epicdragonworld.gameserver.network;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES Rijndael encryption.
 * @author Pantelis Andrianakis
 */
public class Encryption
{
	// Secret keyword.
	private final static String PASSWORD = "SECRET_KEYWORD";
	// 16-byte private password.
	private final static String IV = "0123456789012345";
	// Transformation.
	private final static String ALGORITHM = "AES/CBC/PKCS5Padding";
	
	private static SecretKey _key;
	private static IvParameterSpec _ivParameterSpec;
	
	public Encryption()
	{
		try
		{
			_key = new SecretKeySpec(MessageDigest.getInstance("MD5").digest(PASSWORD.getBytes("UTF-8")), "AES");
			_ivParameterSpec = new IvParameterSpec(IV.getBytes("UTF-8"));
		}
		catch (Exception e)
		{
		}
	}
	
	public static byte[] encrypt(byte[] bytes)
	{
		try
		{
			final Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, _key, _ivParameterSpec);
			return cipher.doFinal(bytes);
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
	public static byte[] decrypt(byte[] bytes)
	{
		try
		{
			final Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, _key, _ivParameterSpec);
			return cipher.doFinal(bytes);
		}
		catch (Exception e)
		{
			return null;
		}
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
