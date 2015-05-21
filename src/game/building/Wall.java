package game.building;

public class Wall extends Building {
	
	public Wall(int id) {
		this._id = id;
		this._name = "Wall";
		this._cost = 10;
		this._maxHp = 10;
		this._hp = this._maxHp;
	}

	@Override
	public String getName() {
		return this._name;
	}

	@Override
	public int getCost() {
		return this._cost;
	}

	@Override
	public void setCost(int cost) {
		this._cost = cost;
	}
}
