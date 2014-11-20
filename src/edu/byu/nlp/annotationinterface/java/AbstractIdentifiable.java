package edu.byu.nlp.annotationinterface.java;

import edu.byu.nlp.annotationinterface.Identifiable;
import edu.byu.nlp.annotationinterface.Instance;

public abstract class AbstractIdentifiable implements Identifiable {

	private long id;

	public AbstractIdentifiable(long id){
		this.id=id;
	}
	
	@Override
	public long getId() {
		return id;
	}
	
	public void setId(long id){
		this.id=id;
	}

	@Override
	public int hashCode() {
		return (int)id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Instance){
			return this.id==((Instance<?>)obj).getId(); 
		}
		return false;
	}
	
	@Override
	public String toString() {
		return this.getClass().getName()+"(id="+id+")";
	}

}
