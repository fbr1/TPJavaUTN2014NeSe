package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Entities.Color;
import Entities.ConsumoEnergetico;
import Entities.Television;
import Entities.Entity.States;

public class TelevisionAdapter{

	public ArrayList<Television> getAll() throws Exception
    {
        ArrayList<Television> televisiones = new ArrayList<Television>();
        Statement statement=null;
        ResultSet rs=null;
        try
        {
        	Connection conn = DataConnectionManager.getInstancia().getConn();
        	statement = conn.createStatement();
        	rs = statement.executeQuery("SELECT elec.id_electrodomestico, elec.descripcion, col.nombre_color,con.nombre, con.precio, elec.precio_base, "
        							  + " elec.peso, elec.resolucion, elec.TDT, elec.id_color, elec.id_consumo "
        							  + "FROM electrodomesticos elec "
        							  + "INNER JOIN colores col on col.id_color = elec.id_color "
        							  + "INNER JOIN consumosenergeticos con on con.id_consumos = elec.id_consumo "
        							  + "WHERE elec.carga IS NULL");       	


            while (rs.next())
            {
            	Television television = new Television();
            	television.setId(rs.getInt("elec.id_electrodomestico"));
            	television.setDescripcion(rs.getString("elec.descripcion"));
            	television.setColor(new Color(rs.getInt("elec.id_color"),rs.getString("col.nombre_color")));
            	television.setConsumoEnergetico(new ConsumoEnergetico(rs.getInt("elec.id_consumo"),rs.getString("con.nombre").charAt(0),rs.getDouble("con.precio")));
            	television.setPrecio_base(rs.getDouble("elec.precio_base"));
            	television.setPeso(rs.getDouble("elec.peso"));
            	television.setResolucion(rs.getDouble("elec.resolucion"));
            	television.setSinTDT(rs.getBoolean("elec.TDT"));            	
            	televisiones.add(television);
            }
        }
        catch (Exception Ex)
        {                
            throw new Exception("Error al recuperar datos de las televisiones", Ex);
        }
        finally
        {
        	try{
        		if(rs!=null){rs.close();}
        		if(statement!=null && !statement.isClosed()){statement.close();}
        		DataConnectionManager.getInstancia().CloseConn();
        	}
        	catch (SQLException sqle){
        		sqle.printStackTrace();
        	}
        }
        return televisiones;
    }

    public Television getOne(int ID) throws Exception
    {    	
        Television television = null;
        PreparedStatement statement=null;
        ResultSet rs=null;
        
        try
        {
        	Connection conn = DataConnectionManager.getInstancia().getConn();
        	statement = conn.prepareStatement("SELECT elec.id_electrodomestico, elec.descripcion, col.nombre_color, con.nombre,con.precio, elec.precio_base, "
						        		  	  + "elec.peso, elec.resolucion, elec.TDT, elec.id_color, elec.id_consumo "
											  + "FROM electrodomesticos elec "
											  + "INNER JOIN colores col on col.id_color = elec.id_color "
											  + "INNER JOIN consumosenergeticos con on con.id_consumos = elec.id_consumo "
											  + "WHERE elec.carga IS NULL AND elec.id_electrodomestico = ?");  
        	statement.setInt(1, ID);
        	rs = statement.executeQuery();     	
        	
        	if(rs.next()){
        		television = new Television();
            	television.setId(rs.getInt("elec.id_electrodomestico"));
            	television.setDescripcion(rs.getString("elec.descripcion"));
            	television.setColor(new Color(rs.getInt("elec.id_color"),rs.getString("col.nombre_color")));
            	television.setConsumoEnergetico(new ConsumoEnergetico(rs.getInt("elec.id_consumo"),rs.getString("con.nombre").charAt(0),rs.getDouble("con.precio")));
            	television.setPrecio_base(rs.getDouble("precio_base"));
            	television.setPeso(rs.getDouble("elec.peso"));
            	television.setResolucion(rs.getDouble("elec.resolucion"));
            	television.setSinTDT(rs.getBoolean("elec.TDT"));
        	}
        }
        catch (Exception Ex)
        {                
            throw new Exception("Error al recuperar la television por ID", Ex);
        }
        finally
        {
        	try{
        		if(rs!=null){rs.close();}
        		if(statement!=null && !statement.isClosed()){statement.close();}
        		DataConnectionManager.getInstancia().CloseConn();
        	}
        	catch (SQLException sqle){
        		sqle.printStackTrace();
        	}
        }
        return television;
    } 

