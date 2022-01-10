package TerraBlocks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

////////////////////////////////////////////////////////////////////////////
///        @author Gustaf Franzén :: https://github.com/BjorneEk;        ///
////////////////////////////////////////////////////////////////////////////


///
/// read and parse a world from a file
///
/// in a separate thread becouse who not...

public class WorldReader implements Runnable {


	private ArrayList<Block>  blocks = new ArrayList<>();;
	private ArrayList<String> inv    = new ArrayList<>();
	private ArrayList<Tool>   tools  = new ArrayList<>();
	private Thread t;
	private String fileName;
	boolean done = false;

	WorldReader(String file) {
		t = new Thread(this);
		this.fileName = file;
		blocks = new ArrayList<>();
		t.start();
	}

	@Override
	public void run() {
		String file = "saves/" + fileName;
		try {
			Scanner s = new Scanner(new File(file));
			int toolAmount = Integer.valueOf(s.nextLine());
			int invAmount = Integer.valueOf(s.nextLine());
			for (int i = 0; i < toolAmount; i++) {
				String te = s.nextLine();
				System.out.println(te);
				tools.add(new Tool(te));
			}
			for (int i = 0; i < invAmount; i++) inv.add(s.nextLine());
			while (s.hasNextLine()) {
				String temp = s.nextLine();
				String id = temp.substring(0, temp.indexOf(','));
				int x = Integer.valueOf(temp.substring(temp.indexOf(',') + 1, temp.indexOf('.')));
				int y = Integer.valueOf(temp.substring(temp.indexOf('.') + 1, temp.length()));
				blocks.add(new Block(id, x, y));
			}
			t.interrupt();
			done = true;
		} catch (FileNotFoundException e) {
			t.interrupt();
			done = true;
		}
	}

	public ArrayList<Block> getBlocks(){
		return blocks;
	}

	public ArrayList<Tool> getTools(){
		return tools;
	}

	public ArrayList<String> getInv(){
		return inv;
	}
	
	public boolean isRunning(){
		return !done;
	}

}
