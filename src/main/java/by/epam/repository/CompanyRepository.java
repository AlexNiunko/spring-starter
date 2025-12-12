package by.epam.repository;

import by.epam.bpp.Auditing;
import by.epam.bpp.InjectBean;
import by.epam.bpp.Transaction;
import by.epam.entity.Company;
import by.epam.pool.ConnectionPool;
import java.util.Optional;
import javax.annotation.PostConstruct;

@Transaction
@Auditing
public class CompanyRepository implements CrudRepository<Integer, Company> {

    @InjectBean
    private ConnectionPool connectionPool;

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
