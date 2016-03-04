package es.us.isa.papamoscas.proxysla.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties()
public class GeneralConfig {
	
	
	private Map<String, String> urls;
	private Map<String, Integer> timers;
	
	
	public GeneralConfig(){
		
	}
	
	public Map<String, String> getUrls() {
		return this.urls;
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

	

}
