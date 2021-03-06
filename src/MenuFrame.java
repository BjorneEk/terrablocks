package TerraBlocks;

import java.io.File;
import java.awt.Color;
import java.awt.event.*;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;

import static javax.swing.JOptionPane.showInputDialog;

////////////////////////////////////////////////////////////////////////////
///        @author Gustaf Franzén :: https://github.com/BjorneEk;        ///
////////////////////////////////////////////////////////////////////////////

public class MenuFrame extends javax.swing.JFrame {

	/**
	 * Creates new form MenuFrame
	 */
	String[] filenames;
	public MenuFrame() {
		initComponents();
		File folder = new File("saves/");
		File[] listOfFiles = folder.listFiles();
		filenames = new String[listOfFiles.length];
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				this.levelSelect.addItem(listOfFiles[i].getName());
				filenames[i] = listOfFiles[i].getName();
			}
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		startMenu      = new StartMenu();
		loadGameBtn    = new JButton();
		newGameBtn     = new JButton();
		levelSelect    = new JComboBox();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		loadGameBtn.setBackground(new Color(90, 60, 60));
		loadGameBtn.setFont(new Font("Dialog", 1, 48)); // NOI18N
		loadGameBtn.setForeground(new Color(0, 0, 0));
		loadGameBtn.setText("Load Game");
		loadGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				loadGameActionPerformed(evt);
			}
		});

		newGameBtn.setBackground(new Color(90, 60, 60));
		newGameBtn.setFont(new Font("Dialog", 1, 48)); // NOI18N
		newGameBtn.setForeground(new Color(0, 0, 0));
		newGameBtn.setText("New Game");
		newGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				newGameBtnActionPerformed(evt);
			}
		});

		levelSelect.setBackground(new Color(90, 60, 60));
		levelSelect.setFont(new Font("Dialog", 1, 24)); // NOI18N
		levelSelect.setMaximumRowCount(10);
		levelSelect.setToolTipText("");

		javax.swing.GroupLayout startMenuLayout = new javax.swing.GroupLayout(startMenu);
		startMenu.setLayout(startMenuLayout);
		startMenuLayout.setHorizontalGroup(
			startMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(startMenuLayout.createSequentialGroup()
				.addContainerGap(362, Short.MAX_VALUE)
				.addGroup(startMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, startMenuLayout.createSequentialGroup()
						.addComponent(loadGameBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(274, 274, 274))
					.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, startMenuLayout.createSequentialGroup()
						.addComponent(levelSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(410, 410, 410))))
			.addGroup(startMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, startMenuLayout.createSequentialGroup()
					.addContainerGap(312, Short.MAX_VALUE)
					.addComponent(newGameBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
					.addGap(273, 273, 273)))
		);
		startMenuLayout.setVerticalGroup(
			startMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, startMenuLayout.createSequentialGroup()
				.addContainerGap(434, Short.MAX_VALUE)
				.addComponent(loadGameBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGap(30, 30, 30)
				.addComponent(levelSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGap(138, 138, 138))
			.addGroup(startMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(startMenuLayout.createSequentialGroup()
					.addGap(322, 322, 322)
					.addComponent(newGameBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
					.addContainerGap(336, Short.MAX_VALUE)))
		);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup()
				.addComponent(startMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGap(0, 0, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addComponent(startMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void loadGameActionPerformed(ActionEvent evt) {//GEN-FIRST:event_loadGameActionPerformed
		String s = filenames[this.levelSelect.getSelectedIndex()];
		if (s != null) {
			System.out.println(s);
			TerraBlocks t = new TerraBlocks(s, false);
			t.setVisible(true);
			t.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setVisible(false);
		}
	}//GEN-LAST:event_loadGameActionPerformed

	private void newGameBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_newGameBtnActionPerformed
		String s = showInputDialog(this, "World name");
		if (s != null) {
			System.out.println(s);
			TerraBlocks t = new TerraBlocks(s, true);
			t.setVisible(true);
			t.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setVisible(false);
			this.dispose();
		}

	}//GEN-LAST:event_newGameBtnActionPerformed

	/**
	 * @param args the command line arguments
	 */
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
			java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MenuFrame().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private JComboBox levelSelect;
	private JButton   loadGameBtn;
	private JButton   newGameBtn;
	private StartMenu startMenu;
	// End of variables declaration//GEN-END:variables
}
