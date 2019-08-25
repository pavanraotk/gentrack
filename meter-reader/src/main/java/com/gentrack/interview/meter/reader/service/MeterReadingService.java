package com.gentrack.interview.meter.reader.service;

import com.gentrack.interview.meter.reader.repository.MeterReadingRepository;
import com.gentrack.interview.meter.reader.repository.MeterRepository;
import com.gentrack.interview.meter.reader.repository.model.Meter;
import com.gentrack.interview.meter.reader.repository.model.MeterReading;
import com.gentrack.interview.meter.reader.request.MeterReadingRequest;
import com.gentrack.interview.meter.reader.response.MeterResponse;
import com.gentrack.interview.meter.reader.service.exception.MeterReaderServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MeterReadingService extends AbstractMeterService {
    private static final Logger log = LoggerFactory.getLogger(MeterReadingService.class);

    @Autowired
    private MeterReadingRepository meterReadingRepository;

    @Autowired
    private MeterRepository meterRepository;

    public MeterResponse addMeterReading(MeterReadingRequest meterReadingRequest) throws MeterReaderServiceException {
        try {
            log.debug("Adding meter {}", meterReadingRequest);
            Meter meter = meterRepository.findByMeterIdAndActive(meterReadingRequest.getMeterId(), true);
            if(meter == null) {
                log.error("Active meter doesn't exist {}", meterReadingRequest.getMeterId());
                throw new MeterReaderServiceException("Meter doesn't exist");
            }
            meterReadingRepository.save(generateMeterReading(meterReadingRequest, meter));
            meter.currentReading(addMeterReading(meterReadingRequest, meter));
            return generateMeterResponse(meterRepository.save(meter));
        } catch (Exception ex) {
            log.error("Error in adding meter reading {}", meterReadingRequest, ex);
            throw new MeterReaderServiceException("Error in adding meter", ex);
        }
    }

    private BigDecimal addMeterReading(MeterReadingRequest meterReadingRequest, Meter meter) {
        return meter.initialReading().add(meterReadingRequest.getMeterReading());
    }

    private MeterReading generateMeterReading(MeterReadingRequest meterReadingRequest, Meter meter) {
        return new MeterReading()
                .meter(meter)
                .fromTime(meterReadingRequest.getFromTime())
                .toTime(meterReadingRequest.getToTime())
                .readingValue(meterReadingRequest.getMeterReading());
    }

}
