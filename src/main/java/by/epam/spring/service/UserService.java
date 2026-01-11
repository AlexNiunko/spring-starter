package by.epam.spring.service;

import by.epam.spring.database.entity.Company;
import by.epam.spring.database.repository.CrudRepository;
import by.epam.spring.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CrudRepository<Integer, Company> companyRepository;

}
