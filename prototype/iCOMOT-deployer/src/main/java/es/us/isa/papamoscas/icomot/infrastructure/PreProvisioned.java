package es.us.isa.papamoscas.icomot.infrastructure;

import java.util.Map;

import static at.ac.tuwien.dsg.comot.common.model.ArtifactTemplate.MiscArtifact;
import static at.ac.tuwien.dsg.comot.common.model.ArtifactTemplate.SingleScriptArtifact;
import static at.ac.tuwien.dsg.comot.common.model.BASHAction.BASHAction;
import at.ac.tuwien.dsg.comot.common.model.Capability;
import at.ac.tuwien.dsg.comot.common.model.Constraint;
import at.ac.tuwien.dsg.comot.common.model.Constraint.Metric;
import static at.ac.tuwien.dsg.comot.common.model.EntityRelationship.ConnectToRelation;
import static at.ac.tuwien.dsg.comot.common.model.EntityRelationship.HostedOnRelation;
import at.ac.tuwien.dsg.comot.common.model.LifecyclePhase;
import at.ac.tuwien.dsg.comot.common.model.OperatingSystemUnit;
import at.ac.tuwien.dsg.comot.common.model.Requirement;
import at.ac.tuwien.dsg.comot.common.model.CloudService;
import static at.ac.tuwien.dsg.comot.common.model.CloudService.ServiceTemplate;
import static at.ac.tuwien.dsg.comot.common.model.CommonOperatingSystemSpecification.DockerDefault;
import static at.ac.tuwien.dsg.comot.common.model.CommonOperatingSystemSpecification.LocalDocker;
import static at.ac.tuwien.dsg.comot.common.model.DockerUnit.DockerUnit;
import at.ac.tuwien.dsg.comot.common.model.DockerUnit;
import at.ac.tuwien.dsg.comot.common.model.ElasticityCapability;
import static at.ac.tuwien.dsg.comot.common.model.OperatingSystemUnit.OperatingSystemUnit;
import at.ac.tuwien.dsg.comot.common.model.ServiceTopology;
import static at.ac.tuwien.dsg.comot.common.model.ServiceTopology.ServiceTopology;
import at.ac.tuwien.dsg.comot.common.model.ServiceUnit;
import static at.ac.tuwien.dsg.comot.common.model.SoftwareNode.SingleSoftwareUnit;
import static at.ac.tuwien.dsg.comot.common.model.Strategy.Strategy;
import at.ac.tuwien.dsg.icomot.iCOMOTOrchestrator;
import es.us.isa.papamoscas.icomot.util.ProcessArgs;
import es.us.isa.papamoscas.icomot.util.ProcessArgs.Arg;

/**
 * This example deploys an elastic IOT platform running in the cloud
 *
 * @author http://dsg.tuwien.ac.at
 */
public class PreProvisioned {

