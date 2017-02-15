package gunduel.contests;

import gunduel.contests.bots.Player;


public class Duel
{

	// result matrix
	private static final Result[][] resultMatrix =
	{
			{ Result.DRAW, Result.BWIN, Result.DRAW, Result.DRAW },
			{ Result.AWIN, Result.DRAW, Result.DRAW, Result.AWIN },
			{ Result.DRAW, Result.DRAW, Result.DRAW, Result.DRAW },
			{ Result.DRAW, Result.BWIN, Result.DRAW, Result.DRAW } };

	// internal game stats
	private static short playerAAmmo;
	private static short playerBAmmo;
	private static short playerAShieldHealth;
	private static short playerBShieldHealth;
	private static Result result;
	private static boolean forceEnd;

	// config
	private static short rounds;
	private static short turn;
	private static boolean squelchOutput;

	// players
	private static Player playerA;
	private static Player playerB;

	public Duel(final Player pA, final Player pB, final boolean squelch)
	{
		this.playerA = pA;
		this.playerB = pB;
		this.squelchOutput = squelch;
		this.playerAAmmo = 0;
		this.playerBAmmo = 0;
		this.playerAShieldHealth = 5;
		this.playerBShieldHealth = 5;
		this.forceEnd = false;

		this.rounds = 100;
		this.turn = 1;

		this.result = gameloop();
	}

	// Getter for results
	public Result getResult()
	{
		return this.result;
	}

	private static Result gameloop()
	{
		Result res = Result.DRAW;
		// for number of rounds perform turns
		while (!forceEnd && turn <= rounds && res == Result.DRAW)
		{
			res = turn();
			turn++;
		}

		return res;
	}

	// actual game logic
	private static Result turn()
	{
		Result res;
		boolean playerAValidAction = true;
		boolean playerBValidAction = true;

		if (squelchOutput == false)
		{
			System.out.println(turn + ":\tA(" + playerAAmmo + ")S(" + playerAShieldHealth + ")  B(" + playerBAmmo + ")S("
					+ playerBShieldHealth + ")");
		}

		// receive players actions and inform them
		final Action playerAAction = playerA.nextAction();
		final Action playerBAction = playerB.nextAction();

		// this call could also be done or eventually avoided after the action validity check
		playerA.perceive(playerBAction);
		playerB.perceive(playerAAction);

		// check for validity of moves and publish result
		// Breaking up these if statements

		// Find if player A made a valid action.  Invalid action is defending with no shield and getting shot
		// or firing with no bullets
		if ((playerAAmmo == 0 && playerAAction == Action.FIRE)
				|| (playerAShieldHealth == 0 && (playerAAction == Action.DFND && playerBAction == Action.FIRE)))
		{
			playerAValidAction = false;
		}
		else
		{
			playerAValidAction = true;
		}

		// Find if player B made a valid action.  See above
		if ((playerBAmmo == 0 && playerBAction == Action.FIRE)
				|| (playerBShieldHealth == 0 && (playerBAction == Action.DFND && playerAAction == Action.FIRE)))
		{
			playerBValidAction = false;
		}
		else
		{
			playerBValidAction = true;
		}

		// If both players made valid actions, get the results from resultMatrix
		if (playerAValidAction && playerBValidAction)
		{
			res = resultMatrix[playerAAction.ordinal()][playerBAction.ordinal()];
		}
		else
		{
			// If we have entered an invalid action, end thegame
			forceEnd = true;
			// If player A made a valid action and B did not, player A wins
			if (playerAValidAction && !playerBValidAction)
			{
				return Result.AWIN;
			}
			// if player B made a valid action and A did not, player B wins
			else if (!playerAValidAction && playerBValidAction)
			{
				return Result.BWIN;
			}
			// catch if both made an invalid action and draw
			else
			{
				return Result.DRAW;
			}


		}

		if (squelchOutput == false)
		{
			System.out.println(turn + ":\tA->" + playerAAction + "\t  B->" + playerBAction + " ==> " + res);
		}
		//update stats
		if (playerAAction == Action.LOAD)
		{
			playerAAmmo++;
		}
		if (playerAAction == Action.FIRE && playerAAmmo > 0)
		{
			playerAAmmo--;
		}
		if (playerAAction == Action.DFND && playerBAction == Action.FIRE && playerBValidAction)
		{
			playerAShieldHealth--;
		}
		if (playerAAction == Action.REPR)
		{
			playerAShieldHealth++;
		}
		if (playerBAction == Action.LOAD)
		{
			playerBAmmo++;
		}
		if (playerBAction == Action.FIRE && playerBAmmo > 0)
		{
			playerBAmmo--;
		}
		if (playerBAction == Action.DFND && playerAAction == Action.FIRE && playerAValidAction)
		{
			playerBShieldHealth--;
		}
		if (playerBAction == Action.REPR)
		{
			playerBShieldHealth++;
		}

		return res;
	}
}
