#! /bin/bash
export NODE_ENV=production

sudo apt-get update
sudo apt-get install -y curl git

curl -sL https://deb.nodesource.com/setup_6.x | sudo -E bash -
sudo apt-get install -y nodejs
sudo apt-get install -y nodejs-legacy
sudo apt-get install -y build-essential

git clone https://github.com/isa-group/governify-elasticity-test-sla-proxy.git

cd governify-elasticity-test-sla-proxy

npm install

npm start &> /tmp/proxy-sla.log &

cd ..

#java -jar papamoscas-proxy-sla.war  &> /tmp/proxy-sla.log &

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
bash ./setupPlugIns.sh
sudo -S service ganglia-monitor restart

exit 0
