package es.us.isa.papamoscas;

import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@ComponentScan
@SpringBootApplication
@EnableAutoConfiguration
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		String myIP="";
		
		try {
			String interfaceName = "eth0";
		    NetworkInterface networkInterface = NetworkInterface.getByName(interfaceName);
		    Enumeration<InetAddress> inetAddress = networkInterface.getInetAddresses();
		    InetAddress currentAddress;
		    currentAddress = inetAddress.nextElement();
		    while(inetAddress.hasMoreElements())
		    {
		        currentAddress = inetAddress.nextElement();
		        if(currentAddress instanceof Inet4Address && !currentAddress.isLoopbackAddress())
		        {
		            myIP = currentAddress.toString().split("/")[1];
		            System.out.println(myIP);
		            break;
		        }
		    }
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		RestTemplate rest = new RestTemplate();
		try{
			String ip = getIP();
			rest.postForObject("http://"+ ip + ":8080/registry?ip="+myIP+":8080", "", String.class);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
	    TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();	
	    
	    tomcat.addConnectorCustomizers(new TomcatConnectorCustomizer() {
			
			@Override
			public void customize(Connector arg0) {
				arg0.setAttribute("acceptCount", 1);
				arg0.setAttribute("connectionTimeout", 10000);
				arg0.setAttribute("maxConnections", 1);
				arg0.setAttribute("maxThreads", 1);
//				arg0.setAttribute("maxQueueSize", 1);
			}
			
		});
	    
	    return tomcat;
	}
	
	public static String getIP(){
		File f = new File("/etc/environment");
		Path path = Paths.get(f.getAbsolutePath());
		String ret = "";
		try{
			List<String> files = Files.readAllLines(path, StandardCharsets.UTF_8);
			for (String file : files){
				if(file.contains("proxy")){
					String[] aux = file.split(";");
					for (String s : aux){
						if(s.contains("proxy"))
							ret = s.split("=")[1];
					}
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}		
		return ret;
	}
}
