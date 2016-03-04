package es.us.isa.papamoscas.proxysla.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceTopology {
	
	@XmlAttribute
	private String id;
	
	@XmlElements(value = { @XmlElement(name="ServiceUnit") })
	private List<ServiceUnit> serviceUnits;
	
	public ServiceTopology(){
		
	}
	
	public ServiceTopology(String id, List<ServiceUnit> serviceUnits) {
		super();
		this.id = id;
		this.serviceUnits = serviceUnits;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<ServiceUnit> getServiceUnits() {
		return serviceUnits;
	}

	public void setServiceUnits(List<ServiceUnit> serviceUnits) {
		this.serviceUnits = serviceUnits;
	}

	@Override
	public String toString() {
		return "ServiceTopology [id=" + id + ", serviceUnits=" + serviceUnits
				+ "]";
	}
	
	
	
}
