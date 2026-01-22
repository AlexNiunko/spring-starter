package by.epam.spring.dto;

import by.epam.spring.database.entity.Role;
import java.time.LocalDate;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.format.annotation.DateTimeFormat;

@Value
@FieldNameConstants
public class UserCreateEditDto {

    String username;
    String firstname;
    String lastname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthDate;
    Role role;
    Integer companyId;

}
