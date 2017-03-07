package es.us.isa.papamoscas.proxysla.config;

import java.util.Map;

public class ElasticityRules {

    private String of;
    private Map<String, String> with;

    public ElasticityRules() {

    }

    public void setOf(String of) {
        this.of = of;
    }

    public void setWith(Map<String, String> with) {
        this.with = with;
    }

    public String getOf() {
        return of;
    }

    public Map<String, String> getWith() {
        return with;
    }

    @Override
    public String toString() {
        return "elasticityRules [of=" + of + ", with=" + with
                + "]";
    }
}
