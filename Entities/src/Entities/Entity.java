package Entities;

public class Entity {

    private States state;
    private int id;
    
    public int getId(){
    	return this.id;
    }
    
    public void setId(int id){
    	this.id = id;
    }

    public States getState() {
		return state;
	}

	public void setState(States state) {
		this.state = state;
	}

	public enum States { Deleted, New, Modified, Unmodified}

    public Entity()
    {
        this.setState(States.New);   
    }
}
