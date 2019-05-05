/**
 * Author: Pantelis Andrianakis
 * Date: May 5th 2019
 */
public class SkillHolder
{
    private protected int skillId;
    private protected int level;
    private protected SkillType skillType;
    private protected int reuse;
    private protected int range;
    private protected int param1;
    private protected int param2;

    public SkillHolder(int skillId, int level, SkillType skillType, int reuse, int range, int param1, int param2)
    {
        this.skillId = skillId;
        this.level = level;
        this.skillType = skillType;
        this.reuse = reuse;
        this.range = range;
        this.param1 = param1;
        this.param2 = param2;
    }

    public int GetSkillId()
    {
        return skillId;
    }

    public int GetLevel()
    {
        return level;
    }

    public SkillType GetSkillType()
    {
        return skillType;
    }

    public int GetReuse()
    {
        return reuse;
    }

    public int GetRange()
    {
        return range;
    }

    public int GetParam1()
    {
        return param1;
    }

    public int GetParam2()
    {
        return param2;
    }
}
