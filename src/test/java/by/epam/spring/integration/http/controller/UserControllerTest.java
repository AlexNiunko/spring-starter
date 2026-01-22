package by.epam.spring.integration.http.controller;


import static by.epam.spring.dto.UserCreateEditDto.Fields.birthDate;
import static by.epam.spring.dto.UserCreateEditDto.Fields.companyId;
import static by.epam.spring.dto.UserCreateEditDto.Fields.firstname;
import static by.epam.spring.dto.UserCreateEditDto.Fields.lastname;
import static by.epam.spring.dto.UserCreateEditDto.Fields.username;
import static by.epam.spring.dto.UserCreateEditDto.Fields.role;

import by.epam.spring.dto.UserCreateEditDto;
import by.epam.spring.integration.IntegrationBaseTest;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.org.hamcrest.collection.IsCollectionWithSize;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
@RequiredArgsConstructor
class UserControllerTest extends IntegrationBaseTest {

    private final MockMvc mockMvc;

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/users"))
                .andExpect(model().attribute("users", hasSize(5)));
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/users")
                .param(username,"test")
                .param(firstname,"test@gmail.com")
                .param(lastname,"test")
                .param(role,"ADMIN")
                .param(companyId, "1")
                .param(birthDate,"2000-01-01")
        ).andExpectAll(
                status().is3xxRedirection(),
                redirectedUrlPattern("/users/{\\d+}")
        );
    }
}