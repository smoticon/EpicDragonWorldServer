package com.epicdragonworld.gameserver.tasks;

/**
 * @author Pantelis Andrianakis
 */
public class ExplicitlyNullifyTask implements Runnable
{
	private Object _object;
	
	public ExplicitlyNullifyTask(Object object)
	{
		_object = object;
	}
	
	@Override
	public void run()
	{
		if (_object != null)
		{
			_object = null;
		}
	}
}