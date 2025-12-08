package by.epam;

import by.epam.ioc.Container;
import by.epam.pool.ConnectionPool;
import by.epam.repository.CompanyRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {

    public static void main(String[] args) {

        var context = new ClassPathXmlApplicationContext("application.xml");

        var connectionPool = context.getBean("p1", ConnectionPool.class);

        System.out.println(context.getBean("driver"));
        System.out.println(connectionPool);
        CompanyRepository companyRepository = context.getBean("companyRepository", CompanyRepository.class);
        System.out.println(companyRepository);

    }

}
