package Data;

import java.sql.*;
import java.util.ArrayList;

import Entities.Color;
import Entities.Entity.States;

public class ColorAdapter{
	

    public ArrayList<Color> getAll() throws Exception
    {
        ArrayList<Color> colores = new ArrayList<Color>();
        Statement statement=null;
        ResultSet rs=null;
        try
        {
        	Connection conn = DataConnectionManager.getInstancia().getConn();
        	statement = conn.createStatement();
        	rs = statement.executeQuery("SELECT id_color, nombre_color FROM colores");       	


            while (rs.next())
            {
            	Color color = new Color();
            	color.setId(rs.getInt("id_color"));
            	color.setNombre(rs.getString("nombre_color"));
            	colores.add(color);
            }
        }
        catch (Exception Ex)
        {                
            throw new Exception("Error al recuperar datos de los colores", Ex);
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
        return colores;
    }

    public Color getOne(int ID) throws Exception
    {    	
        Color color = null;
        PreparedStatement statement=null;
        ResultSet rs=null;
        
        try
        {
        	Connection conn = DataConnectionManager.getInstancia().getConn();
        	statement = conn.prepareStatement("SELECT id_color, nombre_color FROM colores WHERE id_color=?");
        	statement.setInt(1, ID);
        	rs = statement.executeQuery();     	
        	
        	if(rs.next()){
        		color = new Color();
        		color.setId(rs.getInt("id_color"));
        		color.setNombre(rs.getString("nombre_color"));
        	}

        }
        catch (Exception Ex)
        {                
            throw new Exception("Error al recuperar datos de los colores", Ex);
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
        return color;
    }

    public void delete(int ID) throws Exception
    {
        PreparedStatement statement=null;
        
        try
        {
        	Connection conn = DataConnectionManager.getInstancia().getConn();
        	statement = conn.prepareStatement("DELETE colores WHERE id_color=?");
        	statement.setInt(1, ID);
        	statement.executeUpdate();  	
        }
        catch (Exception Ex)
        {                
            throw new Exception("Error al eliminar el color", Ex);
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

    public void save(Color color) throws Exception
    {
        if (color.getState() == States.New)
        {
        	this.insert(color);
        }
        else if (color.getState() == States.Deleted)
        {
            this.delete(color.getId());
        }
        else if (color.getState() == States.Modified)
        {
        	this.update(color);
        }
        color.setState(States.Unmodified);         
    }
    
    public void update(Color color) throws Exception{
        PreparedStatement statement=null;
        
        try
        {
        	Connection conn = DataConnectionManager.getInstancia().getConn();
        	statement = conn.prepareStatement("UPDATE colores SET nombre_color=? WHERE id_color=?");
        	statement.setString(1, color.getNombre());
        	statement.setInt(2, color.getId());        	
        	statement.executeUpdate();  	
        }
        catch (Exception Ex)
        {                
            throw new Exception("Error al modificar el color", Ex);
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
    
    public void insert(Color color) throws Exception{  	
        PreparedStatement statement=null;
        
        try
        {
        	Connection conn = DataConnectionManager.getInstancia().getConn();
        	statement = conn.prepareStatement("INSERT INTO colores(nombre_color) values(?)", Statement.RETURN_GENERATED_KEYS);
        	statement.setString(1, color.getNombre());
        	statement.executeUpdate();  	
        	ResultSet rs = statement.getGeneratedKeys();
        	if(rs.next()){
        		color.setId(rs.getInt(1));
        	}
        }
        catch (Exception Ex)
        {                
            throw new Exception("Error al crear el color", Ex);
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
