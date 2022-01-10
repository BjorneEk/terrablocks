package TerraBlocks;

import java.awt.Color;
import java.util.ArrayList;

////////////////////////////////////////////////////////////////////////////
///        @author Gustaf Franzén :: https://github.com/BjorneEk;        ///
////////////////////////////////////////////////////////////////////////////

public class Tree {

	private final int height;
	private final int x, y;
	private final int size = 20;

	private final ArrayList<Block> leaves = new ArrayList<>();
	private final ArrayList<Block> stem   = new ArrayList<>();

	public Tree(int x, int y, int height) {
		this.x = x;
		this.y = y;
		this.height = height;
		generate();
	}

	// generate a tree stem and leves

	private void generate() {
		int temp = 0;
		for (int i = 0; i < height; i++) {
			stem.add((new Block("log", x, y - (i * size))));
			if ((i + 1) % 2 == 0) {
				for (int j = 0; j < (height / 2) - (int) (i / 2); j++)
					leaves.add(new Block("leaf", (x - size) - (j * size), y - (i * size)));
				for (int j = 0; j < (height / 2) - (int) (i / 2); j++)
					leaves.add(new Block("leaf", (x + size) + (j * size), y - (i * size)));
			}
		}
		leaves.add(new Block("leaf", x, y - (size * (height))));
	}

	//return a peace of the stem

	public Block getLog(int i) {
		return stem.get(i);
	}

	public int stemSize() {
		return stem.size();
	}

	public Block getLeaf(int i) {
		return leaves.get(i);
	}

	public int leafSize() {
		return leaves.size();
	}
}
