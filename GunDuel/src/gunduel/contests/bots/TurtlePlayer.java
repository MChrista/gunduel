package gunduel.contests.bots;

import gunduel.contests.Action;


public class TurtlePlayer implements Player
{

	private short ownAmmo;
	private int oppAmmo;
	private int shieldHealth;
	private int turn;

	public TurtlePlayer()
	{
		ownAmmo = 0;
		oppAmmo = 0;
		shieldHealth = 5;
		turn = 0;
	}

	@Override
	public Action nextAction()
	{
		// First Turn, load bullet
		if (turn == 0)
		{
			return this.load();
		}

		if (turn >= 1 && oppAmmo == 0)
		{
			// If no bullets in gun, load one
			if (ownAmmo == 0)
			{
				return this.load();
			}
			// If shield is low, repair
			if (shieldHealth < 3)
			{
				return this.repr();
			}
			// If bullets are in gun, fire one
			else if (ownAmmo > 0)
			{
				return this.fire();
			}
		}
		// Else, if opponent has bullets
		else
		{
			// Defend while the shield has health
			if (shieldHealth > 0)
			{
				return this.dfnd();
			}
			// If the shield is broken, go down guns a blazin'
			else
			{
				// Shoot if you have ammo
				if (ownAmmo > 0)
				{
					this.fire();
				}
				// Load if you don't
				else
				{
					this.load();
				}
			}
		}
		return this.dfnd();
	}

	@Override
	public void perceive(final Action a)
	{
		// Returns what the other player performed last turn
		// Goal here is to count how many bullets the other play has in his gun and then only shoot when he has none
		if (a == Action.LOAD)
		{
			oppAmmo++;
		}

		if (a == Action.FIRE)
		{
			oppAmmo--;
			shieldHealth--;
		}

		turn++;

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
