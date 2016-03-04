package es.us.isa.papamoscas.proxysla.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.us.isa.papamoscas.proxysla.config.ConfigDTO;
import es.us.isa.papamoscas.proxysla.config.GeneralConfig;
import es.us.isa.papamoscas.proxysla.config.GovernanceConfig;
import es.us.isa.papamoscas.proxysla.config.GovernanceConfigDTO;

@RestController
@RequestMapping("/configs")
public class ConfigResource {
	
	@Autowired
	private GeneralConfig generalConfig;
	
	@Autowired
	private GovernanceConfig governConfig;
	
	
	
	/* ----------POST---------- */
	
	@RequestMapping(value = "", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConfigDTO> update(@RequestBody ConfigDTO config){
		
		try{
			if(config == null)
				return new ResponseEntity<ConfigDTO>(HttpStatus.BAD_REQUEST);
			
			//UPDATE GENERAL CONFIG
			generalConfig.setTimers(config.getTimers());
			generalConfig.setUrls(config.getUrls());
			
			//UDATE governANCE CONFIG
			governConfig.setLevels(config.getGovernanceProperties().getLevels());
			governConfig.setService(config.getGovernanceProperties().getService());
			governConfig.setLevelElasticityPercentages(config.getGovernanceProperties().getLevelElasticityPercentages());
			governConfig.setDefaultRules(config.getGovernanceProperties().getDefaultRules());
			governConfig.setRouterConfig(config.getGovernanceProperties().getRouterConfig());
			
			
			return new ResponseEntity<ConfigDTO>(HttpStatus.OK);
			
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<ConfigDTO>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	/* ----------GET---------- */
	
	@RequestMapping(value = "", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConfigDTO> get(){
		ConfigDTO ret = new ConfigDTO();
		
		//ADD GENERAL CONFIG
		ret.setTimers(generalConfig.getTimers());
		ret.setUrls(generalConfig.getUrls());
		
		//ADD governANCE CONFIG
		GovernanceConfigDTO governDto = new GovernanceConfigDTO();
		governDto.setLevels(governConfig.getLevels());
		governDto.setLevelElasticityPercentages(governConfig.getLevelElasticityPercentages());
		governDto.setService(governConfig.getService());
		governDto.setDefaultRules(governConfig.getDefaultRules());
		governDto.setRouterConfig(governConfig.getRouterConfig());
		ret.setGovernanceProperties(governDto);
		
		return new ResponseEntity<ConfigDTO>(ret, HttpStatus.OK);
	}
	
}
