package by.epam.spring.dto;

import java.time.LocalDate;

public record PersonInfo(
        String firstname,
        String lastname,
        LocalDate birthDate
) {
}
