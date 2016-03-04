package es.us.isa.papamoscas.repository;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

@Repository	
public class MetricsRepository {
	
	public static final int OK = 1;
	
	private static Integer throughput = 0;
	private static List<Integer> throughputPerSecond = new ArrayList<Integer>();
	
	
	public MetricsRepository(){
		
	}
	
	@PostConstruct
	public void initMetrics(){
		setMetric();
		Timer timer = new Timer(true);
		timer.scheduleAtFixedRate(new TimerTask() {			
				@Override
				public void run() {
					try{
						
						MetricsRepository.throughputPerSecond.add(MetricsRepository.throughput);
						MetricsRepository.throughput = 0;
						
					}catch(Exception e){
						
						e.printStackTrace();
						
					}
				}			
			
		}, 0, 1 * 1000);
		
		Timer timer2 = new Timer(true);
		timer2.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				try{
					
					List<Integer> newlist = new ArrayList<Integer>();
					newlist.add(getThroughput());
					MetricsRepository.throughputPerSecond = new ArrayList<Integer>(newlist);
					
				}catch (Exception e) {
					
					e.printStackTrace();
					
				}
			}
			
		}, 1*60*1000, 1*60*1000);
	}
	
	public void addRequestIn(){
		MetricsRepository.throughput += 1;
	}
	
	
	public Integer getThroughput() throws IllegalArgumentException {
		Integer ret = 0;
			
		for (Integer i : MetricsRepository.throughputPerSecond){
			ret += i;
		}
		
		try{
			ret = ret / MetricsRepository.throughputPerSecond.size();
		}catch(Exception e){
			e.printStackTrace();
		}		
		
		return 	ret;	
	}
	
	public void setMetric(){
		MetricsRepository.throughput = 0;
	}
	
	public Map<String , String> getRegistry(){
		Map<String, String> ret = new HashMap<String, String>();
		
		ret.put("throughput", getThroughput().toString());
		ret.put("listOfLastThroughput", MetricsRepository.throughputPerSecond.toString());
		
		return ret;
	}
}
