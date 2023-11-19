package MiniProjet;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class Identification extends JFrame {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Identification window = new Identification();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * Create the application.
	 */
	public Identification() {
		initialize();
		updateButtonState();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(158, 158, 158));
		frame.setBounds(400, 200, 450, 300);
		//frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(81, 55, 268, 179);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Mon ID :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(23, 25, 84, 14);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(51, 50, 173, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Mot de passe :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(23, 81, 97, 14);
		panel.add(lblNewLabel_2);
		
		btnNewButton = new JButton("Se connecter");
	

		
		
		passwordField = new JPasswordField();
		passwordField.setBounds(51, 106, 173, 20);
		passwordField.addKeyListener(new KeyAdapter() {
		    public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		            btnNewButton.doClick();
		        }
		    }
		});

		panel.add(passwordField);
		
		JLabel lblNewLabel = new JLabel("Je m'identifie");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(154, 30, 115, 14);
		frame.getContentPane().add(lblNewLabel);
		textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                updateButtonState();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateButtonState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateButtonState();
            }
        });
		passwordField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                updateButtonState();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateButtonState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateButtonState();
            }
        });
		btnNewButton.setBounds(72, 145, 118, 23);
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        login();
		    }
		});

		panel.add(btnNewButton);
		
		 
	}
	 private void updateButtonState() {
	        String username = textField.getText().trim();
	        String password = new String(passwordField.getPassword()).trim();
	        
	        btnNewButton.setEnabled(!username.isEmpty() && !password.isEmpty());
	    }
	 private void login() {
		    String userName = textField.getText();
		    String password = passwordField.getText();
		    try {
		        Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1/MiniProjet?serverTimezone=UTC",
		            "root", "");

		        PreparedStatement st = (PreparedStatement) connection.prepareStatement("Select UtilisateurID, Mot_de_passe from Utilisateur where UtilisateurID=? and Mot_de_passe=?");

		        st.setString(1, userName);
		        st.setString(2, password);
		        ResultSet rs = st.executeQuery();
		        if (rs.next()) {
		            rs.getString("UtilisateurID");

		            PreparedStatement st1 = (PreparedStatement) connection.prepareStatement("SELECT * FROM `agent` WHERE AgentID="+rs.getString("UtilisateurID")+";");
		            ResultSet rs1 = st1.executeQuery();
		            if (rs1.next()) {
		                frame.dispose();
		                Accueil ah = new Accueil();
		                ah.getFrame().setTitle("Accueil");
		                ah.getFrame().setVisible(true);
		            } else {
		                st1 = (PreparedStatement) connection.prepareStatement("SELECT * FROM `admin` WHERE adminId="+rs.getString("UtilisateurID")+";");
		                rs1 = st1.executeQuery();
		                rs1.next();
		                rs1.getString(1);
		                frame.dispose();
		                Accueil_2 ah = new Accueil_2();
		                ah.getFrame().setTitle("Accueil");
		                ah.getFrame().setVisible(true);
		            }
		        } else {
		            JOptionPane.showMessageDialog(frame, "Mauvais identifiant ou mot de passe");
		        }
		    } catch (SQLException sqlException) {
		        sqlException.printStackTrace();
		    }
		}

	
}
