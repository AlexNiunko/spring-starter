package by.epam.spring.dto;

import by.epam.spring.database.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.format.annotation.DateTimeFormat;

@Value
@FieldNameConstants
public class UserCreateEditDto {

    @Email
    String username;

    @NotNull
    @Size(min = 3, max = 64)
    String firstname;

    @NotNull
    String lastname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthDate;
    Role role;
    Integer companyId;

}
