package es.us.isa.papamoscas.proxysla.dto;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="CloudService")
public class CloudService {
	
		private String id;
		private ServiceTopology serviceTopology;
		
		public CloudService(){
			
		}
		
		public CloudService(String id, ServiceTopology serviceTopology) {
			super();
			this.id = id;
			this.serviceTopology = serviceTopology;
		}

		public String getId() {
			return id;
		}
		
		@XmlAttribute
		public void setId(String id) {
			this.id = id;
		}

		public ServiceTopology getServiceTopology() {
			return serviceTopology;
		}
		
		@XmlElement(name = "ServiceTopology")
		public void setServiceTopology(ServiceTopology serviceTopology) {
			this.serviceTopology = serviceTopology;
		}

		@Override
		public String toString() {
			return "CloudService [id=" + id + ", serviceTopology="
					+ serviceTopology.toString() + "]";
		}
		
		
		
		
}
