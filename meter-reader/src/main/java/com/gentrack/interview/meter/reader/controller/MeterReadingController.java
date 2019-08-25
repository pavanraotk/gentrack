package com.gentrack.interview.meter.reader.controller;

import com.gentrack.interview.meter.reader.request.MeterReadingRequest;
import com.gentrack.interview.meter.reader.request.MeterRequest;
import com.gentrack.interview.meter.reader.response.MeterResponse;
import com.gentrack.interview.meter.reader.service.MeterReadingService;
import com.gentrack.interview.meter.reader.service.exception.MeterReaderServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
@RequestMapping(value = "/meterReading")
public class MeterReadingController {

    @Autowired
    private MeterReadingService meterReadingService;

    @RequestMapping(value = "/addMeterReading", method = POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<MeterResponse> addMeterReading(@Valid MeterReadingRequest meterReadingRequest) throws MeterReaderServiceException {
        return new ResponseEntity<>(meterReadingService.addMeterReading(meterReadingRequest), OK);
    }
}
