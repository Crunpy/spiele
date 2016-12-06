package fachlogik;

import java.util.Date;

public class Spiel {
	
	private int id;
	private String name;
	private Date ge;
	
	private int nextID;
	
	public Spiel()
	{
		setId(0);
		setName("Neu");
		setGe(new Date());
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getGe() {
		return ge;
	}
	public void setGe(Date ge) {
		this.ge = ge;
	}
	
	@Override
	public String toString()
	{
		return name;
	}

	public int getNextID() {
		return nextID;
	}

	public void setNextID(int nextID) {
		this.nextID = nextID;
	}
}
