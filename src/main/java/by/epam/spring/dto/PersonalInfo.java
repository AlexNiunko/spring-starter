package by.epam.spring.dto;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

public interface PersonalInfo {
    LocalDate getBirthDate();

    String getFirstname();

    String getLastname();

    @Value("#{target.firstname + ' ' + target.lastname}")
    String getFullName();

}
