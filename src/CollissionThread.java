package TerraBlocks;

import java.util.ArrayList;

////////////////////////////////////////////////////////////////////////////
///        @author Gustaf Franzén :: https://github.com/BjorneEk;        ///
////////////////////////////////////////////////////////////////////////////

///
/// a class that handles the colissions of the game in a separate thread
///

public class CollissionThread implements Runnable {

	private ArrayList<Block> blocks;
	private Player           player;
	private Thread           t;
	private final int        size = 20;
	boolean up = false, down = false, left = false, right = false;

	public CollissionThread(Player p, ArrayList<Block> b) {
		t           = new Thread(this);
		this.blocks = b;
		this.player = p;
		t.start();
	}

	public void setBlocks(ArrayList<Block> b){
		this.blocks = b;
	}
	public void setPlayer(Player p){
		this.player = p;
	}
	@Override
	public void run() {
		right = false;
		left = false;
		up = false;
		down = false;

		for (int i = 0; i < blocks.size(); i++) {
			if (blocks.get(i).getY() >= player.top() && blocks.get(i).getY() < player.bottom()) {
				if (blocks.get(i).getX() <= player.back()) {
					left = blocks.get(i).getX() + size >= player.back();
				}
				if (blocks.get(i).getX() >= player.back()) {
					right = blocks.get(i).getX() <= player.front();
				}
			}
			if (blocks.get(i).getX() > player.back() - size && blocks.get(i).getX() < player.front()) {
				if (blocks.get(i).getY() <= player.top()) {
						up = blocks.get(i).getY() + size >= player.top();
				}
				if (blocks.get(i).getY() >= player.top()) {
						down = blocks.get(i).getY() <= player.bottom();
				}
			}
		}
	}

	public boolean up() {
		return up;
	}

	public boolean down() {
		return down;
	}

	public boolean left() {
		return left;
	}

	public boolean right() {
		return right;
	}

}
