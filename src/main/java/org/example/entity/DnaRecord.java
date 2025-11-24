package org.example.entity;
/// entidades JPA (tabla dna_records)
import jakarta.persistence.*;
import lombok.*;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Entity
@Table(
        name = "dna_records",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "dnaHash")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DnaRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "dna_hash", nullable = false, unique = true, length = 64)
    private String dnaHash;


    @Column(name = "dna_sequence", nullable = false, length = 2000)
    private String dnaSequence;

    @Column(name = "is_mutant", nullable = false)
    private boolean mutant;
}
