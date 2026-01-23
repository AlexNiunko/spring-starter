package by.epam.spring.dto;

import by.epam.spring.database.entity.Role;
import java.time.LocalDate;
import lombok.Value;

@Value
public class UserReadDto {
    Long id;
    String username;
    String firstname;
    String lastname;
    LocalDate birthDate;
    String image;
    Role role;
    CompanyReadDto company;
}
