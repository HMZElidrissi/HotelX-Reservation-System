package MiniProjet;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JFormattedTextField;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import java.text.SimpleDateFormat;
import java.util.*;

public class Reserver {

	private JFrame frame;
	public static int cpt=1;
	public static int cmt;
	public static int numRooms = 0;
	public int nbrSimple;
	public int nbrDouble;
	public int nbrSuite;
	public int nbrAdulteSimple;
	public int nbrAdulteDouble;
	public int nbrAdulteSuite;
	public int nbrEnfantSimple;
	public int nbrEnfantDouble;
	public int nbrEnfantSuite;
	public int ReservationId=0;
	public JLabel labelReser;
	public JLabel labelPrix;
	private int clientId;
	public Connexion myCnx;
	private JDateChooser dateChooser= new JDateChooser();
	private JDateChooser dateChooser_1= new JDateChooser();
	private ArrayList<Chambre>roomPanels = new ArrayList<Chambre>(); // array to store the room panels
	private int nbrSimpleVoulu;
	private int nbrDoubleVoulu;
	private int nbrSuiteVoulu;
	private JLabel idreserv ;
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private JLabel prix ;
	JButton btnNewButton_3 = new JButton("Rechercher");
	JButton btnDelete = new JButton("Supprimer");
	JLabel lblNewLabel_10 = new JLabel("Enregistrer reservation");
	JPanel panel_5 = new JPanel();
	JLabel lblNewLabel_11 = new JLabel("Nom   :");
	JTextField textField_10 = new JTextField();
	JLabel lblNewLabel_12 = new JLabel("Prenom   :");
	JTextField textField_11 = new JTextField();
	JLabel lblNewLabel_13 = new JLabel("N telephone :");
	JTextField textField_12 = new JTextField();
	JButton btnNewButton_10 = new JButton("Enregistrer");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reserver window = new Reserver();
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
	public void updateTabTitles(JTabbedPane tabbedPane) {
		int count=tabbedPane.getTabCount();
	    for (int i = 0; i < count; i++) {
	        Component component = tabbedPane.getComponentAt(i);
	        if (component instanceof JPanel) {
	        	if(count<5)
	            tabbedPane.setTitleAt(i,"chambre"+ (i+1));
	        	else
	        		tabbedPane.setTitleAt(i,""+ (i+1));
	        }
	    }
	    if(tabbedPane.getTabCount()==0||dateChooser_1.getDate()==null||dateChooser.getDate()==null) {
        	btnNewButton_3.setEnabled(false);
        }
	    else {btnNewButton_3.setEnabled(true);}
	   
	}

