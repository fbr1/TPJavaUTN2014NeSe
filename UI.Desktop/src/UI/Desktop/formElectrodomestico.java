package UI.Desktop;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import Business.LavarropasLogic;
import Business.TelevisionLogic;
import UI.Desktop.formMain.TipoOperacion;
import Entities.ElectroDomestico;
import Entities.Lavarropas;
import Entities.Television;

import java.awt.FlowLayout;

import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class formElectrodomestico extends defaultDialog implements ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDescripcion;
	private JTextField txtPrecioBase;
	private JTextField txtPeso;
	private JTextField txtCarga;
	private JComboBox cbbColor;
	private JComboBox cbbConsumo;
	private TipoOperacion tipoOperacion;
	private ElectroDomestico electrodomestico;
	private JTextField txtResolucion;
	private JCheckBox ckbSintonizador;
	private JComboBox cbNuevoElectroDomestico;
	private JPanel cards;
	
	// get & set

	public TipoOperacion getTipoOperacion() {
		return tipoOperacion;
	}


	public void setTipoOperacion(TipoOperacion tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	
	public void setElectroDomestico(ElectroDomestico elecDom){
		this.electrodomestico = elecDom;
	}
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
			        //Create and set up the content pane.
			        formElectrodomestico form = new formElectrodomestico();
			        
			        //Display the window.
			        form.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	// Constructor Form de creacion
	public formElectrodomestico() {
		setResizable(false);
		setTitle("Nuevo Electrodomestico");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 312, 284);		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		addComponentToPane(contentPane);
	}
	// Constructor Form de modificacion
	public formElectrodomestico(ElectroDomestico elecDom) {
		this();
		setTipoOperacion(formMain.TipoOperacion.modificacion);
		setElectroDomestico(elecDom);
		CardLayout cardLayout = (CardLayout) cards.getLayout();
		if(electrodomestico instanceof Television){
			cardLayout.show(cards, "Television");
        	cbNuevoElectroDomestico.setSelectedIndex(1);
        	 	
		}else{
			cardLayout.show(cards, "Lavarropas");
        	cbNuevoElectroDomestico.setSelectedIndex(2);
		}
		cbNuevoElectroDomestico.setEnabled(false);   
		this.populateFields(elecDom);
	}

	public void addComponentToPane(Container pane) {
		
		contentPane.setLayout(null);
		
		JPanel electroDomesticoPanel = new JPanel();
		this.cargarElectroDomesticoPanel(electroDomesticoPanel);
		
		// Seleccion de tipo
		
        JLabel lblTipoElectrodomestico = new JLabel("Tipo Electrodomestico:");
        lblTipoElectrodomestico.setBounds(10, 14, 142, 14);
        electroDomesticoPanel.add(lblTipoElectrodomestico);
        
        String comboBoxItems[] = {"Elije un tipo","Television", "Lavarropas" };
        cbNuevoElectroDomestico = new JComboBox(comboBoxItems);
        cbNuevoElectroDomestico.setBounds(152, 11, 122, 20);
        cbNuevoElectroDomestico.setEditable(false);
        cbNuevoElectroDomestico.addItemListener(this);    	
        electroDomesticoPanel.add(cbNuevoElectroDomestico);   
		
		// CardLayout inner panels

        JPanel cardTelevision = new JPanel();
        cardTelevision.setLayout(null);
        this.cargarPanelTelevision(cardTelevision);
        
        JPanel cardLavarropas = new JPanel();
        cardLavarropas.setLayout(null);
        this.cargarPanelLavarropas(cardLavarropas);
        
        JPanel cardEmpty = new JPanel();
        cardEmpty.setLayout(null);
                
        // CardLayout
        
        cards = new JPanel(new CardLayout());
        cards.setBounds(10, 169, 284, 45);
        cards.add(cardEmpty, "Elije un tipo");   
        cards.add(cardTelevision, "Television");       
        cards.add(cardLavarropas, "Lavarropas");
        
        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(10, 215, 284, 29);    
        buttonPanel.setLayout(null);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		try {
					Aceptar((String)cbNuevoElectroDomestico.getSelectedItem());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        });
        btnAceptar.setBounds(50, 5, 89, 23);
        btnAceptar.setVerticalAlignment(SwingConstants.TOP);
        buttonPanel.add(btnAceptar);       
     
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Cancelar();
        	}
        });
        btnCancelar.setBounds(185, 5, 89, 23);
        btnCancelar.setVerticalAlignment(SwingConstants.TOP);
        buttonPanel.add(btnCancelar);
        
        // Agregar los paneles al contentPane
        pane.add(electroDomesticoPanel);
        pane.add(cards);
        pane.add(buttonPanel);
        

    }
	
	private JPanel cargarElectroDomesticoPanel(JPanel electroDomesticoPanel){
		
        electroDomesticoPanel.setBounds(10, 11, 284, 155);
        electroDomesticoPanel.setLayout(null);   
        
        JLabel lblDescripcion = new JLabel("Descripcion:");
        lblDescripcion.setBounds(10, 39, 101, 14);
        electroDomesticoPanel.add(lblDescripcion);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(152, 36, 122, 20);
		electroDomesticoPanel.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		JLabel lblPrecioBase = new JLabel("Precio base(USD):");
		lblPrecioBase.setBounds(10, 64, 132, 14);
		electroDomesticoPanel.add(lblPrecioBase);
		
		txtPrecioBase = new JTextField();
		txtPrecioBase.setBounds(152, 61, 58, 20);
		electroDomesticoPanel.add(txtPrecioBase);
		txtPrecioBase.setColumns(10);
		
		JLabel lblColor = new JLabel("Color:");
		lblColor.setBounds(10, 89, 44, 14);
		electroDomesticoPanel.add(lblColor);
		
		String[] colores = {"Blanco","Negro","Rojo","Azul","Gris"};
		cbbColor = new JComboBox(colores);
		cbbColor.setBounds(152, 86, 79, 20);
		cbbColor.setSelectedIndex(0);
		electroDomesticoPanel.add(cbbColor);
		
		JLabel lblConsumoEnergetico = new JLabel("Consumo Energetico:");
		lblConsumoEnergetico.setBounds(10, 114, 132, 14);
		electroDomesticoPanel.add(lblConsumoEnergetico);			
		
		String[] consumos = {"A","B","C","D","E","F"};
		cbbConsumo = new JComboBox(consumos);
		cbbConsumo.setBounds(152, 111, 44, 20);
		cbbConsumo.setSelectedIndex(5);
		electroDomesticoPanel.add(cbbConsumo);				
		
		JLabel lblPeso = new JLabel("Peso:");
		lblPeso.setBounds(10, 139, 58, 14);
		electroDomesticoPanel.add(lblPeso);
		
		txtPeso = new JTextField();
		txtPeso.setBounds(152, 136, 58, 20);
		electroDomesticoPanel.add(txtPeso);
		txtPeso.setColumns(10);
		
		return electroDomesticoPanel;
	}

	private void cargarPanelLavarropas(JPanel cardLavarropas) {
		
		JLabel lblCarga = new JLabel("Carga:");
		lblCarga.setBounds(10, 2, 46, 14);
		cardLavarropas.add(lblCarga);
		
		txtCarga = new JTextField();
		txtCarga.setBounds(152, 2, 56, 20);
		cardLavarropas.add(txtCarga);
		txtCarga.setColumns(10);
		
	}

	private void cargarPanelTelevision(JPanel cardTelevision) {
		
		JLabel lblResolucion = new JLabel("Resolucion:");
		lblResolucion.setBounds(10, 2, 92, 14);
        cardTelevision.add(lblResolucion);
        
		JLabel lblSintonizador = new JLabel("Tiene sintonizador? :");
		lblSintonizador.setBounds(10, 25, 120, 14);
		cardTelevision.add(lblSintonizador);
		
		txtResolucion = new JTextField();
		txtResolucion.setBounds(152, 2, 56, 20);
		cardTelevision.add(txtResolucion);
		txtResolucion.setColumns(10);
		
		ckbSintonizador = new JCheckBox("");
		ckbSintonizador.setBounds(148, 22, 97, 23);
		cardTelevision.add(ckbSintonizador);
	}
	
	private void Aceptar(String tipo) throws Exception{		
		if(tipo.equalsIgnoreCase("Lavarropas")){
				LavarropasLogic lavarropasLogic = new LavarropasLogic();
				Lavarropas lavarropa = new Lavarropas(txtDescripcion.getText(), Double.parseDouble(txtPrecioBase.getText()), Double.parseDouble(txtPeso.getText()), 
														 Character.valueOf(cbbConsumo.getSelectedItem().toString().charAt(0)),
														 (String)cbbColor.getSelectedItem(), Double.parseDouble(txtCarga.getText()));
				if (this.getTipoOperacion() == formMain.TipoOperacion.alta){
					lavarropa.setState(Entities.Entity.States.New);
				}else{
					lavarropa.setState(Entities.Entity.States.Modified);
				}
				lavarropasLogic.save(lavarropa);	
				this.setResultado(resultado.Completado);
				this.dispose();
		
		} else if(tipo.equalsIgnoreCase("Television")){
				
				TelevisionLogic televisionLogic = new TelevisionLogic();
				Television television = new Television(txtDescripcion.getText(), Double.parseDouble(txtPrecioBase.getText()), Double.parseDouble(txtPeso.getText()), 
														Character.valueOf(cbbConsumo.getSelectedItem().toString().charAt(0)),
														(String)cbbColor.getSelectedItem(), Double.parseDouble(txtResolucion.getText()),
														ckbSintonizador.isSelected());
				
				if (this.getTipoOperacion() == formMain.TipoOperacion.alta){
					television.setState(Entities.Entity.States.New);
				}else{
					television.setState(Entities.Entity.States.Modified);
				}
				televisionLogic.save(television);	
				this.setResultado(resultado.Completado);
				this.dispose();
		}else{
			this.setResultado(resultado.Error);
			this.dispose();
			throw new Exception("Elije un tipo de Electrodomestico");				
		}
		
	}	

	
	private void Cancelar(){		
		this.setResultado(resultado.Cancelado);
		this.dispose();
	}
	
	private void populateFields(ElectroDomestico elecDom) {
		TextPrompt txtpDescripcion = new TextPrompt(elecDom.getDescripcion(), this.txtDescripcion);
		txtpDescripcion.setForeground(Color.GRAY);
		TextPrompt txtpPrecioBase = new TextPrompt(String.valueOf(elecDom.getPrecio_base()), this.txtPrecioBase);
		txtpPrecioBase.setForeground(Color.GRAY);
		TextPrompt txtpPeso = new TextPrompt(String.valueOf(elecDom.getPeso()), this.txtPeso);
		txtpPeso.setForeground(Color.GRAY);
		 
		//this.cbbColor.setSelectedIndex(anIndex);  Falta implementar los combobox
		if ( elecDom instanceof Television){
			this.ckbSintonizador.setSelected(((Television)elecDom).tieneSinTDT());
			TextPrompt txtpResolucion = new TextPrompt(String.valueOf(((Television)elecDom).getResolucion()), this.txtResolucion);
			txtpResolucion.setForeground(Color.GRAY);
		} else{
			TextPrompt txtpCarga = new TextPrompt(String.valueOf(((Lavarropas)elecDom).getCarga()), this.txtCarga);
			txtpCarga.setForeground(Color.GRAY);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)e.getItem());
		
	}

}
