package TerraBlocks;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

////////////////////////////////////////////////////////////////////////////
///        @author Gustaf Franzén :: https://github.com/BjorneEk;        ///
////////////////////////////////////////////////////////////////////////////


///
/// the ingame cursor with limited reach
///

public class Cursor {

	private int playerX, playerY, x, y, x2, y2;
	private int    length = 100;
	private double angle = 0;

	public Cursor(int playerX, int playerY, int x, int y) {
		this.playerX = playerX;
		this.playerY = playerY;
		this.x2 = x;
		this.y2 = y;
	}

	private void updateCursor() {
		try {
			angle = Math.atan((double) (y2 - playerY) / (x2 - playerX));
		} catch (ArithmeticException e) {
		}
		if (Math.sqrt((Math.pow((y2 - playerY), 2) + Math.pow((x2 - playerX), 2))) < 100) {
			length = (int) Math.sqrt((Math.pow((y2 - playerY), 2) + Math.pow((x2 - playerX), 2)));

		} else {
			length = 100;
		}
		if (x2 < playerX) {
			this.x = (int) (playerX - (length * Math.cos(angle)));
			this.y = (int) (playerY - (length * Math.sin(angle)));
		} else {
			this.x = (int) (playerX + (length * Math.cos(angle)));
			this.y = (int) (playerY + (length * Math.sin(angle)));
		}
	}

	public void setCursor(int x, int y) {
		this.x2 = x;
		this.y2 = y;
		updateCursor();
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setPlayer(int playerX, int playerY) {
		this.playerX = playerX;
		this.playerY = playerY;
		updateCursor();
	}

	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		Graphics2D g2 = (Graphics2D) g.create();
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
		g2.setStroke(dashed);
		g2.drawLine(playerX, playerY, x, y);
		g2.dispose();
		g.drawLine(x - 5, y - 5, x + 5, y + 5);
		g.drawLine(x - 5, y + 5, x + 5, y - 5);
	}
}
