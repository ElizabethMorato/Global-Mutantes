package org.example.dto;

import org.example.validation.ValidDnaSequence;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DnaRequest {

    @NotNull(message = "Field 'dna' must not be null")
    @NotEmpty(message = "Field 'dna' must not be empty")
    @ValidDnaSequence
    private String[] dna;
}