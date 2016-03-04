package es.us.isa.papamoscas.proxysla.config;

import java.util.Map;

public class ConfigDTO {
	
	private Map<String, String> urls;
	private Map<String, Integer> timers;
	private GovernanceConfigDTO governanceProperties;
	
	
	public ConfigDTO (){
		
	}

	public Map<String, String> getUrls() {
		return urls;
	}

	public void setUrls(Map<String, String> urls) {
		this.urls = urls;
	}

	public Map<String, Integer> getTimers() {
		return timers;
	}

	public void setTimers(Map<String, Integer> timers) {
		this.timers = timers;
	}

	public GovernanceConfigDTO getGovernanceProperties() {
		return governanceProperties;
	}

	public void setGovernanceProperties(GovernanceConfigDTO governanceProperties) {
		this.governanceProperties = governanceProperties;
	}

	@Override
	public String toString() {
		return "ConfigDTO [urls=" + urls + ", timers=" + timers
				+ ", governConfig=" + governanceProperties + ", routerConfig="
				+ "]";
	}

	

	
	
}
