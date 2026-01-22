package by.epam.spring.dto;

import by.epam.spring.database.entity.Role;
import java.time.LocalDate;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

@Value
@FieldNameConstants
public class UserCreateEditDto {

    String username;
    String firstname;
    String lastname;
    LocalDate birthDate;
    Role role;
    Integer companyId;

}
