package com.gentrack.interview.meter.reader.service;

import com.gentrack.interview.meter.reader.repository.model.Meter;
import com.gentrack.interview.meter.reader.request.MeterRequest;
import com.gentrack.interview.meter.reader.response.MeterResponse;

public abstract class AbstractMeterService {

    protected MeterResponse generateMeterResponse(Meter meter) {
        return new MeterResponse()
                .id(meter.id())
                .meterId(meter.meterId())
                .initialReading(meter.initialReading())
                .currentReading(meter.currentReading());
    }

    protected Meter generateMeter(MeterRequest meterRequest) {
        return new Meter()
                .meterId(meterRequest.getMeterId())
                .initialReading(meterRequest.getMeterReading())
                .currentReading(meterRequest.getMeterReading())
                .active(true);
    }
}
