package com.gentrack.interview.meter.reader.service.exception;

public class MeterReaderServiceException extends Exception {
    String message;

    public MeterReaderServiceException(String message) {
        this.message = message;
    }

    public MeterReaderServiceException(String message, Throwable cause) {
        super(cause);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
