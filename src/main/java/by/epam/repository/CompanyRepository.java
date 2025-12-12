package by.epam.repository;

import by.epam.bpp.Auditing;
import by.epam.bpp.InjectBean;
import by.epam.bpp.Transaction;
import by.epam.entity.Company;
import by.epam.pool.ConnectionPool;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

@Transaction
@Auditing
public class CompanyRepository implements CrudRepository<Integer, Company> {

    //    @Resource(name="pool2")
//    @Autowired
//    @Qualifier("pool2")

    @Value("${db.pool.size}")
    private Integer poolSize;

    private ConnectionPool pool2;

    @Autowired
    private List<ConnectionPool> poolList;

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

    @Autowired
    public void setPool2(ConnectionPool pool2) {
        this.pool2 = pool2;
    }

}
