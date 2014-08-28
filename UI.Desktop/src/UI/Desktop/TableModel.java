package UI.Desktop;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;


class TableModel extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Object[]> data;		
	String[] columnNames = {"","ID","Descripcion", "Color",
			"Consumo Energetico", "Peso",
			"Precio Base", "Carga", "Resolucion", "TDT", "Precio Final"};
	private int rowSelected;
	
	public TableModel(ArrayList<Object[]> data){
		this.data = data;
	}
	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return data.get(rowIndex)[columnIndex];
	}
	
    public void setValueAt(Object value, int row, int col) {
        data.get(row)[col] = value;
        fireTableCellUpdated(row, col);
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
    
    public void add(Object[] obj) {
        data.add(obj);
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
            data.get(i)[0] = false;
        }
        fireTableRowsUpdated(0, getRowCount());
    }

}
