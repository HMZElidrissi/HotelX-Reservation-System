package MiniProjet;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Accueil extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Accueil window = new Accueil();
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
	

	/**
	 * Initialize the contents of the frame.
	 */
	
	public Accueil() {
		frame = new JFrame();
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
		
		JButton btnNewButton = new JButton("Reserver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
                Reserver ah = new Reserver();
                ah.getFrame().setTitle("Reserver");
                ah.getFrame().setVisible(true);
			}
		});
		btnNewButton.setBounds(43, 27, 245, 23);
		panel.add(btnNewButton);
		
		
		JButton btnNewButton_1 = new JButton("Consulter reservation");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ConsulterReservation ah = new ConsulterReservation();
                ah.getFrame().setTitle("Consulter reservation");
                ah.getFrame().setVisible(true);
			}
		});
		btnNewButton_1.setBounds(43, 81, 245, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Entrer client");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				CheckIn ah = new CheckIn();
                ah.getFrame().setTitle("Entrer client");
                ah.getFrame().setVisible(true);
			}
		});
		btnNewButton_2.setBounds(43, 141, 245, 23);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Se deconnecter");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(btnNewButton, "Etes vous sur?");
                // JOptionPane.setRootFrame(null);
                if (a == JOptionPane.YES_OPTION) {
                    dispose();
                    Identification obj = new Identification();
                    obj.getFrame().setTitle("Identification");
                    obj.getFrame().setVisible(true);;
                }
                
            }
			
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_3.setBounds(56, 229, 126, 23);
		frame.getContentPane().add(btnNewButton_3);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}
