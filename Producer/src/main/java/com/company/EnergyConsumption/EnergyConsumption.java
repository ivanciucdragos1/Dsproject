package com.company.EnergyConsumption;

import java.util.UUID;

public class EnergyConsumption {

    private long time;
    private UUID device_id;
    private double value;

    public EnergyConsumption(long time, UUID device_id, double value) {
        this.time = time;
        this.device_id = device_id;
        this.value = value;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public UUID getDevice_id() {
        return device_id;
    }

    public void setDevice_id(UUID device_id) {
        this.device_id = device_id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
