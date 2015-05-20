package game.building;

public abstract class Building {
	protected String _name;
	protected int _hp;
	protected int _maxHp;
	protected int _cost;
	protected int _id;
	
	public abstract String getName();
	
	public abstract int getCost();
	
	public abstract void setCost(int cost);
	
	protected int getId() {
		return _id;
	}
	
	protected int getMaxHp() {
		return _maxHp;
	}

	protected int getHp() {
		return _hp;
	}
}
