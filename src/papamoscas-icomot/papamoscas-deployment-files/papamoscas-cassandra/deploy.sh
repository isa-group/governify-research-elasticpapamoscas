#! /bin/bash

. /etc/environment

bash ./apache-cassandra-2.2.1/bin/cassandra > /tmp/salsa.artifact.log

sleep 80s

echo "create keyspace papamoscaskeyspace with replication =  {'class':'SimpleStrategy','replication_factor':1};" > ./cassandra 
echo "use papamoscaskeyspace;" >> ./cassandra 
echo "create table bird (id uuid primary key, specie text, place text, legDiameter float, wingSize float, eggs int, hatches int);" >> ./cassandra 
echo "insert into bird ( id, specie, place , legDiameter , wingSize , eggs , hatches ) values(now(), 'xxx','xxx', 1.0, 1.0, 1, 1);" >> ./cassandra 
#cat ./cassandra | telnet -E 10.0.2.15 12345

./apache-cassandra-2.2.1/bin/cqlsh -f ./cassandra >> /tmp/salsa.artifact.log

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

exit 0
