package by.epam.spring.database.repository;

import by.epam.spring.bpp.Auditing;
import by.epam.spring.bpp.Transaction;
import by.epam.spring.database.entity.Company;
import by.epam.spring.database.pool.ConnectionPool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@Transaction
@Auditing
@RequiredArgsConstructor
public class CompanyRepository implements CrudRepository<Integer, Company> {

    @Value("${db.pool.size}")
    private final Integer poolSize;

    private final ConnectionPool pool1;

    private List<ConnectionPool> poolList;

    @PostConstruct
    private void init() {
        log.info("init company repository");
    }

    @Override
    public Optional<Company> findById(Integer id) {
        log.info("findById method ... ");
        return Optional.of(new Company(id,null, Collections.emptyMap()));
    }

    @Override
    public void delete(Company entity) {
        log.info("delete method ... ");
    }

}
