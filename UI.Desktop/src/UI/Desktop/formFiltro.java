package UI.Desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class formFiltro extends defaultJFrame {
	
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
	private JComboBox cbbConsumoEnergetico;
	
	public formFiltro(formMain formMain){		
		setResizable(false);
		setTitle("Filtrado de datos");
		setBounds(100, 100, 398, 128);			

		
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
		
		String[] consumos = {"Elija un valor","A","B","C","D","E","F"};
		cbbConsumoEnergetico = new JComboBox(consumos);
		cbbConsumoEnergetico.setBounds(166, 43, 131, 20);
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
		
		JButton btnAceptar = new JButton("Aceptar");
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
		
		JButton btnAplicar = new JButton("Aplicar");
		btnAplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				Aplicar();
			}
		});
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
		
	}
	private void Cancelar(){
		this.setResultado(resultado.Cancelado);
		this.dispose();
	}
	private void Aplicar(){
		
	}

}
