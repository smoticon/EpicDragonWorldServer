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

import java.io.ByteArrayInputStream;

/**
 * @author Pantelis Andrianakis
 */
public class ReceivablePacket
{
	private final ByteArrayInputStream _bais;
	
	public ReceivablePacket(byte[] bytes)
	{
		_bais = new ByteArrayInputStream(bytes);
	}
	
	public String readString()
	{
		String result = "";
		try
		{
			result = new String(readBytes(readByte()), "UTF-8");
		}
		catch (Exception e)
		{
		}
		return result;
	}
	
	public byte[] readBytes(int length)
	{
		final byte[] result = new byte[length];
		for (int i = 0; i < length; i++)
		{
			result[i] = (byte) _bais.read();
		}
		return result;
	}
	
	public int readByte()
	{
		return _bais.read();
	}
	
	public int readShort()
	{
		return (_bais.read() & 0xff) | ((_bais.read() << 8) & 0xff00);
	}
	
	public int readInt()
	{
		int result = _bais.read() & 0xff;
		result |= (_bais.read() << 8) & 0xff00;
		result |= (_bais.read() << 0x10) & 0xff0000;
		result |= (_bais.read() << 0x18) & 0xff000000;
		return result;
	}
	
	public long readLong()
	{
		long result = _bais.read() & 0xff;
		result |= (_bais.read() & 0xffL) << 8L;
		result |= (_bais.read() & 0xffL) << 16L;
		result |= (_bais.read() & 0xffL) << 24L;
		result |= (_bais.read() & 0xffL) << 32L;
		result |= (_bais.read() & 0xffL) << 40L;
		result |= (_bais.read() & 0xffL) << 48L;
		result |= (_bais.read() & 0xffL) << 56L;
		return result;
	}
	
	public double readDouble()
	{
		long result = _bais.read() & 0xff;
		result |= (_bais.read() & 0xffL) << 8L;
		result |= (_bais.read() & 0xffL) << 16L;
		result |= (_bais.read() & 0xffL) << 24L;
		result |= (_bais.read() & 0xffL) << 32L;
		result |= (_bais.read() & 0xffL) << 40L;
		result |= (_bais.read() & 0xffL) << 48L;
		result |= (_bais.read() & 0xffL) << 56L;
		return Double.longBitsToDouble(result);
	}
}
