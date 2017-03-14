package es.us.isa.papamoscas.proxysla;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.catalina.connector.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.us.isa.papamoscas.proxysla.config.NewConfigDTO;

@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class Application {

    @Autowired
    private static Environment env;

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
        env = SpringApplication.run(Application.class, args).getEnvironment();

//        Timer updateConfig = new Timer(true);
//        updateConfig.scheduleAtFixedRate(new TimerTask() {
//
//            @Override
//            public void run() {
//                // TODO Auto-generated method stub
//                try {
//                    log.info("Getting config from: " + env.getProperty("urls.config"));
//
//                    RestTemplate rest = new RestTemplate();
//                    rest.getMessageConverters().add(new StringHttpMessageConverter());
//
//                    if (env.getProperty("urls.config") != null) {
//                        String configDto = rest.getForObject(env.getProperty("urls.config"), String.class);
//
//                        ObjectMapper mapper = new ObjectMapper();
//                        NewConfigDTO dto = mapper.readValue(configDto, NewConfigDTO.class);
//                        @SuppressWarnings("unused")
//                        ResponseEntity<NewConfigDTO> ret = rest.postForEntity("http://localhost:" + env.getProperty("server.port") + "/configs", dto, NewConfigDTO.class);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }, 0, new Integer(env.getProperty("timers.config")) * 1000);

    }

// CONFIGURE TOMCAT TO ACCEPT A NUMBER OF CONNECTIONS
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();

        tomcat.addConnectorCustomizers(new TomcatConnectorCustomizer() {

            @Override
            public void customize(Connector arg0) {
                arg0.setAttribute("acceptCount", 100000);
                arg0.setAttribute("maxConnections", 100000);
                arg0.setAttribute("maxThreads", 1000);
            }

        });

        return tomcat;
    }

}
