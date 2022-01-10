package TerraBlocks;

import java.awt.event.MouseMotionListener;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.awt.Graphics;
import javax.swing.Timer;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Point;
import java.io.File;

////////////////////////////////////////////////////////////////////////////
///        @author Gustaf Franzén :: https://github.com/BjorneEk;        ///
////////////////////////////////////////////////////////////////////////////


///
/// the main code file for the game here is where the magic hapens
///

public class Game extends javax.swing.JPanel implements ActionListener, MouseMotionListener {

	public static final java.awt.Cursor normalCursor = new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR);
	private static final BufferedImage  cursorImg    = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
	public static final java.awt.Cursor blankCursor  = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
	private static boolean              blank        = true;

	private final Chunk[]          world  = new Chunk[500];
	private final Timer            tm     = new Timer(16, this);
	private final Player           player = new Player(960, 540);
	private final Cursor           cursor = new Cursor(960, 540, 960, 540);
	private final ArrayList<Tree>  trees  = new ArrayList<>();
	private       ArrayList<Block> placed = new ArrayList<>();

	private boolean   first       = true;
	private float     jumpVel     = 0;
	private float     maxJumpVel  = 0;
	private final int size        = 20;
	private double    speed_      = 140;
	private boolean   jump        = false;
	private boolean   doneReading = false;
	private boolean   isNew       = false;
	private String    filename    = "WORLD";

	private boolean KEY_W        = false;
	private boolean KEY_S        = false;
	private boolean KEY_A        = false;
	private boolean KEY_D        = false;
	private boolean KEY_SPACEBAR = false;

	private CollissionThread col;
	private WorldReader      wr;
	private Block            colBlock;
	private long             millis;
	private long             prevMillis = 0;
	private int              fps;
	private int              tempFrames;

	public Game(String f) {
		this.setCursor(blankCursor);
		this.filename = f;
		addMouseMotionListener(this);
		tm.start();
		initComponents();
		this.setFocusable(true);
		this.requestFocus();
		initWorld();

	}

	public Game(String name, boolean isNew) {
		this.isNew = isNew;
		this.setCursor(blankCursor);
		this.filename = name;
		addMouseMotionListener(this);
		tm.start();
		initComponents();
		this.setFocusable(true);
		this.requestFocus();
		if (isNew) initWorld();
		else wr = new WorldReader(this.filename);
	}

	@Override
	protected void paintComponent(Graphics g) {
		millis = System.currentTimeMillis();
		if (!isNew && !wr.isRunning() && !this.doneReading) readWorld();

		super.paintComponent(g);
		g.setColor(new Color(100, 100, 255));
		g.fillRect(0, 0, 1920, 10080);

		if (first) {
			tm.start();
			first = false;
			repaint();
		}
		for (int i = 0; i < placed.size(); i++) {
			if (placed.get(i).getX() > 0 && placed.get(i).getX() < super.getWidth())
				placed.get(i).draw(g);
		}

		tempFrames++;
		if (millis - 1000 > prevMillis) {
			prevMillis = millis;
			fps = tempFrames;
			tempFrames = 0;
		}
		try {
			player.draw(g);
		} catch (IOException ex) {
			Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
		}
		cursor.draw(g);
		g.drawString("" + fps, 1880, 30);
	}

	private void readWorld() {
		ArrayList<Block>  inv   = new ArrayList<>();
		ArrayList<String> str   = wr.getInv();
		ArrayList<Tool>   tools = wr.getTools();

		placed = wr.getBlocks();
		try {
			for (String s : str) {
				for (int i = 0; i < Integer.valueOf(            /// loops itemcount amiunts to add
						s.substring(s.indexOf(',') + 1)); i++) {  /// correct amount
					player.addToInventory(new Block(s.substring(s.indexOf(':') + 1, s.indexOf(',')), 0, 0));
				}
			}
		} catch (NullPointerException e) {
		}
		try {
			for (Tool t : tools) {
				if (!player.hasTool(t)) {
					player.addTool(t);
				}
			}
		} catch (NullPointerException e) {
		}
		updateInventory();
		updateTools();
		placed = wr.getBlocks();
		doneReading = true;
	}

	public void saveWorld() throws FileNotFoundException, IOException {
		WorldSaver ws = new WorldSaver(filename, placed, player);
		/*ArrayList<String> tools = new ArrayList<>();
		for (int i = 0; i < player.toolAmount(); i++) {
			tools.add(player.getTool(i).toString());
		}

		ArrayList<String> inventory = new ArrayList<>();
		for (int i = 0; i < player.inventorySize(); i++) {
			inventory.add(player.getInventoryItem(i).toString());
		}

		File f = (filename != null) ? new File("saves/" + filename) : new File("World.txt");
		f.createNewFile();
		try (PrintWriter writer = new PrintWriter("saves/" + filename)) {
			writer.println("" + tools.size());
			writer.println("" + inventory.size());
			for (String s : tools)     writer.println(s);
			for (String s : inventory) writer.println(s);
			for (Block b : placed)     writer.println(b.toString());
		}*/
	}

	private void initWorld() {
		int height = 7;
		for (int i = 0; i < world.length; i++) {
			height = noise(0.5, height);
			world[i] = new Chunk((height), i * size);
			if (Math.random() < 0.05) {
				trees.add(new Tree(world[i].getX(), world[i].getTop() - size,
				                     (int) ((Math.random() * (15 - 1)) + 1)));
			}
		}
		addToPlaced();
		for (Block b : placed) b.setX(b.getX() - (250*size));
	}

	private void addToPlaced() {
		for (Chunk c : world) {
			for (int i = 0; i < c.getHeight(); i++) placed.add(c.getBlock(i));
		}
		updateTrees();
	}

	private void updateTrees() {
		for (Tree tree : trees) {
			for (int j = 0; j < tree.stemSize(); j++) placed.add(tree.getLog(j));
			for (int j = 0; j < tree.leafSize(); j++) placed.add(tree.getLeaf(j));
		}
	}

	private int noise(double range, int prev) {
		if (Math.random() <= 0.5) {
			return (int) (prev + (Math.random() * ((range - (-range)) + 1)) + range);
		} else {
			int val = (int) (prev - (Math.random() * ((range - (-range)) + 1)) + range);
			return (val < 0) ? 0 : val;
		}
	}

	public void setFilename(String s) {
		this.filename = s;
	}

	private void updateEquiped(int i) {
		try {
			player.setEquiped(player.getInventoryItem(i - 1).getId());
		} catch (IndexOutOfBoundsException e) {
		}
		TerraBlocks.equip(player.getEquiped());
	}

	private void updateEquipedTool(int i) {
		try {
			player.setEquipedTool(player.getTool(i - 1).id());
		} catch (IndexOutOfBoundsException e) {
		}
		TerraBlocks.equipTool(player.getEquipedTool());
	}

	private void getNewTools() {
		player.setTools(TerraBlocks.getTools());
	}

	private void updateInventory() {
		TerraBlocks.clearInventory();
		for (int i = 0; i < player.inventorySize(); i++)
			TerraBlocks.addInventoryItem(player.getInventoryItem(i));
	}

	private void updateTools() {
		TerraBlocks.clearToolInventory();
		for (int i = 0; i < player.toolAmount(); i++)
			TerraBlocks.addTool(player.getTool(i));
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		setPreferredSize(new java.awt.Dimension(1920, 1080));
		addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				formMouseClicked(evt);
			}
		});
		addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent evt) {
				formKeyPressed(evt);
			}
			public void keyReleased(KeyEvent evt) {
				formKeyReleased(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 1920, Short.MAX_VALUE)
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 1080, Short.MAX_VALUE)
		);
	}// </editor-fold>//GEN-END:initComponents

	private void formKeyPressed(KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

		if (evt.getKeyChar() == 'a') KEY_A = true;

		if (evt.getKeyChar() == 'd') KEY_D = true;

		if (evt.getKeyChar() == 'w') {
			if (jump) {
				jumpVel = (float) (15 - fps * 0.01);
				jump = false;
			}
		}

		if (evt.getKeyChar() == 't') player.nextTool();

		if (evt.getKeyChar() == 'e') {
			if (TerraBlocks.showCraftingMenu()) {
				this.setCursor(blankCursor);
				getNewTools();
			} else {
				this.setCursor(normalCursor);
			}
		}
		if (evt.getKeyCode() == 27) {
			if (TerraBlocks.showExitMenu()) {
				this.setCursor(blankCursor);
			} else {
				this.setCursor(normalCursor);
			}
		}
		for (int i = 0; i < 8; i++)
			if (evt.getKeyChar() == (char) (i + '0')) updateEquiped(i);
	}//GEN-LAST:event_formKeyPressed

	private boolean placedContains(int x, int y) {
		for (Block placed1 : placed)
			if (placed1.getX() == x && placed1.getY() == y) return true;
		return false;
	}

	private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
		int x = cursor.getX();
		int y = cursor.getY();

		if (evt.getButton() == 1) {
			for (int i = 0; i < placed.size(); i++) {
				if (x > placed.get(i).getX() && x < (placed.get(i).getX() + size)) {
					if (y > placed.get(i).getY() && y < (placed.get(i).getY() + size)) {
						if (placed.get(i).getPower() <= player.getToolPower() + 1)         {
							//System.out.println("bloakc power: " + placed.get(i).getPower() + ", tool power: " + (player.getToolPower() + 1));
							if (placed.get(i).getPower() == 1 && player.getToolPower() == 0) {
								placed.remove(i);
								if (!player.hasTool(new Tool("stick"))) {
									System.out.println("adding stick");
									player.addTool(new Tool("stick"));
									TerraBlocks.setAllTools(player.getTools());
								} else {
									player.addToInventory(new Block(placed.get(i).getId(), 1, 1));
									updateInventory();
								}
								player.useTool();
							} else {
								if (placed.get(i).getPower() != 0 || player.getToolPower() != 0) {
									if (player.getToolPower() > (placed.get(i).getPower() + 1)) {
										player.addToInventoryXtimes(new Block(placed.get(i).getId(), 1, 1), player.getToolPower()- placed.get(i).getPower());
									}
									else player.addToInventory(new Block(placed.get(i).getId(), 1, 1));
								}
								for (int j = 0; j < player.toolAmount(); j++) {
									System.out.println("player tools: " + player.getTool(j).toString());
								}
								placed.remove(i);
								updateInventory();
								player.useTool();
							}
						}
					}
				}
			}
		} else if (evt.getButton() == 3) {
			for (int i = 0; i < placed.size(); i++) {
				if (x <= placed.get(i).getX() + size && x >= placed.get(i).getX())
					if (!placedContains((int) placed.get(i).getX(), (size * ((int) (y / size)))))
						if (player.removeFromInventory(new Block(player.getEquiped(), 1, 1)))
							placed.add(new Block(player.getEquiped(), (int) placed.get(i).getX(), (size * ((int) (y / size)))));
			}
		}
	}//GEN-LAST:event_formMouseClicked

	private boolean upCol() {
		for (Block b : placed) {
			if (b.getX() + size > player.back() + 3 && b.getX() < player.front() - 3) {
				if (b.getY() < player.bottom()) {
					if (b.getY() + size >= player.top()) {
						colBlock = b;
						tm.start();
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean downCol() {
		for (Block b : placed) {
			if (b.getX() + size > player.back() + 1 && b.getX() < player.front() - 1) {
				if (b.getY() <= player.bottom()) {
					if (b.getY() > player.top() + size) {
						colBlock = b;
						jump = true;
						tm.start();
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean downColCheck(int i) {
		for (Block b : placed) {
			if (b.getX() + size > player.back() + 1 && b.getX() < player.front() - 1) {
				if (b.getY() <= (player.bottom() + i)) {
					if (b.getY() >= (player.top() + i + size)) {
						colBlock = b;
						maxJumpVel = 0;
						jump = true;
						tm.start();
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean colLeftCheck(int i) {
		for (Block b : placed) {
			if ((b.getX() + i) <= player.back() - (size / 2)) {
				if (b.getY() + size > player.top() && b.getY() < player.bottom()) {
					if (b.getX() + i + size >= player.back()) {
						colBlock = b;
						tm.start();
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean colRightCheck(int i) {
		for (Block b : placed) {
			if (b.getX() + i <= player.front()) {
				if (b.getY() + size > player.top() && b.getY() < player.bottom()) {
					if (b.getX() + i + (size / 2) >= player.back()) {
						colBlock = b;
						tm.start();
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean rightCol() {
		for (Block b : placed) {
			if (b.getX() <= player.front()) {
				if (b.getY() + size > player.top() && b.getY() < player.bottom()) {
					if (b.getX() + (size / 2) >= player.back()) {
						colBlock = b;
						tm.start();
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean leftCol() {
		for (Block b : placed) {
			if (b.getX() <= player.back()) {
				if (b.getY() + size > player.top() && b.getY() < player.bottom()) {
					if (b.getX() + size >= player.back()) {

						colBlock = b;
						tm.start();
						return true;
					}
				}
			}
		}
		return false;
	}

	private int distLeft(Block b) {
		return (int) (player.back() - (b.getX() + size));
	}

	private int distRight(Block b) {
		return (int) (b.getX() - player.front());
	}

	public void onColRight(Block bl) {
		int amount = (int) (player.front() - bl.getX());
		for (Block b : placed) {
			b.setX(b.getX() + amount);
		}
	}

	public void onColLeft(Block bl) {
		int amount = (int) ((bl.getX() + size) - player.back());
		for (Block b : placed) {
			b.setX(b.getX() - (amount));
		}
	}
	private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
		if (evt.getKeyChar() == 'a') KEY_A = false;
		if (evt.getKeyChar() == 'd') KEY_D = false;
	}//GEN-LAST:event_formKeyReleased


	// Variables declaration - do not modify//GEN-BEGIN:variables
	// End of variables declaration//GEN-END:variables
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean forwardsCol  = true;
		boolean backwardsCol = true;
		tm.stop();
		cursor.setPlayer(player.back() + (size / 2), player.top() + (size / 2));
		if (KEY_A) {
			if (!leftCol()) {
				if (!colLeftCheck((int) (speed_ / (fps * 0.7)))) {
					for (Block b : placed) b.setX(b.getX() + (speed_ / (fps * 0.7)));
				} else {
					int dist = distLeft(colBlock);
					for (Block b : placed) b.setX(b.getX() + dist);
				}
			}
		}
		if (KEY_D) {
			if (!rightCol()) {
				if (!colRightCheck((int) -(speed_ / (fps * 0.7)))) {
					for (Block b : placed) b.setX(b.getX() - (speed_ / (fps * 0.7)));
				} else {
					int dist = distRight(colBlock);
					for (Block b : placed) b.setX(b.getX() - dist);
				}
			}//else if(!colRightCheck(1))onColRight(colBlock);
		}
		runUpDown();
		tm.start();
		repaint();
	}

	private void runUpDown() {
		if (!downCol()) {
			if (fps < 80) {
				try {
					if      (!downColCheck(5)) player.moveAlt(5);
					else if (!downColCheck(4)) player.moveAlt(4);
					else if (!downColCheck(3)) player.moveAlt(3);
					else if (!downColCheck(2)) player.moveAlt(2);
					else                       player.moveAlt(1);
				} catch (Exception ex) {
				}
			} else {
				try {
					if (!downColCheck(2)) player.moveAlt(2);
					else                  player.moveAlt(1);
				} catch (Exception ex) {
				}
			}
		}
		if (!upCol()) {
			if (!downColCheck((int) -jumpVel) && jumpVel > 0) {
				player.moveAlt((int) -jumpVel);
			}
		}
		if (jumpVel != -30) jumpVel = (fps < 80) ? jumpVel - 1.4f : jumpVel - 0.8f;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		cursor.setCursor(e.getX(), e.getY());
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		cursor.setCursor(e.getX(), e.getY());
		repaint();
	}

}
