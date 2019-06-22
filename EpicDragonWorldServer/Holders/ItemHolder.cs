/**
 * Author: Pantelis Andrianakis
 * Date: May 5th 2019
 */
public class ItemHolder
{
    private readonly int itemId;
    private readonly int slotId;
    private readonly ItemType itemType;
    private readonly bool stackable;
    private readonly bool tradable;
    private readonly long price;
    private readonly int stamina;
    private readonly int strength;
    private readonly int dexterity;
    private readonly int intelect;
    private readonly SkillHolder skillHolder;

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
