package com.gentrack.interview.meter.reader.service;

import com.gentrack.interview.meter.reader.repository.MeterRepository;
import com.gentrack.interview.meter.reader.repository.model.Meter;
import com.gentrack.interview.meter.reader.request.MeterRequest;
import com.gentrack.interview.meter.reader.response.MeterResponse;
import com.gentrack.interview.meter.reader.service.exception.MeterReaderServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MeterService extends AbstractMeterService {

    private static final Logger log = LoggerFactory.getLogger(MeterService.class);

    @Autowired
    private MeterRepository meterRepository;

    public MeterResponse addMeter(MeterRequest meterRequest) throws MeterReaderServiceException {
        try {
            log.debug("Adding meter {}", meterRequest);
            Meter meter = meterRepository.save(generateMeter(meterRequest));
            return generateMeterResponse(meter);
        } catch (Exception ex) {
            log.error("Error in adding meter {}", meterRequest, ex);
            throw new MeterReaderServiceException("Error in adding meter", ex);
        }
    }

    public MeterResponse findActiveMeter(UUID meterId) throws MeterReaderServiceException {
        try {
            return generateMeterResponse(meterRepository.findByMeterIdAndActive(meterId, true));
        } catch (Exception ex) {
            log.error("Error in getting meter {}", meterId, ex);
            throw new MeterReaderServiceException("Error in getting meter ", ex);
        }
    }

    public MeterResponse findInActiveMeter(UUID meterId) throws MeterReaderServiceException {
        try {
            return generateMeterResponse(meterRepository.findByMeterIdAndActive(meterId, false));
        } catch (Exception ex) {
            log.error("Error in getting meter {}", meterId, ex);
            throw new MeterReaderServiceException("Error in getting meter ", ex);
        }
    }

    public MeterResponse deleteMeter(UUID meterId) throws MeterReaderServiceException {
        try {
            log.debug("Deleting meter {}", meterId);
            Meter activeMeter = meterRepository.findByMeterIdAndActive(meterId, true);
            Meter meter = meterRepository.save(activeMeter.active(false));
            return generateMeterResponse(activeMeter);
        } catch (Exception ex) {
            log.error("Error in deleting meter {}", meterId, ex);
            throw new MeterReaderServiceException("Error in deleting meter", ex);
        }
    }

}
