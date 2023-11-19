package MiniProjet;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.border.EtchedBorder;


public class Chambre { 
	JPanel panel = new JPanel();
    JButton btnDelete = new JButton("Supprimer");
    JComboBox comboBox = new JComboBox();
    JSpinner spinner = new JSpinner();
    JSpinner spinner_1 = new JSpinner();
    List<JSpinner> spinners = new ArrayList<>();

	public JButton getBtnDelete() {
		return btnDelete;
	}
	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}
	Chambre(){
	 
     
     panel.setBackground(new Color(192, 192, 192));
     panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
     panel.setBounds(49, 130 , 324, 174);
     panel.setLayout(null);
     
     JLabel lblNewLabel_2 = new JLabel("Categorie du chambre :");
     lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
     lblNewLabel_2.setBounds(10, 33, 162, 14);
     panel.add(lblNewLabel_2);

     JLabel lblNewLabel_3 = new JLabel("Nombre d'adulte          :");
     lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
     lblNewLabel_3.setBounds(10, 73, 181, 14);
     panel.add(lblNewLabel_3);

     JLabel lblNewLabel_4 = new JLabel("Nombre d'enfant         :");
     lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
     lblNewLabel_4.setBounds(10, 112, 162, 14);
     panel.add(lblNewLabel_4);

     comboBox.setModel(new DefaultComboBoxModel(new String[] {"Simple", "Double", "Suite"}));
     comboBox.setBounds(191, 31, 99, 22);
     panel.add(comboBox);

     
     spinner.setBounds(191, 72, 47, 20);
     spinner.setModel(new SpinnerNumberModel(1, 1, 2, 1));
     spinner.setValue(1);
     panel.add(spinner);
     
		spinner_1.setModel(new SpinnerNumberModel(0, 0, 2, 1));
		spinner_1.setBounds(191, 111, 47, 20);


    
     
	btnDelete.setBounds(210, 140, 100, 23);
	panel.add(btnDelete);
			            
     List<JLabel> labels = new ArrayList<>();
		

		spinner_1.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		    	//Object source = e.getSource();
		    	//if(source.equals(spinner_1)) {
		    		int enfantCount = (int) spinner_1.getValue();
 		        if (enfantCount < labels.size()) {
 		            for (int i = enfantCount; i < labels.size(); i++) {
 		                panel.remove(labels.get(i));
 		                panel.remove(spinners.get(i));
 		            }
 		            labels.subList(enfantCount, labels.size()).clear();
 		            spinners.subList(enfantCount, spinners.size()).clear();
 		        } else {
 		            for (int i = labels.size() + 1; i <= enfantCount; i++) {
 		                JLabel label = new JLabel("enfant " + i);
 		                label.setBounds(33+(i-1)*60, 129, 49, 14);
 		                panel.add(label);
 		                labels.add(label);

 		                JSpinner enfantSpinner = new JSpinner();
 		                enfantSpinner.setModel(new SpinnerNumberModel(0, 0, 12, 1));
 		                enfantSpinner.setBounds(33+(i-1)*60, 143, 40, 20);
 		                panel.add(enfantSpinner);
 		                spinners.add(enfantSpinner);
 		            }
 		        }
 		        panel.revalidate();
 		        panel.repaint();
 		    }
		   // }
		    
		        
		});
		panel.add(spinner_1);
     panel.add(btnDelete); 
     
 }
	public JPanel getPanel() {
		return panel;
	}
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
	public JComboBox getComboBox() {
		return comboBox;
	}
	public JSpinner getSpinner() {
		return spinner;
	}
	
	public JSpinner getSpinner_1() {
		return spinner_1;
	}
	public List<JSpinner> getSpinners() {
		return spinners;
	}
	public void setSpinners(List<JSpinner> spinners) {
		this.spinners = spinners;
	}
	
	
 
}



