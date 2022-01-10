package TerraBlocks;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

////////////////////////////////////////////////////////////////////////////
///        @author Gustaf Franzén :: https://github.com/BjorneEk;        ///
////////////////////////////////////////////////////////////////////////////

public class Tool {

	private String id;
	private int    power;
	private String type;

	public Tool(String id) {
		this.id = id;
		//figure out what tool has been added for example iron_pic is a iron pickaxe
		if (id.contains("_")) {
			this.type = id.substring(id.indexOf("_")+1);
			switch (id.substring(0, id.indexOf("_"))) {
				case "wood":
					this.power = 2;
					break;
				case "stone":
					this.power = 3;
					break;
				case "copper":
					this.power = 4;
					break;
				case "iron":
					this.power = 5;
					break;
				default:
					this.power = 0;
					break;
			}
		}
		else if (id.equals("stick")) {
			this.type = id;
			this.power = 1;
		}
		else {
			this.type = id;
			this.power = 0;
		}

	}

	public int getPower() {
		return this.power;
	}

	public String id() {
		return this.id;
	}

	public String type() {
		return this.type;
	}

	public void draw(int x, int y, Graphics g) throws IOException {
		Graphics2D g2 = (Graphics2D) g;
		switch (type) {
			case "pickaxe":
				switch (power) {
					case 1:
						g.setColor(new Color(200, 150, 100));
						g.fillRect(x, y, 10, 10);
						g.setColor(Color.BLACK);
						g.drawRect(x, y, 10, 10);
						break;
					case 2:
						BufferedImage woodPic = ImageIO.read(new File("src/Assets/Wood_pic.png"));
						g.drawImage(woodPic, x, y, null);
						break;
					case 3:
						BufferedImage stonePic = ImageIO.read(new File("src/Assets/Stone_pic.png"));
						g.drawImage(stonePic, x, y, null);
						break;
					case 4:
						BufferedImage copperPic = ImageIO.read(new File("src/Assets/Copper_pic.png"));
						g.drawImage(copperPic, x, y, null);
						break;
					case 5:
						BufferedImage ironPic = ImageIO.read(new File("src/Assets/Iron_pic.png"));
						g.drawImage(ironPic, x, y, null);
						break;
				}
				break;
			case "axe": //UNIMPLEMENTED
				switch (power) {
					case 2:
						break;
					case 3:
						break;
					case 4:
						break;
					case 5:
						break;
				}
				break;
			case "stick":
				Stroke defaultStroke;
				defaultStroke = g2.getStroke();
				g2.setColor(new Color(0,0,0));
				g2.setStroke(new BasicStroke(5));
				g2.drawLine(x, y+Block.SIZE, x+Block.SIZE, y);
				g2.setStroke(defaultStroke);
				defaultStroke = g2.getStroke();
				g2.setColor(new Color(139, 69, 19));
				g2.setStroke(new BasicStroke(3));
				g2.drawLine(x, y+Block.SIZE, x+Block.SIZE, y);
				g2.setStroke(defaultStroke);
				break;
		}
	}
	@Override
	public String toString(){
		return id;
	}
}
