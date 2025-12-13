package by.epam.repository;

import by.epam.bpp.Auditing;
import by.epam.bpp.Transaction;
import by.epam.entity.Company;
import by.epam.pool.ConnectionPool;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
@Transaction
@Auditing
public class CompanyRepository implements CrudRepository<Integer, Company> {

    private final Integer poolSize;

    private final ConnectionPool pool1;

    private List<ConnectionPool> poolList;

    public CompanyRepository(@Value("${db.pool.size}") Integer poolSize,
                             ConnectionPool pool1,
                             List<ConnectionPool> poolList) {
        this.poolSize = poolSize;
        this.pool1 = pool1;
        this.poolList = poolList;
    }

    @PostConstruct
    private void init(){
        System.out.println("init company repository");
    }

    @Override
    public Optional<Company> findById(Integer id) {
        System.out.println("findById method ... ");
        return Optional.of(new Company(id));
    }

    @Override
    public void delete(Company entity) {
        System.out.println("delete method ... ");
    }


}
