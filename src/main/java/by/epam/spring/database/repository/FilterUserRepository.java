package by.epam.spring.database.repository;

import by.epam.spring.database.entity.User;
import by.epam.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter filter);
}
