package main;
import map.Cell;
import map.Map;
import game.MyFont;
import game.Player;
import game.building.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;

import static org.lwjgl.opengl.GL11.*;

public class MainDisplay {
	
	public int _width = 800;
	public int _height = 600;
	public MyFont _font;
	public Player _player;
	
	private Map _map;
	private static int _cellWidth = 9;
	private boolean _debugMode = false;
	private int _wallId = 1;
	private int _miningStationId = 1;
	
	public MainDisplay(int width, int height) {
		this._width = width;
		this._height = height;
		this._player = new Player();
	}
	
	public static int getCellWidth() {
		return _cellWidth;
	}
	
	public void start() {
        try {
	        Display.setDisplayMode(new DisplayMode(this._width, this._height));
	        Display.create();
	        Display.setVSyncEnabled(true);
	        Display.setTitle("TestZone Display");
	        Display.setResizable(true);
	        
	        glEnable(GL_TEXTURE_2D);
	        glShadeModel(GL_SMOOTH);
	        glDisable(GL_DEPTH_TEST);
	        glDisable(GL_LIGHTING);
	        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
	        glClearDepth(1);
	        glEnable(GL_BLEND);
	        //glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	        glViewport(0, 0, _width, _height);
	        glMatrixMode(GL_PROJECTION);
	        glLoadIdentity();
	        glOrtho(0, this._width, this._height, 0, 1, -1);
	        glMatrixMode(GL_MODELVIEW);
	        
		} catch (LWJGLException e) {
		    e.printStackTrace();
		    System.exit(0);
		}
 
		// init OpenGL here
        this._font = new MyFont(18);
		this._font.init();
		
		while (!Display.isCloseRequested()) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		    // render OpenGL here
			for (int i = 0; i < this._map._width; i++) {
				for (int j = 0; j < this._map._height; j++) {
					glBegin(GL_QUADS);
					if (this._map.getCell(i, j).getState() == Cell.State.BLOCKED)
						glColor3f(0.6f, 0.6f, 0.6f);
					else if (this._map.getCell(i, j).getState() == Cell.State.DIRTY)
						glColor3f(0.9f, 0.9f, 0.9f);
					else if (this._map.getCell(i, j).getType() == Cell.Type.WATER)
						glColor3f(0.1f, 0.1f, 1.0f);
					else if (this._map.getCell(i, j).getType() == Cell.Type.DIRT)
						glColor3f(0.8f, 0.4f, 0.0f);
					else if (this._map.getCell(i, j).getType() == Cell.Type.GRASS)
						glColor3f(0.1f, 1.0f, 0.1f);

					glVertex2f(i * _cellWidth, j * _cellWidth);
					glVertex2f((i * _cellWidth) + _cellWidth, j * _cellWidth);
					glVertex2f((i * _cellWidth) + _cellWidth,  (j * _cellWidth) + _cellWidth);
					glVertex2f(i * _cellWidth, (j * _cellWidth) + _cellWidth);
					glEnd();
				}
			}
			
			glColor3f(0.0f, 0.0f, 0.0f);
			while (this._map.getCell(this._player.getPlayerPositionX(),this._player.getPlayerPositionY()).getType() == Cell.Type.WATER) {
				this._player.setPlayerPositionX(this._player.getPlayerPositionX() + 1);
			}
			glBegin(GL_QUADS);
			glVertex2f(this._player.getPlayerPositionX() * _cellWidth, this._player.getPlayerPositionY() * _cellWidth);
			glVertex2f(this._player.getPlayerPositionX() * _cellWidth + _cellWidth, this._player.getPlayerPositionY() * _cellWidth);
			glVertex2f(this._player.getPlayerPositionX() * _cellWidth + _cellWidth, this._player.getPlayerPositionY() * _cellWidth + _cellWidth);
			glVertex2f(this._player.getPlayerPositionX() * _cellWidth, this._player.getPlayerPositionY() * _cellWidth + _cellWidth);
			glEnd();
			//this._font.render((float)(this._player.getPlayerPositionX() * _cellWidth), (float)(this._player.getPlayerPositionY() * _cellWidth), "P", Color.white);
			keyboardInput();
			Display.update();
			//Display.sync(60);
		}
	 
