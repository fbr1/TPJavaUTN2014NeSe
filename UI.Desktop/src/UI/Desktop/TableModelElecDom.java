package UI.Desktop;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import Business.ElectroDomesticoLogic;
import Entities.ElectroDomestico;
import Entities.Lavarropas;
import Entities.Television;


class TableModelElecDom extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<ElectroDomestico> data;		
	ArrayList<Boolean> checklist ;
	String[] columnNames = {"","ID","Descripcion", "Color",
			"Consumo Energetico", "Peso",
			"Precio Base", "Carga", "Resolucion", "TDT", "Precio Final"};
	private int rowSelected;
	

	public TableModelElecDom(ArrayList<ElectroDomestico> data){
		this.data = data;
		checklist=new ArrayList<Boolean>(); 
		for(int i = 0;i<=this.getRowCount();i++){
			checklist.add(Boolean.FALSE); 			
		}
	}
	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Object value=null;
		switch(columnIndex){
			case 0:{
				value = checklist.get(rowIndex);	
				break;
			}
			case 1: {
				value = String.valueOf(data.get(rowIndex).getId());
				break;
			}
			case 2:{
				value = data.get(rowIndex).getDescripcion();
				break;
			}
			case 3:{
				value = data.get(rowIndex).getColor().getNombre();
				break;
			}
			case 4:{
				value = String.valueOf(data.get(rowIndex).getConsumoEnergetico().getNombre());
				break;
			}
			case 5:{
				value = String.valueOf(data.get(rowIndex).getPeso());
				break;
			}
			case 6:{
				value = String.valueOf(data.get(rowIndex).getPrecio_base());
				break;
			}
			case 10:{
				try {
					value = String.valueOf(new ElectroDomesticoLogic().precioFinal(data.get(rowIndex)));
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
		}		
		if(data.get(rowIndex) instanceof Lavarropas){
			if(columnIndex == 7){
				value = String.valueOf(((Lavarropas)data.get(rowIndex)).getCarga());
			}
		}else if(data.get(rowIndex) instanceof Television){
			if(columnIndex == 8){
				value = String.valueOf(((Television)data.get(rowIndex)).getResolucion());
			}else if (columnIndex == 9){
				value = ((Television)data.get(rowIndex)).tieneSinTDT();
			}
		}else{
			JOptionPane.showMessageDialog(null, "Error al recuperar datos, no puede haber un electrodomestico sin tipo", "Error", JOptionPane.ERROR_MESSAGE);
		}		
		return value;
	}
	public ElectroDomestico getElecDomAt(int rowIndex){
		return data.get(rowIndex);
	}
    public void setValueAt(Object value, int rowIndex, int colIndex) {
	    if(colIndex == 0){
	    	checklist.set(rowIndex,(boolean)value);
	    }	    
	    fireTableCellUpdated(rowIndex,colIndex);
    }
	public String getColumnName(int index){
		return columnNames[index];
	}	

	public Class<?> getColumnClass(int columnIndex) {
	    if (columnIndex == 9)
	        return Boolean.class;
	    if ( columnIndex == 0)
	    	return Boolean.class;
	    return super.getColumnClass(columnIndex);
	}
	
    public boolean isCellEditable(int row, int col) {
        if (col == 0) {
            return true;
        } else {
            return false;
        }
    }    

    public int getRowSelected(){
    	return this.rowSelected;
    }
    public void setRowSelected(int row){
    	this.rowSelected = row;
    }
    
    public void add(ElectroDomestico electroDomestico) {
        data.add(electroDomestico);
        fireTableRowsInserted(getRowCount(), getRowCount());
    }

    public void remove(Object[] obj) {
        if (data.contains(obj)) {
            int index = data.indexOf(obj);
            data.remove(obj);
            this.fireTableRowsDeleted(index, index);
        }
    }    
    public void clearChecks() {
        for (int i = 0; i < getRowCount(); i++) {
            checklist.set(i, Boolean.FALSE);
        }
        fireTableRowsUpdated(0, getRowCount());
    }

}
