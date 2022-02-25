## pulsar-energy-function
### FLiP-Py

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

# Overview

Using Java to send data to Pulsar to InfluxDB v2.1.1 - InfluxCloud

Energy Data

# Prerequisites

- Java 1.8 or higher version
- Java Client: 2.9.1

# Details

## Create a Standaone Apache Pulsar 2.9.1 Cluster or Use StreamNative Cloud

## Download InfluxDB Connector for 2.9.1

https://www.apache.org/dyn/mirrors/mirrors.cgi?action=download&filename=pulsar/pulsar-2.9.1/connectors/pulsar-io-influxdb-2.9.1.nar

## Create a InfluxDB Cloud Account

## Create a Bucket

pulsar

## Create A Token

https://us-Location.aws.cloud2.influxdata.com/notebook/from/bucket/pulsar

## Create Pulsar Topics

## setup

````

bin/pulsar-admin topics create persistent://public/default/energydead
bin/pulsar-admin topics create persistent://public/default/energylog
bin/pulsar-admin topics create persistent://public/default/energy
bin/pulsar-admin topics create persistent://public/default/energy-influx

````

## Create a Pulsar IO Connector for InfluxDB v2 Sink

## Config conf/influxcloud.yml

### Note:   preceision MS and Log Level BASIC are needed or things won't work

````
configs:
    influxdbUrl: "https://us-east-1-1.aws.cloud2.influxdata.com"
    organization: "tim.spann@streamnative.io"
    bucket: "pulsar"
    token: "2THISISVERYLONGGENERATEinCLOUDog=="
    logLevel: "BASIC"
    precision: "ms"

````

## Deploy the Connector (Let's stop and delete if we already have one)

````
bin/pulsar-admin sink stop --name influxdb-sink-jetson --namespace default --tenant public

bin/pulsar-admin sinks delete --tenant public --namespace default --name influxdb-sink-jetson

bin/pulsar-admin sinks create --archive ./connectors/pulsar-io-influxdb-2.9.1.nar --tenant public --namespace default --name influxdb-sink-jetson --sink-config-file conf/influxcloud.yml --inputs persistent://public/default/energy-influx --parallelism 1

bin/pulsar-admin sinks get --tenant public --namespace default --name influxdb-sink-jetson

bin/pulsar-admin sinks status --tenant public --namespace default --name influxdb-sink-jetson

````

## Test Consume Messages

````

bin/pulsar-client consume "persistent://public/default/energy-influx" -s "influxr" -n 0

````

## Check the Sink

````
bin/pulsar-admin sinks get --tenant public --namespace default --name influxdb-sink-jetson
````

## Get Sink Counts and Status

````
bin/pulsar-admin sinks status --tenant public --namespace default --name influxdb-sink-jetson
````

## Run Java 

````
java -jar /opt/demo/energy/EnergyProducer-1.0-jar-with-dependencies.jar --serviceUrl pulsar://pulsar1:6650 --topic 'persistent://public/default/energy-influx'
````

## Telegraf MQTT

````
# Read metrics from MQTT topic(s)
[[inputs.mqtt_consumer]]
  ## Broker URLs for the MQTT server or cluster.  To connect to multiple
  ## clusters or standalone servers, use a separate plugin instance.
  ##   example: servers = ["tcp://localhost:1883"]
  ##            servers = ["ssl://localhost:1883"]
  ##            servers = ["ws://localhost:1883"]
  servers = ["tcp://192.168.1.230:1883"]

  ## Topics that will be subscribed to.
  topics = [
    "telegrafcpu",
    "telegrafmem",
    "sensors",
  ]
````


