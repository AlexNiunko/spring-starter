package by.epam.spring.integration.database.repository;

import by.epam.spring.database.entity.User;
import by.epam.spring.database.repository.UserRepository;
import by.epam.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.List;

@IT
@RequiredArgsConstructor
class UserRepositoryTest {

    private final UserRepository userRepository;

    @Test
    void checkQueries(){
        var users = userRepository.findAllBy("i", "a");
        System.out.println(users);
    }

}