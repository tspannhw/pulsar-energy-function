package dev.pulsarfunction.energy;

import org.apache.pulsar.client.impl.schema.JSONSchema;
import org.apache.pulsar.functions.api.*;

import java.util.UUID;

/**
 * Energy Function
**/
public class EnergyFunction implements Function<byte[], Void> {

    /** PROCESS */
    @Override
    public Void process(byte[] input, Context context) {
        if ( input == null || context == null ) {
            return null;
        }
        if ( context.getLogger() != null) {
            context.getLogger().info("LOG:" + input.toString());

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

//            ByteBuffer value = null;
//            context.putState("energy", value);
        }

//        String inputTopic = "persistent://public/default/energy";
        String outputTopic  = "persistent://public/default/energy-influx";
    //    String OS = System.getProperty("os.name").toLowerCase();
        DeviceSerde serde = new DeviceSerde();
        Device device = new Device();
        String jsonString = "";

        try {
            device = serde.deserialize(input);

 //           context.newConsumerBuilder(JSONSchema.of(Device.class))

            if ( device != null ) {
                if ( context.getLogger() != null ) {
                    context.getLogger().debug("Receive message JSON:" + device);
                }
//                jsonString = serde.serializeToJSON(device);
//                System.out.println(jsonString);

                System.out.println("Device:" + device.toString());

                context.newOutputMessage(outputTopic, JSONSchema.of(Device.class))
                        .key(UUID.randomUUID().toString())
                        .value(device)
                        .send();
            }
        } catch (Throwable e) {
            e.printStackTrace();
            if ( context.getLogger() != null) {
                context.getLogger().error("ERROR:" + e.getLocalizedMessage());
            }
        }
        return null;
    }
}