		Display.destroy();
    }
	
	public void setMap(Map m) {
		this._map = m;
	}
	
	public void keyboardInput() {
		while (Keyboard.next()) {
	        if (Keyboard.getEventKeyState()) {
	        	// MOVEMENT HANDLING
	        	if (Keyboard.getEventKey() == Keyboard.KEY_UP) {
	    			if (this._player.getPlayerPositionY() - 1 >= 0
	    					&& this._map.getCell(this._player.getPlayerPositionX(), this._player.getPlayerPositionY() - 1).getType() != Cell.Type.WATER
	    					&& this._map.getCell(this._player.getPlayerPositionX(), this._player.getPlayerPositionY() - 1).getState() != Cell.State.BLOCKED)
	    				this._player.setPlayerPositionY(this._player.getPlayerPositionY() - 1);
	    		}
	        	else if (Keyboard.getEventKey() == Keyboard.KEY_DOWN) {
	    			if (this._player.getPlayerPositionY() + 1 < this._map._width
	    					&& this._map.getCell(this._player.getPlayerPositionX(), this._player.getPlayerPositionY() + 1).getType() != Cell.Type.WATER
	    					&& this._map.getCell(this._player.getPlayerPositionX(), this._player.getPlayerPositionY() + 1).getState() != Cell.State.BLOCKED)
	    				this._player.setPlayerPositionY(this._player.getPlayerPositionY() + 1);
	    		}
	        	else if (Keyboard.getEventKey() == Keyboard.KEY_LEFT) {
	    			if (this._player.getPlayerPositionX() - 1 >= 0
	    					&& this._map.getCell(this._player.getPlayerPositionX() - 1, this._player.getPlayerPositionY()).getType() != Cell.Type.WATER
	    					&& this._map.getCell(this._player.getPlayerPositionX() - 1, this._player.getPlayerPositionY()).getState() != Cell.State.BLOCKED)
	    				this._player.setPlayerPositionX(this._player.getPlayerPositionX() - 1);
	    		}
	        	else if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT) {
	    			if (this._player.getPlayerPositionX() + 1 < this._map._height
	    					&& this._map.getCell(this._player.getPlayerPositionX() + 1, this._player.getPlayerPositionY()).getType() != Cell.Type.WATER
	    					&& this._map.getCell(this._player.getPlayerPositionX() + 1, this._player.getPlayerPositionY()).getState() != Cell.State.BLOCKED)
	    				this._player.setPlayerPositionX(this._player.getPlayerPositionX() + 1);
	    		}
	        	// UTILITY HANDLING
	        	// SCANNING
	        	else if (Keyboard.getEventKey() == Keyboard.KEY_S) {
	        		System.out.println("Scanning : found "
	        				+ this._map.getCell(this._player.getPlayerPositionX(), this._player.getPlayerPositionY()).getResource());
	        	}
	        	// DIGING
	        	else if (Keyboard.getEventKey() == Keyboard.KEY_D) {
	        		System.out.println("Diging : found "
	        				+ this._map.getCell(this._player.getPlayerPositionX(), this._player.getPlayerPositionY()).getResource()
	        				+ " (" + this._map.getCell(this._player.getPlayerPositionX(), this._player.getPlayerPositionY()).getResourceAmount() + ")");
	        		switch (this._map.getCell(this._player.getPlayerPositionX(), this._player.getPlayerPositionY()).getResource()) {
	        		case NOTHING: break;
	        		case GOLD: this._player.addGold(this._map.getCell(this._player.getPlayerPositionX(), this._player.getPlayerPositionY()).getResourceAmount());
	        			this._map.getCell(this._player.getPlayerPositionX(), this._player.getPlayerPositionY()).resetResource();
	        			break;
	        		case FUEL: this._player.addFuel(this._map.getCell(this._player.getPlayerPositionX(), this._player.getPlayerPositionY()).getResourceAmount());
	        			this._map.getCell(this._player.getPlayerPositionX(), this._player.getPlayerPositionY()).resetResource();
	        			break;
	        		case MINERAL: this._player.addMineral(this._map.getCell(this._player.getPlayerPositionX(), this._player.getPlayerPositionY()).getResourceAmount());
	        			this._map.getCell(this._player.getPlayerPositionX(), this._player.getPlayerPositionY()).resetResource();
        				break;
	        		}
	        		System.out.println(this._player.resourceToString());
	        	}
	        	// BUILDING
	        	// WALL
	        	else if (Keyboard.getEventKey() == Keyboard.KEY_W) {
        			Building b = new Wall(this._wallId);
	        		if (this._player.addMineral(-b.getCost())) {
	        			this._map.getCell(this._player.getPlayerPositionX(), this._player.getPlayerPositionY()).setState(Cell.State.BLOCKED);
	        			this._map.getCell(this._player.getPlayerPositionX(), this._player.getPlayerPositionY()).setBuilding(b);
	        			System.out.println("Wall succesfully builded!");
	        			System.out.println(this._player.resourceToString());
	        			this._wallId++;
	        		}
	        		else
	        			System.out.println("Cannot build a wall : not enough mineral. " + (10 - this._player.getMineralAmount()) + " missing.");
	        	}
	        	// MINING STATION
	        	else if (Keyboard.getEventKey() == Keyboard.KEY_M) {
        			Building b = new MiningStation(this._miningStationId);
	        		if (this._player.addMineral(-b.getCost())) {
	        			this._map.getCell(this._player.getPlayerPositionX(), this._player.getPlayerPositionY()).setState(Cell.State.BLOCKED);
	        			this._map.getCell(this._player.getPlayerPositionX(), this._player.getPlayerPositionY()).setBuilding(b);
	        			System.out.println("Mining station succesfully builded!");
	        			System.out.println(this._player.resourceToString());
	        			this._miningStationId++;
	        		}
	        		else
	        			System.out.println("Cannot build a wall : not enough mineral. " + (10 - this._player.getMineralAmount()) + " missing.");
	        	}
	        	// RESETING GAME
	        	else if (Keyboard.getEventKey() == Keyboard.KEY_R) {
	        		setMap(new Map(this._map._width, this._map._height));
	        		this._map.generateMap(750);
	        		this._player.setPlayerPositionX(50);
	        		this._player.setPlayerPositionY(50);
	        		while (this._map.getCell(this._player.getPlayerPositionX(),this._player.getPlayerPositionY()).getType() == Cell.Type.WATER) {
	    				this._player.setPlayerPositionX(this._player.getPlayerPositionX() + 1);
	    			}
	        	}
	        	// ENABLE DEBUG MODE
	        	else if (Keyboard.getEventKey() == Keyboard.KEY_COMMA) {
	        		this._debugMode = !this._debugMode;
	        	}
	        }
		}
		
		
	}
}
