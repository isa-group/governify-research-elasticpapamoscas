package es.us.isa.icomot.deployer;

import static at.ac.tuwien.dsg.comot.common.model.ArtifactTemplate.MiscArtifact;
import static at.ac.tuwien.dsg.comot.common.model.ArtifactTemplate.SingleScriptArtifact;
import static at.ac.tuwien.dsg.comot.common.model.BASHAction.BASHAction;
import static at.ac.tuwien.dsg.comot.common.model.CloudService.ServiceTemplate;
import static at.ac.tuwien.dsg.comot.common.model.CommonOperatingSystemSpecification.DockerDefault;
import static at.ac.tuwien.dsg.comot.common.model.CommonOperatingSystemSpecification.LocalDocker;
import static at.ac.tuwien.dsg.comot.common.model.DockerUnit.DockerUnit;
import static at.ac.tuwien.dsg.comot.common.model.EntityRelationship.ConnectToRelation;
import static at.ac.tuwien.dsg.comot.common.model.EntityRelationship.HostedOnRelation;
import static at.ac.tuwien.dsg.comot.common.model.OperatingSystemUnit.OperatingSystemUnit;
import static at.ac.tuwien.dsg.comot.common.model.ServiceTopology.ServiceTopology;
import static at.ac.tuwien.dsg.comot.common.model.SoftwareNode.SingleSoftwareUnit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;