    public void delete(int ID) throws Exception
    {
        PreparedStatement statement=null;
        
        try
        {
        	Connection conn = DataConnectionManager.getInstancia().getConn();
        	statement = conn.prepareStatement("DELETE FROM electrodomesticos WHERE id_electrodomestico=?");
        	statement.setInt(1, ID);
        	statement.executeUpdate();  	
        }
        catch (Exception Ex)
        {           
        	System.out.println(Ex.getMessage());
            throw new Exception("Error al eliminar la television", Ex);            
        }
        finally
        {
        	try{
        		if(statement!=null && !statement.isClosed()){statement.close();}
        		DataConnectionManager.getInstancia().CloseConn();
        	}
        	catch (SQLException sqle){
        		sqle.printStackTrace();
        	}
        }  
    }

    public void save(Television television) throws Exception
    {
        if (television.getState() == States.New)
        {
        	this.insert(television);
        }
        else if (television.getState() == States.Deleted)
        {
            this.delete(television.getId());
        }
        else if (television.getState() == States.Modified)
        {
        	this.update(television);
        }
        television.setState(States.Unmodified);         
    }
    
    public void update(Television television) throws Exception{
        PreparedStatement statement=null;
        
        try
        {
        	Connection conn = DataConnectionManager.getInstancia().getConn();
        	statement = conn.prepareStatement("UPDATE electrodomesticos SET descripcion=?, id_color=?, "
        									+ "id_consumo = ?, precio_base=?,peso=?,resolucion=?,TDT=? WHERE id_electrodomestico=?");
        	statement.setString(1, television.getDescripcion());
			statement.setInt(2, television.getColor().getId());
			statement.setInt(3, television.getConsumoEnergetico().getId());
        	statement.setDouble(4, television.getPrecio_base());
        	statement.setDouble(5, television.getPeso());
        	statement.setDouble(6, television.getResolucion());
        	statement.setBoolean(7, television.tieneSinTDT());
        	statement.setInt(8, television.getId());        	
        	statement.executeUpdate();  	
        }
        catch (Exception Ex)
        {                
            throw new Exception("Error al modificar la television", Ex);
        }
        finally
        {
        	try{
        		if(statement!=null && !statement.isClosed()){statement.close();}
        		DataConnectionManager.getInstancia().CloseConn();
        	}
        	catch (SQLException sqle){
        		sqle.printStackTrace();
        	}
        }      
    }
    
    public void insert(Television television) throws Exception{  	
        PreparedStatement statement=null;
        
        try
        {
        	Connection conn = DataConnectionManager.getInstancia().getConn();
        	statement = conn.prepareStatement("INSERT INTO electrodomesticos(descripcion,id_color,id_consumo,precio_base,peso,resolucion,TDT) "
        									+ "values(?,?, ?, ?,?,?,?) ", Statement.RETURN_GENERATED_KEYS);
        	statement.setString(1, television.getDescripcion());
			statement.setInt(2, television.getColor().getId());
			statement.setInt(3, television.getConsumoEnergetico().getId());
        	statement.setDouble(4, television.getPrecio_base());
        	statement.setDouble(5, television.getPeso());
        	statement.setDouble(6, television.getResolucion());
        	statement.setBoolean(7, television.tieneSinTDT());
        	
        	statement.executeUpdate();  	
        	ResultSet rs = statement.getGeneratedKeys();
        	if(rs.next()){
        		television.setId(rs.getInt(1));
        	}
        }
        catch (Exception Ex)
        {                
            throw new Exception("Error al crear la television", Ex);
        }
        finally
        {
        	try{
        		if(statement!=null && !statement.isClosed()){statement.close();}
        		DataConnectionManager.getInstancia().CloseConn();
        	}
        	catch (SQLException sqle){
        		sqle.printStackTrace();
        	}
        }  
    }
}
