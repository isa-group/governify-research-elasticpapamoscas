package es.us.isa.papamoscas.proxysla.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceUnit {
	
	@XmlAttribute
	private String id;
	
	@XmlElement(name="SYBLDirective")
	private SYBLDirective syblDirective;
	
	public ServiceUnit(){
		
	}

	public ServiceUnit(String id, SYBLDirective syblDirective) {
		super();
		this.id = id;
		this.syblDirective = syblDirective;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SYBLDirective getSyblDirective() {
		return syblDirective;
	}

	public void setSyblDirective(SYBLDirective syblDirective) {
		this.syblDirective = syblDirective;
	}

	@Override
	public String toString() {
		return "ServiceUnit [id=" + id + ", syblDirective=" + syblDirective.toString()
				+ "]";
	}
	
	
	
}
