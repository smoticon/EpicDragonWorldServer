/**
 * Author: Pantelis Andrianakis
 * Date: May 5th 2019
 */
public class ItemHolder
{
    private protected int itemId;
    private protected int slotId;
    private protected ItemType itemType;
    private protected bool stackable;
    private protected bool tradable;
    private protected long price;
    private protected int stamina;
    private protected int strength;
    private protected int dexterity;
    private protected int intelect;
    private protected SkillHolder skillHolder;

    public ItemHolder(int itemId, int slotId, ItemType itemType, bool stackable, bool tradable, long price, int stamina, int strength, int dexterity, int intelect, SkillHolder skillHolder)
    {
        this.itemId = itemId;
        this.slotId = slotId;
        this.itemType = itemType;
        this.stackable = stackable;
        this.tradable = tradable;
        this.price = price;
        this.stamina = stamina;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelect = intelect;
        this.skillHolder = skillHolder;
    }

    public int GetItemId()
    {
        return itemId;
    }

    public int GetSlotId()
    {
        return slotId;
    }

    public ItemType GetItemType()
    {
        return itemType;
    }

    public bool IsStackable()
    {
        return stackable;
    }

    public bool IsTradable()
    {
        return tradable;
    }

    public long GetPrice()
    {
        return price;
    }

    public int GetSTA()
    {
        return stamina;
    }

    public int GetSTR()
    {
        return strength;
    }

    public int GetDEX()
    {
        return dexterity;
    }

    public int GetINT()
    {
        return intelect;
    }

    public SkillHolder GetSkillHolder()
    {
        return skillHolder;
    }
}
