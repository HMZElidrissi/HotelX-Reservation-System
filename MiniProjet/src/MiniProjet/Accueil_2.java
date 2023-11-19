package MiniProjet;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Accueil_2 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Accueil_2 window = new Accueil_2();
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
	public Accueil_2() {
		frame = new JFrame();
		frame.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().setBackground(new Color(158, 158, 158));
		frame.setBounds(400, 200, 450, 300);
		//frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(58, 29, 329, 194);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Ajouter chambre");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
                AjouterChambre ah = new AjouterChambre();
                ah.getFrame().setTitle("AjouterChambre");
                ah.getFrame().setVisible(true);
			}
		});
		btnNewButton.setBounds(43, 47, 245, 23);
		panel.add(btnNewButton);
		
		
		JButton btnNewButton_1 = new JButton("Modifier chambre");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
                RechercherChambre ah = new RechercherChambre();
                ah.getFrame().setTitle("RechercherChambre");
                ah.getFrame().setVisible(true);
			}
		});
		btnNewButton_1.setBounds(43, 125, 245, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("Se deconnecter");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(btnNewButton, "Etes vous sur?");
                // JOptionPane.setRootFrame(null);
                if (a == JOptionPane.YES_OPTION) {
                    frame.dispose();
                    Identification obj = new Identification();
                    obj.getFrame().setTitle("Identification");
                    obj.getFrame().setVisible(true);;
                }
                
            }
			
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_3.setBounds(56, 229, 125, 23);
		frame.getContentPane().add(btnNewButton_3);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}
