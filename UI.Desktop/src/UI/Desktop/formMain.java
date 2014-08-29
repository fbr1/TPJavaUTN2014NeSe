package UI.Desktop;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import UI.Desktop.defaultDialog.resultado;
import Business.ElectroDomesticoLogic;
import Business.LavarropasLogic;
import Business.TelevisionLogic;
import Entities.ElectroDomestico;
import Entities.Lavarropas;
import Entities.Television;

public class formMain{
	
	public enum TipoOperacion{alta,modificacion};
	
	private JFrame frame;
	private TableModel model;
	private JTable table;
	private ElectroDomesticoLogic electroDomesticos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					formMain window = new formMain();
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
	public formMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		electroDomesticos = new ElectroDomesticoLogic();
		
		// Create frame
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setOpacity(1);
		
		// Set position & size
		
	    Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
	    int windowWidth = 691;
	    int windowHeight = 410;
	    frame.setBounds(center.x - windowWidth / 2, center.y - windowHeight / 2, 836,
	        415);
		
		// Create Model
		
		model = new TableModel(generateTableInput());
		
		// Create Table
		
		table = new JTable(model);
		table.setCellSelectionEnabled(true);
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(false);
		table.setSelectionMode( javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false) ;
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e) {									
		    	if (e.getValueIsAdjusting()) {
		    		if( table.getSelectedRow() != -1 && e.getFirstIndex() < table.getRowCount() && e.getLastIndex() < table.getRowCount()){
		    			// Setea todo los checkbox a falso menos el seleccionado
				    	int index = e.getFirstIndex();
				    	TableModel model = (TableModel) table.getModel();
				    	model.setValueAt(Boolean.FALSE, index, 0);
				    	index = e.getLastIndex();
				    	model.setValueAt(Boolean.FALSE, index, 0);		
				    	// Si se selecciona una fila pero no en el checkbox, setea el checkbox a true
				    	model.setValueAt(Boolean.TRUE, table.getSelectedRow() ,0);	    	
			    	}
			    }		    	
			}
	    });
		// Add table to frame
		
		frame.getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
		
		
		// Other Controls
	
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmNuevo = new JMenuItem("Nuevo");
		mntmNuevo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		Nuevo();
        	}
        });
		mnArchivo.add(mntmNuevo);
		
		JMenuItem mntmModificar = new JMenuItem("Modificar");
		mntmModificar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		Modificar();
        	}
        });
		mnArchivo.add(mntmModificar);
		
		JMenuItem mntmEliminar = new JMenuItem("Eliminar");
		mntmEliminar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		Eliminar();
        	}
        });
		mnArchivo.add(mntmEliminar);
		
		JMenuItem mntmFiltrar = new JMenuItem("FIltrar");
		mntmFiltrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		Filtrar();
        	}
        });
		mnArchivo.add(mntmFiltrar);
		
		JMenuItem mntmListar = new JMenuItem("Listar");		
		mntmListar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		UpdateTable();
        	}
        });
		mnArchivo.add(mntmListar);
		
		
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de");
		mnAyuda.add(mntmAcercaDe);
	}
	
	private ArrayList<Object[]> generateTableInput(ArrayList<ElectroDomestico> elecDom){	
		
		/*
		 * El Alternativo a esta linea en java 7 es Collections.sort(elecDom, new CustomComparator());
		 */
		Collections.sort(elecDom, (e1, e2) -> e1.getDescripcion().compareToIgnoreCase(e2.getDescripcion()));
		
		ArrayList<Object[]> data = new ArrayList<Object[]>();
		for(ElectroDomestico la : elecDom){
			if(la instanceof Lavarropas){
				Object[] obj = convertLavarropasToObject(la);
				data.add(obj);
			}else if (la instanceof Television){
				Object[] obj = convertTelevisionToObject(la);
				data.add(obj);
			}
		}
		
		return data;
	}
	
	private ArrayList<Object[]> generateTableInput(){			
		return generateTableInput(electroDomesticos.getTodos());		
	}
	
	private Object[] convertTelevisionToObject(ElectroDomestico tes){
		
		Television te = (Television)tes;
		
		Object[] obj = { false, te.getId(), te.getDescripcion(), te.getColor(),
				 te.getConsumoEnergetico(), te.getPeso(),
				 te.getPrecio_base(), null, te.getResolucion(), te.tieneSinTDT(), new TelevisionLogic().precioFinal(te.getId())};
		return obj;
	}
	
	private Object[] convertLavarropasToObject(ElectroDomestico las){
		
		Lavarropas la = (Lavarropas)las;
		
		Object[] obj = { false, la.getId(), la.getDescripcion(), la.getColor(),
				 la.getConsumoEnergetico(), la.getPeso(),
				 la.getPrecio_base(), la.getCarga(), null, null,new LavarropasLogic().precioFinal(la.getId())};
		return obj;
	}
	
	private void Nuevo(){
		
		formElectrodomestico form = new formElectrodomestico();
		
		form.setTipoOperacion(formMain.TipoOperacion.alta);
		form.setVisible(true);
		form.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent ev) {            
        		if(form.getResultado() == formElectrodomestico.resultado.Completado){
        			UpdateTable();
        		}
        		form.dispose();
            }
        });
	}
	private void Modificar(){
		if(table.getSelectedRow()==-1){
			JOptionPane.showMessageDialog(null, "Seleccione un electrodomestico a modificar", "Error", JOptionPane.ERROR_MESSAGE);
		}else{
			formElectrodomestico form = new formElectrodomestico(new ElectroDomesticoLogic().getOne((int)table.getValueAt(table.getSelectedRow(), 1)));
			form.setVisible(true);
			form.addWindowListener(new WindowAdapter() {
	            public void windowClosed(WindowEvent ev) {                
	                
	        		if(form.getResultado() == formElectrodomestico.resultado.Completado){
	        			UpdateTable();	        			
	        		}
	        		form.dispose();
	            }
	        });
		}
	}
	
	private void Eliminar(){
		if(table.getSelectedRow()==-1){
			JOptionPane.showMessageDialog(null, "Seleccione un electrodomestico a eliminar", "Error", JOptionPane.ERROR_MESSAGE);
		}else{
			try {
				ElectroDomestico elecDom = new ElectroDomesticoLogic().getOne((int)table.getValueAt(table.getSelectedRow(), 1));
				int dialogResult = JOptionPane.showConfirmDialog (null, "Realmente desea borrar el electrodomestico?","Warning", JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION){
					if(elecDom instanceof Television){
						new TelevisionLogic().delete((int)table.getValueAt(table.getSelectedRow(), 1));
					}else{
						new LavarropasLogic().delete((int)table.getValueAt(table.getSelectedRow(), 1));
					}
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al borrar el electrodomestico", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		TableModel model = (TableModel) table.getModel();
		model.clearChecks();
		UpdateTable();
	}
	
	private void Filtrar(){
		formFiltro formfiltro = new formFiltro();
		formfiltro.addApplyListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				formfiltro.Aplicar();
				if(formfiltro.getResultado() != resultado.Error){					
					UpdateTable(formfiltro.getFilteredCollection());
				}
			}			
		});
		formfiltro.setVisible(true);
	}
	
	private void UpdateTable(ArrayList<ElectroDomestico> elecDoms){
	     table.setModel(new TableModel(this.generateTableInput(elecDoms)));
	}
	
	private void UpdateTable(){
		this.UpdateTable(electroDomesticos.getTodos());
	}
	
	
	/* Sacar el Comentario de esta porcion de codigo si se quiere usar java 7
	 * 
	 *
	/*class CustomComparator implements Comparator {
		  public int compare(ElectroDomestico o1, ElectroDomestico o2) {
		    return o1.getDescripcion().compareTo(o2.getDescripcion());
		}
	}
	*/


}
