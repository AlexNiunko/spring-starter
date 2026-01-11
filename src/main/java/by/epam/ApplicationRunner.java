package by.epam;

import by.epam.spring.config.ApplicationConfiguration;
import by.epam.spring.pool.ConnectionPool;
import by.epam.spring.repository.CrudRepository;
import by.epam.spring.service.CompanyService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationRunner {

    public static void main(String[] args) {

        try(var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class)){
            var connectionPool = context.getBean("pool1", ConnectionPool.class);

            CompanyService companyService = context.getBean("companyService", CompanyService.class);
            System.out.println(companyService.findById(1));

        }

    }

}
