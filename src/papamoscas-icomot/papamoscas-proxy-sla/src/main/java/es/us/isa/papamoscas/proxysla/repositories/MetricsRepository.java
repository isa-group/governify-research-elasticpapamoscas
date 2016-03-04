package es.us.isa.papamoscas.proxysla.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import es.us.isa.papamoscas.proxysla.services.StoreService;

@Repository	
public class MetricsRepository {
	
	public static final int OK = 1;
	public static final int ERROR = 0;
	
	@Autowired
	StoreService storeService;
	
	private static final Logger log = LoggerFactory.getLogger(MetricsRepository.class);
	
	//Throughput's Variables
	private static final Map<String, Integer> throughputRegistry = new HashMap<String, Integer>();
	private static final Map<String, List<Integer>> throughputSecondPerUser = new HashMap<>();
	
	//Avaiability's Variables
	private static final Map<String, Integer> totalRequestRegistry = new HashMap<>();
	private static final Map<String, Integer> OKRequestRegistry = new HashMap<>();
	
	
	
	//Timer's Variables
	@Value("${timers.availability}")
	private Integer availability;
	
	@Value("${timers.store}")
	private Integer store;
	
	@Value("${timers.throughput}")
	private Integer throughput;
	
	public MetricsRepository(){
		
	}
	
	@PostConstruct
	public void startTimers(){
		
		log.info("Set availability each "+ availability + "m");
		log.info("Set throughput each "+ throughput + "m");
		log.info("Update store each "+ store + "s");
		
		Timer timer = new Timer(true);
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				for(String user : MetricsRepository.throughputRegistry.keySet()){
					try{						
						
						addThroughtputSecond(user);	
						
					}catch(Exception e){
						e.printStackTrace();
					}
				}				
			}
			
		}, 0, 1 * 1000);
		
		Timer timer2 = new Timer(true);
		timer2.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				try{
					for(String user : MetricsRepository.throughputSecondPerUser.keySet()){
						MetricsRepository.throughputSecondPerUser.put(user, new ArrayList<Integer>(MetricsRepository.throughputSecondPerUser.get(user).subList(
								MetricsRepository.throughputSecondPerUser.get(user).size() - 1, MetricsRepository.throughputSecondPerUser.get(user).size())));
					}	
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
		}, throughput *1000, throughput *1000);
		
		Timer timer3 = new Timer(true);
		timer3.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				try{
					for(String user : MetricsRepository.totalRequestRegistry.keySet()){
						MetricsRepository.totalRequestRegistry.put(user, 0);
						MetricsRepository.OKRequestRegistry.put(user, 0);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
		}, 0 * 1000, availability * 60 * 1000);
	}
	protected void addThroughtputSecond(String user) {
		try{
			if(!MetricsRepository.throughputSecondPerUser.containsKey(user))
				MetricsRepository.throughputSecondPerUser.put(user, new ArrayList<Integer>());
			
			MetricsRepository.throughputSecondPerUser.get(user).add(MetricsRepository.throughputRegistry.get(user));
			MetricsRepository.throughputRegistry.put(user, 0);
		}catch(Exception e){
			e.printStackTrace();
		}		
	}

	public void addRequestIn(String user){
		try{
			
			if(!MetricsRepository.throughputRegistry.keySet().contains(user)){
				MetricsRepository.throughputRegistry.put(user, 0);
				MetricsRepository.totalRequestRegistry.put(user, 0);
				MetricsRepository.OKRequestRegistry.put(user, 0);
			}
			
			MetricsRepository.throughputRegistry.put(user, MetricsRepository.throughputRegistry.get(user) + 1);
			//MetricsRepository.totalRequestRegistry.put(user, MetricsRepository.totalRequestRegistry.get(user) + 1);
			
		}catch (Exception e) {
			
			e.printStackTrace();
			
		}
	}
	
	public void addRequestOK(String user, String req){
		try{
			
			if(!MetricsRepository.totalRequestRegistry.keySet().contains(user)){
				MetricsRepository.totalRequestRegistry.put(user, 0);
				MetricsRepository.OKRequestRegistry.put(user,0);
			}
			
			MetricsRepository.totalRequestRegistry.put(user, MetricsRepository.totalRequestRegistry.get(user) + 1);
			if(req.equals("OK"))
				MetricsRepository.OKRequestRegistry.put(user, MetricsRepository.OKRequestRegistry.get(user) + 1);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Integer getThroughput(String user)  {
		if(!MetricsRepository.throughputRegistry.keySet().contains(user)){
			MetricsRepository.throughputRegistry.put(user, 0);
			MetricsRepository.totalRequestRegistry.put(user, 0);
			MetricsRepository.OKRequestRegistry.put(user, 0);
		}
		Integer ret = 0;
		try {	
			
			for (Integer i : MetricsRepository.throughputSecondPerUser.get(user)){
				ret += i;
			}
			
			ret = ret / MetricsRepository.throughputSecondPerUser.get(user).size();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret; 	
	}
	
	public Double getAvailability(String user){
		if(!MetricsRepository.totalRequestRegistry.keySet().contains(user)){
			MetricsRepository.totalRequestRegistry.put(user, 0);
			MetricsRepository.OKRequestRegistry.put(user, 0);
		}
		Double ret = 1.0;
		try{
			ret = new Double(MetricsRepository.OKRequestRegistry.get(user)) /  new Double(MetricsRepository.totalRequestRegistry.get(user));
			
			if(ret.isNaN() || ret.isInfinite())
				ret = 1.0;
			
			if(ret > 1)
				ret = 1.0;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return ret * 100; 
	}
	
	public Map<String, Map<String, Integer>> getRegistry(){
		Map<String, Map<String, Integer>> registry = new HashMap<String, Map<String, Integer>>();
		registry.put("throughputRegistry", MetricsRepository.throughputRegistry);
		registry.put("totalRequestRegistry", MetricsRepository.totalRequestRegistry);
		registry.put("OKRequestRegistry", MetricsRepository.OKRequestRegistry);
		
		return registry;
	}
	
	public Integer getTotalThroughput(){
		Integer ret = 0;
		
		for (String i : MetricsRepository.throughputSecondPerUser.keySet() ){
			ret += getThroughput(i);
		}
		
		return ret;
	}
	
	
}
