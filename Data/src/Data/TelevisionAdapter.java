package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Entities.Television;
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
        	rs = statement.executeQuery("SELECT elec.id_electrodomestico, elec.descripcion, col.nombre_color, elec.precio_base, elec.peso, elec.resolucion, elec.TDT "
        							  + "FROM electrodomesticos elec"
        							  + "INNER JOIN colores col on col.id_color = elec.id_color "
        							  + "WHERE elec.carga is null");       	


            while (rs.next())
            {
            	Television television = new Television();
            	television.setId(rs.getInt("ele.id_electrodomestico"));
            	television.setDescripcion(rs.getString("ele.descripcion"));
            	television.setColor(rs.getString("col.nombre_color"));
            	television.setPrecio_base(rs.getDouble("precio_base"));
            	television.setPeso(rs.getDouble("peso"));
            	television.setResolucion(rs.getDouble("resolucion"));
            	television.setSinTDT(rs.getBoolean("TDT"));
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
        	statement = conn.prepareStatement("SELECT elec.id_electrodomestico, elec.descripcion, col.nombre_color, elec.precio_base, elec.peso, elec.resolucion, elec.TDT "
					  + "FROM electrodomesticos elec"
					  + "INNER JOIN colores col on col.id_color = elec.id_color "
					  + "WHERE elec.carga is null AND elec.id_electrodomestico = ?");  
        	statement.setInt(1, ID);
        	rs = statement.executeQuery();     	
        	
        	if(rs.next()){
        		television = new Television();
            	television.setId(rs.getInt("ele.id_electrodomestico"));
            	television.setDescripcion(rs.getString("ele.descripcion"));
            	television.setColor(rs.getString("col.nombre_color"));
            	television.setPrecio_base(rs.getDouble("precio_base"));
            	television.setPeso(rs.getDouble("peso"));
            	television.setResolucion(rs.getDouble("resolucion"));
            	television.setSinTDT(rs.getBoolean("TDT"));
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
        	statement = conn.prepareStatement("DELETE electrodomesticos WHERE id_electrodomestico=?");
        	statement.setInt(1, ID);
        	statement.executeUpdate();  	
        }
        catch (Exception Ex)
        {                
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
        	statement = conn.prepareStatement("UPDATE electrodomesticos SET precio=?, nombre=? WHERE id_electrodomestico=?");
        	statement.setString(1, television.getDescripcion());
        	
        	statement.setString(2, String.valueOf(television.getNombre()));
        	statement.setInt(3, television.getId());        	
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
        	statement = conn.prepareStatement("INSERT INTO electrodomesticos(precio,nombre) values(?,?)", Statement.RETURN_GENERATED_KEYS);
        	statement.setDouble(1, television.getPrecio());
        	statement.setString(2, String.valueOf(television.getNombre()));
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
