package com.gentrack.interview.meter.reader;

import com.gentrack.interview.meter.reader.AbstractSpringIT;
import com.gentrack.interview.meter.reader.controller.MeterController;
import com.gentrack.interview.meter.reader.controller.MeterReadingController;
import com.gentrack.interview.meter.reader.request.MeterReadingRequest;
import com.gentrack.interview.meter.reader.request.MeterRequest;
import com.gentrack.interview.meter.reader.response.MeterResponse;
import com.gentrack.interview.meter.reader.service.exception.MeterReaderServiceException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class MeterIT extends AbstractSpringIT {

    @Autowired
    private MeterController meterController;

    @Autowired
    private MeterReadingController meterReadingController;

    @Test
    public void test() throws MeterReaderServiceException {

        MeterRequest meterRequest = new MeterRequest();
        meterRequest.setMeterId(UUID.randomUUID());
        meterRequest.setMeterReading(BigDecimal.ONE);

        ResponseEntity<MeterResponse> addMeterResponse = meterController.addMeter(meterRequest);

        ResponseEntity<MeterResponse> getMeterResponse = meterController.getMeter(addMeterResponse.getBody().meterId());

        assertThat(getMeterResponse.getBody().meterId()).isEqualTo(addMeterResponse.getBody().meterId());


        MeterReadingRequest meterReadingRequest = new MeterReadingRequest();
        meterReadingRequest.setMeterId(meterRequest.getMeterId());
        meterReadingRequest.setMeterReading(BigDecimal.ONE);
        meterReadingRequest.setFromTime(Timestamp.from(Instant.now()));
        meterReadingRequest.setToTime(Timestamp.from(Instant.now()));
        ResponseEntity<MeterResponse> meterReadingResponse = meterReadingController.addMeterReading(meterReadingRequest);

        assertThat(meterReadingResponse.getBody().meterId()).isEqualTo(addMeterResponse.getBody().meterId());

        ResponseEntity<MeterResponse> secondGetMeterResponse = meterController.getMeter(addMeterResponse.getBody().meterId());

        assertThat(secondGetMeterResponse.getBody().currentReading()).isEqualTo(new BigDecimal(2).setScale(3));


    }

}