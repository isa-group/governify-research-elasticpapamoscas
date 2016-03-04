package es.us.isa.papamoscas.proxysla.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import es.us.isa.papamoscas.proxysla.config.GeneralConfig;
import es.us.isa.papamoscas.proxysla.dto.ServiceData;

@Service
public class MELAService {
	
	@Autowired
	private GeneralConfig generalConfig;
	
	public ServiceData getMonitorinData(String id){
		Gson gson = new Gson();
		ServiceData res = null;
		
		RestTemplate rest = new RestTemplate();
		ResponseEntity<String> ret = null;

		try{
			//System.out.println(getMELAURL() + "REST_WS/" + id + "/monitoringdata/json");
			ret = rest.getForEntity(getMELAURL() + "REST_WS/" + id + "/monitoringdata/json", String.class);
			if(ret != null)
				res = gson.fromJson(ret.getBody(), ServiceData.class);
			
		}catch(Exception e){
			res = null;			
		}
		
		return res;
	}
	
	public String getMetricOfServiceUnit(String service, String serviceUnitID, String metric){
		ServiceData data = getMonitorinData(service);
		
		if(data == null)
			return "0";
		
		ServiceData topology = data.getChildren().get(0);
		
		String ret = "0";
		for(ServiceData su : topology.getChildren()){
			if(su.getName().equalsIgnoreCase(serviceUnitID)){
				for (ServiceData m : su.getChildren()) {
					if(m.getName().contains(metric)){
						ret = m.getName().split(" ")[0];
					}
				}
			}
		}
		
		return ret;
	}
	public String getMELAURL(){
		return generalConfig.getUrls().get("mela");
	}
}