	public Reserver() {
		
		myCnx=new Connexion();
		String Select1="SELECT COUNT(ChambreID) FROM `chambre` WHERE CategorieID=1;";
		String Select2="SELECT COUNT(ChambreID) FROM `chambre` WHERE CategorieID=2;";
		String Select3="SELECT COUNT(ChambreID) FROM `chambre` WHERE CategorieID=3;";
		
		ResultSet RS;
		try {
			RS = myCnx.getData(Select1);
			if(RS.next())
				this.nbrSimple=Integer.parseInt(RS.getString(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			RS = myCnx.getData(Select2);
			if(RS.next())
				this.nbrDouble=Integer.parseInt(RS.getString(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			RS = myCnx.getData(Select3);
			if(RS.next())
				this.nbrSuite=Integer.parseInt(RS.getString(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(158, 158, 158));
		frame.setBounds(400, 200, 461, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(49, 40, 324, 57);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("La date d'arrivee       :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(28, 11, 167, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("La date de depart     :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(28, 34, 174, 14);
		panel.add(lblNewLabel_1);
		
		
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(177, 30, 121, 20);
		panel.add(dateChooser);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(177, 5, 121, 20);
		panel.add(dateChooser_1);
		
		
		tabbedPane.setBounds(49, 130, 324, 200);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		//tabbedPane.addTab("Chambre 1", null, panel_1, null);
		panel_1.setBackground(new Color(192, 192, 192));
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2_1 = new JLabel("Categorie du chambre :");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(10, 33, 162, 14);
		panel_1.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("Nombre d'adulte          :");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_1.setBounds(10, 73, 181, 14);
		panel_1.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_4_1 = new JLabel("Nombre d'enfant         :");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4_1.setBounds(10, 112, 162, 14);
		panel_1.add(lblNewLabel_4_1);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Simple", "Double", "Suite"}));
		comboBox_1.setBounds(191, 31, 99, 22);
		panel_1.add(comboBox_1);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerNumberModel(1, 1, 2, 1));
		spinner_2.setBounds(191, 72, 47, 20);
		spinner_2.setValue(1);
		panel_1.add(spinner_2);
		
		JSpinner spinner_1_1 = new JSpinner();
		spinner_1_1.setModel(new SpinnerNumberModel(0, 0, 2, 1));
		spinner_1_1.setBounds(191, 111, 47, 20);
		 List<JLabel> labels = new ArrayList<>();
			List<JSpinner> spinners = new ArrayList<>();
				spinner_1_1.addChangeListener(new ChangeListener() {
				    public void stateChanged(ChangeEvent e) {
				    	//Object source = e.getSource();
				    	//if(source.equals(spinner_1)) {
				    		int enfantCount = (int) spinner_1_1.getValue();
		    		        if (enfantCount < labels.size()) {
		    		            for (int i = enfantCount; i < labels.size(); i++) {
		    		            	panel_1.remove(labels.get(i));
		    		                panel_1.remove(spinners.get(i));
		    		            }
		    		            labels.subList(enfantCount, labels.size()).clear();
		    		            spinners.subList(enfantCount, spinners.size()).clear();
		    		        } else {
		    		            for (int i = labels.size() + 1; i <= enfantCount; i++) {
		    		                JLabel label = new JLabel("enfant " + i);
		    		                label.setBounds(33+(i-1)*60, 129, 49, 14);
		    		                panel_1.add(label);
		    		                labels.add(label);
		
		    		                JSpinner enfantSpinner = new JSpinner();
		    		                enfantSpinner.setModel(new SpinnerNumberModel(0, 0, 12, 1));
		    		                enfantSpinner.setBounds(33+(i-1)*60, 143, 40, 20);
		    		                panel_1.add(enfantSpinner);
		    		                spinners.add(enfantSpinner);
		    		            }
		    		        }
		    		        panel_1.revalidate();
		    		        panel_1.repaint();
		    		    }
				   // }
				    
				        
				});
				panel_1.add(spinner_1_1);
       
		
		
		JLabel lblNewLabel_5 = new JLabel("Reserver");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5.setBounds(179, 15, 93, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		JButton btnNewButton_1 = new JButton("Accueil");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Accueil ah = new Accueil();
                ah.getFrame().setTitle("Accueil");
                ah.getFrame().setVisible(true);
			}
		});
		btnNewButton_1.setBounds(10, 6, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		
		
		
		

		
		JButton btnNewButton_2 = new JButton("Annuler");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateChooser.setDate(null);
		        dateChooser_1.setDate(null);
		        comboBox_1.setSelectedIndex(0);
		        spinner_2.setValue(1);
		        spinner_1_1.setValue(0);
			}
		});
		btnNewButton_2.setBounds(92, 368, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String arrivalDate = dateChooser_1.getDate().toString();
			        String departureDate = dateChooser.getDate().toString();
			        String category = comboBox_1.getSelectedItem().toString();
			        int numAdults = (int) spinner_2.getValue();
			        int numChildren = (int) spinner_1_1.getValue();
			        
			        if(reservationPossible()==1)
			        {	int a = JOptionPane.showConfirmDialog(null, "la reservation est possible \n voulez-vous enregistrer la reservation", "reservation", JOptionPane.YES_NO_OPTION);
			        	if (a == JOptionPane.YES_OPTION) {
			        	
			        	
			    		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 16));
			    		lblNewLabel_10.setBounds(129+400, 15, 194, 28);
			    		frame.getContentPane().add(lblNewLabel_10);
			    		
			    		panel_5.setBackground(new Color(192, 192, 192));
			    		panel_5.setBounds(32+400, 74, 371, 218);
			    		frame.getContentPane().add(panel_5);
			    		panel_5.setLayout(null);
			    		
			    		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 16));
			    		lblNewLabel_11.setBounds(37, 23, 104, 14);
			    		panel_5.add(lblNewLabel_11);
			    		
			    		textField_10.setBounds(74, 48, 234, 20);
			    		panel_5.add(textField_10);
			    		textField_10.setColumns(10);
			    		
			    		lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 16));
			    		lblNewLabel_12.setBounds(37, 79, 104, 14);
			    		panel_5.add(lblNewLabel_12);
			    		
			    		textField_11.setBounds(74, 104, 234, 20);
			    		panel_5.add(textField_11);
			    		textField_11.setColumns(10);
			    		
			    		lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 16));
			    		lblNewLabel_13.setBounds(37, 135, 104, 14);
			    		panel_5.add(lblNewLabel_13);
			    		
			    		textField_12.setBounds(74, 162, 234, 20);
			    		panel_5.add(textField_12);
			    		textField_12.setColumns(10);
			    		
			    		btnNewButton_10.setBounds(166+400, 303, 104, 23);
			    		frame.getContentPane().add(btnNewButton_10);
			    		btnNewButton_10.addActionListener(new ActionListener() {
			    			public void actionPerformed(ActionEvent e) {
			    				
			    				idreserv= new JLabel("n de reservation :"+ReservationId);
	    						idreserv.setBounds(166+400, 333, 200, 14);
	    						
	    						//panel_5.add(idreserv);
	    						prix=new JLabel("prix:"+calculerprix());
	    						prix.setBounds(166+400, 365, 150, 14);
	    						frame.getContentPane().add(prix);
	    						frame.getContentPane().add(idreserv);
	    						frame.getContentPane().revalidate();
	    				        frame.getContentPane().repaint();
			    				ResultSet RS;
			    				
			    				SimpleDateFormat Da= new SimpleDateFormat("yyyy-MM-dd");
			    				String Select1="INSERT INTO `client`(Nom, Prenom, Num_tel)\r\n"
			    						+ " VALUES ('"+textField_10.getText()+"','" +textField_11.getText()+"','" +textField_12.getText()+"');\r\n";
			    				String Select2="SELECT ClientID FROM `client` WHERE Nom='"+textField_10.getText()+"' AND Prenom='"+textField_11.getText()+"';";
			    				
			    				try {
			    					myCnx.insert(Select1);
			    					
			    				} catch (SQLException ex) {
			    					// TODO Auto-generated catch block
			    					ex.printStackTrace();
			    				}
			    				try {
			    					RS = myCnx.getData(Select2);
			    					if(RS.next())
			    					clientId=Integer.parseInt(RS.getString(1));
			    				} catch (SQLException ex) {
			    					// TODO Auto-generated catch block
			    					ex.printStackTrace();
			    				}
			    					
			    				String Select3="INSERT INTO Reservation (ClientID, Date_arrivée, Date_depart)\r\n"
			    						+ "VALUES ("+clientId+",'"+Da.format(dateChooser_1.getDate())+"','"+Da.format(dateChooser.getDate())+"');";
			    				try {
			    					myCnx.insert(Select3);
			    
			    				} catch (SQLException ex1) {
			    					// TODO Auto-generated catch block
			    					ex1.printStackTrace();
			    				}
			    				
		    					for(Chambre ch:roomPanels)
		    					{
		    						if((int)ch.getSpinner_1().getValue()==2){
			    						if(ch.getComboBox().getSelectedItem().equals("Simple"))
			    						{
			    							
			    							String Select4="INSERT INTO Detailreservation (\r\n"
						    						+ "        CategorieID,\r\n"
						    						+ "        ReservationID,\r\n"
						    						+ "        Nombre_adultes,\r\n"
						    						+ "        Nombre_enfants,\r\n"
						    						+ "        age_enfant1,\r\n"
						    						+ "        age_enfant2\r\n"
						    						+ "    )\r\n"
						    						+ "VALUES ("+1+","+ ReservationId+"," +(int)ch.getSpinner().getValue()+","+(int)ch.getSpinner_1().getValue()+","+(int)ch.getSpinners().get(0).getValue()+","+(int)ch.getSpinners().get(1).getValue()+");";
						    				try {
						    					myCnx.insert(Select4);;
						    				} catch (SQLException ex3) {
						    					// TODO Auto-generated catch block
						    					ex3.printStackTrace();
						    				}
			    						}
			    						if(ch.getComboBox().getSelectedItem().equals("Double"))
			    						{
			    							String Select4="INSERT INTO Detailreservation (\r\n"
						    						+ "        CategorieID,\r\n"
						    						+ "        ReservationID,\r\n"
						    						+ "        Nombre_adultes,\r\n"
						    						+ "        Nombre_enfants,\r\n"
						    						+ "        age_enfant1,\r\n"
						    						+ "        age_enfant2\r\n"
						    						+ "    )\r\n"
						    						+ "VALUES ("+2+","+ ReservationId+"," +(int)ch.getSpinner().getValue()+","+(int)ch.getSpinner_1().getValue()+","+(int)ch.getSpinners().get(0).getValue()+","+(int)ch.getSpinners().get(1).getValue()+");";
						    				try {
						    					myCnx.insert(Select4);;
						    				} catch (SQLException ex3) {
						    					// TODO Auto-generated catch block
						    					ex3.printStackTrace();
						    				}
			    						}
			    						if(ch.getComboBox().getSelectedItem().equals("Suite"))
			    						{
			    							String Select4="INSERT INTO Detailreservation (\r\n"
						    						+ "        CategorieID,\r\n"
						    						+ "        ReservationID,\r\n"
						    						+ "        Nombre_adultes,\r\n"
						    						+ "        Nombre_enfants,\r\n"
						    						+ "        age_enfant1,\r\n"
						    						+ "        age_enfant2\r\n"
						    						+ "    )\r\n"
						    						+ "VALUES ("+3+","+ ReservationId+"," +(int)ch.getSpinner().getValue()+","+(int)ch.getSpinner_1().getValue()+","+(int)ch.getSpinners().get(0).getValue()+","+(int)ch.getSpinners().get(1).getValue()+");";
						    				try {
						    					myCnx.insert(Select4);;
						    				} catch (SQLException ex3) {
						    					// TODO Auto-generated catch block
						    					ex3.printStackTrace();
						    				}
			    						}}
		    						if((int)ch.getSpinner_1().getValue()==1){
			    						if(ch.getComboBox().getSelectedItem().equals("Simple"))
			    						{
			    							
			    							String Select4="INSERT INTO Detailreservation (\r\n"
						    						+ "        CategorieID,\r\n"
						    						+ "        ReservationID,\r\n"
						    						+ "        Nombre_adultes,\r\n"
						    						+ "        Nombre_enfants,\r\n"
						    						+ "        age_enfant1\r\n"
						    						+ "    )\r\n"
						    						+ "VALUES ("+1+","+ ReservationId+"," +(int)ch.getSpinner().getValue()+","+1+","+(int)ch.getSpinners().get(0).getValue()+");";
						    				try {
						    					myCnx.insert(Select4);;
						    				} catch (SQLException ex3) {
						    					// TODO Auto-generated catch block
						    					ex3.printStackTrace();
						    				}
			    						}
			    						if(ch.getComboBox().getSelectedItem().equals("Double"))
			    						{
			    							String Select4="INSERT INTO Detailreservation (\r\n"
						    						+ "        CategorieID,\r\n"
						    						+ "        ReservationID,\r\n"
						    						+ "        Nombre_adultes,\r\n"
						    						+ "        Nombre_enfants,\r\n"
						    						+ "        age_enfant1\r\n"
						    						+ "    )\r\n"
						    						+ "VALUES ("+2+","+ ReservationId+"," +(int)ch.getSpinner().getValue()+","+1+","+(int)ch.getSpinners().get(0).getValue()+");";
			    							try {
						    					myCnx.insert(Select4);;
						    				} catch (SQLException ex3) {
						    					// TODO Auto-generated catch block
						    					ex3.printStackTrace();
						    				}
			    						}
			    						if(ch.getComboBox().getSelectedItem().equals("Suite"))
			    						{
			    							String Select4="INSERT INTO Detailreservation (\r\n"
						    						+ "        CategorieID,\r\n"
						    						+ "        ReservationID,\r\n"
						    						+ "        Nombre_adultes,\r\n"
						    						+ "        Nombre_enfants,\r\n"
						    						+ "        age_enfant1\r\n"
						    						+ "    )\r\n"
						    						+ "VALUES ("+3+","+ ReservationId+"," +(int)ch.getSpinner().getValue()+","+1+","+(int)ch.getSpinners().get(0).getValue()+");";
			    							try {
						    					myCnx.insert(Select4);;
						    				} catch (SQLException ex3) {
						    					// TODO Auto-generated catch block
						    					ex3.printStackTrace();
						    				}
			    						}}
		    						if((int)ch.getSpinner_1().getValue()==0){
			    						if(ch.getComboBox().getSelectedItem().equals("Simple"))
			    						{
			    							
			    							String Select4="INSERT INTO Detailreservation (\r\n"
						    						+ "        CategorieID,\r\n"
						    						+ "        ReservationID,\r\n"
						    						+ "        Nombre_adultes,\r\n"
						    						+ "        Nombre_enfants\r\n"
						    						+ "    )\r\n"
						    						+ "VALUES ("+1+","+ ReservationId+"," +(int)ch.getSpinner().getValue()+","+0+");";
						    				try {
						    					myCnx.insert(Select4);;
						    				} catch (SQLException ex3) {
						    					// TODO Auto-generated catch block
						    					ex3.printStackTrace();
						    				}
			    						}
			    						if(ch.getComboBox().getSelectedItem().equals("Double"))
			    						{
			    							String Select4="INSERT INTO Detailreservation (\r\n"
						    						+ "        CategorieID,\r\n"
						    						+ "        ReservationID,\r\n"
						    						+ "        Nombre_adultes,\r\n"
						    						+ "        Nombre_enfants\r\n"
						    						+ "    )\r\n"
						    						+ "VALUES ("+2+","+ ReservationId+"," +(int)ch.getSpinner().getValue()+","+0+");";
			    							try {
						    					myCnx.insert(Select4);;
						    				} catch (SQLException ex3) {
						    					// TODO Auto-generated catch block
						    					ex3.printStackTrace();
						    				}
			    						}
			    						if(ch.getComboBox().getSelectedItem().equals("Suite"))
			    						{
			    							String Select4="INSERT INTO Detailreservation (\r\n"
						    						+ "        CategorieID,\r\n"
						    						+ "        ReservationID,\r\n"
						    						+ "        Nombre_adultes,\r\n"
						    						+ "        Nombre_enfants\r\n"
						    						+ "    )\r\n"
						    						+ "VALUES ("+3+","+ ReservationId+"," +(int)ch.getSpinner().getValue()+","+0+");";
			    							try {
						    					myCnx.insert(Select4);;
						    				} catch (SQLException ex3) {
						    					// TODO Auto-generated catch block
						    					ex3.printStackTrace();
						    				}
			    						}}
		    						
		    					}
			    				
			    				}
			    				
			    				
			    			
			    		});
			        	frame.setBounds(400, 200,900, 450);
			        	frame.getContentPane().revalidate();
				        frame.getContentPane().repaint();
	                    
			        }
	                }
			        else
			        {
			        	JOptionPane.showMessageDialog(null, "la reservation n'est pas possible ", "reservation", JOptionPane.OK_OPTION);
			        	}
		}});
		btnNewButton_3.setBounds(230, 368, 104, 23);
		btnNewButton_3.setEnabled(false);
		frame.getContentPane().add(btnNewButton_3);
		
		
		Chambre  temp  = new Chambre();
