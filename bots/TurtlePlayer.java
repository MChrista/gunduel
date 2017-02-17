public class TurtlePlayer implements Player
{

	private short ownAmmo;
	private int oppAmmo;
	private int turn = 0;

	public TurtlePlayer()
	{
		ownAmmo = 0;
	}

	@Override
	public Action nextAction()
	{
		// First Turn, load bullet
		if (turn == 0)
		{
			return this.load();
		}

		// From here on out, only take action when the opponent has 0 bullets.  Allow game to draw if this situation does not occur
		if (turn > 1 && oppAmmo == 0)
		{
			// If no bullets in gun, load one
			if (ownAmmo == 0)
			{
				return this.load();
			}
			// If bullets are in gun, fire one
			else if (ownAmmo > 0)
			{
				return this.fire();
			}
		}
		// Else, if opponent has bullets, defense.
		else
		{
			return this.dfnd();
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

}
