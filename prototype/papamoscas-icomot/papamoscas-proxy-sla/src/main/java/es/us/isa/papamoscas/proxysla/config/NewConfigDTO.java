package es.us.isa.papamoscas.proxysla.config;

public class NewConfigDTO {
	
	private DownRiseSpeed downRiseSpeed;
	private ElasticityRules elasticityRules;
        private ElasticitySpeed elasticitySpeed;
        private String id;
        private Integer levels;
        private UpRiseSpeed upRiseSpeed;
        private InitialInstances initialInstances;

    public void setInitialInstances(InitialInstances initialInstances) {
        this.initialInstances = initialInstances;
    }

    public InitialInstances getInitialInstances() {
        return initialInstances;
    }

    public void setDownRiseSpeed(DownRiseSpeed downRiseSpeed) {
        this.downRiseSpeed = downRiseSpeed;
    }

    public void setElasticityRules(ElasticityRules elasticityRules) {
        this.elasticityRules = elasticityRules;
    }

    public void setElasticitySpeed(ElasticitySpeed elasticitySpeed) {
        this.elasticitySpeed = elasticitySpeed;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLevels(Integer levels) {
        this.levels = levels;
    }

    public void setUpRiseSpeed(UpRiseSpeed upRiseSpeed) {
        this.upRiseSpeed = upRiseSpeed;
    }

    public DownRiseSpeed getDownRiseSpeed() {
        return downRiseSpeed;
    }

    public ElasticityRules getElasticityRules() {
        return elasticityRules;
    }

    public ElasticitySpeed getElasticitySpeed() {
        return elasticitySpeed;
    }

    public String getId() {
        return id;
    }

    public Integer getLevels() {
        return levels;
    }

    public UpRiseSpeed getUpRiseSpeed() {
        return upRiseSpeed;
    }

    @Override
    public String toString() {
        return "NewConfigDTO{" + "downRiseSpeed=" + downRiseSpeed + ", elasticityRules=" + elasticityRules + ", elasticitySpeed=" + elasticitySpeed + ", id=" + id + ", levels=" + levels + ", upRiseSpeed=" + upRiseSpeed + ", initialInstances=" + initialInstances + '}';
    }
	
	
	
	

	
	
}
