package gunduel.contests.bots;

import gunduel.contests.Action;


public interface Player
{
	public Action nextAction();

	public void perceive(Action a);
}
