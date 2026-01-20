package by.epam.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

//@EntityScan
@ConfigurationPropertiesScan
@SpringBootApplication
public class ApplicationRunner {

    public static void main(String[] args) {
        var configurableApplicationContext = SpringApplication.run(ApplicationRunner.class, args);
        System.out.println();

    }

}
