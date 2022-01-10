package TerraBlocks;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;

////////////////////////////////////////////////////////////////////////////
///        @author Gustaf Franzén :: https://github.com/BjorneEk;        ///
////////////////////////////////////////////////////////////////////////////

public class Player {

	private int   x;
	private int   y;
	private Block head;
	private Block body;
	private int   health;

	private String equiped         = "stone";
	private Tool   equipedTool     = new Tool("none");
	private int    toolX           = x + (Block.SIZE - 1);
	private int    toolY           = y - (Block.SIZE + 3);
	private float  toolPosModifier = 0;

	private final ArrayList<InventoryItem> inventory = new ArrayList<>();
	private ArrayList<Tool> tools                    = new ArrayList<>();

	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		equipedTool = new Tool("none");
		tools.add(equipedTool);
		update();
	}

	private void update() {
		toolX = x + (Block.SIZE - 1);
		toolY = y - (Block.SIZE + 3);
		head  = new Block("head", x, y - 20, new Color(200, 150, 100));
		body  = new Block("body", x, y, Color.BLUE);
		if (toolPosModifier != 0) {
			toolPosModifier -= 0.5;
			if (toolX == (x + (Block.SIZE - 1))) {
				toolX += (int) toolPosModifier;
			}
		}
	}



	public void addToInventory(Block b) {
		boolean isNew = true;
		for (InventoryItem item : inventory) {
			if (item.getId().equals(b.getId())) {
				item.add(1);
				isNew = false;
			}
		}
		if (isNew) {
			inventory.add(new InventoryItem(b));
		}
	}

	public void addToInventoryXtimes(Block b, int x) {
		for (int i = 0; i < x; i++) {
			addToInventory(b);
		}
	}

	public void setTools(ArrayList<Tool> t){
		this.tools = t;
	}

	public boolean removeFromInventory(Block b) {
		boolean isValid = false;
		for (InventoryItem item : inventory) {
			if (item.getId().equals(b.getId())) {
				if (item.getAmount() > 0) {

					item.remove(1);
					isValid = true;
				}
			}
		}
		return isValid;
	}

	public void addTool(Tool t) {
		for(Tool to : tools){
			System.out.println(to.toString());
		}
		tools.add(t);
		for(Tool to : tools){
			System.out.println(to.toString());
		}
	}

	public ArrayList<Tool> getTools(){
		return tools;
	}

	public void removeTool(Tool t) {
		tools.remove(t);
	}

	public boolean hasTool(Tool t) {
		for (Tool tool : tools) {
			if (tool.id().equals(t.id())) {
				return true;
			}
		}
		return false;
	}

	public void setEquiped(String s) {
		this.equiped = s;
	}

	public void setEquipedTool(String s) {
		this.equipedTool = new Tool(s);
	}

	public String getEquiped() {
		if (tools.size() == 0) {
			return "none";
		}
		return equiped;
	}

	public String getEquipedTool() {
		return this.equipedTool.id();
	}

	public int toolAmount() {
		return tools.size();
	}

	public int inventorySize() {
		return inventory.size();
	}

	public InventoryItem getInventoryItem(int i) {
		return inventory.get(i);
	}

	public Tool getTool(int i) {
		return tools.get(i);
	}

	public void moveForward(int amount) {
		x += amount;
	}

	public void moveBackwards(int amount) {
		x -= amount;
	}

	public void setX(int x_){
		x = x_;
	}

	public void setY(int y_){
		y = y_;
	}

	public int front() {
		return x + Block.SIZE;
	}

	public int back() {
		return x;
	}

	public int bottom() {
		return y + 20;
	}

	public int top() {
		return y - 20;
	}

	public void nextTool() {
		int temp = 0;
		if (!tools.contains(equipedTool)) {
			tools.add(equipedTool);
		}
		for (int i = 0; i < tools.size(); i++) {
			if (tools.get(i).id().equals(equipedTool.id())) {
				temp = i;
			}
		}
		if (temp >= tools.size() - 1) {
			equipedTool = tools.get(0);
		} else {
			equipedTool = tools.get(temp + 1);
		}
	}

	public void moveAlt(int amount) {
		this.y += amount;
		update();
	}

	public void useTool() {
		toolPosModifier = 5;
	}

	public int getToolPower() {
		if (hasTool(equipedTool)) {
			return equipedTool.getPower();
		} else {
			return 0;
		}
	}

	public void draw(Graphics g) throws IOException {
		update();
		head.draw(g);
		body.draw(g);
		for (Tool t : tools) {
			if (equipedTool.id().equals(t.id())) {
				t.draw(toolX, toolY, g);
			}
		}
	}
}
