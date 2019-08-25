package com.gentrack.interview.meter.reader.repository;

import com.gentrack.interview.meter.reader.repository.model.Meter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MeterRepository extends JpaRepository<Meter, Long> {
    Meter findByMeterIdAndActive(UUID meterId, Boolean active);
}
