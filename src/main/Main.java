package main;
import main.MainDisplay;
import map.Map;

public class Main {
    public static void main(String[] args) {
    	Map m = new Map(101, 101);
    	MainDisplay displayExample = new MainDisplay(m._width * MainDisplay.getCellWidth(), m._height * MainDisplay.getCellWidth());
    	m.generateMap(750);
    	displayExample.setMap(m);
    	displayExample.start();
    }
}