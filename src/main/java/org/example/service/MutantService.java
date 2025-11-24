package org.example.service;
/// (Orquestación)
import lombok.RequiredArgsConstructor;
import org.example.entity.DnaRecord;
import org.example.exception.DnaHashCalculationException;
import org.example.repository.DnaRecordRepository;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MutantService {

    private final MutantDetector mutantDetector;
    private final DnaRecordRepository dnaRecordRepository;

    // analiza el ADN para evitar duplicados
    //devuelve true si es mutante, o false si es humano
    public boolean isMutant(String[] dna) {
        String hash = calculateDnaHash(dna);

        Optional<DnaRecord> existing = dnaRecordRepository.findByDnaHash(hash);
        if (existing.isPresent()) {
            return existing.get().isMutant();
        }

        boolean isMutant = mutantDetector.isMutant(dna);
        String dnaSequence = String.join(",", dna);

        DnaRecord record = DnaRecord.builder()
                .dnaHash(hash)
                .dnaSequence(dnaSequence)
                .mutant(isMutant)
                .build();

        dnaRecordRepository.save(record);

        return isMutant;
    }

    private String calculateDnaHash(String[] dna) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String joined = String.join(",", dna);
            byte[] hashBytes = digest.digest(joined.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new DnaHashCalculationException("Error calculating DNA hash", e);
        }
    }
}