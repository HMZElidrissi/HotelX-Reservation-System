package MiniProjet;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.TitledBorder;

public class ModifierChambre {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifierChambre window = new ModifierChambre();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ModifierChambre() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(158, 158, 158));
		frame.setBounds(400, 200, 450, 368);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JButton btnNewButton_1 = new JButton("Accueil");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Accueil_2 ah = new Accueil_2();
                ah.getFrame().setTitle("Accueil");
                ah.getFrame().setVisible(true);
			}
		});
		btnNewButton_1.setBounds(10, 6, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		JLabel lblNewLabel = new JLabel("Rechercher Chambre");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(120, 29, 194, 28);
		frame.getContentPane().add(lblNewLabel);
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Chambre n :", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(46, 64, 339, 199);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Type de chambre :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(26, 45, 134, 18);
		panel.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEnabled(false);
		comboBox.setBounds(170, 41, 30, 22);
		panel.add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Etage N :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(26, 87, 134, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("N ordre :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(26, 123, 134, 14);
		panel.add(lblNewLabel_3);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("hors service");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxNewCheckBox.setBounds(26, 155, 99, 23);
		panel.add(chckbxNewCheckBox);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setEnabled(false);
		spinner_1.setBounds(170, 122, 30, 20);
		panel.add(spinner_1);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setEnabled(false);
		comboBox_1.setBounds(170, 85, 30, 22);
		panel.add(comboBox_1);
		
		JButton btnNewButton = new JButton("Confirmer");
		btnNewButton.setBounds(165, 274, 105, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Supprimer");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(btnNewButton, "Cette chambre va etre supprimer definitivement,\n Etes vous sur ?");
                // JOptionPane.setRootFrame(null);
                if (a == JOptionPane.YES_OPTION) {
                    frame.dispose();
                    RechercherChambre obj = new RechercherChambre();
                    obj.getFrame().setTitle("Identification");
                    obj.getFrame().setVisible(true);
			}
			}
		});
		btnNewButton_2.setBounds(46, 274, 110, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Annuler");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
                RechercherChambre obj = new RechercherChambre();
                obj.getFrame().setTitle("Identification");
                obj.getFrame().setVisible(true);
			}
		});
		btnNewButton_3.setBounds(280, 274, 105, 23);
		frame.getContentPane().add(btnNewButton_3);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}
