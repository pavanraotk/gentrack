package com.gentrack.interview.meter.reader.response;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Accessors(fluent = true)
public class MeterResponse {
    private Long id;
    private UUID meterId;
    private BigDecimal initialReading;
    private BigDecimal currentReading;
}
