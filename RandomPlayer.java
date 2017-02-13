package gunduel;

public class RandomPlayer implements Player {

	private short ownAmmo;
	
	public RandomPlayer () {
		ownAmmo = 0;
	}
	
	@Override
	public Action nextAction() {
		if (ownAmmo == 0) {
			return (Math.random() >= 0.5) ? this.load() : this.dfnd();
		} else {
			return (Math.random() >= 0.5) ? this.load() : this.fire();
		}
	}

	@Override
	public void perceive(Action a) {
		// TODO Auto-generated method stub
		
	}
	
	private Action load () {
		ownAmmo++;
		return Action.LOAD;
	}
	
	private Action fire () {
		ownAmmo--;
		return Action.FIRE;
	}
	
	private Action dfnd () {
		return Action.DFND;
	}

}
