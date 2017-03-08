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

            List levels = new ArrayList();
            Map<String, Double> elasticity = new HashMap<>();
            Map<String, String> defaultRules = new HashMap<>();
            Map<String, String> service = new HashMap<>();
            Map<String, Double> routerConfig = new HashMap<>();

            Double maxElasticity = config.getElasticitySpeed().getMax();
            Double minElasticity = config.getElasticitySpeed().getMin();
            Integer level = config.getLevels();
            Double[] distribution = new Double[level];

            distribution[0] = minElasticity;
            //generate distribution array
            if (level > 0) {
                Double difference = (maxElasticity - minElasticity);
                Double factor = difference / (level - 1);
                distribution[distribution.length - 1] = maxElasticity;
                //fill array
                for (int i = 1; i <= (level - 2); i++) {
                    distribution[i] = minElasticity + factor;
                }
            }

            for (int i = 0; i < level; i++) {
                String tag = "L" + (i < 10 ? "0" : "") + i;
                levels.add(tag);
                elasticity.put(tag, distribution[i]);
                defaultRules.put(tag, config.getElasticityRules().getOf());

            }

            service.put("id", config.getId());
            service.put("scalable", "true");
            service.put("unitTh", "8");
            service.put("weight", "0");
            routerConfig.put("nextLevel", config.getUpRiseSpeed().getMin());
            routerConfig.put("backLevel", config.getUpRiseSpeed().getMax());

            governConfig.setLevels(levels);
            governConfig.setService(service);
            governConfig.setLevelElasticityPercentages(elasticity);
            governConfig.setDefaultRules(defaultRules);
            governConfig.setRouterConfig(routerConfig);

            System.out.println(governConfig);
            /*
            
            //UPDATE GENERAL CONFIG
            generalConfig.setTimers(config.getTimers());
            generalConfig.setUrls(config.getUrls());

             */

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
        governDto.setLevelElasticityPercentages(governConfig.getLevelElasticityPercentages());
        governDto.setService(governConfig.getService());
        governDto.setDefaultRules(governConfig.getDefaultRules());
        governDto.setRouterConfig(governConfig.getRouterConfig());
        ret.setGovernanceProperties(governDto);

        return new ResponseEntity<ConfigDTO>(ret, HttpStatus.OK);
    }

}
