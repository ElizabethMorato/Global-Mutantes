package org.example.dto;
/// (Output API)

import lombok.*;
import org.example.entity.DnaRecord;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatsResponse {

    private long countMutantDna;
    private long countHumanDna;
    private double ratio;

    private List<DnaRecord> records;
}