//		tabbedPane.addTab("Chambre"+(numRooms), null,temp.getPanel(), null);
//        updateTabTitles( tabbedPane);
		JButton btnNewButton = new JButton("Ajouter une autre chambre");
		btnNewButton.setBounds(153, 331, 219, 23);
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (e.getSource() == btnNewButton) {
		            Chambre  temp  = new Chambre();
		            
		            numRooms++;
		            
//		            cmt=numRooms;
		            roomPanels.add(temp);
		            tabbedPane.addTab("Chambre"+(numRooms), null,temp.getPanel(), null);
		            updateTabTitles( tabbedPane);
		            
		            
		            temp.getBtnDelete().addActionListener(new ActionListener() {
    	     		    @Override
    	     		    public void actionPerformed(ActionEvent e) {
    	     		        int index = tabbedPane.getSelectedIndex();
    	     		        if (index != -1) {
    	     		        	
    	     		        	tabbedPane.remove(index);
    	     		        	updateTabTitles( tabbedPane);
    	     		        	roomPanels.remove(index);
    	     		        //	roomPanels.remove(index);
    	     		        	//updateTabTitles(tabbedPanel) ;	        	
    	     		        }
    	     		       numRooms--;
    	     	}    
    	     });
		        }}    
		});
		
		frame.getContentPane().add(btnNewButton);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	public double calculerprix(){
		double prixttl=0;
		Date day =new Date();
		Date end=new Date();
		SimpleDateFormat Da= new SimpleDateFormat("yyyy-MM-dd");
		try {
			day = new SimpleDateFormat("yyyy-MM-dd").parse(Da.format(dateChooser_1.getDate()));
			end =new SimpleDateFormat("yyyy-MM-dd").parse(Da.format(dateChooser.getDate()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		try {
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(end);
	        cal1.add(Calendar.DATE, 1);
	       end=cal1.getTime();
	       long diffInMillies = Math.abs(day.getTime() - end.getTime());
	       long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	       int countdays=(int)diffInDays-1;
	       System.out.println(countdays);
		while(countdays!=0){
			  for (int i = 0; i < tabbedPane.getTabCount(); i++) {
				  //adulte apres 3 eme
				  if(roomPanels.get(i).getComboBox().getSelectedItem().equals("Simple"))
				  {
					  
					ResultSet	ressimp = myCnx.getData("SELECT prix FROM `prix` ps WHERE CategorieID = "+1+" and ps.Nom=(SELECT Nom FROM Saison WHERE MONTH('"+new SimpleDateFormat("yyyy-MM-dd").format(day)+"') * 100 + DAY('"+new SimpleDateFormat("yyyy-MM-dd").format(day)+"') BETWEEN MONTH(Date_debut) * 100 + DAY(Date_debut) AND MONTH(Date_fin) * 100 + DAY(Date_fin));");
				  ressimp.next();
				  if(countdays<3) {
					  
					  prixttl= prixttl+ ( (int)roomPanels.get(i).getSpinner().getValue())*Double.parseDouble(ressimp.getString("prix"));
					  if((int)roomPanels.get(i).getSpinner_1().getValue()==2)
					  	prixttl= prixttl+ (0.5)*Double.parseDouble(ressimp.getString("prix"));
					  
				  }else {
					  System.out.println(ressimp.getString("prix"));
					  prixttl= prixttl+ (0.95)*( (int)roomPanels.get(i).getSpinner().getValue())*Double.parseDouble(ressimp.getString("prix"));
					  if((int)roomPanels.get(i).getSpinner_1().getValue()==2)
					  	prixttl= prixttl+ (0.95)*(0.5)*Double.parseDouble(ressimp.getString("prix"));
				  }
				
			  		}
				  if(roomPanels.get(i).getComboBox().getSelectedItem().equals("Double"))
				  {
					ResultSet	ressimp = myCnx.getData("SELECT prix FROM `prix` ps WHERE CategorieID = "+2+" and ps.Nom=(SELECT SaisonID FROM Saison WHERE MONTH('"+new SimpleDateFormat("yyyy-MM-dd").format(day)+"') * 100 + DAY('"+new SimpleDateFormat("yyyy-MM-dd").format(day)+"') BETWEEN MONTH(Date_debut) * 100 + DAY(Date_debut) AND MONTH(Date_fin) * 100 + DAY(Date_fin));");
				  ressimp.next();
				  if(countdays<3) {
					  
					  prixttl= prixttl+ ( (int)roomPanels.get(i).getSpinner().getValue())*Double.parseDouble(ressimp.getString("prix"));
					  if((int)roomPanels.get(i).getSpinner_1().getValue()==2)
					  	prixttl= prixttl+ (0.5)*Double.parseDouble(ressimp.getString("prix"));
				  }else {
					  prixttl= prixttl+ (0.95)*( (int)roomPanels.get(i).getSpinner().getValue())*Double.parseDouble(ressimp.getString("prix"));
					  if((int)roomPanels.get(i).getSpinner_1().getValue()==2)
					  	prixttl= prixttl+ (0.95)*(0.5)*Double.parseDouble(ressimp.getString("prix"));
				  }
				
			  		}
				  if(roomPanels.get(i).getComboBox().getSelectedItem().equals("Suite"))
				  {
					ResultSet	ressimp = myCnx.getData("SELECT prix FROM `prix` ps WHERE CategorieID = "+3+" and ps.Nom=(SELECT SaisonID FROM Saison WHERE MONTH('"+new SimpleDateFormat("yyyy-MM-dd").format(day)+"') * 100 + DAY('"+new SimpleDateFormat("yyyy-MM-dd").format(day)+"') BETWEEN MONTH(Date_debut) * 100 + DAY(Date_debut) AND MONTH(Date_fin) * 100 + DAY(Date_fin));");
				  ressimp.next();
				  if(countdays<3) {
					  
					  prixttl= prixttl+ ( (int)roomPanels.get(i).getSpinner().getValue())*Double.parseDouble(ressimp.getString("prix"));
					  if((int)roomPanels.get(i).getSpinner_1().getValue()==2)
					  	prixttl= prixttl+ (0.5)*Double.parseDouble(ressimp.getString("prix"));
				  }else {
					  prixttl= prixttl+ (0.95)*( (int)roomPanels.get(i).getSpinner().getValue())*Double.parseDouble(ressimp.getString("prix"));
					  if((int)roomPanels.get(i).getSpinner_1().getValue()==2)
					  	prixttl= prixttl+ (0.95)*(0.5)*Double.parseDouble(ressimp.getString("prix"));
				  }
				
			  		}}
			  
			  
			Calendar cal = Calendar.getInstance();
			cal.setTime(day);
	        cal.add(Calendar.DATE, 1);
	       day=cal.getTime();
	       countdays--;// pour la reduction 
		}}catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println(prixttl);
		 
		
		return prixttl;
		
	}
//	public void save(){
//		//resrvation
//		//INSERT INTO `reservation`( `client_nom`, `client_prenom`, `guest_phone`, `checkin_date`, `checkout_date`, `total_price`) VALUES ('"++"','"++"','"++"','"++"','"++"','"++"');
//		try {
//			//mycnx.insert("INSERT INTO `reservation`( `client_nom`, `client_prenom`, `guest_phone`, `checkin_date`, `checkout_date`, `total_price`) VALUES ('"+nom.getText()+"','"+prenom.getText()+"','"+num.getText()+"','"+ df1.format(startdate)+"','"+ df1.format(enddate)+"','"+calculerprix()+"');");
//			myCnx.insert("INSERT INTO `reservation`( `client_nom`, `client_prenom`, `guest_phone`, `checkin_date`, `checkout_date`, `total_price`) VALUES ('"+nom.getText()+"','"+prenom.getText()+"','"+num.getText()+"','"+ startdate+"','"+ enddate+"','"+calculerprix()+"');");
//	} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		ResultSet resres;
//		try {
//			resres = myCnx.getData("SELECT Max(id) FROM `reservation`;");
//			resres.next();
//		
//		for (int i = 0; i < tabbedPane.getTabCount(); i++) 
//		{	String age1;
//			if(simples.get(i).getEnfant1().isSelected())
//				 age1="'"+ (String) simples.get(i).getAgeEnfant1().getValue()+"'";
//				else
//					age1= "NULL";
//			String age2;
//			if(simples.get(i).getEnfant2().isSelected())
//				 age2="'" +(String) simples.get(i).getAgeEnfant2().getValue()+"'";
//				else
//					age2= "NULL";
//			/*mycnx.insert("INSERT INTO `reservations_room`( `reservation_id`, `room_type`, `num_adults`, `num_kids`, `child1_age`, `child2_age`) "
//					+ "VALUES ('"+resres.getString("Max(id)")+"','simple','"+simples.get(i).getAdultes()+"','"+simples.get(i).getEnfant()+"','"+age1+"','"+age2+"');"); */
//			mycnx.insert("INSERT INTO `reservations_room`( `reservation_id`, `room_type`, `num_adults`, `num_kids`, `child1_age`, `child2_age`) "
//					+ "VALUES ('"+resres.getString("Max(id)")+"','simple','"+simples.get(i).getAdultes()+"','"+simples.get(i).getEnfant()+"',"+age1+","+age2+");");
//	
//		    //INSERT INTO `reservations_room`( `reservation_id`, `room_type`, `num_adults`, `num_kids`, `child1_age`, `child2_age`) VALUES ('"++"','"++"','"++"','"++"','"++"','"++"');
//		}
//		for (int i = 0; i < Panedouble.getTabCount(); i++) 
//		{	String age1;
//		if(doubles.get(i).getEnfant1().isSelected())
//			 age1= "'"+(String) doubles.get(i).getAgeEnfant1().getValue()+"'";
//			else
//				age1= "NULL";
//		String age2;
//		if(doubles.get(i).getEnfant2().isSelected())
//			 age2="'" +(String) doubles.get(i).getAgeEnfant2().getValue()+"'";
//			else
//				age2= "NULL";
//		/*mycnx.insert("INSERT INTO `reservations_room`( `reservation_id`, `room_type`, `num_adults`, `num_kids`, `child1_age`, `child2_age`) "
//				+ "VALUES ('"+resres.getString("Max(id)")+"','suite','"+doubles.get(i).getAdultes()+"','"+doubles.get(i).getEnfant()+"','"+age1+"','"+age2+"');"); */
//		mycnx.insert("INSERT INTO `reservations_room`( `reservation_id`, `room_type`, `num_adults`, `num_kids`, `child1_age`, `child2_age`) "
//				+ "VALUES ('"+resres.getString("Max(id)")+"','doubles','"+doubles.get(i).getAdultes()+"','"+doubles.get(i).getEnfant()+"',"+age1+","+age2+");");
//
//	
//		}
//		for (int i = 0; i < Panesuite.getTabCount(); i++) 
//		{
//			{	String age1;
//			if(suites.get(i).getEnfant1().isSelected())
//				 age1= "'"+(String) suites.get(i).getAgeEnfant1().getValue()+"'";
//				else
//					age1= "NULL";
//			String age2;
//			if(suites.get(i).getEnfant2().isSelected())
//				 age2= "'"+(String) suites.get(i).getAgeEnfant2().getValue()+"'";
//				else
//					age2= "NULL";
//			/*mycnx.insert("INSERT INTO `reservations_room`( `reservation_id`, `room_type`, `num_adults`, `num_kids`, `child1_age`, `child2_age`) "
//					+ "VALUES ('"+resres.getString("Max(id)")+"','suite','"+doubles.get(i).getAdultes()+"','"+doubles.get(i).getEnfant()+"','"+age1+"','"+age2+"');"); */
//			mycnx.insert("INSERT INTO `reservations_room`( `reservation_id`, `room_type`, `num_adults`, `num_kids`, `child1_age`, `child2_age`) "
//					+ "VALUES ('"+resres.getString("Max(id)")+"','suite','"+suites.get(i).getAdultes()+"','"+suites.get(i).getEnfant()+"',"+age1+","+age2+");");
//
//		
//			}
//		}
//	      } catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//			// facture
//	       // simple
//			for (int i = 0; i < Panesimple.getTabCount(); i++) 
//			{
//				int countdays=0;
//				double prixttl=0;
//				Date day =new Date();
//				Date end=new Date();
//				try {
//					end =new SimpleDateFormat("yyyy-MM-dd").parse(enddate);
//							day = new SimpleDateFormat("yyyy-MM-dd").parse(startdate);;
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				Calendar cal1 = Calendar.getInstance();
//				cal1.setTime(end);
//		        cal1.add(Calendar.DATE, 1);
//		       end=cal1.getTime();
//				       while(end.after(day)){
//					  		  
//				
//							  ResultSet ressimp;
//							try {
//								ressimp = mycnx.getData("SELECT prixnuit FROM `prixsaison` ps WHERE typechambre = 'simple' and ps.typesaison=(SELECT type FROM Saison WHERE MONTH('"+new SimpleDateFormat("yyyy-MM-dd").format(day)+"') * 100 + DAY('"+new SimpleDateFormat("yyyy-MM-dd").format(day)+"') BETWEEN MONTH(Datedebut) * 100 + DAY(Datedebut) AND MONTH(Datefin) * 100 + DAY(Datefin));");
//								ressimp.next();
//							
//									if(countdays<3) {
//								  prixttl= prixttl+ ( simples.get(i).getAdultes())*Double.parseDouble(ressimp.getString("prixnuit"));
//								  if(simples.get(i).getEnfant()==2)
//								  	prixttl= prixttl+ (0.5)*Double.parseDouble(ressimp.getString("prixnuit"));
//							  			}else{
//								  prixttl= prixttl+ (0.95)*( simples.get(i).getAdultes())*Double.parseDouble(ressimp.getString("prixnuit"));
//								  if(simples.get(i).getEnfant()==2)
//								
//										prixttl= prixttl+ (0.95)*(0.5)*Double.parseDouble(ressimp.getString("prixnuit"));
//							  			}} catch (Exception e) {
//										// TODO Auto-generated catch block
//										e.printStackTrace();
//									
//							  		}
//							
//							
//	
//						Calendar cal = Calendar.getInstance();
//							cal.setTime(day);
//					        cal.add(Calendar.DATE, 1);
//					       day=cal.getTime();
//					       countdays++;
//							
//				     }
//				       
//				       try {
//	 
//				       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//				       		resres = mycnx.getData("SELECT Max(id) FROM `reservation`;");
//							 resres.next();
//						/*mycnx.insert("INSERT INTO `facture`( `ReservationID`, `Dateedition`, `type`, `nbrJouur`, `nbrAdulte`, `nbrEnfant`, `Prixttl`)"
//						   		+ " VALUES ('"+resres.getString("Max(id)")+"','"+df.format(new  Date())+"','simple','"+countdays+"','"+simples.get(i).getAdultes()+"','"+simples.get(i).getEnfant()+"','"+prixttl+"');");
//					*/mycnx.insert("INSERT INTO `facture`( `ReservationID`, `Dateedition`, `type`, `nbrJouur`, `nbrAdulte`, `nbrEnfant`, `Prixttl`)"
//					   		+ " VALUES ('"+resres.getString("Max(id)")+"','"+df.format(new  Date())+"','simple','"+countdays+"','"+simples.get(i).getAdultes()+"','"+simples.get(i).getEnfant()+"','"+prixttl+"');");
//							 } catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//	    }
//		//double
//		for (int i = 0; i < Panedouble.getTabCount(); i++) {
//			int countdays=0;
//			double prixttl=0;
//	Date day =new Date();
//	Date end=new Date();
//
//		
//		try {
//			end =new SimpleDateFormat("yyyy-MM-dd").parse(enddate);
//					day = new SimpleDateFormat("yyyy-MM-dd").parse(startdate);;
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Calendar cal1 = Calendar.getInstance();
//		cal1.setTime(end);
//        cal1.add(Calendar.DATE, 1);
//       end=cal1.getTime();
//		       while(end.after(day)){
//			  		  
//		
//					  ResultSet ressimp;
//					try {
//						ressimp = mycnx.getData("SELECT prixnuit FROM `prixsaison` ps WHERE typechambre = 'double' and ps.typesaison=(SELECT type FROM Saison WHERE MONTH('"+new SimpleDateFormat("yyyy-MM-dd").format(day)+"') * 100 + DAY('"+new SimpleDateFormat("yyyy-MM-dd").format(day)+"') BETWEEN MONTH(Datedebut) * 100 + DAY(Datedebut) AND MONTH(Datefin) * 100 + DAY(Datefin));");
//						ressimp.next();
//					
//							if(countdays<3) {
//						  prixttl= prixttl+ ( doubles.get(i).getAdultes())*Double.parseDouble(ressimp.getString("prixnuit"));
//						  if(doubles.get(i).getEnfant()==2)
//						  	prixttl= prixttl+ (0.5)*Double.parseDouble(ressimp.getString("prixnuit"));
//					  			}else{
//						  prixttl= prixttl+ (0.95)*( doubles.get(i).getAdultes())*Double.parseDouble(ressimp.getString("prixnuit"));
//						  if(doubles.get(i).getEnfant()==2)
//						
//								prixttl= prixttl+ (0.95)*(0.5)*Double.parseDouble(ressimp.getString("prixnuit"));
//					  			}} catch (Exception e) {
//								// TODO Auto-generated catch block
//									e.printStackTrace();
//								
//						  		}
//						
//						
//
//					Calendar cal = Calendar.getInstance();
//						cal.setTime(day);
//				        cal.add(Calendar.DATE, 1);
//				       day=cal.getTime();
//				       countdays++;
//						
//			     }
//			       
//			       try {
// 
//			       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		       		resres = mycnx.getData("SELECT Max(id) FROM `reservation`;");
//					 resres.next();
//				/*mycnx.insert("INSERT INTO `facture`( `ReservationID`, `Dateedition`, `type`, `nbrJouur`, `nbrAdulte`, `nbrEnfant`, `Prixttl`)"
//				   		+ " VALUES ('"+resres.getString("Max(id)")+"','"+df.format(new  Date())+"','double','"+countdays+"','"+simples.get(i).getAdultes()+"','"+simples.get(i).getEnfant()+"','"+prixttl+"');");
//			*/mycnx.insert("INSERT INTO `facture`( `ReservationID`, `Dateedition`, `type`, `nbrJouur`, `nbrAdulte`, `nbrEnfant`, `Prixttl`)"
//			   		+ " VALUES ('"+resres.getString("Max(id)")+"','"+df.format(new  Date())+"','double','"+countdays+"','"+simples.get(i).getAdultes()+"','"+simples.get(i).getEnfant()+"','"+prixttl+"');");
//					 } catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	    }
/////
//		//suite
//		for (int i = 0; i < Panesuite.getTabCount(); i++) {
//			int countdays=0;
//			double prixttl=0;
//			Date day =new Date();
//			Date end=new Date();
//
//		
//			try {
//				end =new SimpleDateFormat("yyyy-MM-dd").parse(enddate);
//				day = new SimpleDateFormat("yyyy-MM-dd").parse(startdate);;
//				}
//			catch (ParseException e) {
//				e.printStackTrace();
//				}
//			Calendar cal1 = Calendar.getInstance();
//			cal1.setTime(end);
//			cal1.add(Calendar.DATE, 1);
//			end=cal1.getTime();
//		    while(end.after(day)){
//			   ResultSet ressimp;
//				try {
//					ressimp = mycnx.getData("SELECT prixnuit FROM `prixsaison` ps WHERE typechambre = 'suite' and ps.typesaison=(SELECT type FROM Saison WHERE MONTH('"+new SimpleDateFormat("yyyy-MM-dd").format(day)+"') * 100 + DAY('"+new SimpleDateFormat("yyyy-MM-dd").format(day)+"') BETWEEN MONTH(Datedebut) * 100 + DAY(Datedebut) AND MONTH(Datefin) * 100 + DAY(Datefin));");
//					ressimp.next();
//					
//					if(countdays<3) {
//						  prixttl= prixttl+ ( suites.get(i).getAdultes())*Double.parseDouble(ressimp.getString("prixnuit"));
//						  if(suites.get(i).getEnfant()==2)
//						  	prixttl= prixttl+ (0.5)*Double.parseDouble(ressimp.getString("prixnuit"));
//					  			}
//					else{
//					  prixttl= prixttl+ (0.95)*( suites.get(i).getAdultes())*Double.parseDouble(ressimp.getString("prixnuit"));
//					  if(suites.get(i).getEnfant()==2)
//					
//							prixttl= prixttl+ (0.95)*(0.5)*Double.parseDouble(ressimp.getString("prixnuit"));
//				  			}}
//				catch (Exception e) {
//								e.printStackTrace();
//							}
//				Calendar cal = Calendar.getInstance();
//				cal.setTime(day);
//		        cal.add(Calendar.DATE, 1);
//		       day=cal.getTime();
//		       countdays++;
//				}
//			       
//			       try {
// 
//			       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		       		resres = mycnx.getData("SELECT Max(id) FROM `reservation`;");
//					 resres.next();
//				/*mycnx.insert("INSERT INTO `facture`( `ReservationID`, `Dateedition`, `type`, `nbrJouur`, `nbrAdulte`, `nbrEnfant`, `Prixttl`)"
//				   		+ " VALUES ('"+resres.getString("Max(id)")+"','"+df.format(new  Date())+"','suite','"+countdays+"','"+simples.get(i).getAdultes()+"','"+simples.get(i).getEnfant()+"','"+prixttl+"');");
//				 */mycnx.insert("INSERT INTO `facture`( `ReservationID`, `Dateedition`, `type`, `nbrJouur`, `nbrAdulte`, `nbrEnfant`, `Prixttl`)"
//			   		+ " VALUES ('"+resres.getString("Max(id)")+"','"+df.format(new  Date())+"','suite','"+countdays+"','"+simples.get(i).getAdultes()+"','"+simples.get(i).getEnfant()+"','"+prixttl+"');");
//					 } catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	    }
//		  
//		//System.out.println(prixttl);
//	
//			
//			  		}
	public int reservationPossible()
	{
		int nbrSimpleLibre=0;
		int nbrDoubleLibre=0;
		int nbrSuiteLibre=0;
		
		SimpleDateFormat Da= new SimpleDateFormat("yyyy-MM-dd");
		String select="SELECT \r\n"
				+ "    c.CategorieID,\r\n"
				+ "    c.Nom,\r\n"
				+ "    COUNT(*) AS NbDispo\r\n"
				+ "FROM \r\n"
				+ "    Categorie c \r\n"
				+ "    JOIN Detailreservation dr ON c.CategorieID = dr.CategorieID \r\n"
				+ "    JOIN Reservation r ON r.ReservationID = dr.ReservationID \r\n"
				+ "WHERE \r\n"
				+ "    (r.Date_arrivée > "+Da.format(this.dateChooser.getDate())+" OR r.Date_depart < "+Da.format(this.dateChooser_1.getDate())+")\r\n"
				+ "    OR r.ReservationID IS NULL\r\n"
				+ "GROUP BY \r\n"
				+ "    c.CategorieID, c.Nom\r\n"
				+ "HAVING \r\n"
				+ "    COUNT(*) >= 1;\r\n"
				+ "";
		ResultSet RS;
		try {
			RS = myCnx.getData(select);
			if(RS.next())
				nbrSimpleLibre=Integer.parseInt(RS.getString(3));
			if(RS.next())
				nbrDoubleLibre=Integer.parseInt(RS.getString(3));
			if(RS.next())
				nbrSuiteLibre=Integer.parseInt(RS.getString(3));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nbrSimpleLibre=this.nbrSimple-nbrSimpleLibre;
		nbrDoubleLibre=this.nbrDouble-nbrDoubleLibre;
		nbrSuiteLibre=this.nbrSuite-nbrSuiteLibre;
		for(Chambre ch:this.roomPanels)
		{
			if(ch.getComboBox().getSelectedItem().equals("Simple"))
			{
				nbrSimpleVoulu+=1;
			}
			if(ch.getComboBox().getSelectedItem().equals("Double"))
			{
				nbrDoubleVoulu+=1;
			}
			if(ch.getComboBox().getSelectedItem().equals("Suite"))
			{
				nbrSuiteVoulu+=1;
			}
		}
		if(nbrSimpleVoulu<=nbrSimpleLibre&&nbrDoubleVoulu<=nbrDoubleLibre&&nbrSuiteVoulu<=nbrSuiteLibre)
		{
			ResultSet resres;
			try {
				resres = myCnx.getData("SELECT Max(ReservationID)+1 FROM `reservation`;");
			 resres.next();
			 this.ReservationId=Integer.parseInt(resres.getString("Max(ReservationID)+1"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 1;
		}
		return 0;
	}

	public JDateChooser getDateChooser() {
		return dateChooser;
	}

	public void setDateChooser(JDateChooser dateChooser) {
		this.dateChooser = dateChooser;
	}

	public JDateChooser getDateChooser_1() {
		return dateChooser_1;
	}

	public void setDateChooser_1(JDateChooser dateChooser_1) {
		this.dateChooser_1 = dateChooser_1;
	}
	
}
