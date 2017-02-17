package gunduel.contests.bots;

import gunduel.contests.Action;

public class ReductionPlayer implements Player {

	private byte ownAmmunition;
	private byte oppAmmunition;
	
	public ReductionPlayer () {
		this.ownAmmunition = 0;
		this.oppAmmunition = 0;
	}
	
	@Override
	public Action nextAction() {
		String state = (this.ownAmmunition == 0) ? (this.oppAmmunition == 0) ? "00" : "01" : (this.oppAmmunition == 0) ? "10" : "11";
		switch(state) {
			case "00": this.ownAmmunition++; return Action.LOAD;
			case "01": return Action.DFND;
			case "10": this.ownAmmunition--; return Action.FIRE;
			case "11": this.ownAmmunition--; return Action.FIRE;
		}
		return Action.DFND;
	}

	@Override
	public void perceive(Action a) {
		switch (a) {
			case FIRE: this.oppAmmunition--; break;
			case LOAD: this.oppAmmunition++; break;
		}
	}
}
