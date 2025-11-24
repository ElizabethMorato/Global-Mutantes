package org.example.validation;
/// (Lógica)
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidDnaSequenceValidator implements ConstraintValidator<ValidDnaSequence, String[]> {

    @Override
    public boolean isValid(String[] dna, ConstraintValidatorContext context) {
        if (dna == null || dna.length < 4) {
            return false;
        }

        int n = dna.length;
        for (String row : dna) {
            if (row == null || row.length() != n) {
                return false; // must be NxN
            }
            for (char c : row.toCharArray()) {
                if ("ATCG".indexOf(c) == -1) {
                    return false; // invalid character
                }
            }
        }
        return true;
    }
}