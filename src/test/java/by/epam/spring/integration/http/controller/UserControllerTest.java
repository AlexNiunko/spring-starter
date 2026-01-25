package by.epam.spring.integration.http.controller;


import static by.epam.spring.dto.UserCreateEditDto.Fields.birthDate;
import static by.epam.spring.dto.UserCreateEditDto.Fields.companyId;
import static by.epam.spring.dto.UserCreateEditDto.Fields.firstname;
import static by.epam.spring.dto.UserCreateEditDto.Fields.lastname;
import static by.epam.spring.dto.UserCreateEditDto.Fields.username;
import static by.epam.spring.dto.UserCreateEditDto.Fields.role;

import by.epam.spring.database.entity.Role;
import by.epam.spring.dto.UserCreateEditDto;
import by.epam.spring.integration.IntegrationBaseTest;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.org.hamcrest.collection.IsCollectionWithSize;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
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


//    @BeforeEach
//    void init(){
//        var list = Arrays.asList(Role.ADMIN, Role.USER);
//        var testUser = new User("test@gmail.com", "test", list);
//        var authenticationToken = new TestingAuthenticationToken(testUser, testUser.getPassword(), testUser.getAuthorities());
//
//        SecurityContext context= SecurityContextHolder.createEmptyContext();
//        context.setAuthentication(authenticationToken);
//        SecurityContextHolder.setContext(context);
//    }

    @Test
    @WithMockUser(username="test@gmail.com",password = "test",authorities = {"ADMIN","USER"})
    void findAll() throws Exception {
        mockMvc.perform(get("/users")
                        .with(user("test@gamil.com").authorities(Role.USER)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/users"));
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