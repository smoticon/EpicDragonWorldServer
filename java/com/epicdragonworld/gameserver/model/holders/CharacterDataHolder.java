package com.epicdragonworld.gameserver.model.holders;

/**
 * @author Pantelis Andrianakis
 */
public class CharacterDataHolder
{
	private String _name = "";
	private byte _slot = 0;
	private boolean _selected = false;
	private byte _classId = 0;
	private String _locationName = "";
	private float _x = 0;
	private float _y = 0;
	private float _z = 0;
	private int _heading = 0;
	private long _experience = 0;
	private long _hp = 0;
	private long _mp = 0;
	private byte _accessLevel = 0;
	private int _itemHead = 0;
	private int _itemChest = 0;
	private int _itemGloves = 0;
	private int _itemLegs = 0;
	private int _itemBoots = 0;
	private int _itemRightHand = 0;
	private int _itemLeftHand = 0;
	
	public String getName()
	{
		return _name;
	}
	
	public void setName(String name)
	{
		_name = name;
	}
	
	public byte getSlot()
	{
		return _slot;
	}
	
	public void setSlot(byte slot)
	{
		_slot = slot;
	}
	
	public boolean isSelected()
	{
		return _selected;
	}
	
	public void setSelected(boolean selected)
	{
		_selected = selected;
	}
	
	public byte getClassId()
	{
		return _classId;
	}
	
	public void setClassId(byte classId)
	{
		_classId = classId;
	}
	
	public String getLocationName()
	{
		return _locationName;
	}
	
	public void setLocationName(String locationName)
	{
		_locationName = locationName;
	}
	
	public float getX()
	{
		return _x;
	}
	
	public void setX(float x)
	{
		_x = x;
	}
	
	public float getY()
	{
		return _y;
	}
	
	public void setY(float y)
	{
		_y = y;
	}
	
	public float getZ()
	{
		return _z;
	}
	
	public void setZ(float z)
	{
		_z = z;
	}
	
	public int getHeading()
	{
		return _heading;
	}
	
	public void setHeading(int heading)
	{
		_heading = heading;
	}
	
	public long getExperience()
	{
		return _experience;
	}
	
	public void setExperience(long experience)
	{
		_experience = experience;
	}
	
	public long getHp()
	{
		return _hp;
	}
	
	public void setHp(long hp)
	{
		_hp = hp;
	}
	
	public long getMp()
	{
		return _mp;
	}
	
	public void setMp(long mp)
	{
		_mp = mp;
	}
	
	public byte getAccessLevel()
	{
		return _accessLevel;
	}
	
	public void setAccessLevel(byte accessLevel)
	{
		_accessLevel = accessLevel;
	}
	
	public int getItemHead()
	{
		return _itemHead;
	}
	
	public void setItemHead(int itemHead)
	{
		_itemHead = itemHead;
	}
	
	public int getItemChest()
	{
		return _itemChest;
	}
	
	public void setItemChest(int itemChest)
	{
		_itemChest = itemChest;
	}
	
	public int getItemGloves()
	{
		return _itemGloves;
	}
	
	public void setItemGloves(int itemGloves)
	{
		_itemGloves = itemGloves;
	}
	
	public int getItemLegs()
	{
		return _itemLegs;
	}
	
	public void setItemLegs(int itemLegs)
	{
		_itemLegs = itemLegs;
	}
	
	public int getItemBoots()
	{
		return _itemBoots;
	}
	
	public void setItemBoots(int itemBoots)
	{
		_itemBoots = itemBoots;
	}
	
	public int getItemRightHand()
	{
		return _itemRightHand;
	}
	
	public void setItemRightHand(int itemRightHand)
	{
		_itemRightHand = itemRightHand;
	}
	
	public int getItemLeftHand()
	{
		return _itemLeftHand;
	}
	
	public void setItemLeftHand(int itemLeftHand)
	{
		_itemLeftHand = itemLeftHand;
	}
}
