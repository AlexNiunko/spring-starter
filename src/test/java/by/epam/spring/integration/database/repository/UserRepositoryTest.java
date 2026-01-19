package by.epam.spring.integration.database.repository;

import by.epam.spring.database.entity.Role;
import by.epam.spring.database.entity.User;
import by.epam.spring.database.repository.UserRepository;
import by.epam.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void checkFirstTop(){
        var topByOrderByIdDesc = userRepository.findTopByOrderByIdDesc();
        assertTrue(topByOrderByIdDesc.isPresent());
        topByOrderByIdDesc.ifPresent(user->assertEquals(5L,user.getId()));
    }

    @Test
    void checkSort(){
        var sortBy = Sort.sort(User.class);
        var and = sortBy.by(User::getFirstname).and(sortBy.by(User::getLastname));

        var sort = Sort.by("firstname").and(Sort.by("lastname"));
        var users = userRepository.findTop3ByBirthDateBefore(LocalDate.now(),and);
        assertThat(users).hasSize(3);

    }

    @Test
    void checkPageable(){
        var id = PageRequest.of(1, 2, Sort.by("id"));
        var users = userRepository.findAllBy(id);
        assertThat(users).hasSize(2);
    }


}