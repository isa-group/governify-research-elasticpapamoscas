#SETUP GUIDE

1. [Introduction](#introduction)
2. [Installation](#installation)
3. [Deployment](#deployment)

##INTRODUCTION

This directory, ```prototype/```, contains all of files that are necesary to deploy our infrastructures on iCOMOT platform.

First, you can find all the souce files of java maven proyects that integrate our infrastructures on: ```prototype/papamoscas-icomot/papamoscas-proxy-sla```, ```prototype/papamascas-icomot/papamoscas-proxy```, ```prototype/papamoscas-icomot/papamoscas-sprint-boot```.

Secondly, you can find all compile proyects and deployment files on: ```prototype/papamoscas-icomot/papamoscas-deployment-files```. This directory contains ```.war```, ```.py``` or ```.sh``` which are used by iCOMOT to deploy our infrastructures.

Finally, you can find an implementation of iCOMOT-Orchestator which will be responsible for connect to iCOMOT platform and deploy our infrastructure. All of source code and java classes that define our service with iCOMOT API language are on ```prototype/iCOMOT-deployer```, also you can find a compile ```.jar```.  

##INSTALLATION

1. [Install iCOMOT](#install-icomot)
2. [Serve deployment files](#serve-deployment-files)
3. [Reference to files](#reference-to-files)
4. [Configure SLA and config.json](#configure-sla)

###Install iCOMOT

Before you can deploy the services, you have to install iCOMOT from [iCOMTO tutorial](http://tuwiendsg.github.io/iCOMOT/demo.html).

###Serve deployment files

After install iCOMOT you will need to serve all of deployment files. For example you can use [http-server](https://www.npmjs.com/package/http-server) with [nodeJS](https://nodejs.org/en/) to serve this files and use this commands:

```javascript
npm install http-server -g
```

```javascript
cd ../prototype/papamoscas-icomot/papamoscas-deployment-files
```

```javascript
http-server -p 80
```

The files will be serve on <http://localhost>.

Also you can use the server that is used by iCOMOT and you can copy the files from ```prototype/papamoscas-icomot/papamoscas-deployment-files``` to ```/var/www/html/papamoscas-deployment-files```. You will need to be sudo to execute this option.

```javascript
sudo mkdir /var/www/html/papamoscas-deployment-files
```

```javascript
sudo cp prototype/papamoscas-icomot/papamoscas-deployment-files /var/www/html/papamoscas-deployment-files
```

###Reference to files

After serve the files, you have to change the references to them on the classes that define our services. You have to change this lines of code and change the URL to access the files on ```prototype/iCOMOT-deployer``` proyect: 

```javascript
public class Governed {

    public static void main(String[] args) {
        //specify service units in terms of software

        String platformRepo = "http://[host:port]/papamoscas-deployment-files/";
        String miscRepo = "http://[host:port]/papamoscas-deployment-files/";

```
After change the URL on the three classes, ```src/main/java/es/us/isa/papamoscas/icomot/infractructure/*```, you have to build the new ```iCOMOT-deployer-1.0.jar```. Execute: 

```
maven clean install
```
And you can find this on ```target/iCOMOT-deployer-1.0.jar```

###Configure SLA

To control scalability by SLA, first you need to describe this SLA and after you need to configure the SLA GovernanceController and define what are the tools it must use to control. To define the SLA templates that will be used to create the agreements you can use our [Designer](https://labs.isa.us.es:8181/IDEAS/). Login into with your twitter or google account and execute this commad:

```
generateDemoWorkspace iCOMOTDemo 
```

This generates a workspace with 6 files:

* pro.at
* basic.at
* basic.html
* pro.html
* portal-config.txt
* config.txt

```pro.at``` and ```basic.at``` contain the SLAs that define the limits and objectives that provider and consumer must comply. ```pro.html``` and ```basic.html``` are HTML templates to visualize this agreements in natural language.
With ```portal-config.txt``` and [portal.governify.io](http://portal.governify.io) you can create a Web Portal to buy and create agreements from the agreement templates before defined, only you have to change ```{{your_username}}``` for all URLs on this file, and access to: 

```
http://portal.governify.io/app/#/portal?configurl=http://labs.isa.us.es/ir/{{your_username}}/iCOMOTDemo/iCOMOT/portal-config.txt
```

Also you have to configure SLA GovernanceController, the file ```config.txt``` contains a JSON object that define some conficuration of this component, you have to change it on three point:

* {{your_username}}
  Put here your username or change the URL to your config file.
* {{service_description_name}}
  Depending of the infrastructure that you want to deploy you need to put here:
    * Governed : PapamoscasElastic
    * UnGoverned : PapamoscasElasticOnlyOneLeve
    * PreProvisioned : PapamoscasElasticOnlyOneLevePreProvisioned
* levels
  Depending of the infrastructure that you want to deploy you need to put here:
    * Governed : [ "basic", "pro" ]
    * UnGoverned : [ "basic" ]
    * PreProvisioned : [ "basic" ]

Finally, you have to prepare SLA GovernanceController and change the configuration to access to the new ```config.txt```. You need to modify the ```prototype/papamoscas-icomot/papamoscas-proxy-sla/src/main/resources/application.yml``` file, and change: 

```
urls.config = http://labs.isa.us.es/ir/{{your_username}}/iCOMOTDemo/iCOMOT/portal-config.txt
``` 
run:

```
cd ../prototype/papamoscas-icomot/papamoscas-proxy-sla/
``` 
```
maven clean install
``` 
actualize your serving files:

```
cp target/papamoscas-proxy-sla.war /var/www/html/papamoscas-deployment-files/papamoscas-proxy-sla/
``` 

and compress ```papamoscas-proxy-sla.war``` and ```papamoscas-proxy-sla/gangliaPlugIns``` into ```papamoscas-proxy-sla.tar.gz```

##DEPLOYMENT

Finally, if you want to deploy one of our infrastructures, you have to execute this command:

####Governed

```javascript
java -cp iCOMOT-deployer-1.0.jar es.us.isa.papamoscas.icomot.infrastructure.Governed
```

####UnGoverned

```javascript
java -cp iCOMOT-deployer-1.0.jar es.us.isa.papamoscas.icomot.infrastructure.UnGoverned
```

####PreProvisioned

```javascript
java -cp iCOMOT-deployer-1.0.jar es.us.isa.papamoscas.icomot.infrastructure.PreProvisioned
```