package com.gentrack.interview.meter.reader.request;

import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public class MeterRequest {

    @NotNull
    private UUID meterId;

    @NotNull
    private BigDecimal meterReading;

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
}
