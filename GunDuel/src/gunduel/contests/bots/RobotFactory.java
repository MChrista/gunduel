package gunduel.contests.bots;

public class RobotFactory
{
	public RobotFactory()
	{

	}

	public Player getPlayer(final String input)
	{
		if (input.equals("RandomPlayer"))
		{
			return new RandomPlayer();
		}
		else if (input.equals("BerserkerPlayer"))
		{
			return new BerserkerPlayer();
		}
		else if (input.equals("TurtlePlayer"))
		{
			return new TurtlePlayer();
		}
		else
		{
			return null;
		}
	}

}
