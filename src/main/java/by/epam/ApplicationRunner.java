package by.epam;

import by.epam.spring.config.ApplicationConfiguration;
import by.epam.spring.pool.ConnectionPool;
import by.epam.spring.repository.CrudRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationRunner {

    public static void main(String[] args) {

        try(var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class)){
            var connectionPool = context.getBean("pool1", ConnectionPool.class);
            context.register(ApplicationConfiguration.class);
            context.getEnvironment().setActiveProfiles("web","prod");
            context.refresh();
            CrudRepository companyRepository = context.getBean("companyRepository", CrudRepository.class);
            System.out.println(companyRepository.findById(1));

        }

    }

}
