package dev.pulsarfunction.energy;

import java.io.Serializable;
import java.util.Map;
import java.util.StringJoiner;

/**
 */
public class Device  implements Serializable {
    private static final long serialVersionUID = 1L;

        public String measurement;
    public long timestamp;
    public Map<String, String> tags;
    public Map<String, Object> fields;

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    public Map<String, Object> getFields() {
        return fields;
    }

    public void setFields(Map<String, Object> fields) {
        this.fields = fields;
    }

    /**
     *
     */
    public Device() {
        super();
    }

    /**
     *
     * @param measurement
     * @param timestamp
     * @param tags
     * @param fields
     */
    public Device(String measurement, long timestamp, Map<String, String> tags, Map<String, Object> fields) {
        super();
        this.measurement = measurement;
        this.timestamp = timestamp;
        this.tags = tags;
        this.fields = fields;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Device.class.getSimpleName() + "[", "]")
                .add("measurement='" + measurement + "'")
                .add("timestamp=" + timestamp)
                .add("tags=" + tags)
                .add("fields=" + fields)
                .toString();
    }
}
