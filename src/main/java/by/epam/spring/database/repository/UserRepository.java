package by.epam.spring.database.repository;

import by.epam.spring.database.pool.ConnectionPool;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Scope(BeanDefinition.SCOPE_SINGLETON)
@Repository
@RequiredArgsConstructor
public class UserRepository {

    @Qualifier("pool1")
    private final ConnectionPool connectionPool;

}
