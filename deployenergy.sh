bin/pulsar-admin functions stop --name Energy --namespace default --tenant public
bin/pulsar-admin functions delete --name Energy --namespace default --tenant public
bin/pulsar-admin functions create --auto-ack true --jar /opt/demo/energy/energy-1.0.jar --classname "dev.pulsarfunction.energy.EnergyFunction" --dead-letter-topic "persistent://public/default/energydead" --inputs "persistent://public/default/energy" --log-topic "persistent://public/default/energylog" --name Energy --namespace default --tenant public --max-message-retries 5

