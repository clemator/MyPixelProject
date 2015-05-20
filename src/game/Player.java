package game;

public class Player {
	private int _playerPositionX = 50;
	private int _playerPositionY = 50;
	private int _mineralAmount = 0;
	private int _fuelAmount = 0;
	private int _goldAmount = 0;
	
	public Player() {}
	
	public int getPlayerPositionX() {
		return _playerPositionX;
	}
	public void setPlayerPositionX(int _playerPositionX) {
		this._playerPositionX = _playerPositionX;
	}

	public int getPlayerPositionY() {
		return _playerPositionY;
	}

	public void setPlayerPositionY(int _playerPositionY) {
		this._playerPositionY = _playerPositionY;
	}

	public int getMineralAmount() {
		return _mineralAmount;
	}

	public void setMineralAmount(int _mineralAmount) {
		this._mineralAmount = _mineralAmount;
	}
	
	public boolean addMineral(int mineral) {
		if (getMineralAmount() + mineral < 0)
			return false;
		setMineralAmount(getMineralAmount() + mineral);
		return true;
	}

	public int getFuelAmount() {
		return _fuelAmount;
	}

	public void setFuelAmount(int _fuelAmount) {
		this._fuelAmount = _fuelAmount;
	}
	
	public boolean addFuel(int fuel) {
		if (getFuelAmount() < fuel && fuel < 0)
			return false;
		setFuelAmount(getFuelAmount() + fuel);
		return true;
	}

	public int getGoldAmount() {
		return _goldAmount;
	}

	public void setGoldAmount(int _goldAmount) {
		this._goldAmount = _goldAmount;
	}
	
	public boolean addGold(int gold) {
		if (getGoldAmount() < gold && gold < 0)
			return false;
		setGoldAmount(getGoldAmount() + gold);
		return true;
	}
	
	public String resourceToString() {
		return "Player ressources :" + System.lineSeparator()
				+ "Mineral: " + getMineralAmount() + ", Fuel: " + getFuelAmount() + ", Gold: " + getGoldAmount();
	}
}
