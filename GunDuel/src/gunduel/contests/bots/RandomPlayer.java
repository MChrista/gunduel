package gunduel.contests.bots;

import gunduel.contests.Action;

public class RandomPlayer implements Player
{

	private short ownAmmo;
	private short ownShieldHealth = 5;

	public RandomPlayer()
	{
		ownAmmo = 0;
	}

	@Override
	public Action nextAction()
	{
		if (ownAmmo == 0)
		{
			return (Math.random() >= 0.5) ? this.load() : this.dfnd();
		}
		else if (ownShieldHealth == 1)
		{
			return (Math.random() >= 0.5) ? this.load() : this.repr();
		}
		else
		{
			return (Math.random() >= 0.5) ? this.load() : this.fire();
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
