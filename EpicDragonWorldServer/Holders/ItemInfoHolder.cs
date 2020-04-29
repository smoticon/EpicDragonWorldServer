using System;

public class ItemInfoHolder
{
    private readonly int _itemId;
    private readonly bool _equipped;
    private readonly int _amount;
    private readonly int _enchantLvl;
    public ItemInfoHolder(int itemId, bool equipped, int amount, int enchantLvl)
    {
        _itemId = itemId;
        _equipped = equipped;
        _amount = amount;
        _enchantLvl = enchantLvl;
    }

    public int GetItemId()
    {
        return _itemId;
    }

    public bool IsEquipped()
    {
        return _equipped;
    }
    
    public int GetAmount()
    {
        return _amount;
    }

    public int GetEnchantLvl()
    {
        return _enchantLvl;
    }
}