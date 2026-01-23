package by.epam.spring.dto;

import by.epam.spring.database.entity.Role;
import by.epam.spring.validation.UserInfo;
import by.epam.spring.validation.group.CreateAction;
import by.epam.spring.validation.group.UpdateAction;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Value
@FieldNameConstants
@UserInfo(groups = UpdateAction.class)
public class UserCreateEditDto {

    @Email
    String username;

    @Size(min = 3, max = 64)
    String firstname;

    String lastname;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthDate;

    Role role;

    Integer companyId;

    MultipartFile image;

}
