package dev.pulsarfunction.energy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import org.apache.pulsar.functions.api.*;
import java.time.Instant;

/**
 * Energy Function
**/
public class EnergyFunction implements Function<String, Device> {

    /**
     * build device in influx db line format
     *
     * @return device
     */
    public Device buildInfluxMessage(String host, String topic, Energy energy, String os) {
        Device device = new Device();
        if (host == null || topic == null || energy == null) {
            return device;
        }
        try {
            device.setMeasurement("current");
            device.timestamp = Instant.now().toEpochMilli();
            device.tags = Maps.newHashMap();
            device.tags.put("host", host);
            device.tags.put("topic", topic);
            device.tags.put("os", os);
            device.fields = Maps.newHashMap();
            device.fields.put("power",  energy.getPower());
            device.fields.put("value", energy.getCurrent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return device;
    }

    /**
     * parse Energy JSON Message into Class
     *
     * todo  Make a schema and find how to attach to websockets
     * @param message String of message
     * @return IoTMessage
     */
    public Energy parseMessage(String message) {
        Energy energyMessage = null;
        if ( message == null) {
            return energyMessage;
        }
        System.out.println("Message:" + message);

        try {
            if (message != null && message.trim().length() > 0) {
                ObjectMapper mapper = new ObjectMapper();
                energyMessage = mapper.readValue(message, Energy.class);
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }

        if (energyMessage == null) {
            energyMessage = new Energy();
        }
        return energyMessage;
    }

    /** PROCESS */
    @Override
    public Device process(String input, Context context) {
        if ( input == null || context == null ) {
            return null;
        }
        if ( context.getLogger() != null) {
            context.getLogger().info("LOG:" + input);

            System.setProperty("org.slf4j.simpleLogger.logFile", "System.out");

            context.getLogger().debug("Available processors (cores): " +
                    Runtime.getRuntime().availableProcessors());

            /* Total amount of free memory available to the JVM */
            context.getLogger().debug("Free memory (bytes): " +
                    Runtime.getRuntime().freeMemory());

            /* This will return Long.MAX_VALUE if there is no preset limit */
            long maxMemory = Runtime.getRuntime().maxMemory();
            /* Maximum amount of memory the JVM will attempt to use */
            context.getLogger().debug("Maximum memory (bytes): " +
                    (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));

            /* Total memory currently available to the JVM */
            context.getLogger().debug("Total memory available to JVM (bytes): " +
                    Runtime.getRuntime().totalMemory());

            context.recordMetric("energy-count", 1);
        }

//        String inputTopic = "persistent://public/default/energy";
        String outputTopic  = "persistent://public/default/energy-influx";
        String OS = System.getProperty("os.name").toLowerCase();
        Device device = null;
        try {
            Energy energyMsg = parseMessage("" + input);

            if ( energyMsg != null ) {

                if ( context.getLogger() != null ) {
                    context.getLogger().info("Receive message Current:" + energyMsg.getCurrent());
                    context.getLogger().info("Receive message Power:" + energyMsg.getPower());
                    context.getLogger().debug("Receive message JSON:" + energyMsg);
                }
                device = buildInfluxMessage("pulsar1", outputTopic, energyMsg, OS);
            }

        } catch (Throwable e) {
            e.printStackTrace();
            if ( context.getLogger() != null) {
                context.getLogger().error("ERROR:" + e.getLocalizedMessage());
            }
        }
        return device;
	    //return String.format("%s", "Test");
    }
}