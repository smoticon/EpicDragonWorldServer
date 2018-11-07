/**
 * @author Pantelis Andrianakis
 */
public class Creature : WorldObject
{
    public override bool IsCreature()
    {
        return true;
    }

    public override Creature AsCreature()
    {
        return this;
    }
}
