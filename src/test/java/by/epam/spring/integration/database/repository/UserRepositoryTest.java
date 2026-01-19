package by.epam.spring.integration.database.repository;

import by.epam.spring.database.entity.Role;
import by.epam.spring.database.entity.User;
import by.epam.spring.database.repository.UserRepository;
import by.epam.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Commit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@IT
@RequiredArgsConstructor
class UserRepositoryTest {

    private final UserRepository userRepository;

    @Test
    void checkQueries() {
        var users = userRepository.findAllBy("i", "a");
        System.out.println(users);
    }

    @Test
    void checkUpdate(){
        var ivan = userRepository.getById(1L);
        assertSame(Role.ADMIN,ivan.getRole());

        ivan.setFirstname("Cucaracha");

        var resultCount = userRepository.updateRole(Role.USER, 1L, 2L);
        assertEquals(2,resultCount);

        var companyName = ivan.getCompany().getName();

        var theSameIvan=userRepository.getById(1L);
        assertSame(Role.USER,theSameIvan.getRole());
    }

}