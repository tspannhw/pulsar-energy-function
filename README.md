## pulsar-energy-function

## Developer Workspace

* Using JDK 8.
* Using Python 3.7.
* Using Mac OS Monterey 12.0.1

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
