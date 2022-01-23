## pulsar-energy-function

## Developer Workspace

* Using JDK 8. 1.8.0_292.  OPEN JDK 64-bit Server
* Using Python 3.7.12
* Using Mac OS Monterey 12.0.1
* Using IntelliJ IDEA CE 2021.2

## Developer Deployment Server

* Ubuntu Ubuntu 18.04.6 LTS
* JDK 1.8.0_312
* Python 3.6.9
* 70G RAM
* 24 Virtual Cores
* HP ProLiant DL360 G7 1U RackMount 64-bit Server with 2×Six-Core X5677 Xeon 3.46GHz CPUs 
*    72GB PC3-10600R RAM 
*    4×900GB 10K SAS SFF HDD, P410i RAID, 4×GigaBit NIC

## setup

````

bin/pulsar-admin topics create persistent://public/default/energydead
bin/pulsar-admin topics create persistent://public/default/energylog
bin/pulsar-admin topics create persistent://public/default/energy
bin/pulsar-admin topics create persistent://public/default/energy-influx

bin/pulsar-admin functions stop --name Energy --namespace default --tenant public
bin/pulsar-admin functions delete --name Energy --namespace default --tenant public
bin/pulsar-admin functions create --auto-ack true --jar /opt/demo/energy/energy-1.0.jar --classname "dev.pulsarfunction.energy.EnergyFunction" --dead-letter-topic "persistent://public/default/energydead" --inputs "persistent://public/default/energy" --log-topic "persistent://public/default/energylog" --name Energy --namespace default --output "persistent://public/default/energy-influx" --tenant public --max-message-retries 5

bin/pulsar-admin functions get --name Energy --namespace default --tenant public

bin/pulsar-admin functions status --name Energy --namespace default --tenant public

bin/pulsar-client consume "persistent://public/default/energy-infux" -s "fnchatresultreader" -n 5


````

## references

* https://github.com/tspannhw/flip-java-energy

