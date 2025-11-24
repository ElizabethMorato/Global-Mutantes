package org.example.service;
/// (Estadísticas)
import lombok.RequiredArgsConstructor;
import org.example.dto.StatsResponse;
import org.example.entity.DnaRecord;
import org.example.repository.DnaRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatsService {

    private final DnaRecordRepository repository;

    public StatsResponse getStats() {
        long mutants = repository.countByMutant(true);
        long humans = repository.countByMutant(false);

        double ratio;
        if (humans == 0) {
            ratio = mutants > 0 ? 1.0 : 0.0;
        } else {
            ratio = (double) mutants / humans;
        }

        List<DnaRecord> allRecords = repository.findAll();

        return StatsResponse.builder()
                .countMutantDna(mutants)
                .countHumanDna(humans)
                .ratio(ratio)
                .records(allRecords)
                .build();
    }
}