package by.epam.spring.service.integration.service;

import by.epam.spring.ApplicationRunner;
import by.epam.spring.config.DatabaseProperties;
import by.epam.spring.database.entity.Company;
import by.epam.spring.dto.CompanyReadDto;
import by.epam.spring.listener.entity.EntityEvent;
import by.epam.spring.service.CompanyService;
import by.epam.spring.service.integration.service.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@IT
//@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = ApplicationRunner.class,
//initializers = ConfigDataApplicationContextInitializer.class)
public class CompanyServiceIT {

    public static final Integer COMPANY_ID = 1;


    private final CompanyService companyService;

    private final DatabaseProperties databaseProperties;

    @Test
    void findById(){

        var actualResult = companyService.findById(COMPANY_ID);

        assertTrue(actualResult.isPresent());
        var expectedResult = new CompanyReadDto(COMPANY_ID);
        actualResult.ifPresent(actual->assertEquals(expectedResult,actual));

    }

}
