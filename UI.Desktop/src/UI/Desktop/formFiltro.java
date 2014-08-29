package UI.Desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;

import Business.ElectroDomesticoLogic;
import Entities.ElectroDomestico;

public class formFiltro extends defaultDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtPrecioMin;
	private JTextField txtPrecioMax;
	private JCheckBox chckbxPrecio;
	private JCheckBox chckbxConsumo;
	private JLabel lblPrecioMinimo;
	private JLabel lblPrecioMaximo;
	private JLabel lblConsumoEnergetico;
	private JComboBox<String> cbbConsumoEnergetico;
	private JButton btnAplicar;
	private JButton btnAceptar;
	private ArrayList<ElectroDomestico> electroDomesticos;
	
	public formFiltro(){		
		setModal(true);
		setResizable(false);
		setTitle("Filtrado de datos");
	    Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
	    int windowWidth = 398;
	    int windowHeight = 128;
	    setBounds(center.x - windowWidth / 2, center.y - windowHeight / 2, windowWidth,
	        windowHeight);	

		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lblPrecioMinimo = new JLabel("Precio minimo:");
		lblPrecioMinimo.setBounds(42, 17, 102, 14);
		panel.add(lblPrecioMinimo);
		
		lblPrecioMaximo = new JLabel("Precio Maximo:");
		lblPrecioMaximo.setBounds(207, 17, 102, 14);
		panel.add(lblPrecioMaximo);
		
		lblConsumoEnergetico = new JLabel("Consumo Energetico:");
		lblConsumoEnergetico.setBounds(42, 46, 126, 14);
		panel.add(lblConsumoEnergetico);
		
		txtPrecioMin = new JTextField();
		txtPrecioMin.setBounds(130, 14, 71, 20);
		panel.add(txtPrecioMin);
		txtPrecioMin.setColumns(10);
		
		txtPrecioMax = new JTextField();
		txtPrecioMax.setBounds(298, 14, 71, 20);
		panel.add(txtPrecioMax);
		txtPrecioMax.setColumns(10);
		
		String[] consumos = {"A","B","C","D","E","F"};
		cbbConsumoEnergetico = new JComboBox<String>(consumos);
		cbbConsumoEnergetico.setBounds(166, 43, 49, 20);
		cbbConsumoEnergetico.setSelectedIndex(0);
		panel.add(cbbConsumoEnergetico);

		
		chckbxPrecio = new JCheckBox("");
		chckbxPrecio.setBounds(15, 14, 21, 21);
		chckbxPrecio.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent actionEvent) {
                 if(chckbxPrecio.isSelected()){
                	 setPrecioState(true);
                 }else{
                	 setPrecioState(false);
                 }
              }
		});
		panel.add(chckbxPrecio);
		
		chckbxConsumo = new JCheckBox("");
		chckbxConsumo.setBounds(15, 42, 21, 21);
		chckbxConsumo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
               if(chckbxConsumo.isSelected()){
            	   setConsumoState(true);
               }else{
            	   setConsumoState(false);
               }
            }
		});
		panel.add(chckbxConsumo);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Aceptar();
			}
		});
		btnAceptar.setBounds(116, 72, 89, 23);
		panel.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cancelar();
			}
		});
		btnCancelar.setBounds(207, 72, 89, 23);
		panel.add(btnCancelar);
		
		btnAplicar = new JButton("Aplicar");
		btnAplicar.setBounds(298, 72, 89, 23);
		panel.add(btnAplicar);	
		
		// Set fields disabled by default
		
		setPrecioState(false);
		setConsumoState(false);
		
	}
	
	private void setPrecioState(boolean bool){
	   	lblPrecioMinimo.setEnabled(bool);
	   	lblPrecioMaximo.setEnabled(bool);               
	   	txtPrecioMax.setEnabled(bool);
	   	txtPrecioMin.setEnabled(bool);
	}
	private void setConsumoState(boolean bool){
 	   lblConsumoEnergetico.setEnabled(bool);
 	   cbbConsumoEnergetico.setEnabled(bool);
	}
	
	private void Aceptar(){
		if(this.getResultado() != resultado.Error){
			this.dispose();
		}
	}
	private void Cancelar(){
		this.setResultado(resultado.Cancelado);
		this.dispose();
	}
	public ArrayList<ElectroDomestico> getFilteredCollection(){
		return electroDomesticos;
	}
	public void Aplicar(){
		ElectroDomesticoLogic electroDomesticoNegocio = new ElectroDomesticoLogic();
		electroDomesticos = null;
		this.setResultado(resultado.Completado);
		if(validateinput()){
			if(this.chckbxPrecio.isSelected()){
				Double precio_min = Double.parseDouble(txtPrecioMin.getText());
				Double precio_max = Double.parseDouble(txtPrecioMax.getText());
				if(this.chckbxConsumo.isSelected()){						
					electroDomesticos = electroDomesticoNegocio.getTodos(precio_min,precio_max, this.cbbConsumoEnergetico.getSelectedItem().toString().charAt(0));	
				}else{
					electroDomesticos = electroDomesticoNegocio.getTodos(precio_min,precio_max);
				}				
				
			}else if(this.chckbxConsumo.isSelected()){
				electroDomesticos = electroDomesticoNegocio.getTodos(this.cbbConsumoEnergetico.getSelectedItem().toString().charAt(0));	
			}
		}else{ this.setResultado(resultado.Error);}
	}

	private boolean validateinput(){
		if(this.chckbxPrecio.isSelected() || this.chckbxConsumo.isSelected()){
			if(this.chckbxPrecio.isSelected()){
				if(txtPrecioMin.getText().isEmpty() || txtPrecioMax.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Llene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
					return false;
				}else{
				    try
				    {
				       Double.parseDouble(txtPrecioMin.getText());
				       Double.parseDouble(txtPrecioMax.getText());
				       return true;
				    }
				    catch( NumberFormatException e )
				    {
				  	   JOptionPane.showMessageDialog(null, "Ingrese numeros reales", "Error", JOptionPane.ERROR_MESSAGE); 
				       return false;
				    }
				}
			}else { return true; }
		}else{
			JOptionPane.showMessageDialog(null, "Seleccione un tipo de filtrado", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	public void addApplyListener(ActionListener listener) {
		btnAplicar.addActionListener(listener);
		btnAceptar.addActionListener(listener);
	}

}
