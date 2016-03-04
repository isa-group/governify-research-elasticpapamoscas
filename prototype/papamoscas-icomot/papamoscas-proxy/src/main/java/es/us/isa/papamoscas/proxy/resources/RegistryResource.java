package es.us.isa.papamoscas.proxy.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.us.isa.papamoscas.proxy.repositories.RegistryRepository;

@RestController
@RequestMapping("/registry")
public class RegistryResource {
	
	@Autowired
	RegistryRepository registryRepo;
	
	/* ----------GET---------- */
	
	@RequestMapping(value = "", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getAll(){
		return registryRepo.getRegistry();
	}
	
	/* ----------POST---------- */
	
	@RequestMapping(value = "", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer create(@RequestParam String ip){
		return registryRepo.putNode(ip);
	}

	/* ----------DELETE---------- */
	
	@RequestMapping(value = "", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@RequestParam String ip){
		registryRepo.deleteNode(ip);
	}
}
