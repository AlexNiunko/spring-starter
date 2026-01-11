package by.epam.spring.service;

import by.epam.spring.dto.CompanyReadDto;
import by.epam.spring.entity.Company;
import by.epam.spring.listener.entity.AccessType;
import by.epam.spring.listener.entity.EntityEvent;
import by.epam.spring.repository.CrudRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {

    private final CrudRepository<Integer, Company> companyRepository;
    private final UserService userService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public CompanyService(UserService userService,
                          CrudRepository<Integer, Company> companyRepository,
                          ApplicationEventPublisher applicationEventPublisher) {
        this.userService = userService;
        this.companyRepository = companyRepository;
        this.applicationEventPublisher=applicationEventPublisher;
    }

    public Optional<CompanyReadDto>findById(Integer id){
        return companyRepository.findById(id)
                .map(entity->{
                    applicationEventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
                       return new CompanyReadDto(entity.id());
                });
    }


}
