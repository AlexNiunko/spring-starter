package by.epam.spring.service;

import by.epam.spring.database.repository.CompanyRepository;
import by.epam.spring.dto.CompanyReadDto;
import by.epam.spring.listener.entity.AccessType;
import by.epam.spring.listener.entity.EntityEvent;
import by.epam.spring.mapper.CompanyReadMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyReadMapper companyReadMapper;
    private final CompanyRepository companyRepository;
    private final ApplicationEventPublisher applicationEventPublisher;


    public Optional<CompanyReadDto> findById(Integer id) {
        return companyRepository.findById(id)
                .map(entity -> {
                    applicationEventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
                    return companyReadMapper.map(entity);
                });
    }

    public List<CompanyReadDto> findAll(){
        return companyRepository.findAll().stream()
                .map(companyReadMapper::map)
                .toList();
    }


}
