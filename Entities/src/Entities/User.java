package Entities;
import java.io.Serializable;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Name;
	private int ID;
	private String Pass;
	public String getName() {
		return Name;
	}
	public int getID() {
		return ID;
	}
	public void setName(String name) {
		Name = name;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getPass() {
		return Pass;
	}
	public void setPass(String hashedPass) {
		Pass = hashedPass;
	}
	public User(int ID, String Name, String HashedPass){
		this.setID(ID);
		this.setName(Name);
		this.setPass(HashedPass);
	}
}
