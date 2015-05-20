package game;

import java.io.InputStream;

import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public class MyFont {
	private TrueTypeFont font;
	private String _file;
	private int _size;
	
	public MyFont(int size){
		this._file = null;
		this._size = size;
	}
	
	public MyFont(String file, int size) {
		this._file = file;
		this._size = size;
	}
	
	public void init() {
		// load a default java font
		if (this._file == null) {
			Font awtFont = new Font("Arial", Font.PLAIN, this._size);
			font = new TrueTypeFont(awtFont, false);
		}
		else {
			// load font from a .ttf file
			try {
				InputStream inputStream	= ResourceLoader.getResourceAsStream(this._file);
		 
				Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
				awtFont = awtFont.deriveFont((float)this._size); // set font size
				font = new TrueTypeFont(awtFont, false);
		 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void render(float x, float y, String str, Color clr) {
		Color.white.bind();
 
		font.drawString(x, y, str, clr);
	}
}
