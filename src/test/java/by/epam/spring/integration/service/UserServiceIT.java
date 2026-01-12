package by.epam.spring.integration.service;

import by.epam.spring.database.pool.ConnectionPool;
import by.epam.spring.integration.annotation.IT;
import by.epam.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.DirtiesContext;

@IT
@RequiredArgsConstructor
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceIT {

    private final UserService userService;

    private final ConnectionPool pool;

    @Test
    void test() {
        System.out.println();

    }

    @Test
    void test2(){
        System.out.println();
    }


}
