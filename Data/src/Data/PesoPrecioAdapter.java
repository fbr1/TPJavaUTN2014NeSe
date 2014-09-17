package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Entities.PesoPrecio;
import Entities.Entity.States;

public class PesoPrecioAdapter{

	public ArrayList<PesoPrecio> getAll() throws Exception
    {
        ArrayList<PesoPrecio> pesoPrecios = new ArrayList<PesoPrecio>();
        Statement statement=null;
        ResultSet rs=null;
        try
        {
        	Connection conn = DataConnectionManager.getInstancia().getConn();
        	statement = conn.createStatement();
        	rs = statement.executeQuery("SELECT id_pesoprecios, peso_min, peso_max, precio FROM pesoprecios");       	


            while (rs.next())
            {
            	PesoPrecio pesoPrecio = new PesoPrecio();
            	pesoPrecio.setId(rs.getInt("id_pesoprecios"));
            	pesoPrecio.setPeso_min(rs.getDouble("peso_min"));
            	pesoPrecio.setPeso_max(rs.getDouble("peso_max"));
            	pesoPrecio.setPrecio(rs.getDouble("precio"));
            	pesoPrecios.add(pesoPrecio);
            }
        }
        catch (Exception Ex)
        {                
            throw new Exception("Error al recuperar datos de los pesoPrecios", Ex);
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
        return pesoPrecios;
    }

    public PesoPrecio getOne(int ID) throws Exception
    {    	
        PesoPrecio pesoPrecio = null;
        PreparedStatement statement=null;
        ResultSet rs=null;
        
        try
        {
        	Connection conn = DataConnectionManager.getInstancia().getConn();
        	statement = conn.prepareStatement("SELECT id_pesoprecios, peso_min, peso_max, precio FROM pesoprecios WHERE id_pesoprecios=?");
        	statement.setInt(1, ID);
        	rs = statement.executeQuery();     	
        	
        	if(rs.next()){
        		pesoPrecio = new PesoPrecio();
            	pesoPrecio.setId(rs.getInt("id_pesoprecios"));
            	pesoPrecio.setPeso_min(rs.getDouble("peso_min"));
            	pesoPrecio.setPeso_max(rs.getDouble("peso_max"));
            	pesoPrecio.setPrecio(rs.getDouble("precio"));        		
        	}

        }
        catch (Exception Ex)
        {                
            throw new Exception("Error al recuperar el pesoPrecio", Ex);
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
        return pesoPrecio;
    }

    public void delete(int ID) throws Exception
    {
        PreparedStatement statement=null;
        
        try
        {
        	Connection conn = DataConnectionManager.getInstancia().getConn();
        	statement = conn.prepareStatement("DELETE pesoprecios WHERE id_pesoprecios=?");
        	statement.setInt(1, ID);
        	statement.executeUpdate();  	
        }
        catch (Exception Ex)
        {                
            throw new Exception("Error al eliminar el pesoPrecio", Ex);
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

    public void save(PesoPrecio pesoPrecio) throws Exception
    {
        if (pesoPrecio.getState() == States.New)
        {
        	this.insert(pesoPrecio);
        }
        else if (pesoPrecio.getState() == States.Deleted)
        {
            this.delete(pesoPrecio.getId());
        }
        else if (pesoPrecio.getState() == States.Modified)
        {
        	this.update(pesoPrecio);
        }
        pesoPrecio.setState(States.Unmodified);         
    }
    
    public void update(PesoPrecio pesoPrecio) throws Exception{
        PreparedStatement statement=null;
        
        try
        {
        	Connection conn = DataConnectionManager.getInstancia().getConn();
        	statement = conn.prepareStatement("UPDATE pesoprecios SET peso_min=?, peso_max=?, precio=? WHERE id_pesoprecios=?");
        	statement.setDouble(1, pesoPrecio.getPeso_min());
        	statement.setDouble(2, pesoPrecio.getPeso_max());
        	statement.setDouble(3, pesoPrecio.getPrecio());
        	statement.setInt(4, pesoPrecio.getId());        	
        	statement.executeUpdate();  	
        }
        catch (Exception Ex)
        {                
            throw new Exception("Error al modificar el pesoPrecio", Ex);
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
    
    public void insert(PesoPrecio pesoPrecio) throws Exception{  	
        PreparedStatement statement=null;
        
        try
        {
        	Connection conn = DataConnectionManager.getInstancia().getConn();
        	statement = conn.prepareStatement("INSERT INTO pesoprecios(peso_min,peso_max,precio) values(?,?,?)", Statement.RETURN_GENERATED_KEYS);
        	statement.setDouble(1, pesoPrecio.getPeso_min());
        	statement.setDouble(2, pesoPrecio.getPeso_max());
        	statement.setDouble(3, pesoPrecio.getPrecio());
        	statement.executeUpdate();  	
        	ResultSet rs = statement.getGeneratedKeys();
        	if(rs.next()){
        		pesoPrecio.setId(rs.getInt(1));
        	}
        }
        catch (Exception Ex)
        {                
            throw new Exception("Error al crear el pesoPrecio", Ex);
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
