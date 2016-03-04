package es.us.isa.papamoscas.proxy;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	// CONFIGURE TOMCAT TO ACCEPT A NUMBER OF CONNECTIONS
		@Bean
		public EmbeddedServletContainerFactory servletContainer() {
		    TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();	
		    
		    tomcat.addConnectorCustomizers(new TomcatConnectorCustomizer() {
				
				@Override
				public void customize(Connector arg0) {
					arg0.setAttribute("acceptCount", 100000);
//					arg0.setAttribute("connectionTimeout", 1);
					arg0.setAttribute("maxConnections", 100000);
					arg0.setAttribute("maxThreads", 1000);
				}
				
			});
		    
		    return tomcat;
		}
}
