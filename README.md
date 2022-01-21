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

bin/pulsar-admin functions delete --name EnergyTransform --namespace default --tenant public

bin/pulsar-admin functions create --auto-ack true --jar energy-1.0.jar --classname "dev.pulsarfunction.sentiment.SentimentFunction" --dead-letter-topic "persistent://public/default/chatdead" --inputs "persistent://public/default/chat" --log-topic "persistent://public/default/chatlog" --name SentimentAnalysis --namespace default --output "persistent://public/default/chatresult" --tenant public --max-message-retries 5


````

## references

* https://github.com/tspannhw/flip-java-energy
