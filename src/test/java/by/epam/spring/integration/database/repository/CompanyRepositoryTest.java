package by.epam.spring.integration.database.repository;

import by.epam.spring.database.entity.Company;
import by.epam.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@IT
@Transactional
//@Commit // или @Commit если
@RequiredArgsConstructor
class CompanyRepositoryTest {

    private final EntityManager entityManager;

    @Test
    void findById() {
        var company = entityManager.find(Company.class, 1);
        assertNotNull(company);
        assertThat(company.getLocales()).hasSize(2);
    }

    @Test
    void createCompany(){
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

    @Test
    void delete() {
    }
}