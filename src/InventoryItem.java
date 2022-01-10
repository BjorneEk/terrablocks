package TerraBlocks;

import java.awt.Graphics;

////////////////////////////////////////////////////////////////////////////
///        @author Gustaf Franzén :: https://github.com/BjorneEk;        ///
////////////////////////////////////////////////////////////////////////////

public class InventoryItem {

	private int amount;
	private final String id;
	private final Block block;

	public InventoryItem(Block block) {
		this.block = block;
		this.id = block.getId();
		this.amount = 1;
	}
	public InventoryItem(String id, int amount){
		this.id = id;
		this.amount = amount;
		this.block = new Block(id, 0, 0);
	}
	public void add(int amount) {
		this.amount += amount;
	}

	public void remove(int amount) {
		this.amount -= amount;
	}

	public String getId() {
		return this.id;
	}

	public Block getBlock() {
		return block;
	}

	public int getAmount() {
		return amount;
	}

	public void draw(int x, int y, Graphics g) {
		Block b = new Block(id, x, y);
		b.draw(g);
	}

	@Override
	public String toString() {
		return "inv:" + id + "," + amount;
	}

}
