package es.us.isa.papamoscas.proxysla.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import es.us.isa.papamoscas.proxysla.config.GeneralConfig;
import es.us.isa.papamoscas.proxysla.config.GovernanceConfig;
import es.us.isa.papamoscas.proxysla.dto.CloudService;
import es.us.isa.papamoscas.proxysla.dto.Property;
import es.us.isa.papamoscas.proxysla.dto.SYBLDirective;
import es.us.isa.papamoscas.proxysla.dto.ServiceUnit;
import es.us.isa.papamoscas.proxysla.repositories.MetricsRepository;
import es.us.isa.papamoscas.proxysla.repositories.RouterRepository;
import java.util.ArrayList;
import java.util.List;

@Service
public class GovernanceController {

    private static final Logger log = LoggerFactory.getLogger(GovernanceController.class);

    @Autowired
    private MetricsRepository metricsRepo;

    @Autowired
    private GeneralConfig config;

    @Autowired
    private GovernanceConfig governConfig;

    @Autowired
    private StoreService storeService;

    @Autowired
    private MELAService melaService;

    @Autowired
    private RouterRepository routerRepository;

    public GovernanceController() {

    }

    @PostConstruct
    public void governs() {

        Timer updateStore = new Timer(true);
        updateStore.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {

                log.info("UpdateStore Agent: Store has been udated.");
                for (String user : routerRepository.getRoutingTable().keySet()) {
                    storeService.setProperty(user, "Throughput", metricsRepo.getThroughput(user).toString());
                    storeService.setProperty(user, "Availability", metricsRepo.getAvailability(user).toString());
                }
            }

        }, config.getTimers().get("store") * 1000, config.getTimers().get("store") * 1000);

        Timer rSYBLControl = new Timer(true);
        rSYBLControl.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                try {
                    //System.out.println(levelIsPrepared(0));			
                    RestTemplate rest = new RestTemplate();
                    SimpleClientHttpRequestFactory rf = (SimpleClientHttpRequestFactory) rest.getRequestFactory();
                    rf.setReadTimeout(60 * 1000);
                    rf.setConnectTimeout(60 * 1000);
                    JAXBContext jaxbC = JAXBContext.newInstance(CloudService.class);
                    Marshaller marshaller = jaxbC.createMarshaller();
                    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                    Map<String, Integer> throughputINPerLevel = getThroughputINPerLevel();
                    CloudService cs = rest.getForObject(config.getUrls().get("rsybl") + "restWS/" + governConfig.getService().get("id") + "/description", CloudService.class);
//                    CloudService cs = rest.getForObject(config.getUrls().get("rsybl") + "restWS/" +"UnGoverned" + "/description", CloudService.class);
                    if (cs != null && throughputINPerLevel.keySet().size() != 0) {

                        System.out.println("Updating rsybl rules with percentage: " + governConfig.getLevelElasticityPercentages().toString());
                        System.out.println("and throughput per level: " + throughputINPerLevel);

                        for (String level : throughputINPerLevel.keySet()) {

                           // String mayuscula = level.charAt(0) + "";
                           // mayuscula = mayuscula.toUpperCase();
                           // level = level.replaceFirst(level.charAt(0) + "", mayuscula);

                            for (ServiceUnit su : cs.getServiceTopology().getServiceUnits()) {
                                if (su.getId().contains("api_" + level)) {

                                    String levell = level.toLowerCase();
                                    Integer in = throughputINPerLevel.get(levell);

                                    Integer unitTh = new Integer(governConfig.getService().get("unitTh"));
                                    Double eSpeed = governConfig.getLevelElasticityPercentages().get(levell);

                                    Double scaleLimit = unitTh * (1 - eSpeed);
                                    
                                    Double instancesFactor = (governConfig.getLevelMinInstances().get(levell) - 1);
                                    
                                    Double scaleINleftSide = in + ((scaleLimit * instancesFactor) +1);
                                    
                                    String scaleOut = "";
                                    String scaleIN = "";
                                    
                                    
                                    scaleOut = "DN_ST2:STRATEGY CASE " + in + " > " + scaleLimit + "*numberOfVMs #:scaleOut";
                                    scaleIN = "DN_ST1:STRATEGY CASE " + scaleINleftSide + " <= " + scaleLimit + "*numberOfVMs-1 #:scaleIn";
                                    
                                    log.info("LEVEL: "  + levell);
                                    log.info("scaleOut: "  + scaleOut);
                                    log.info("scaleIN: "  + scaleIN);

                                    SYBLDirective directive = new SYBLDirective();

                                    directive.setStrategies(scaleOut + ";" + scaleIN);

                                    su.setSyblDirective(directive);
                                }
                            }
                        }

                        marshaller.marshal(cs, System.out);
                        log.info("POSTing to : " + config.getUrls().get("rsybl") + "restWS/" + governConfig.getService().get("id") + "/description");
                        if (governConfig.getService().get("scalable").equals("true")) {
                            ResponseEntity<String> entity = rest.postForEntity(config.getUrls().get("rsybl") + "restWS/" + governConfig.getService().get("id") + "/description", cs, String.class);
                        }
                    }

                } catch (Exception e) {

                    e.printStackTrace();

                }

            }

        }, 5000, config.getTimers().get("rsybl") * 1000);

        Timer udateRoutingTable = new Timer(true);
        udateRoutingTable.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                try {
                    //List users = new ArrayList();
                    //users.add("t00");
                    //users.add("t01");
                    //users.add("t02");
                    //routerRepository.setUserRoute("t00", "l00");
                    
    
                    for (String user : routerRepository.getRoutingTable().keySet()) {
                        String currentlevel =  routerRepository.getLevelForUser(user);
                        Map<String,Double> rates =  governConfig.getRouterConfig().get(currentlevel);
                        Boolean permited = storeService.isGuaranteeComplied(user, "ThroughputTerm");
                        routerRepository.setPermitedRequest(user, permited);						
                        Double minAvailability = new Double(storeService.getProperty(user, "MinAvailability"));
                        Double availability = new Double(storeService.getProperty(user, "Availability"));
                        Boolean nextLevel = availability < minAvailability + (rates.get("nextLevel")* (100 - minAvailability));
                        Boolean backLevel = availability > minAvailability + (rates.get("backLevel") * (100 - minAvailability));
                         
                        if (nextLevel) {
                            userToNextLevel(user);
                        } else if (backLevel) {
                            userToBackLevel(user);
                        }

                    }

                    log.info("UpdateRouter Agent: Updated Routing Table: " + routerRepository.getRoutingTable());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }, 0, config.getTimers().get("routing") * 1000);
    }

    public Map<String, Integer> getMaxThroughputPerLevel() {
        Map<String, Integer> throughputPerLevel = new HashMap<String, Integer>();
        for (String level : governConfig.getLevelElasticityPercentages().keySet()) {
            throughputPerLevel.put(level, 0);
        }
        try {
            Map<String, String> userLevel = routerRepository.getRoutingTable();
            RestTemplate rest = new RestTemplate();
            for (String user : userLevel.keySet()) {

                String level = userLevel.get(user);
                Property pp = rest.getForObject(config.getUrls().get("store") + "agreements/" + user + "/properties/MaxThroughput", Property.class);

                if (!throughputPerLevel.containsKey(level)) {

                    throughputPerLevel.put(level, new Integer(pp.getValue()));

                } else {

                    throughputPerLevel.put(level, throughputPerLevel.get(level) + new Integer(pp.getValue()));

                }

            }

            return throughputPerLevel;

        } catch (Exception e) {

            e.printStackTrace();

        }
        return throughputPerLevel;
    }

    public Map<String, Integer> getThroughputINPerLevel() {
        Map<String, Integer> throughputPerLevel = new HashMap<String, Integer>();
        for (String level : governConfig.getLevelElasticityPercentages().keySet()) {
            throughputPerLevel.put(level, 0);
        }
        try {
            Map<String, String> userLevel = routerRepository.getAssignmentTable();

            for (String user : userLevel.keySet()) {

                String level = userLevel.get(user);

                if (!throughputPerLevel.containsKey(level)) {

                    throughputPerLevel.put(level, new Integer(metricsRepo.getThroughput(user)));

                } else {

                    throughputPerLevel.put(level, throughputPerLevel.get(level) + metricsRepo.getThroughput(user));

                }

            }

            return throughputPerLevel;

        } catch (Exception e) {

            e.printStackTrace();

        }
        return throughputPerLevel;
    }

    public void userToNextLevel(String user) {
        if (!routerRepository.getRoutingTable().containsKey(user)) {
            routerRepository.setUserRoute(user, governConfig.getLevels().get(0));
            routerRepository.assigneUserRouter(user, governConfig.getLevels().get(0));

        } else {

            int nextLevel = governConfig.getLevels().indexOf(routerRepository.getLevelForUser(user));
            if (nextLevel != governConfig.getLevels().size() - 1) {
                nextLevel += 1;
                routerRepository.assigneUserRouter(user, governConfig.getLevels().get(nextLevel));

                if (levelIsPrepared(nextLevel)) {
                    routerRepository.setUserRoute(user, governConfig.getLevels().get(nextLevel));
                } else {
                    try {
                        Thread.sleep(90 * 1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    routerRepository.setUserRoute(user, governConfig.getLevels().get(nextLevel));
                }

            }

        }
    }

    public void userToBackLevel(String user) {
        if (!routerRepository.getRoutingTable().containsKey(user)) {

            routerRepository.setUserRoute(user, governConfig.getLevels().get(0));
            routerRepository.assigneUserRouter(user, governConfig.getLevels().get(0));

        } else {
            int backLevel = governConfig.getLevels().indexOf(routerRepository.getLevelForUser(user));
            if (backLevel != 0) {
                backLevel -= 1;
                routerRepository.assigneUserRouter(user, governConfig.getLevels().get(backLevel));

                if (levelIsPrepared(backLevel)) {
                    routerRepository.setUserRoute(user, governConfig.getLevels().get(backLevel));
                } else {
                    try {
                        Thread.sleep(90 * 1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    routerRepository.setUserRoute(user, governConfig.getLevels().get(backLevel));
                }

            }
        }
    }

    public Boolean levelIsPrepared(int level) {
        Integer thAssigned = getThroughputINPerLevel().get(governConfig.getLevels().get(level));
        
        thAssigned = ( thAssigned.equals(0) ? 1 : thAssigned); 
        
        Integer numberOfVMs = new Integer(melaService.getMetricOfServiceUnit(governConfig.getService().get("id"),
                governConfig.getLevels().get(level), "numberOfVMs"));
        
        log.info(governConfig.getLevels().get(level) + " numero de maquinas: " + numberOfVMs);
        
        if (thAssigned / new Integer(governConfig.getService().get("unitTh")) > numberOfVMs) {
            return false;
        }

        return true;
    }

}
