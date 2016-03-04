package es.us.isa.papamoscas.proxysla.config;

import java.util.List;
import java.util.Map;

public class GovernanceConfigDTO {
	
	private  List<String> levels;
	
	private Map<String, String> service;
	
	private Map<String, Double> levelElasticityPercentages;
	
	private Map<String, String> defaultRules;
	
	private Map<String, Double> routerConfig;
	
	public GovernanceConfigDTO(){
		
	}

	public Map<String, String> getService() {
		return service;
	}

	public void setService(Map<String, String> service) {
		this.service = service;
	}

	public List<String> getLevels() {
		return levels;
	}

	public void setLevels(List<String> levels) {
		this.levels = levels;
	}

	public Map<String, Double> getLevelElasticityPercentages() {
		return levelElasticityPercentages;
	}

	public void setLevelElasticityPercentages(
			Map<String, Double> levelElasticityPercentages) {
		this.levelElasticityPercentages = levelElasticityPercentages;
	}

	public Map<String, String> getDefaultRules() {
		return defaultRules;
	}

	public void setDefaultRules(Map<String, String> defaultRules) {
		this.defaultRules = defaultRules;
	}

	public Map<String, Double> getRouterConfig() {
		return routerConfig;
	}

	public void setRouterConfig(Map<String, Double> routerConfig) {
		this.routerConfig = routerConfig;
	}

	@Override
	public String toString() {
		return "GovernanceConfigDTO [levels=" + levels + ", service=" + service
				+ ", levelElasticityPercentages=" + levelElasticityPercentages
				+ ", defaultRules=" + defaultRules + ", routerConfig="
				+ routerConfig + "]";
	}
	
	
	
	
}
