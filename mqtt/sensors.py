import time
from time import sleep
from math import isnan
import subprocess
import sys
import os
from subprocess import PIPE, Popen
import datetime
import traceback
import math
import json
from time import gmtime, strftime
import random, string
import psutil
import logging
import paho.mqtt.client as mqtt
import pulsar


client = pulsar.Client('pulsar://localhost:6650')
producer = client.create_producer('persistent://public/default/jetsoninflux')

client = mqtt.Client("pulsar1-iot")

while (1):
    # https://www.influxdata.com/blog/mqtt-topic-payload-parsing-telegraf/
    json_string = "cpup value=" + str(psutil.cpu_percent(interval=1)+random.randint(0, 9))
    client.connect("192.168.1.230", 1883, 180)
    client.publish("persistent://public/default/telegrafcpu", payload=json_string, qos=0, retain=True)
    print("sent telegrafcpu mqtt: " + json_string) 

    #myMeasurement,tag1=value1,tag2=value2 fieldKey="fieldValue" 1556813561098000000
    #mem,host=host2 used_percent=73.77 1577836820000000000
    value_string ="mem,host=pulsar1 value=\"" + str(psutil.virtual_memory().percent + random.randint(0, 6)) + "\" " + str(int(time.time())) + "\n"
    producer.send(value_string.encode('utf-8')) 
    # .encode('utf-8'))
    print("PulsarSink:" + value_string)

    #telegrafmem
    json_string = "mem value=" + str(psutil.virtual_memory().percent + random.randint(0, 6))
    client.publish("persistent://public/default/telegrafmem", payload=json_string, qos=0, retain=True)
    print("sent telegrafmem mqtt: " + json_string) 
    
    #sensors
    temps = psutil.sensors_temperatures(fahrenheit=True)
    json_string = "sensors value=" + str(temps['coretemp'][0].current + random.randint(0, 19))
    client.publish("persistent://public/default/sensors", payload=json_string, qos=0, retain=True)
    print("sent telegrafmem sensors: " + json_string) 

    #time.sleep(1)
