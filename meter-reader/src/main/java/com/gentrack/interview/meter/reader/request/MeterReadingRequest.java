package com.gentrack.interview.meter.reader.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

public class MeterReadingRequest {
    private UUID meterId;
    private BigDecimal meterReading;
    private Timestamp fromTime;
    private Timestamp toTime;

    public UUID getMeterId() {
        return meterId;
    }

    public void setMeterId(UUID meterId) {
        this.meterId = meterId;
    }

    public BigDecimal getMeterReading() {
        return meterReading;
    }

    public void setMeterReading(BigDecimal meterReading) {
        this.meterReading = meterReading;
    }

    public Timestamp getFromTime() {
        return fromTime;
    }

    public void setFromTime(Timestamp fromTime) {
        this.fromTime = fromTime;
    }

    public Timestamp getToTime() {
        return toTime;
    }

    public void setToTime(Timestamp toTime) {
        this.toTime = toTime;
    }
}
