package gunduel.contests.bots;

import gunduel.contests.Action;


public class BerserkerPlayer implements Player
{

	private short ownAmmo;
	private short ownShieldHealth;

	public BerserkerPlayer()
	{
		ownAmmo = 0;
		ownShieldHealth = 5;
	}

	@Override
	public Action nextAction()
	{
		if (ownAmmo == 0)
		{
			return this.load();
		}
		else
		{
			return this.fire();
		}
	}

	@Override
	public void perceive(final Action a)
	{
		if (a == Action.FIRE && this.nextAction() == Action.DFND)
		{
			ownShieldHealth--;
		}
	}

	private Action load()
	{
		ownAmmo++;
		return Action.LOAD;
	}

	private Action fire()
	{
		ownAmmo--;
		return Action.FIRE;
	}

	private Action dfnd()
	{
		return Action.DFND;
	}

	private Action repr()
	{
		return Action.REPR;
	}

}