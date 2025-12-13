package by.epam;

import by.epam.pool.ConnectionPool;
import by.epam.repository.CrudRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {

    public static void main(String[] args) {

        try(var context = new ClassPathXmlApplicationContext("application.xml")){
            var connectionPool = context.getBean("pool1", ConnectionPool.class);

            System.out.println(context.getBean("driver"));
            CrudRepository companyRepository = context.getBean("companyRepository", CrudRepository.class);
            System.out.println(companyRepository.findById(1));

        }

    }

}
