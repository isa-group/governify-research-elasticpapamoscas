package es.us.isa.papamoscas.proxysla.resources;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.us.isa.papamoscas.proxysla.repositories.RouterRepository;

@RestController
@RequestMapping("/registry")
public class RouterResource {
	
	@Autowired
	RouterRepository routerRepo;
	
	/* ----------GET---------- */
	
	@RequestMapping(value = "", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Map<String, String>> getAll(){
		return routerRepo.getRegistry();
	}
	
	/* ----------POST---------- */
	
	@RequestMapping(value = "/{type}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public String create(@RequestParam String ip, @PathVariable String type){
		return "{\"name\":\""+routerRepo.putNode(type, ip)+"\"}";
	}

	/* ----------DELETE---------- */
	
	@RequestMapping(value = "/{name}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable String name){
		routerRepo.deleteNode(name);
	}
	
	@RequestMapping(value = "routingtable", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> getroutingtable(){
		return routerRepo.getRoutingTable();
	}
	
	@RequestMapping(value = "assisgnementtable", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> getassignementtable(){
		return routerRepo.getAssignmentTable();
	}
}
