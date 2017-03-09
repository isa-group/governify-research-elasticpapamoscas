package es.us.isa.icomot.deployer.domain;

import java.util.ArrayList;
import java.util.List;

public class ServiceDescription {
	
	private String id;
	private List<Component> components;
	
	
	public ServiceDescription() {
	}
	
	
	public ServiceDescription(String id) {
		this.id = id;
		this.components = new ArrayList<Component>();
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public List<Component> getComponents() {
		return components;
	}
	
	public void setComponents(List<Component> components) {
		this.components = components;
	}
	
	public void addComponent(Component c){
		this.components.add(c);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((components == null) ? 0 : components.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceDescription other = (ServiceDescription) obj;
		if (components == null) {
			if (other.components != null)
				return false;
		} else if (!components.equals(other.components))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ServiceDescription [id=" + id + ", components=" + components.toString()
				+ "]";
	}
	
	
	
}
