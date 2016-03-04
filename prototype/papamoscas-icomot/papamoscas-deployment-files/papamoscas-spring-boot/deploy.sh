#! /bin/bash

. /etc/environment

sudo apt-get update
sudo apt-get -y install curl

sleep 10s

MY_IP=$(ifconfig eth0 | grep 'inet addr:' | cut -d: -f2 | awk '{ print $1}')
echo $MY_IP

if [ -z "$proxy_IP" ]
then
	proxy_IP=$proxyPro_IP
fi

#echo http://$proxy_IP:8080/registry?ip=$MY_IP:8080
#curl -X POST http://$proxy_IP:8080/registry?ip=$MY_IP:8080

echo "curl -X DELETE http://$proxy_IP:8080/registry?ip=$MY_IP:8080" >> /tmp/undeploy.sh

echo "cassandra_IP: " 

echo "#IP cassandra" >> /etc/hosts

java -jar papamoscas-spring-boot.war &> /tmp/proxy-sla.log &

#used in unicast
GANGLIA_IP=192.1.1.15

sudo -S service ganglia-monitor stop

#delete all joins on multicast
eval "sed -i 's/mcast_join.*//' /etc/ganglia/gmond.conf"
#add unicast host destination
eval "sed -i 's#udp_send_channel {.*#udp_send_channel { \n host = $GANGLIA_IP#' /etc/ganglia/gmond.conf"
#delete the bind on multicast for receive
eval "sed -i 's/bind.*//' /etc/ganglia/gmond.conf"
eval "sed -i 's/send_metadata_interval.*/send_metadata_interval = 30/' /etc/ganglia/gmond.conf"

sudo -S service ganglia-monitor restart

cd ./gangliaPlugIns
./setupPlugIns.sh
sudo -S service ganglia-monitor restart

exit 0
