package es.us.isa.papamoscas.proxysla.resources;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.us.isa.papamoscas.proxysla.repositories.MetricsRepository;
import es.us.isa.papamoscas.proxysla.services.GovernanceController;



@RestController
@RequestMapping("/metrics")
public class MetricsResource {
	
	@Autowired
	MetricsRepository metricsRepo;
	
	@Autowired
	GovernanceController govenrController;
	
	/* ----------GET ---------- */
	@RequestMapping(value = "", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Map<String, Integer>> getAll(){
		return metricsRepo.getRegistry();
	}
	
	@RequestMapping(value = "/throughput/{user}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Integer> getThroughputUser(@PathVariable String user){
		Map<String,  Integer> ret = new HashMap<>();
		ret.put("throughput", metricsRepo.getThroughput(user));
		return ret;
	}
	
	@RequestMapping(value = "/throughput/level/{level}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Integer> getThroughputLevel(@PathVariable String level){
		Map<String,  Integer> ret = new HashMap<>();
		ret.put("throughput", govenrController.getThroughputINPerLevel().get(level));
		return ret;
	}
	
	@RequestMapping(value = "/throughput/level", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Map<String, Integer>> getThroughputPerLevel(){
		Map<String,  Map<String, Integer>> ret = new HashMap<>();
		ret.put("throughput", govenrController.getThroughputINPerLevel());
		return ret;
	}
	
	@RequestMapping(value = "/throughput", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Integer> getThroughputTotal(){
		Map<String,  Integer> ret = new HashMap<>();
		ret.put("throughput", metricsRepo.getTotalThroughput());
		return ret;
	}
	
	@RequestMapping(value = "/availability/{user}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Double> getAvaiability(@PathVariable String user){
		Map<String,  Double> ret = new HashMap<>();
		ret.put("availability", metricsRepo.getAvailability(user));
		return ret;
	}
	
}
