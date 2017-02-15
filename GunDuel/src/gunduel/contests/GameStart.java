package gunduel.contests;


import java.util.Scanner;

import gunduel.contests.bots.Player;
import gunduel.contests.bots.RobotFactory;


public class GameStart
{
	private static Player PlayerA = null;
	private static Player PlayerB = null;
	private static final RobotFactory robotFactory = new RobotFactory();

	private static Duel duel;
	private static Match match;


	public static void main(final String[] args)
	{
		int mode;

		System.out.println("Welcome to GunDuel!");

		mode = PromptMode();
		switch (mode)
		{
			case 1:
				// Duel
				System.out.println("Selected Duel");
				StartDuel();
				break;
			case 2:
				// Match
				System.out.println("Selected Match");
				StartMatch();
				break;
			case 3:
				// Tournament
				System.out.println("Selected Tournament");
				break;
		}
	}

	public static int PromptMode()
	{
		final Scanner scan = new Scanner(System.in);
		System.out.println("Select Mode:");
		System.out.println("1 for Duel, 2 for Match, 3 for Tournament:");
		return scan.nextInt();
	}

	public static String PromptBot()
	{
		final Scanner scan = new Scanner(System.in);
		System.out.println("Enter robot:");
		return scan.next();
	}

	public static void StartDuel()
	{
		System.out.println("Getting contestants...");
		PlayerA = robotFactory.getPlayer("RandomPlayer");
		PlayerB = robotFactory.getPlayer("RandomPlayer");

		System.out.println("Starting duel...");

		duel = new Duel(PlayerA, PlayerB, false);
	}

	public static void StartMatch()
	{
		System.out.println("Getting contestants...");
		PlayerA = robotFactory.getPlayer("TurtlePlayer");
		PlayerB = robotFactory.getPlayer("RandomPlayer");

		System.out.println("Starting match...");

		match = new Match(PlayerA, PlayerB);
	}

}
