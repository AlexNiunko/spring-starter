package by.epam;

import by.epam.ioc.Container;
import by.epam.pool.ConnectionPool;
import by.epam.repository.CompanyRepository;
import by.epam.repository.CrudRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {

    public static void main(String[] args) {
        String res="er";

        try(var context = new ClassPathXmlApplicationContext("application.xml")){
            var connectionPool = context.getBean("p1", ConnectionPool.class);

            System.out.println(context.getBean("driver"));
            CrudRepository companyRepository = context.getBean("companyRepository", CrudRepository.class);
            System.out.println(companyRepository.findById(1));

        }




    }

}
