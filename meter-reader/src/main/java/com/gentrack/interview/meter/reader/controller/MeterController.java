package com.gentrack.interview.meter.reader.controller;

import com.gentrack.interview.meter.reader.request.MeterRequest;
import com.gentrack.interview.meter.reader.response.MeterResponse;
import com.gentrack.interview.meter.reader.service.MeterService;
import com.gentrack.interview.meter.reader.service.exception.MeterReaderServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/meter")
public class MeterController {

    @Autowired
    private MeterService meterService;

    @RequestMapping(value = "/addMeter", method = POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody ResponseEntity<MeterResponse> addMeter(@Valid @RequestBody MeterRequest meterRequest) throws MeterReaderServiceException {
        return new ResponseEntity<>(meterService.addMeter(meterRequest), OK);
    }

    @RequestMapping(value = "/getMeter", method = GET, produces = "application/json")
    public @ResponseBody ResponseEntity<MeterResponse> getMeter(@RequestParam UUID meterId) throws MeterReaderServiceException {
        return new ResponseEntity<>(meterService.findActiveMeter(meterId), OK);
    }
}
