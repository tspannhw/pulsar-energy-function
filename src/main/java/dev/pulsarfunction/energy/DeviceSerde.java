package dev.pulsarfunction.energy;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import org.apache.pulsar.functions.api.SerDe;
import java.time.Instant;
import java.util.Locale;

/**
 * Device Serde
 */
public class DeviceSerde implements SerDe<Device> {

    /**
     * parse Energy JSON Message into Class
     *
     * todo  Make a schema and find how to attach to websockets
     * @param message String of message
     * @return IoTMessage
     */
    private Energy parseMessage(String message) {
        Energy energyMessage = new Energy();
        if ( message == null) {
            return energyMessage;
        }
        System.out.println("Message:" + message);

        try {
            if ( message.trim().length() > 0) {
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
    /**
     *
     * @param input   device as a raw string in bytes
     * @return Device object
     */
    public Device deserialize(byte[] input) {
        if ( input == null) {
            return null;
        }
        String s = new String(input);
        Energy energy = parseMessage(s);
        if ( energy == null) {
            return null;
        }
        Device device = new Device();

        try {
            String OS = System.getProperty("os.name").toLowerCase();
            device.setMeasurement("current");
            device.timestamp = Instant.now().toEpochMilli();
            device.tags = Maps.newHashMap();
            device.tags.put("host", "pulsar1");
            device.tags.put("topic", "energy");
            device.tags.put("os", OS);
            device.fields = Maps.newHashMap();
            device.fields.put("power",  energy.getPower());
            device.fields.put("value", energy.getCurrent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return device;
    }

    public Device bytesToDevice(Byte[] input) {
        if ( input == null) {
            return null;
        }
        String s = String.valueOf(input);
        Energy energy = parseMessage(s);
        if ( energy == null) {
            return null;
        }
        Device device = new Device();

        try {
            String OS = System.getProperty("os.name").toLowerCase();
            device.setMeasurement("current");
            device.timestamp = Instant.now().toEpochMilli();
            device.tags = Maps.newHashMap();
            device.tags.put("host", "pulsar1");
            device.tags.put("topic", "energy");
            device.tags.put("os", OS);
            device.fields = Maps.newHashMap();
            device.fields.put("power",  energy.getPower());
            device.fields.put("value", energy.getCurrent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return device;
    }

    /**
     * build json output
     * @param  device   Device as an object
     * @return String device as JSON String
     */
    public String serializeToJSON(Device device) {
        String jsonValue = "";
        try {
            if (device != null) {
                ObjectMapper mapper = new ObjectMapper();
                jsonValue = mapper.writeValueAsString(device);
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return jsonValue;
    }
    /**
     * device to json
     * @param input   device
     * @return byte[] json String
     */
    public byte[] serialize(Device input) {
        if ( input == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        String json = "{}";
        try {
            json = mapper.writeValueAsString(input);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if ( json == null) {
            json = "{}";
        }

        return String.format(Locale.US, "%s", json).getBytes();
    }
}