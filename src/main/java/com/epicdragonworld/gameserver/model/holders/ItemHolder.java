package com.epicdragonworld.gameserver.model.holders;

import com.epicdragonworld.gameserver.enums.ItemType;

/**
 * @author Pantelis Andrianakis
 */
public class ItemHolder
{
	private final int _itemId;
	private final int _slotId;
	private final ItemType _itemType;
	private final boolean _stackable;
	private final boolean _tradable;
	private final long _price;
	private final int _sta;
	private final int _str;
	private final int _dex;
	private final int _int;
	private final SkillHolder _skillHolder;
	
	public ItemHolder(int itemId, int slotId, ItemType itemType, boolean stackable, boolean tradable, long price, int stamina, int strength, int dexterity, int intelect, SkillHolder skillHolder)
	{
		_itemId = itemId;
		_slotId = slotId;
		_itemType = itemType;
		_stackable = stackable;
		_tradable = tradable;
		_price = price;
		_sta = stamina;
		_str = strength;
		_dex = dexterity;
		_int = intelect;
		_skillHolder = skillHolder;
	}
	
	public int getItemId()
	{
		return _itemId;
	}
	
	public int getSlotId()
	{
		return _slotId;
	}
	
	public ItemType getItemType()
	{
		return _itemType;
	}
	
	public boolean isStackable()
	{
		return _stackable;
	}
	
	public boolean isTradable()
	{
		return _tradable;
	}
	
	public long getPrice()
	{
		return _price;
	}
	
	public int getSTA()
	{
		return _sta;
	}
	
	public int getSTR()
	{
		return _str;
	}
	
	public int getDEX()
	{
		return _dex;
	}
	
	public int getINT()
	{
		return _int;
	}
	
	public SkillHolder getSkillHolder()
	{
		return _skillHolder;
	}
}
