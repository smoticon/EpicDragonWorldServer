/**
 * Author: Pantelis Andrianakis
 * Date: November 28th 2019
 */
public class NpcHolder
{
    private readonly int npcId;
    private readonly NpcType npcType;
    private readonly int level;
    private readonly bool sex;
    private readonly int stamina;
    private readonly int strength;
    private readonly int dexterity;
    private readonly int intelect;

    public NpcHolder(int npcId, NpcType npcType, int level, bool sex, int stamina, int strength, int dexterity, int intelect)
    {
        this.npcId = npcId;
        this.npcType = npcType;
        this.level = level;
        this.sex = sex;
        this.stamina = stamina;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelect = intelect;
    }

    public int GetNpcId()
    {
        return npcId;
    }

    public NpcType GetNpcType()
    {
        return npcType;
    }

    public int GetLevel()
    {
        return level;
    }

    public bool IsFemale()
    {
        return sex;
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
}
