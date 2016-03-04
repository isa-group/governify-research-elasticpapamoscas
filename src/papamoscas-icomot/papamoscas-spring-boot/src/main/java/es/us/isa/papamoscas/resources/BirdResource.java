package es.us.isa.papamoscas.resources;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.us.isa.papamoscas.dto.Bird;
import es.us.isa.papamoscas.repository.CassandraDriverBean;

@RestController
@RequestMapping("/api/v3/birds")
public class BirdResource {
	
	@Autowired
	CassandraDriverBean birdRepo;
	
	/* ----------POST---------- */
	
	@RequestMapping(value = "", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> create(@RequestBody Bird bird){
		try {
			birdRepo.insert(bird);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	/* ----------PUT---------- */
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Bird bird){
		try {
			birdRepo.update(id, bird);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	/* ----------DELETE---------- */
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> delete(@PathVariable UUID id){
		try {
			birdRepo.delete(id);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	
	/* ----------GET---------- */
	
	@RequestMapping(value = "", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Bird>> getAll(){
		try {
			return new ResponseEntity<List<Bird>>(birdRepo.selectAll(),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Bird>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Bird> getAll(@PathVariable UUID id){
		try {
			return new ResponseEntity<Bird>(birdRepo.select(id),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Bird>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/count", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public String count(){
		return birdRepo.count();
	}
	
	/* ----------Analytics---------- */
	
	@RequestMapping(value = "/analytics/slow", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public String slow() throws InterruptedException{
		Thread.sleep(2000);
		return "OK-slow: " + Math.abs(new Random().nextGaussian() * 5);
	}
	
	@RequestMapping(value = "/analytics/fast", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public String fast(){
		return "OK-fast: " + Math.abs(new Random().nextGaussian() * 5);
	}
	
	@RequestMapping(value = "/analytics/test", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public String test(){
		return "OK-fast: " + Math.abs(new Random().nextGaussian() * 5);
	}
}
