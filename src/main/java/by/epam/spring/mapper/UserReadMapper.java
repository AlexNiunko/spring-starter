package by.epam.spring.mapper;

import by.epam.spring.database.entity.User;
import by.epam.spring.dto.CompanyReadDto;
import by.epam.spring.dto.UserReadDto;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserReadMapper implements Mapper<User, UserReadDto>{

    private final CompanyReadMapper companyReadMapper;

    @Override
    public UserReadDto map(User object) {
        var company = Optional.ofNullable(object.getCompany())
                .map(companyReadMapper::map)
                .orElse(null);
        return new UserReadDto(
                object.getId(),
                object.getUsername(),
                object.getFirstname(),
                object.getLastname(),
                object.getBirthDate(),
                object.getImage(),
                object.getRole(),
                company
        );
    }
}
