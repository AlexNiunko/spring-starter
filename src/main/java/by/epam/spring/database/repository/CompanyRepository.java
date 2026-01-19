package by.epam.spring.database.repository;

import by.epam.spring.database.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    // Optional , Entity , Future
    Optional<Company> findByName(String name);

    // Collection,  Stream (batch,close() )
    List<Company> findAllByNameContainingIgnoreCase(String fragment);

}
