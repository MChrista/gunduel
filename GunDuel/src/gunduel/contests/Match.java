package gunduel.contests;

import java.util.ArrayList;
import java.util.List;

import gunduel.contests.bots.Player;


// A match is a contest of 10,000 duels between two players.  A match is an attempt to discover which player is better.
public class Match
{
	// Internal Match Statistics
	private static short DuelCount;
	private static short PlayerAWins;
	private static short PlayerBWins;
	private static short Draws = 0;
	private static Duel duel;
	private static Player playerA;
	private static Player playerB;
	private static List<Result> matchResults = new ArrayList<Result>();
	private static List<Duel> duelResults = new ArrayList<Duel>();

	public Match(final Player pA, final Player pB)
	{
		DuelCount = 10000;
		PlayerAWins = 0;
		PlayerBWins = 0;
		Draws = 0;
		duel = null;
		playerA = pA;
		playerB = pB;

		MatchLoop();
	}

	private void MatchLoop()
	{
		// Run 10000 duels
		for (int i = 0; i <= 10000; i++)
		{
			duelResults.add(new Duel(playerA, playerB, true));
			matchResults.add(duelResults.get(i).getResult());
		}

		for (int i = 0; i <= 10000; i++)
		{
			if (matchResults.get(i) == Result.AWIN)
			{
				PlayerAWins++;
			}
			else if (matchResults.get(i) == Result.BWIN)
			{
				PlayerBWins++;
			}
			else
			{
				Draws++;
			}
		}
		System.out.println("Player A wins: " + PlayerAWins + "\nPlayer B wins: " + PlayerBWins + "\nDraws: " + Draws);

	}


}
