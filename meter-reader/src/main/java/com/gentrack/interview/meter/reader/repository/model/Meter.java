package com.gentrack.interview.meter.reader.repository.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

import static javax.persistence.GenerationType.SEQUENCE;

@Table(name = "meter")
@Entity
@Data
@Accessors(fluent = true)
public class Meter {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "meter.meter_id_seq")
    private Long id;

    @NaturalId
    @Column(name = "meter_id")
    private UUID meterId;

    private BigDecimal initialReading;
    private BigDecimal currentReading;
    private Boolean active;

    @CreationTimestamp
    private Timestamp created;

    @UpdateTimestamp
    private Timestamp modified;


}
