package TerraBlocks;

import java.awt.Graphics;
import java.util.ArrayList;

////////////////////////////////////////////////////////////////////////////
///        @author Gustaf Franzén :: https://github.com/BjorneEk;        ///
////////////////////////////////////////////////////////////////////////////

///
/// a class that generates a chunk of the world
/// fairly dumb solution...
///
public class Chunk {

	private int height;
	private int x;
	
	private final ArrayList<Block> chunk = new ArrayList<>();

	public Chunk(int height, int x) {
		this.x = x;
		if (height >= 1 && height <= 50) {
			this.height = height;
		} else if (height < 1) {
			this.height = 1;
		} else {
			this.height = 50;
		}
		update();
	}

	public int getX() {
		return this.x;
	}

	public int getTop() {
		return 1040 - (height * 20);
	}

	public Block getBlock(int i) {
		return chunk.get(i);
	}

	private void update() {
		chunk.clear();
		for (int i = 0; i < height; i++) {
			if (i == height - 1) {
				chunk.add(new Block("grass", x, (1020 - (i * 20))));
			} else if (Math.random() <= 0.01 && i < height - 8) {
				chunk.add(new Block("iron", x, (1020 - (i * 20))));
			} else if (Math.random() <= 0.05 && i < height - 5) {
				chunk.add(new Block("copper", x, (1020 - (i * 20))));
			} else if (Math.random() <= 0.08 && i > height - 8) {
				chunk.add(new Block("granite", x, (1020 - (i * 20))));
			} else {
				chunk.add(new Block("stone", x, (1020 - (i * 20))));
			}

		}
	}

	public void removeBlock() {
		this.height--;
		update();
	}

	public void addBlock() {
		this.height++;
		update();
	}

	public int getHeight() {
		return this.height;
	}

	public void moveForward(int speed) {
		x += speed;
		chunk.stream().forEach((b) -> {
			b.moveForward(speed);
		});
	}

	public void moveBackwards(int speed) {
		x -= speed;
		chunk.stream().forEach((b) -> {
			b.moveBackwards(speed);
		});
	}

	public void draw(Graphics g) {
		chunk.stream().forEach((b) -> {
			b.draw(g);
		});
	}

}
