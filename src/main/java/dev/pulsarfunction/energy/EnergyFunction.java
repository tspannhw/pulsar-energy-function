package dev.pulsarfunction.energy;

import org.apache.pulsar.functions.api.Context;
import org.apache.pulsar.functions.api.Function;

import java.io.IOException;

/**
**/
public class EnergyFunction implements Function<String, String> {

    /** PROCESS */
    @Override
    public String process(String input, Context context) {
        if ( context != null && context.getLogger() != null) {
            context.getLogger().info("LOG:" + input);
        }

        Result result = null;

        try {
           result = null; 
        } catch (Throwable e) {
            e.printStackTrace();
            if ( context != null && context.getLogger() != null) {
                context.getLogger().error("ERROR:" + e.getLocalizedMessage());
            }
        }

	return String.format("%s", "Test");
    }
} 
