package es.us.isa.papamoscas.proxysla.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import es.us.isa.papamoscas.proxysla.config.GeneralConfig;
import es.us.isa.papamoscas.proxysla.config.GovernanceConfig;
import es.us.isa.papamoscas.proxysla.dto.Property;

@Service
public class StoreService {

    @Autowired
    private GeneralConfig generalConfig;

    //private static String storeURL = "http://antgamdiapc.lsi.us.es:9997/api/v5/agreements/";
    public void setProperty(String user, String property, String value) {
        RestTemplate rest = new RestTemplate();
        try {

            rest.postForEntity(getStoreURL() + "agreements/" + user + "/properties/" + property, new Property(property, "int", "Global", value), Property.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Boolean isGuaranteeComplied(String user, String guarantee) {
        RestTemplate rest = new RestTemplate();
        ResponseEntity<Boolean> ret = null;
        Boolean res = false;
        try {
            ret = rest.getForEntity(getStoreURL() + "agreements/" + user + "/guarantees/" + guarantee, Boolean.class);
            if (ret != null) {
                res = ret.getBody();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public String getProperty(String user, String property) {
        RestTemplate rest = new RestTemplate();
        ResponseEntity<Property> ret = null;
        String res = null;
        try {
            ret = rest.getForEntity(getStoreURL() + "agreements/" + user + "/properties/" + property, Property.class);
            if (ret != null) {
                res = ret.getBody().getValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }



    private String getStoreURL() {
        return generalConfig.getUrls().get("store");
    }

}
