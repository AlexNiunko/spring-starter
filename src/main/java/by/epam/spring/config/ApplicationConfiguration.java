package by.epam.spring.config;

import by.epam.spring.database.pool.ConnectionPool;
import by.epam.spring.database.repository.UserRepository;
import by.epam.web.config.WebConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Component;

@Import(WebConfiguration.class)
@ComponentScan(basePackages = "by.epam",
        useDefaultFilters = false,
        includeFilters = {
                @Filter(type = FilterType.ANNOTATION, value = Component.class),
                @Filter(type = FilterType.REGEX, pattern = "by\\..+Repository")
        })
public class ApplicationConfiguration {

    @Bean("pool2")
    public ConnectionPool pool2(@Value("${db.username}") String userName) {
        return new ConnectionPool(userName, 20);
    }

    @Bean
    public ConnectionPool pool3() {
        return new ConnectionPool("test-pool", 20);
    }

    @Bean
    public UserRepository userRepository(ConnectionPool pool2) {
        return new UserRepository(pool2);
    }

    @Bean
    @Profile("prod|web")
    //! & |
    public UserRepository userRepository2() {
        var pool1 = pool3();
        var pool2 = pool3();
        var pool3 = pool3();
        return new UserRepository(pool3());
    }


}
