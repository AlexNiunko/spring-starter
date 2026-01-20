package by.epam.spring.database.repository;

import by.epam.spring.database.entity.Role;
import by.epam.spring.database.entity.User;
import by.epam.spring.database.querydsl.QPredicates;
import by.epam.spring.dto.PersonInfo;
import by.epam.spring.dto.PersonalInfo;
import by.epam.spring.dto.UserFilter;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static by.epam.spring.database.entity.QUser.user;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository{

    private static final String FIND_BY_COMPANY_AND_ROLE= """
            SELECT
            firstname,
            lastname,
            birth_date
            FROM users
            WHERE company_id=?
            AND role=?
            """;


    private final EntityManager entityManager;
    private final JdbcTemplate jdbcTemplate;



    @Override
    public List<User> findAllByFilter(UserFilter filter) {
        var build = QPredicates.builder()
                .add(filter.firstname(), user.firstname::containsIgnoreCase)
                .add(filter.lastname(), user.lastname::containsIgnoreCase)
                .add(filter.birthDate(), user.birthDate::before)
                .build();
        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(build)
                .fetch();

    }

    @Override
    public List<PersonInfo> findAllByCompanyIdAndRole(Integer companyId, Role role) {
        return jdbcTemplate.query(FIND_BY_COMPANY_AND_ROLE,
                (rs, rowNum) -> new PersonInfo(
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getDate("birth_date").toLocalDate()
                ),companyId,role.name());
    }
}
