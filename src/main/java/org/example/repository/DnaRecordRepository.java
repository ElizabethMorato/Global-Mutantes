package org.example.repository;
///acceso a datos (Interface JPA)
import org.example.entity.DnaRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DnaRecordRepository extends JpaRepository<DnaRecord, Long> {

    long countByMutant(boolean mutant);

    Optional<DnaRecord> findByDnaHash(String dnaHash);
}