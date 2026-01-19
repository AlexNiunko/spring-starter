package by.epam.spring.integration.database.repository;

import by.epam.spring.database.entity.Company;
import by.epam.spring.database.repository.CompanyRepository;
import by.epam.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
//@Commit // или @Commit если
@RequiredArgsConstructor
class CompanyRepositoryTest {

    private static final Integer APPLE_ID = 2;
    private final EntityManager entityManager;
    private final TransactionTemplate transactionTemplate;
    private final CompanyRepository companyRepository;

    @Test
    void checkFindByQueries(){
        companyRepository.findByName("google");
        companyRepository.findAllByNameContainingIgnoreCase("a");
    }

    @Test
    void delete() {
        var maybeCompany = companyRepository.findById(APPLE_ID);
        assertTrue(maybeCompany.isPresent());
        maybeCompany.ifPresent(companyRepository::delete);
        entityManager.flush();
        assertTrue(companyRepository.findById(APPLE_ID).isEmpty());
    }


    @Test
    void findById() {
        transactionTemplate.executeWithoutResult(tx -> {
            var company = entityManager.find(Company.class, 1);
            assertNotNull(company);
            assertThat(company.getLocales()).hasSize(2);
        });

    }

    @Test
    void createCompany() {
        var company = Company.builder()
                .name("Megafon")
                .locales(Map.of(
                        "en", "Description Apple",
                        "ru", "Описание компании"
                ))
                .build();
        entityManager.persist(company);
        assertNotNull(company.getId());


    }

}