package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Entities.ConsumoEnergetico;
import Entities.Entity.States;

public class ConsumoEnergeticoAdapter{

	public ArrayList<ConsumoEnergetico> getAll() throws Exception
    {
        ArrayList<ConsumoEnergetico> consumos = new ArrayList<ConsumoEnergetico>();
        Statement statement=null;
        ResultSet rs=null;
        try
        {
        	Connection conn = DataConnectionManager.getInstancia().getConn();
        	statement = conn.createStatement();
        	rs = statement.executeQuery("SELECT id_consumos, precio, nombre FROM consumosenergeticos");       	


            while (rs.next())
            {
            	ConsumoEnergetico consumoEnergetico = new ConsumoEnergetico();
            	consumoEnergetico.setId(rs.getInt("id_consumos"));
            	consumoEnergetico.setPrecio(rs.getDouble("precio"));
            	consumoEnergetico.setNombre(rs.getString("nombre").charAt(0));
            	consumos.add(consumoEnergetico);
            }
        }
        catch (Exception Ex)
        {                
            throw new Exception("Error al recuperar datos de los consumos", Ex);
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
        return consumos;
    }

    public ConsumoEnergetico getOne(int ID) throws Exception
    {    	
        ConsumoEnergetico consumoEnergetico = null;
        PreparedStatement statement=null;
        ResultSet rs=null;
        
        try
        {
        	Connection conn = DataConnectionManager.getInstancia().getConn();
        	statement = conn.prepareStatement("SELECT id_consumos, precio, nombre FROM consumosenergeticos WHERE id_consumos=?");
        	statement.setInt(1, ID);
        	rs = statement.executeQuery();     	
        	
        	if(rs.next()){
        		consumoEnergetico = new ConsumoEnergetico();
            	consumoEnergetico.setId(rs.getInt("id_consumos"));
            	consumoEnergetico.setPrecio(rs.getDouble("precio"));        
            	consumoEnergetico.setNombre(rs.getString("nombre").charAt(0)); 
        	}

        }
        catch (Exception Ex)
        {                
            throw new Exception("Error al recuperar el consumoEnergetico por ID", Ex);
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
        return consumoEnergetico;
    }
	public ConsumoEnergetico getOneByNombre(char nombreConsumo) throws Exception {
        ConsumoEnergetico consumoEnergetico = null;
        PreparedStatement statement=null;
        ResultSet rs=null;
        
        try
        {
        	Connection conn = DataConnectionManager.getInstancia().getConn();
        	statement = conn.prepareStatement("SELECT id_consumos, precio, nombre FROM consumosenergeticos WHERE nombre=?");
        	statement.setString(1, String.valueOf(nombreConsumo));
        	rs = statement.executeQuery();     	
        	
        	if(rs.next()){
        		consumoEnergetico = new ConsumoEnergetico();
            	consumoEnergetico.setId(rs.getInt("id_consumos"));
            	consumoEnergetico.setPrecio(rs.getDouble("precio"));        
            	consumoEnergetico.setNombre(rs.getString("nombre").charAt(0)); 
        	}

        }
        catch (Exception Ex)
        {                
            throw new Exception("Error al recuperar el consumoEnergetico por nombre", Ex);
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
        return consumoEnergetico;		
	}
    public void delete(int ID) throws Exception
    {
        PreparedStatement statement=null;
        
        try
        {
        	Connection conn = DataConnectionManager.getInstancia().getConn();
        	statement = conn.prepareStatement("DELETE consumosenergeticos WHERE id_consumos=?");
        	statement.setInt(1, ID);
        	statement.executeUpdate();  	
        }
        catch (Exception Ex)
        {                
            throw new Exception("Error al eliminar el consumoEnergetico", Ex);
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

    public void save(ConsumoEnergetico consumoEnergetico) throws Exception
    {
        if (consumoEnergetico.getState() == States.New)
        {
        	this.insert(consumoEnergetico);
        }
        else if (consumoEnergetico.getState() == States.Deleted)
        {
            this.delete(consumoEnergetico.getId());
        }
        else if (consumoEnergetico.getState() == States.Modified)
        {
        	this.update(consumoEnergetico);
        }
        consumoEnergetico.setState(States.Unmodified);         
    }
    
    public void update(ConsumoEnergetico consumoEnergetico) throws Exception{
        PreparedStatement statement=null;
        
        try
        {
        	Connection conn = DataConnectionManager.getInstancia().getConn();
        	statement = conn.prepareStatement("UPDATE consumosenergeticos SET precio=?, nombre=? WHERE id_consumos=?");
        	statement.setDouble(1, consumoEnergetico.getPrecio());
        	statement.setString(2, String.valueOf(consumoEnergetico.getNombre()));
        	statement.setInt(3, consumoEnergetico.getId());        	
        	statement.executeUpdate();  	
        }
        catch (Exception Ex)
        {                
            throw new Exception("Error al modificar el consumoEnergetico", Ex);
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
    
    public void insert(ConsumoEnergetico consumoEnergetico) throws Exception{  	
        PreparedStatement statement=null;
        
        try
        {
        	Connection conn = DataConnectionManager.getInstancia().getConn();
        	statement = conn.prepareStatement("INSERT INTO consumosenergeticos(precio,nombre) values(?,?)", Statement.RETURN_GENERATED_KEYS);
        	statement.setDouble(1, consumoEnergetico.getPrecio());
        	statement.setString(2, String.valueOf(consumoEnergetico.getNombre()));
        	statement.executeUpdate();  	
        	ResultSet rs = statement.getGeneratedKeys();
        	if(rs.next()){
        		consumoEnergetico.setId(rs.getInt(1));
        	}
        }
        catch (Exception Ex)
        {                
            throw new Exception("Error al crear el consumoEnergetico", Ex);
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
