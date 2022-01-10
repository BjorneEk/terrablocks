package TerraBlocks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

////////////////////////////////////////////////////////////////////////////
///        @author Gustaf Franzén :: https://github.com/BjorneEk;        ///
////////////////////////////////////////////////////////////////////////////

///
/// read and parse a world from a file
///
/// in a separate thread, actualy makes sense this time
///

public class WorldSaver implements Runnable {

	private Thread t;
	private ArrayList<String> tools;
	private ArrayList<String> inventory;
	private ArrayList<Block> blocks;
	private String filename;

	public WorldSaver(String filename, ArrayList<Block> blocks, Player player){
		this.blocks = blocks;
		this.filename = filename;
		this.tools = new ArrayList<>();
		for (int i = 0; i < player.toolAmount(); i++) {
			tools.add(player.getTool(i).toString());
		}
		this.inventory = new ArrayList<>();
		for (int i = 0; i < player.inventorySize(); i++) {
			inventory.add(player.getInventoryItem(i).toString());
		}
		t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		File f;
		if (filename != null) {
			f = new File("saves/" + filename);
		} else {
			f = new File("World.txt");
		}
		try {
			f.createNewFile();
		} catch (IOException ex) {
			Logger.getLogger(WorldSaver.class.getName()).log(Level.SEVERE, null, ex);
		}
		try (PrintWriter writer = new PrintWriter("saves/" + filename)) {
			writer.println("" + tools.size());
			writer.println("" + inventory.size());

			for (String s : tools) {
				writer.println(s);
			}
			for (String s : inventory) {
				writer.println(s);
			}
			for (Block b : this.blocks) {
				writer.println(b.toString());
			}
		} catch (FileNotFoundException ex) {
			t.interrupt();
			Logger.getLogger(WorldSaver.class.getName()).log(Level.SEVERE, null, ex);
		}
		t.interrupt();
	}

}
