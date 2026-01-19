package by.epam.spring.database.repository;

import by.epam.spring.database.entity.Role;
import by.epam.spring.database.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
            select u from User u
            where u.firstname like %:firstname%
            and u.lastname like %:lastname%
            """)
    List<User> findAllBy(String firstname, String lastname);


    @Query(value = """
            SELECT u.* FROM users u
            WHERE u.username=:username
            """, nativeQuery = true)
    List<User> findAllByUserName(String username);

    @Modifying(clearAutomatically = true,flushAutomatically = false)
    @Query("""
            update User u set u.role=:role where u.id in (:ids)
            """)
    int updateRole(Role role, Long... ids);

    Optional<User> findTopByOrderByIdDesc();

    List<User>findTop3ByBirthDateBeforeOrderByBirthDateDesc(LocalDate birthDate);

    List<User>findTop3ByBirthDateBefore(LocalDate birthDate, Sort sort);

//    @EntityGraph("User.company")
    @EntityGraph(attributePaths = {"company","company.locales"})
    @Query(value = "select u from User u",
    countQuery = "select count(distinct u.firstname) from User u")
    Page<User> findAllBy(Pageable pageable);

}
