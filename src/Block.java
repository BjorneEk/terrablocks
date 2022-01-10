package TerraBlocks;

import java.awt.Color;
import java.awt.Graphics;

////////////////////////////////////////////////////////////////////////////
///        @author Gustaf Franzén :: https://github.com/BjorneEk;        ///
////////////////////////////////////////////////////////////////////////////

///
/// a class with data about a single block and methods to draw it
///
public class Block {

	private final Color color;
	private String id;
	private double x, y;
	private int    pow;

	public static final int SIZE = 20;

	public Block(String id, int x_, int y_) {
		this.id = id;
		this.x = x_;
		this.y = y_;
		if (id.equalsIgnoreCase("stone")) {
			this.color = new Color(145, 145, 145);
			this.pow = 3;
		}else if (id.equalsIgnoreCase("grass")) {
			this.color = new Color(0, 200, 0);
				this.pow = 2;
		} else if (id.equalsIgnoreCase("log")) {
			this.color = new Color(139, 69, 19);
				this.pow = 2;
		} else if (id.equalsIgnoreCase("leaf")) {
			this.color = new Color(0, 128, 0);
				this.pow = 1;
		} else if (id.equalsIgnoreCase("granite")) {
			this.color = new Color(230, 230, 250);
				this.pow = 3;
		} else if (id.equalsIgnoreCase("iron")) {
			this.color = new Color(176, 196, 222);
				this.pow = 5;
		} else if (id.equalsIgnoreCase("copper")) {
			this.color = new Color(255, 127, 80);
				this.pow = 4;
		}else{
			this.color = new Color(0, 0, 0);
			this.pow = 1;
		}
		/*
		switch (id) {
			case ("stone"):
				this.color = new Color(145, 145, 145);
				this.pow = 3;
				break;
			case ("grass"):
				this.color = new Color(0, 200, 0);
				this.pow = 2;
				break;
			case ("log"):
				this.color = new Color(139, 69, 19);
				this.pow = 2;
				break;
			case ("leaf"):
				this.color = new Color(0, 128, 0);
				this.pow = 1;
				break;
			case ("granite"):
				this.color = new Color(230, 230, 250);
				this.pow = 3;
				break;
			case ("iron"):
				this.color = new Color(176, 196, 222);
				this.pow = 5;
				break;
			case ("copper"):
				this.color = new Color(255, 127, 80);
				this.pow = 4;
				break;
			default:
				this.color = new Color(0, 0, 0);
				break;
		}*/

	}

	public Block(String id, int x_, int y_, Color c) {
		this.id = id;
		this.x = x_;
		this.y = y_;
		this.color = c;
	}

	public String getId() {
		return this.id;
	}

	public void moveForward(int speed) {
		this.x += speed;
	}

	public void moveBackwards(int speed) {
		this.x -= speed;
	}

	public int getPower() {
		return this.pow;
	}

	public double getX() {
		return this.x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return this.y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect((int)x, (int)y, SIZE, SIZE);
		g.setColor(Color.BLACK);
		g.drawRect((int)x, (int)y, SIZE, SIZE);
	}
	@Override
	public String toString(){
		return id + "," + (int)x + "." + (int)y;
	}
}