import at.ac.tuwien.dsg.cloud.salsa.common.cloudservice.model.ServiceUnit;
import at.ac.tuwien.dsg.comot.common.model.ArtifactTemplate;
import at.ac.tuwien.dsg.comot.common.model.BundleConfig;
import at.ac.tuwien.dsg.comot.common.model.BundleConfig.RuntimeConfig;
import at.ac.tuwien.dsg.comot.common.model.Capability;
import at.ac.tuwien.dsg.comot.common.model.CloudService;
import at.ac.tuwien.dsg.comot.common.model.DockerUnit;
import at.ac.tuwien.dsg.comot.common.model.ElasticityCapability;
import at.ac.tuwien.dsg.comot.common.model.EntityRelationship;
import at.ac.tuwien.dsg.comot.common.model.LifecyclePhase;
import at.ac.tuwien.dsg.comot.common.model.OperatingSystemUnit;
import at.ac.tuwien.dsg.comot.common.model.Requirement;
import at.ac.tuwien.dsg.comot.common.model.ServiceTopology;
import at.ac.tuwien.dsg.comot.common.model.SoftwareNode;
import at.ac.tuwien.dsg.icomot.iCOMOTOrchestrator;
import es.us.isa.icomot.deployer.domain.Component;
import es.us.isa.icomot.deployer.domain.ServiceDescription;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String jsonDescription = args[0];
		
		ObjectMapper mapper = new ObjectMapper();
		ServiceDescription sd;
		
		try {
			/** Mappe JSON **/
			sd = mapper.readValue(jsonDescription, ServiceDescription.class);
			
			String serviceId = sd.getId(); //get from configuration.
			String topologyId = serviceId.toLowerCase() + "_topology"; //get from configuration
			List<Component> components = sd.getComponents();
			
			/** Create TOSCA topology and service template **/
			ServiceTopology topology = ServiceTopology(topologyId);
			
			CloudService serviceTemplate = ServiceTemplate(serviceId)
					.consistsOfTopologies(topology)
					.withDefaultMetrics();
			
			/** Create OS_Docker Service Unit **/
			OperatingSystemUnit osDocker = OperatingSystemUnit("OS_Docker")
					.providedBy(LocalDocker());
			
			topology.withServiceUnits(osDocker);
			
			
			/** 
			 * For component create DockerUnit(image/container), ElasticityCapabilities,
			 * and SingleSoftwareUnit.
			 * [
			 *   id: papamoscas-api
			 *   packages:
			 *    - openjdk-7-jre
			 *    - ganglia-monitor
			 *    - gmetad
			 *   script: http://localhost/scriptFordeploy.sh
			 *   artifact: http://localhost/artifact[.tar.gz | .war]
			 *   elasticity: true
			 *   exposes: true
			 *   requires: 
			 *     - slaProxy
			 *     - cassandra
			 * ]
			 * 
			 */
			
			Map<String, SoftwareNode> serviceunits = new HashMap<String, SoftwareNode>();
			
			for(Component c : components){
				/** Create DockerUnit **/
				String containerUnitName = "c_" + c.getId();
				String packages = Joiner.on(" ").join(c.getPackages());
				
				DockerUnit containerUnit_aux = DockerUnit(containerUnitName)
		            .providedBy(DockerDefault()
		                .addSoftwarePackage(packages));
				
				/** Create ServiceUnit **/
				String serviceUnitName = c.getId();
				
				SoftwareNode serviceUnit_aux = SingleSoftwareUnit(serviceUnitName)
						.deployedBy(SingleScriptArtifact(c.getScript()))
						.deployedBy(MiscArtifact(c.getArtifact()))
						.withMaxColocatedInstances(1);
				
				
				/** Add exposes **/
				if(c.getExposes()){
					serviceUnit_aux = serviceUnit_aux.exposes(Capability.Variable(serviceUnitName + "_IP_information"));
				}
				
				if(c.getUndeployScript()){
					serviceUnit_aux = serviceUnit_aux.withLifecycleAction(LifecyclePhase.STOP, BASHAction("sudo bash /tmp/undeploy.sh"));
				}
				
				/** Add requirements **/
				
				for(String s : c.getRequires()){
					serviceUnit_aux = serviceUnit_aux.requires(Requirement.Variable(s + "_IP_Req").withName(s));
				}
				
				/** If elasticity = true then it prepares EslasticityCapability **/
				
				if(c.getElasticity()){
					ElasticityCapability scaleIn = ElasticityCapability.ScaleIn();
					ElasticityCapability scaleOut = ElasticityCapability.ScaleOut();
					
					serviceUnit_aux = serviceUnit_aux.provides(scaleIn, scaleOut);
					serviceUnit_aux = serviceUnit_aux.withMinInstances(c.getInstances());
				}
				
				serviceunits.put(c.getId(), serviceUnit_aux);
				
				/** Add component units to topology **/
				topology.withServiceUnits(containerUnit_aux, serviceUnit_aux);
				
				/** Add hosted On Realationships **/
				serviceTemplate.andRelationships(
						
						//Container to OS
						HostedOnRelation(containerUnitName + "ToDocker" )
	                    .from(containerUnit_aux)
	                    .to(osDocker),
	                    
	                    //Software to Container
						HostedOnRelation(c.getId() + "ToContainer")
	                    .from(serviceUnit_aux)
	                    .to(containerUnit_aux)                    
	                    
				);	
			}
			
			
			/** Add relationships between components **/
			for(Component c : components){
				
				for(String s : c.getRequires()){
					serviceTemplate.andRelationships(
						ConnectToRelation(c.getId() + "To" + s)
	                        .from(serviceunits.get(s).getContext().get(s + "_IP_information"))
	                        .to(serviceunits.get(c.getId()).getContext().get(s + "_IP_Req")) //specify which software unit goes to which VM
					);
				}
				
			}
			
			/** Deploy service template **/		
			iCOMOTOrchestrator orchestrator = new iCOMOTOrchestrator("localhost");
	        orchestrator.deployAndControl(serviceTemplate);
	        
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
//		Component api = new Component(
//				"papamoscas-api",
//				"http://raw.githubusercontent.com/isa-group/governify-research-elasticpapamoscas/master/prototype/papamoscas-icomot/papamoscas-deployment-files/papamoscas-spring-boot/deploy.sh",
//				"http://github.com/isa-group/governify-research-elasticpapamoscas/raw/master/prototype/papamoscas-icomot/papamoscas-deployment-files/papamoscas-spring-boot/papamoscas-spring-boot.tar.gz",
//				true,
//				true				
//				);
//		
//		api.addPackage("openjdk-7-jre"); api.addPackage("ganglia-monitor"); api.addPackage("gmetad");
//
//		sd.addComponent(api);
		
		
	}

}
