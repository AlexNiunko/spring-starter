package by.epam.spring.config;

import by.epam.spring.repository.CrudRepository;
import by.epam.web.config.WebConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Component;

//@ImportResource("classpath:application.xml")
@Import(WebConfiguration.class)
@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "by.epam",
useDefaultFilters = false,
includeFilters = {
    @Filter(type = FilterType.ANNOTATION,value = Component.class),
    @Filter(type = FilterType.ASSIGNABLE_TYPE,value = CrudRepository.class),
    @Filter(type = FilterType.REGEX,pattern = "by\\..+Repository")
})
public class ApplicationConfiguration {



}
