package es.us.isa.papamoscas.proxy.repositories;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository	
public class RegistryRepository {
	
	private static final List<String> registry = new ArrayList<String>();
	private static Integer last = -1;
	
	public Integer putNode(String ip){
		if(!RegistryRepository.registry.contains(ip))
			RegistryRepository.registry.add(ip);
		return RegistryRepository.registry.indexOf(ip);
	}
	
	public void deleteNode (String ip){
		RegistryRepository.registry.remove(ip);
	}
	
	public List<String> getRegistry(){
		return RegistryRepository.registry;
	}
	
	public Integer getNext(){
		if(registry.size() == 0){
			last = -1;
			return last;
		}else{
			last = (last + 1) % registry.size();
			return last;
		}
	}
	
}
