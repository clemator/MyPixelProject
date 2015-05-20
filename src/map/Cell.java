package map;

import game.building.Building;

import java.util.Random;

import math.Position;

public class Cell {
	public enum State{
		EMPTY,
		BLOCKED,
		DIRTY
	};
	public enum Type{
		WATER,
		GRASS,
		DIRT,
		COAST
	};
	public enum Resource{
		MINERAL,
		FUEL,
		GOLD,
		NOTHING
	};
	
	private State _state;
	private Resource _resource;
	private int _resourceAmount;
	private int _resourceThreshold = 1000;
	
	public final Type _type;
	public final Position _position;
	public boolean _isScanned;
	public Building _building;
	
	public Cell(Position position, Type type, State state) {
		this._position = position;
		this._type = type;
		this._state = state;
		this._isScanned = false;
		setResource();
	}
	
	public void setState(State state) {
		this._state = state;
	}
	
	public State getState() {
		return this._state;
	}
	
	public Type getType() {
		return this._type;
	}
	
	private void setResource(Resource resource) {
		this._resource = resource;
	}
	
	public void resetResource() {
		setResource(Cell.Resource.NOTHING);
		this._resourceAmount = 0;
	}
	
	public Resource getResource() {
		return this._resource;
	}
	
	public void setScanned() {
		if (!this._isScanned)
			this._isScanned = true;
	}
	
	public boolean isScanned() {
		return this._isScanned;
	}
	
	public void setResource() {
		Random r = new Random();
		if (r.nextInt(this._resourceThreshold) > ((this._resourceThreshold / 5) * 4)) {
			if (r.nextInt(this._resourceThreshold) > (this._resourceThreshold / 2)) {
				if (r.nextInt(this._resourceThreshold) > ((this._resourceThreshold / 3) * 2))
					this._resource = Cell.Resource.GOLD;
				else
					this._resource = Cell.Resource.FUEL;
			}
			else
				this._resource = Cell.Resource.MINERAL;
			setResourceAmount();
		}
		else {
			this._resource = Cell.Resource.NOTHING;
			this._resourceAmount = 0;
		}
	}
	
	public void setResourceAmount() {
		Random r = new Random();
		this._resourceAmount = r.nextInt(16) + 1;
	}
	
	public int getResourceAmount() {
		return this._resourceAmount;
	}
	
	public String toString() {
		String res = "X: ";
		res += this._position._x + ", Y: " 
				+ this._position._y + ". Type: " 
				+ this._type.toString() + ". Ressource: " 
				+ this._resource + ", Amount: " 
				+ this._resourceAmount + ". ";
		return res;
	}
	
	public String resourceToDisplay() {
		String res = "";
		switch (this._resource) {
		case NOTHING : res = " ";
			break;
		case FUEL: res = "F";
			break;
		case GOLD: res = "G";
			break;
		case MINERAL: res = "M";
			break;
		}
		return res;
	}
	
	public String typeToDisplay() {
		String res = "";
		switch (this._type) {
		case COAST : res = "C";
			break;
		case GRASS: res = "G";
			break;
		case WATER: res = "W";
			break;
		case DIRT: res = "D";
			break;
		}
		return res;
	}
	
	public Building getBuilding() {
		return this._building;
	}
	
	public void setBuilding(Building b) {
		this._building = b;
	}
}