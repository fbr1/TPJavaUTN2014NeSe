package UI.Desktop;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Business.ElectroDomesticoLogic;
import Business.LavarropasLogic;
import Business.TelevisionLogic;
import Entities.ElectroDomestico;
import Entities.Lavarropas;
import Entities.Television;

public class formMain {
	
	public enum TipoOperacion{alta,modificacion};
	
	private JFrame frame;
	private TableModel model;
	private JTable table;

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
		
		// Create frame
		
		frame = new JFrame();
		frame.setBounds(100, 100, 691, 410);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setOpacity(1);
		
		// Create Model
		
		model = new TableModel(generateTableInput());
		
		// Create Table
		
		table = new JTable(model);
		table.setCellSelectionEnabled(true);
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(false);
		table.setSelectionMode( javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false) ;
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
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
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
	}
	
	private ArrayList<Object[]> generateTableInput(){
		
		ElectroDomesticoLogic elecDom = new ElectroDomesticoLogic();		
		
		ArrayList<Object[]> data = new ArrayList<Object[]>();
		for(ElectroDomestico la : elecDom.getTodos()){
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
	
	private void Eliminar(){
		try {
			ElectroDomesticoLogic electroDomesticoNegocio = new ElectroDomesticoLogic();
			if(electroDomesticoNegocio.getOne((int)table.getValueAt(table.getSelectedRow(), 1)) instanceof Television){
				new TelevisionLogic().delete((int)table.getValueAt(table.getSelectedRow(), 1));
			}else{
				new LavarropasLogic().delete((int)table.getValueAt(table.getSelectedRow(), 1));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UpdateTable();
	}
	
	private void UpdateTable(){
	     table.setModel(new TableModel(this.generateTableInput()));
	}

}
