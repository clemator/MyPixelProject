package game.building;

public class MiningStation extends Building {

	public MiningStation(int id) {
		this._id = id;
		this._cost = 20;
		this._maxHp = 25;
		this._hp = this._maxHp;
		this._name = "Mining station";
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
