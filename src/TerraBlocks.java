package TerraBlocks;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

////////////////////////////////////////////////////////////////////////////
///        @author Gustaf Franzén :: https://github.com/BjorneEk;        ///
////////////////////////////////////////////////////////////////////////////

public class TerraBlocks extends javax.swing.JFrame {


	public static final java.awt.Cursor cursor      = new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR);
	private static final BufferedImage  cursorImg   = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
	public static final java.awt.Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
	private static boolean              blank       = true;
	private static boolean              blank2      = true;

	public TerraBlocks(String world, boolean isNew) {
		game1 = new Game(world, isNew);
		game1.setFilename(world);

		initComponents();
		this.craftingInventory1.setVisible(false);
		this.settings1.setVisible(false);
		this.setCursor(blankCursor);
	}

	public void save() throws IOException {
			blank = true;
			settings1.setVisible(false);
			game1.saveWorld();
			this.setFocusable(true);
	}

	public static void clearInventory() {
		inventory1.clearInventory();
	}

	public static void equip(String s) {
		inventory1.equip(s);
	}

	public static boolean showCraftingMenu() {
		if (craftingInventory1.isVisible()) {
			blank = true;
			craftingInventory1.setVisible(false);
			return true;
		} else {
			blank = false;
			craftingInventory1.setVisible(true);
			return false;
		}
	}

	public static boolean showExitMenu(){
		if (settings1.isVisible()) {
			blank2 = true;
			settings1.setVisible(false);
			return true;
		} else {
			blank2 = false;
			settings1.setVisible(true);
			return false;
		}
	}

	public static void addInventoryItem(InventoryItem item) {
		inventory1.addInventory(item);
	}

	public static void clearToolInventory() {
		inventory1.clearTools();
	}

	public static void equipTool(String s) {
		inventory1.equipTool(s);
	}

	public static void setAllTools(ArrayList<Tool> t){
		inventory1.setTools(t);
	}

	public static void addTool(Tool tool) {
		inventory1.addTool(tool);
	}

	public static int nrOfTools() {
		return inventory1.nrOfTools();
	}

	public static Tool getTool(int i) {
		return inventory1.getTool(i);
	}

	public static ArrayList<Tool> getTools(){
		for (Tool t : inventory1.getTools()) {
			System.out.println(t.toString());
		}
		return inventory1.getTools();
	}


	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {
		try {
			game1 =(Game)java.beans.Beans.instantiate(getClass().getClassLoader(), "TerraBlocks_game1");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
		inventory1 = new Inventory();
		craftingInventory1 = new CraftingInventory();
		settings1 = new Settings(this);

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		javax.swing.GroupLayout inventory1Layout = new javax.swing.GroupLayout(inventory1);
		inventory1.setLayout(inventory1Layout);
		inventory1Layout.setHorizontalGroup(
			inventory1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 401, Short.MAX_VALUE)
		);
		inventory1Layout.setVerticalGroup(
			inventory1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 171, Short.MAX_VALUE)
		);

		javax.swing.GroupLayout game1Layout = new javax.swing.GroupLayout(game1);
		game1.setLayout(game1Layout);
		game1Layout.setHorizontalGroup(
			game1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(game1Layout.createSequentialGroup()
				.addGap(14, 14, 14)
				.addComponent(inventory1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, game1Layout.createSequentialGroup()
				.addContainerGap(223, Short.MAX_VALUE)
				.addComponent(settings1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGap(210, 210, 210)
				.addComponent(craftingInventory1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGap(490, 490, 490))
		);
		game1Layout.setVerticalGroup(
			game1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(game1Layout.createSequentialGroup()
				.addGap(14, 14, 14)
				.addComponent(inventory1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGroup(game1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					.addGroup(game1Layout.createSequentialGroup()
						.addGap(112, 112, 112)
						.addComponent(craftingInventory1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
					.addGroup(game1Layout.createSequentialGroup()
						.addGap(90, 90, 90)
						.addComponent(settings1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(333, Short.MAX_VALUE))
		);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup()
				.addComponent(game1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGap(0, 0, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup()
				.addComponent(game1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGap(0, 0, Short.MAX_VALUE))
		);

		pack();
	}// </editor-fold>//GEN-END:initComponents


	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(TerraBlocks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(TerraBlocks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(TerraBlocks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(TerraBlocks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>


		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private static CraftingInventory craftingInventory1;
	private static Game              game1;
	private static Inventory         inventory1;
	private static Settings          settings1;
	// End of variables declaration//GEN-END:variables
}
