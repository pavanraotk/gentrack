package com.gentrack.interview.meter.reader.repository.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.GenerationType.SEQUENCE;
import static org.hibernate.annotations.CascadeType.*;

@Entity
@Table(name = "meter_reading")
@Data
@Accessors(fluent = true)
public class MeterReading {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "meter.meter_reading_id_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "meter_id",
            referencedColumnName = "meter_id",
            foreignKey = @ForeignKey(name = "meter_fk", value = ConstraintMode.CONSTRAINT)
    )
    @Cascade(value = DELETE)
    private Meter meter;

    @CreationTimestamp
    private Timestamp created;

    @UpdateTimestamp
    private Timestamp modified;

    private Timestamp fromTime;
    private Timestamp toTime;
    private BigDecimal readingValue;
}
