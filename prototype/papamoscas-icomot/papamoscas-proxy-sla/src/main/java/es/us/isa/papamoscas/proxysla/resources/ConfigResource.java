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
import es.us.isa.papamoscas.proxysla.config.NewConfigDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<NewConfigDTO> update(@RequestBody NewConfigDTO config) {

        try {

            if (config == null) {
                return new ResponseEntity<NewConfigDTO>(HttpStatus.BAD_REQUEST);
            }
            
            generalConfig.getUrls().put("store", config.getDatastore());
            
            List<String> levels = new ArrayList<String>();
            Map<String, Double> elasticity = new HashMap<>();
            Map<String, Double> instances = new HashMap<>();
            Map<String, String> defaultRules = new HashMap<>();
            Map<String, String> service = new HashMap<>();
            Map<String, Map<String, Double>> routerConfig = new HashMap<>();

            Integer count = config.getLevels();
            Double[] distributionInstances = config.getInitialInstances().getDistribution(count);
            Double[] distributionLevel = config.getElasticitySpeed().getDistribution(count);
            Double[] distributionNextLevel = config.getUpRiseSpeed().getDistribution(count);
            Double[] distributionBackLevel = config.getDownRiseSpeed().getDistribution(count);
            
            for (int i = 0; i < count; i++) {
                Map<String, Double> levelRouter = new HashMap<>();
                levelRouter.put("nextLevel",distributionNextLevel[i]);
                levelRouter.put("backLevel",distributionBackLevel[i]);
                String tag = "l" + (i < 10 ? "0" : "") + i;
                levels.add(tag);
                elasticity.put(tag, distributionLevel[i]);
                instances.put(tag,  distributionInstances[i]);
                if(config.getElasticityRules() != null){
                    defaultRules.put(tag, config.getElasticityRules().getOf());
                }
                routerConfig.put(tag, levelRouter);

            }

            service.put("id", config.getId());
            service.put("scalable", config.getUpdateElasticity().toString());
            service.put("unitTh", config.getInstanceLimit().toString());
           

            governConfig.setLevels(levels);
            governConfig.setService(service);
            governConfig.setLevelElasticityPercentages(elasticity);
            governConfig.setLevelMinInstances(instances);
            governConfig.setDefaultRules(defaultRules);
            governConfig.setRouterConfig(routerConfig);
          

            return new ResponseEntity<NewConfigDTO>(HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<NewConfigDTO>(HttpStatus.BAD_REQUEST);
        }

    }


    /* ----------GET---------- */
    @RequestMapping(value = "", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConfigDTO> get() {
        ConfigDTO ret = new ConfigDTO();

        //ADD GENERAL CONFIG
        ret.setTimers(generalConfig.getTimers());
        ret.setUrls(generalConfig.getUrls());

        //ADD governANCE CONFIG
        GovernanceConfigDTO governDto = new GovernanceConfigDTO();
        governDto.setLevels(governConfig.getLevels());
        governDto.setLevelMinInstances(governConfig.getLevelMinInstances());
        governDto.setLevelElasticityPercentages(governConfig.getLevelElasticityPercentages());
        governDto.setService(governConfig.getService());
        governDto.setDefaultRules(governConfig.getDefaultRules());
        governDto.setRouterConfig(governConfig.getRouterConfig());
        ret.setGovernanceProperties(governDto);

        return new ResponseEntity<ConfigDTO>(ret, HttpStatus.OK);
    }

}
