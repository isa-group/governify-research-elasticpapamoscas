package es.us.isa.papamoscas.resources;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.us.isa.papamoscas.repository.MetricsRepository;


@RestController
@RequestMapping("/metrics")
public class MetricsResource {
	
	@Autowired
	MetricsRepository metricsRepo;
	
	/* ----------GET Throuthput---------- */
	@RequestMapping(value = "", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> getAll(){
		return metricsRepo.getRegistry();
	}
	
	@RequestMapping(value = "/throughput", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Integer> getThroughput(){
		Map<String,  Integer> ret = new HashMap<>();
		ret.put("throughput", metricsRepo.getThroughput());
		return ret;
	}
	
	
}
