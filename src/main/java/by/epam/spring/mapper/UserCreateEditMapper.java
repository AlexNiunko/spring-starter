package by.epam.spring.mapper;

import by.epam.spring.database.entity.Company;
import by.epam.spring.database.entity.User;
import by.epam.spring.database.repository.CompanyRepository;
import by.epam.spring.dto.UserCreateEditDto;
import java.util.Optional;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User>{

    private final CompanyRepository companyRepository;

    @Override
    public User map(UserCreateEditDto object) {
        User user=new User();
        copy(object, user);

        return user;
    }

    private void copy(UserCreateEditDto object, User user) {
        user.setUsername(object.getUsername());
        user.setFirstname(object.getFirstname());
        user.setBirthDate(object.getBirthDate());
        user.setLastname(object.getLastname());
        user.setRole(object.getRole());
        user.setCompany(getCompany(object.getCompanyId()));
        Optional.ofNullable(object.getImage())
                .filter(Predicate.not(MultipartFile::isEmpty))
                .ifPresent(image->user.setImage(image.getOriginalFilename()));
    }

    private Company getCompany(Integer companyId ){
        return Optional.ofNullable(companyId)
                .flatMap(companyRepository::findById)
                .orElse(null);
    }

    @Override
    public User map(UserCreateEditDto fromObject, User toObject) {
        copy(fromObject, toObject);

        return toObject;
    }
}
