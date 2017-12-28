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
package com.epicdragonworld.gameserver.network.packets.receivable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import com.epicdragonworld.gameserver.managers.DatabaseManager;
import com.epicdragonworld.gameserver.network.GameClient;
import com.epicdragonworld.gameserver.network.ReceivablePacket;
import com.epicdragonworld.gameserver.network.packets.sendable.AccountAuthenticationResult;

/**
 * @author Pantelis Andrianakis
 */
public class AccountAuthenticationRequest
{
	private static final Logger LOGGER = Logger.getLogger(AccountAuthenticationRequest.class.getName());
	
	private static final String ACCOUNT_QUERY = "SELECT * FROM accounts WHERE account=?";
	private static final String ACCOUNT_INFO_UPDATE = "UPDATE accounts SET last_active=?, last_ip=? WHERE account=?";
	private static final int STATUS_NOT_FOUND = 0;
	private static final int STATUS_WRONG_PASSWORD = 3;
	@SuppressWarnings("unused")
	private static final int STATUS_TOO_MANY_ONLINE = 4; // TODO: STATUS_TOO_MANY_ONLINE
	private static final int STATUS_AUTHENTICATED = 100;
	
	public AccountAuthenticationRequest(GameClient client, ReceivablePacket packet)
	{
		// Read data.
		final String accountName = packet.readString().toLowerCase();
		final String passwordHash = packet.readString();
		
		// Local data.
		String storedPassword = "";
		int status = STATUS_NOT_FOUND;
		
		// Get data from database.
		try (Connection con = DatabaseManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(ACCOUNT_QUERY))
		{
			ps.setString(1, accountName);
			try (ResultSet rset = ps.executeQuery())
			{
				while (rset.next())
				{
					storedPassword = rset.getString("password");
					status = rset.getInt("status");
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.warning(e.getMessage());
		}
		
		// Account status issue.
		// 0 does not exist, 1 banned, 2 requires activation, 3 wrong password, 4 too many online, 5 authenticated
		if (status < STATUS_WRONG_PASSWORD)
		{
			client.channelSend(new AccountAuthenticationResult(status));
			return;
		}
		
		// Wrong password.
		if (!passwordHash.equals(storedPassword))
		{
			client.channelSend(new AccountAuthenticationResult(STATUS_WRONG_PASSWORD));
			return;
		}
		
		// TODO: STATUS_TOO_MANY_ONLINE
		
		// Authentication was successful.
		client.setAccountName(accountName);
		client.channelSend(new AccountAuthenticationResult(STATUS_AUTHENTICATED));
		
		// Update last login date and IP address.
		try (Connection con = DatabaseManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(ACCOUNT_INFO_UPDATE))
		{
			ps.setLong(1, System.currentTimeMillis());
			ps.setString(2, client.getIp());
			ps.setString(3, accountName);
			ps.execute();
		}
		catch (Exception e)
		{
			LOGGER.warning(e.getMessage());
		}
	}
}
