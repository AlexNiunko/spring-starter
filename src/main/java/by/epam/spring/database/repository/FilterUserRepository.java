package by.epam.spring.database.repository;

import by.epam.spring.database.entity.Role;
import by.epam.spring.database.entity.User;
import by.epam.spring.dto.PersonInfo;
import by.epam.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter filter);

    List<PersonInfo> findAllByCompanyIdAndRole(Integer companyId, Role role);

    void updateCompanyAndRole(List<User> users);

    void updateCompanyAndRoleNamed(List<User> users);
}
