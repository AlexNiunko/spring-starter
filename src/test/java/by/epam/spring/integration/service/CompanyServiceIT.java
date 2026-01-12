package by.epam.spring.integration.service;

import by.epam.spring.service.CompanyService;
import by.epam.spring.config.DatabaseProperties;
import by.epam.spring.dto.CompanyReadDto;
import by.epam.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

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
