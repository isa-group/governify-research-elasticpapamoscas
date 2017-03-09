package es.us.isa.papamoscas.proxysla.repositories;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository	
public class RouterRepository {
	
	private static final Map<String, Map<String, String>> registry = new HashMap<>();
	private static Integer id = 0;
	
	private static final Map<String, String> routingTable = new HashMap<String, String>();
	private static final Map<String, String> assignmentTable = new HashMap<String, String>();
	
	//permited_request
	private static final Map<String, Boolean> permitedRequest = new HashMap<>();
	
	public String putNode(String type, String ip){
		String nodeName = "node_" + RouterRepository.id;
		
		if(RouterRepository.registry.keySet().contains(type)){
			RouterRepository.registry.get(type).put(nodeName, ip);
		}else{
			Map<String, String> aux = new HashMap<String, String>();
			aux.put(nodeName, ip);
			RouterRepository.registry.put(type, aux);
		}
		
		RouterRepository.id ++;
		return nodeName;
	}
	
	public Map<String, Map<String, String>> deleteNode (String name){
		for(Map<String, String> s : RouterRepository.registry.values()){
			if(s.keySet().contains(name)){
				s.remove(name);
			}
		}
		return RouterRepository.registry;
	}
	
	public Map<String, Map<String, String>> getRegistry(){
		return RouterRepository.registry;
	}
	
	/** ROUTER METHODs **/
	public void setUserRoute (String user, String level){
		RouterRepository.routingTable.put(user, level);
	}
	
	public Map<String, String> getRoutingTable(){
		return RouterRepository.routingTable;
	}
	
	public String getLevelForUser (String user){
		if(!routingTable.containsKey(user)){
			RouterRepository.routingTable.put(user, "l00");
			RouterRepository.assignmentTable.put(user, "l00");
		}
		
		return RouterRepository.routingTable.get(user);
	}
	
	/** REQUESTS IS PERMITED METHODs **/
	public Boolean isPermitedRequest(String user){
		if(!RouterRepository.permitedRequest.containsKey(user))
			RouterRepository.permitedRequest.put(user, true);
		return RouterRepository.permitedRequest.get(user);
	}
	
	public void setPermitedRequest(String user, Boolean permited){
		RouterRepository.permitedRequest.put(user,permited);
	}
	
	/** ASSIGNEMET METHODs **/
	public void assigneUserRouter(String user, String level){
		RouterRepository.assignmentTable.put(user, level);
	}
	
	public Map<String, String> getAssignmentTable(){
		return RouterRepository.assignmentTable;
	}
	
	public String getAssignedLevelForUser (String user){
		if(!assignmentTable.containsKey(user))
			RouterRepository.assignmentTable.put(user, "l00");
		
		return RouterRepository.assignmentTable.get(user);
	}
}
