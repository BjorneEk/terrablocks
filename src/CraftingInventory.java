package TerraBlocks;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

////////////////////////////////////////////////////////////////////////////
///        @author Gustaf Franzén :: https://github.com/BjorneEk;        ///
////////////////////////////////////////////////////////////////////////////

///
/// a JPanel with the crafting inteface
///

public class CraftingInventory extends javax.swing.JPanel {

	/**
	 * Creates new form CraftingInventory
	 */

	private final ArrayList<Point>         blockSlots = new ArrayList<>();
	private final ArrayList<Point>         toolSlots  = new ArrayList<>();
	public static ArrayList<InventoryItem> inventory  = new ArrayList<>();
	public static ArrayList<Tool>          tools      = new ArrayList<>();

	private Point         craftSlot_1, craftSlot_2, resultSlot;
	private InventoryItem slot1, slot2;
	private Tool          slot1_, slot2_;

	public CraftingInventory() {
		tools.add(new Tool("none"));
		initComponents();
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		cancelBtn = new javax.swing.JButton();
		CraftBtn = new javax.swing.JButton();

		addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				formMouseClicked(evt);
			}
		});

		cancelBtn.setText("cancel");
		cancelBtn.setFocusable(false);
		cancelBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelBtnActionPerformed(evt);
			}
		});

		CraftBtn.setText("Craft!");
		CraftBtn.setFocusable(false);
		CraftBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				CraftBtnActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
				.addContainerGap(342, Short.MAX_VALUE)
				.addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(CraftBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGap(112, 112, 112))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
				.addContainerGap(274, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
					.addComponent(CraftBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(125, 125, 125))
		);

		getAccessibleContext().setAccessibleName("");
		getAccessibleContext().setAccessibleDescription("");
	}// </editor-fold>//GEN-END:initComponents

	private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
		clearSlots();
	}//GEN-LAST:event_cancelBtnActionPerformed

	private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
		for (int i = 0; i < inventory.size(); i++) {

			if (evt.getX() >= blockSlots.get(i).x && evt.getX() <= blockSlots.get(i).x + 40) {
				if (evt.getY() >= blockSlots.get(i).y && evt.getY() <= blockSlots.get(i).y + 40) {
					if (slot1 == null && slot1_ == null) {
						slot1_ = null;
						slot1 = inventory.get(i);
					} else {
						slot2_ = null;
						slot2 = inventory.get(i);
					}
				}
			}
		}
		for (int i = 0; i < tools.size(); i++) {
			if (evt.getX() >= toolSlots.get(i).x && evt.getX() <= toolSlots.get(i).x + 40) {
				if (evt.getY() >= toolSlots.get(i).y && evt.getY() <= toolSlots.get(i).y + 40) {
					if (slot1 == null && slot1_ == null) {
						slot1 = null;
						slot1_ = tools.get(i);
					} else {
						slot2 = null;
						slot2_ = tools.get(i);
					}
				}
			}
		}
	}//GEN-LAST:event_formMouseClicked

	private void clearSlots() {
		slot1  = null;
		slot2  = null;
		slot1_ = null;
		slot2_ = null;
	}
	public ArrayList<Tool> getTools(){
		return tools;
	}

	public void updateTools() {
		TerraBlocks.clearToolInventory();
		for (Tool t : tools) TerraBlocks.addTool(t);
	}

	public void addTool(Tool tool) {
		tools.add(tool);
	}
	public void removeTool(String s){
		for (int i = 0; i < tools.size(); i++) {
			if (tools.get(i).id().equalsIgnoreCase(s)) tools.remove(i);
		}
	}
	private void CraftBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CraftBtnActionPerformed
		if ((slot1 != null || slot1_ != null) && (slot2 != null || slot2_ != null)) {
			if (slot1_ != null) {
				if (slot1_.id().equals("stick")) {
					switch (slot2.getId()) {
						case "log":
							tools.remove(slot1_);
							tools.add(new Tool("wood_pickaxe"));
							clearSlots();
							updateTools();
							break;
						case "stone":
							tools.remove(slot1_);
							tools.add(new Tool("stone_pickaxe"));
							clearSlots();
							updateTools();
							break;
						case "copper":
							tools.remove(slot1_);
							tools.add(new Tool("copper_pickaxe"));
							clearSlots();
							updateTools();
							break;
						case "iron":
							tools.remove(slot1_);
							tools.add(new Tool("iron_pickaxe"));
							clearSlots();
							updateTools();
							break;
						default:
							JOptionPane.showMessageDialog(CraftBtn, "invalid recipie selected");
							clearSlots();
							break;
					}
				}
			} else if (slot2_ != null) {
				if (slot2_.id().equals("stick")) {
					switch (slot1.getId()) {
						case "log":
							tools.remove(slot1_);
							tools.add(new Tool("wood_pickaxe"));
							clearSlots();
							updateTools();
							break;
						case "stone":
							tools.remove(slot1_);
							tools.add(new Tool("stone_pickaxe"));
							clearSlots();
							updateTools();
							break;
						case "copper":
							tools.remove(slot1_);
							tools.add(new Tool("copper_pickaxe"));
							clearSlots();
							updateTools();
							break;
						case "iron":
							tools.remove(slot1_);
							tools.add(new Tool("iron_pickaxe"));
							clearSlots();
							updateTools();
							break;
						default:
							JOptionPane.showMessageDialog(CraftBtn, "invalid recipie selected");
							clearSlots();
							break;
					}
				}
			} else {
				JOptionPane.showMessageDialog(CraftBtn, "invalid recipie selected");
				clearSlots();
			}
		} else {

			JOptionPane.showMessageDialog(CraftBtn, "no recipie selected");
			clearSlots();
		}
	}//GEN-LAST:event_CraftBtnActionPerformed
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Stroke defaultStroke;
		defaultStroke = g2.getStroke();
		g2.setStroke(new BasicStroke(5));
		g.setColor(new Color(169, 169, 169));
		g.fillRect(0, 0, 600, 450);
		g.setColor(new Color(0, 0, 0));
		g.drawRect(0, 0, 599, 449);
		g2.setStroke(defaultStroke);
		Font p = new Font("Dialog", Font.BOLD, 30);
		g.setFont(p);
		g.drawString("Crafting menu", 600 / 3, 30);

		g.drawRect(20, 100, 200, 340);
		p = new Font("Dialog", Font.BOLD, 20);
		g.setFont(p);
		g.drawString("Your items", 70, 130);
		for (float i = 0; i < 7; i += 1.2) {
			for (float j = 0; j < 2; j += 1.2) {
				g.drawRect((int) (27.7 + (j * 40)), (int) (150 + (i * 40)), 40, 40);
				blockSlots.add(new Point((int) (27.7 + (j * 40)), (int) (150 + (i * 40))));
			}
		}
		for (float i = 0; i < 7; i += 1.2) {
			g.drawRect(150, (int) (150 + (i * 40)), 40, 40);
			toolSlots.add(new Point(150, (int) (150 + (i * 40))));
		}
		for (int i = 0; i < inventory.size(); i++) {
			inventory.get(i).draw(blockSlots.get(i).x + 10, blockSlots.get(i).y + 10, g);
			p = new Font("Dialog", Font.BOLD, 12);
			g.setFont(p);
			g.drawString(Integer.toString(inventory.get(i).getAmount()), blockSlots.get(i).x + 20, blockSlots.get(i).y + 10);
		}
		for (int i = 0; i < tools.size(); i++) {
			try {
				tools.get(i).draw(toolSlots.get(i).x + 10, toolSlots.get(i).y + 10, g);
			} catch (IOException ex) {
				Logger.getLogger(CraftingInventory.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		setInventory();
		setTools();
		craftingSection(g);
		if (slot1 != null) {
			slot1.draw(craftSlot_1.x + 10, craftSlot_1.y + 10, g);
		}
		if (slot2 != null) {
			slot2.draw(craftSlot_2.x + 10, craftSlot_2.y + 10, g);
		}
		if (slot1_ != null) {
			try {
				slot1_.draw(craftSlot_1.x + 10, craftSlot_1.y + 10, g);
			} catch (IOException ex) {
				Logger.getLogger(CraftingInventory.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		if (slot2_ != null) {
			try {
				slot2_.draw(craftSlot_2.x + 10, craftSlot_2.y + 10, g);
			} catch (IOException ex) {
				Logger.getLogger(CraftingInventory.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

	}

	public void setInventory() {
		inventory = new ArrayList<>();
		for (InventoryItem inventory1 : Inventory.inventory) {
			inventory.add(inventory1);
		}
	}

	public void setTools() {
		tools = new ArrayList<>();
		for (Tool tool : Inventory.tools) {
			tools.add(tool);
		}
	}

	private void craftingSection(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(new Color(0, 0, 0));
		Stroke defaultStroke;
		defaultStroke = g2.getStroke();
		g2.setStroke(new BasicStroke(5));
		g.drawRect(250, 102, 320, 337);
		g2.setStroke(defaultStroke);
		Font p = new Font("Dialog", Font.BOLD, 20);
		g.setFont(p);
		g.drawString("Crafting", 370, 140);

		g.drawRect(290, 200, 40, 40);
		craftSlot_1 = new Point(290, 200);
		g.drawRect(340, 200, 40, 40);
		craftSlot_2 = new Point(340, 200);

		g2.setStroke(new BasicStroke(5));
		g.drawLine(390, 213, 430, 213);
		g.drawLine(390, 227, 430, 227);
		g2.setStroke(defaultStroke);

		g.drawRect(440, 200, 40, 40);
		resultSlot = new Point(440, 150);

	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton CraftBtn;
	private javax.swing.JButton cancelBtn;
	// End of variables declaration//GEN-END:variables
}