    public static void main(String[] args) {
        //specify service units in terms of software

    	Map<Arg, String> argsMap = ProcessArgs.processArgs(args);
    	
        String platformRepo = "http://" + argsMap.get("INTERNAL_IP") + "/ppm-icomot/papamoscas-config/";
        String miscRepo = "http://" + argsMap.get("INTERNAL_IP") + "/papamoscas-config/";

        //define localhost docker
        OperatingSystemUnit osDockerPro = OperatingSystemUnit("osDockerPro")
                .providedBy(LocalDocker()
                );

        //define localhost docker
        OperatingSystemUnit osDockerBasic = OperatingSystemUnit("osDockerBasic")
                .providedBy(LocalDocker()
                );

        //define localhost docker
        OperatingSystemUnit osDockerCassandra = OperatingSystemUnit("osDockerCassandra")
                .providedBy(LocalDocker()
                );
     ///////////////////////////////////////////////////////////////////////////////////////////////////////
     //////////////////////////////////////////////////////////////////////////////////////////////////////
       //////////////////////////////////////////////////////////////////////////////////////////////////

        //need to specify details of VM and operating system to deploy the software servide units on
        DockerUnit papamoscasBasicAPIVM = DockerUnit("PapamoscasBasicAPIVM")
                .providedBy(DockerDefault()
                		.addSoftwarePackage("openjdk-7-jre ganglia-monitor gmetad")

                ).withMaxColocatedInstances(5);

        DockerUnit papamoscasBasicProxyVM = DockerUnit("PapamoscasBasicProxyVM")
                .providedBy(DockerDefault()
                		.addSoftwarePackage("openjdk-7-jre ganglia-monitor gmetad")

                );

        DockerUnit papamoscasSLAProxyVM = DockerUnit("PapamoscasSLAProxyVM")
                .providedBy(DockerDefault()
                		.addSoftwarePackage("openjdk-7-jre ganglia-monitor gmetad")

                );
      //need to specify details of VM and operating system to deploy the software servide units on
//        DockerUnit papamoscasProAPIVM = DockerUnit("PapamoscasProAPIVM")
//                .providedBy(DockerDefault()
//                		.addSoftwarePackage("openjdk-7-jre ganglia-monitor gmetad")
//
//                );
//
//        DockerUnit papamoscasProProxyVM = DockerUnit("PapamoscasProProxyVM")
//                .providedBy(DockerDefault()
//                		.addSoftwarePackage("openjdk-7-jre ganglia-monitor gmetad")
//
//                );

        DockerUnit cassandraVM = DockerUnit("cassandraUnitVM")
                .providedBy(DockerDefault()
                		.addSoftwarePackage("openjdk-7-jre ganglia-monitor gmetad")

                );

        //start with Data End, and first with Data Controller
        ElasticityCapability papamoscasAPIUnitScaleIn = ElasticityCapability.ScaleIn();
        ElasticityCapability papamoscasAPIUnitScaleOut = ElasticityCapability.ScaleOut();

        ServiceUnit papamoscasBasicAPIUnit = SingleSoftwareUnit("papamoscasBasicAPIUnit")
                //software artifacts needed for unit deployment   = software artifact archive and script to deploy Cassandra
                //.deployedBy(SingleScriptArtifact(platformRepo + "papamoscas-api-basic/deploy.sh"))
                .deployedBy(SingleScriptArtifact(platformRepo + "papamoscas-spring-boot/deploy.sh"))
                .deployedBy(MiscArtifact(platformRepo + "papamoscas-spring-boot/papamoscas-spring-boot.tar.gz"))
                //data controller exposed its IP
                .exposes(Capability.Variable("papamoscasAPI_IP_information"))
        		.requires(Requirement.Variable("papamoscasProxy_IP_Req").withName("requiringpapamoscasProxyIP"))
        		.requires(Requirement.Variable("papamoscasBasicAPIUnit_cassandraUnit_IP_Req").withName("databaseIP"))
        		.provides(papamoscasAPIUnitScaleIn, papamoscasAPIUnitScaleOut)
//                .controlledBy(Strategy("DN_ST1")
//                        .when(Constraint.MetricConstraint("DN_ST1_CO1", new Metric("throughput", "#")).greaterThan("100"))
//                        .enforce(papamoscasAPIUnitScaleIn)
//                )
//                .controlledBy(Strategy("DN_ST2")
//                        .when(Constraint.MetricConstraint("DN_ST2_CO1", new Metric("throughput", "#")).lessThan("-200"))
//                        .and(Constraint.MetricConstraint("DN_ST2_CO2", new Metric("throughput", "#")).greaterEqual("1000000"))
//                        .enforce(papamoscasAPIUnitScaleOut)
//                )
        		.withLifecycleAction(LifecyclePhase.STOP, BASHAction("sudo bash /tmp/undeploy.sh"))
                .withMaxColocatedInstances(1)
                .withMinInstances(5);

        //start with Data End, and first with Data Controller
//        ElasticityCapability papamoscasProAPIUnitScaleIn = ElasticityCapability.ScaleIn();
//        ElasticityCapability papamoscasProAPIUnitScaleOut = ElasticityCapability.ScaleOut();
//
//        ServiceUnit papamoscasProAPIUnit = SingleSoftwareUnit("papamoscasProAPIUnit")
//                //software artifacts needed for unit deployment   = software artifact archive and script to deploy Cassandra
//                //.deployedBy(SingleScriptArtifact(platformRepo + "papamoscas-api-basic/deploy.sh"))
//                .deployedBy(SingleScriptArtifact(platformRepo + "papamoscas-spring-boot/deploy.sh"))
//                .deployedBy(MiscArtifact(platformRepo + "papamoscas-spring-boot/papamoscas-spring-boot.tar.gz"))
//                //data controller exposed its IP
//                .exposes(Capability.Variable("papamoscasProAPI_IP_information"))
//        		.requires(Requirement.Variable("papamoscasProProxy_IP_Req"))
//        		.requires(Requirement.Variable("papamoscasProAPIUnit_cassandraUnit_IP_Req"))
//        		.provides(papamoscasProAPIUnitScaleIn, papamoscasProAPIUnitScaleOut)
////                .controlledBy(Strategy("DN_ST1")
////                        .when(Constraint.MetricConstraint("DN_ST1_CO1", new Metric("throughput", "#")).greaterThan("100"))
////                        .enforce(papamoscasProAPIUnitScaleIn)
////                )
////                .controlledBy(Strategy("DN_ST2")
////                        .when(Constraint.MetricConstraint("DN_ST2_CO1", new Metric("throughput", "#")).lessThan("-50"))
////                        .and(Constraint.MetricConstraint("DN_ST2_CO2", new Metric("throughput", "#")).greaterEqual("1000000"))
////                        .enforce(papamoscasProAPIUnitScaleOut)
////                )
//        		.withLifecycleAction(LifecyclePhase.STOP, BASHAction("sudo bash /tmp/undeploy.sh"))
//                .withMinInstances(2)
//                .withMaxColocatedInstances(1);

        ServiceUnit papamoscasBasicProxyUnit = SingleSoftwareUnit("papamoscasBasicProxyUnit")
                //software artifacts needed for unit deployment   = software artifact archive and script to deploy Cassandra

        		.deployedBy(SingleScriptArtifact(platformRepo + "papamoscas-proxy/deploy-basic.sh"))
                .deployedBy(MiscArtifact(platformRepo + "papamoscas-proxy/papamoscas-proxy.war"))
                //data controller exposed its IP
                .exposes(Capability.Variable("papamoscasProxy_IP_information"))
                .requires(Requirement.Variable("papamoscasBasicProxy_papamoscasSLAProxy_IP_Req"));

//        ServiceUnit papamoscasProProxyUnit = SingleSoftwareUnit("papamoscasProProxyUnit")
//                //software artifacts needed for unit deployment   = software artifact archive and script to deploy Cassandra
//
//        		.deployedBy(SingleScriptArtifact(platformRepo + "papamoscas-proxy/deploy-pro.sh"))
//                .deployedBy(MiscArtifact(platformRepo + "papamoscas-proxy/papamoscas-proxy.war"))
//                //data controller exposed its IP
//                .exposes(Capability.Variable("papamoscasProProxy_IP_information"))
//                .requires(Requirement.Variable("papamoscasProProxy_papamoscasSLAProxy_IP_Req"));

        ServiceUnit papamoscasSLAProxyUnit = SingleSoftwareUnit("papamoscasSLAProxyUnit")
                //software artifacts needed for unit deployment   = software artifact archive and script to deploy Cassandra

        		.deployedBy(SingleScriptArtifact(platformRepo + "papamoscas-proxy-sla/deploy.sh"))
                .deployedBy(MiscArtifact(platformRepo + "papamoscas-proxy-sla/papamoscas-proxy-sla.tar.gz"))
                //data controller exposed its IP
                .exposes(Capability.Variable("papamoscasSLAProxy_IP_information"));

        ServiceUnit cassandraUnit = SingleSoftwareUnit("cassandraUnit")
                //software artifacts needed for unit deployment   = software artifact archive and script to deploy Cassandra

        		.deployedBy(SingleScriptArtifact(platformRepo + "papamoscas-cassandra/deploy.sh"))
                .deployedBy(MiscArtifact(platformRepo + "papamoscas-cassandra/apache-cassandra-2.2.1.tar.gz"))
                //data controller exposed its IP
                .exposes(Capability.Variable("cassandraUnit_IP_information"));

        ServiceTopology papamoscasTopology = ServiceTopology("papamoscasTopology")
                .withServiceUnits(osDockerBasic,papamoscasBasicAPIVM, papamoscasBasicProxyVM, papamoscasSLAProxyVM, cassandraVM,
                		cassandraUnit, papamoscasBasicAPIUnit, papamoscasBasicProxyUnit, papamoscasSLAProxyUnit);

        //describe the service template which will hold more topologies
        CloudService serviceTemplate = ServiceTemplate("PreProvisioned")
                .consistsOfTopologies(papamoscasTopology)
                //defining CONNECT_TO and HOSTED_ON relationships
                .andRelationships(
                        //connections
                        ConnectToRelation("proxy")
                        .from(papamoscasBasicProxyUnit.getContext().get("papamoscasProxy_IP_information"))
                        .to(papamoscasBasicAPIUnit.getContext().get("papamoscasProxy_IP_Req")) //specify which software unit goes to which VM
                        ,
//                        ConnectToRelation("proxyPro")
//                        .from(papamoscasProProxyUnit.getContext().get("papamoscasProProxy_IP_information"))
//                        .to(papamoscasProAPIUnit.getContext().get("papamoscasProProxy_IP_Req")) //specify which software unit goes to which VM
//                        ,
//                        ConnectToRelation("databasePro")
//                        .from(cassandraUnit.getContext().get("cassandraUnit_IP_information"))
//                        .to(papamoscasProAPIUnit.getContext().get("papamoscasProAPIUnit_cassandraUnit_IP_Req")) //specify which software unit goes to which VM
//                        ,
                        ConnectToRelation("database")
                        .from(cassandraUnit.getContext().get("cassandraUnit_IP_information"))
                        .to(papamoscasBasicAPIUnit.getContext().get("papamoscasBasicAPIUnit_cassandraUnit_IP_Req")) //specify which software unit goes to which VM
                        ,
                        ConnectToRelation("slaProxy")
                        .from(papamoscasSLAProxyUnit.getContext().get("papamoscasSLAProxy_IP_information"))
                        .to(papamoscasBasicProxyUnit.getContext().get("papamoscasBasicProxy_papamoscasSLAProxy_IP_Req")) //specify which software unit goes to which VM
                        ,
//                        ConnectToRelation("slaProxyPro")
//                        .from(papamoscasSLAProxyUnit.getContext().get("papamoscasSLAProxy_IP_information"))
//                        .to(papamoscasProProxyUnit.getContext().get("papamoscasProProxy_papamoscasSLAProxy_IP_Req")) //specify which software unit goes to which VM
//                        ,
                       //containers hosted
                        HostedOnRelation("papamoscasProxyToLaptop")
                        .from(papamoscasBasicProxyVM)
                        .to(osDockerBasic),
//                        HostedOnRelation("papamoscasProProxyToLaptop")
//                        .from(papamoscasProProxyVM)
//                        .to(osDockerBasic),
                        HostedOnRelation("papamoscasAPIToLaptop")
                        .from(papamoscasBasicAPIVM)
                        .to(osDockerBasic),
//                        HostedOnRelation("papamoscasProAPIToLaptop")
//                        .from(papamoscasProAPIVM)
//                        .to(osDockerBasic),
                        HostedOnRelation("cassandraToLaptop")
                        .from(cassandraVM)
                        .to(osDockerBasic),
                        HostedOnRelation("papamoscasSLAProxyToLaptop")
                        .from(papamoscasSLAProxyVM)
                        .to(osDockerBasic),

                        //software hosted
                        HostedOnRelation("papamoscasAPIUnitToVM")
                        .from(papamoscasBasicAPIUnit)
                        .to(papamoscasBasicAPIVM),
                        HostedOnRelation("papamoscasSLAProxyUnitToVM")
                        .from(papamoscasSLAProxyUnit)
                        .to(papamoscasSLAProxyVM),
//                        HostedOnRelation("papamoscasProAPIUnitToVM")
//                        .from(papamoscasProAPIUnit)
//                        .to(papamoscasProAPIVM),
                        HostedOnRelation("papamoscasProxyUnitToVM")
                        .from(papamoscasBasicProxyUnit)
                        .to(papamoscasBasicProxyVM),
//                        HostedOnRelation("papamoscasProProxyUnitToVM")
//                        .from(papamoscasProProxyUnit)
//                        .to(papamoscasProProxyVM),
                        HostedOnRelation("cassandraUnitToVM")
                        .from(cassandraUnit)
                        .to(cassandraVM)

                )
                .withDefaultMetrics();

        iCOMOTOrchestrator orchestrator = new iCOMOTOrchestrator("localhost");
        // added to make it easier to run as jar from cmd line
        {
            for (Arg key : argsMap.keySet()) {
                switch (key) {
                    case ORCHESTRATOR_IP:
                        orchestrator.withIP(argsMap.get(key));
                        break;
                    case SALSA_IP:
                        orchestrator.withSalsaIP(argsMap.get(key));
                        break;
                    case SALSA_PORT:
                        orchestrator.withSalsaPort(Integer.parseInt(argsMap
                                .get(key)));
                        break;
                    case rSYBL_IP:
                        orchestrator.withRsyblIP(argsMap.get(key));
                        break;
                    case rSYBL_PORT:
                        orchestrator.withRsyblPort(Integer.parseInt(argsMap
                                .get(key)));
                        break;
                    case GovOps_IP:
                        orchestrator.withGovOpsIP(argsMap.get(key));
                        break;
                    case GovOps_PORT:
                        orchestrator.withGovOpsPort(Integer.parseInt(argsMap
                                .get(key)));
                        break;
                }
            }
        }

        orchestrator.deployAndControl(serviceTemplate);

        //only to deploy
        //orchestrator.deploy(serviceTemplate);
        //for updating anything
//      orchestrator.controlExisting(serviceTemplate);
    }
}
