package es.us.isa.papamoscas.proxysla.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerMapping;

import es.us.isa.papamoscas.proxysla.dto.Bird;
import es.us.isa.papamoscas.proxysla.repositories.RouterRepository;

@RestController
@RequestMapping("api")
public class Router {
	private static final Logger log = LoggerFactory.getLogger(Router.class);
	
	@Autowired
	private RouterRepository routerRepository;
	
	private Random rnd = new Random();
	private RestTemplate restTemplate = new RestTemplate();
	
	/** 
	 * Proxying POST /api/**
	 * **/	
	@RequestMapping(value = "/**", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> doPost(@RequestBody Bird body,  @RequestParam String user,
			 HttpServletRequest requestIn) {
		
		try{
			
			String path = (String) requestIn.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
			
			String ip = randomIP(user);
			RequestEntity<Bird> request = new RequestEntity<Bird>(body, HttpMethod.POST, new URI("http://" + ip + path));
			
			log.info("Send POST Request to: " + request.getUrl().toString());
			log.info("and body: " + body);
			
			return new ResponseEntity<String>(restTemplate.postForObject("http://" + ip + path, body, String.class), HttpStatus.OK);
			
		}catch(Exception e){
			
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.SERVICE_UNAVAILABLE);
			
		}
		
		
	}
	
	/** 
	 * Proxying GET /api/**
	 * **/	
	@RequestMapping(value = "/**", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> get(@RequestParam String user,  HttpServletRequest requestIn){
		
		try{
			
			String path = (String) requestIn.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
			
			String ip = randomIP(user);
			RequestEntity<String> request = new RequestEntity<String>(HttpMethod.GET,  new URI("http://" + ip + path));
			
			log.info("Send GET Request to: " + request.getUrl().toString());
			
			return new ResponseEntity<String>(restTemplate.getForObject("http://" + ip + path, String.class), HttpStatus.OK);
			
		}catch(Exception e){
			
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.SERVICE_UNAVAILABLE);
			
		}
		
		
	}
	
	@RequestMapping(value = "/analytics/test", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> test(@RequestParam String user){
		return  new ResponseEntity<String>(user , HttpStatus.OK);			
	}
	
	public String randomIP (String user){
		String ret = "";
		
		List<String> nodes = new ArrayList<String>(routerRepository.getRegistry().get(routerRepository.getLevelForUser(user)).keySet());
		
		if(nodes.size() != 0){
			Integer node = Math.abs(rnd.nextInt()) % nodes.size();
			ret = routerRepository.getRegistry().get(routerRepository.getLevelForUser(user)).get(nodes.get(node));
		}		
		
		return ret;
	}
}
