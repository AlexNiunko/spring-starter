package by.epam.spring.integration.service;

import by.epam.spring.database.entity.Role;
import by.epam.spring.dto.UserCreateEditDto;
import by.epam.spring.dto.UserReadDto;
import by.epam.spring.integration.IntegrationBaseTest;
import by.epam.spring.service.UserService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;


@RequiredArgsConstructor
public class UserServiceIT extends IntegrationBaseTest {

    public static final Long USER_1=1L;
    public static final int COMPANY_ID = 1;

    private final UserService userService;


    @Test
    void findAll(){
        var result = userService.findAll();
        assertThat(result).hasSize(5);
    }

    @Test
    void findById(){
        var byId = userService.findById(USER_1);
        assertTrue(byId.isPresent());
        byId.ifPresent(user->assertEquals("bill@mail.ru",user.getUsername()));
    }

    @Test
    void create(){
        UserCreateEditDto userDto=new UserCreateEditDto(
                "test@mail.ru",
                "tesst",
                "test",
                "test",
                LocalDate.now(),
                Role.USER,
                COMPANY_ID,
                new MockMultipartFile("test",new byte[0])
        );
        var actualResult = userService.create(userDto);
        assertEquals(userDto.getUsername(),actualResult.getUsername());
        assertEquals(userDto.getLastname(),actualResult.getLastname());
        assertEquals(userDto.getFirstname(),actualResult.getFirstname());
        assertEquals(userDto.getBirthDate(),actualResult.getBirthDate());
        assertSame(userDto.getRole(),actualResult.getRole());
        assertEquals(userDto.getCompanyId(),actualResult.getCompany().id());
    }

    @Test
    void update(){
        UserCreateEditDto userDto=new UserCreateEditDto(
                "test@mail.ru",
                "tesst",
                "test",
                "test",
                LocalDate.now(),
                Role.USER,
                COMPANY_ID,
                new MockMultipartFile("test",new byte[0])
        );
        var actualResult = userService.update(USER_1, userDto);

        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(user->{
            assertEquals(userDto.getUsername(),user.getUsername());
            assertEquals(userDto.getLastname(),user.getLastname());
            assertEquals(userDto.getFirstname(),user.getFirstname());
            assertEquals(userDto.getBirthDate(),user.getBirthDate());
            assertSame(userDto.getRole(),user.getRole());
            assertEquals(userDto.getCompanyId(),user.getCompany().id());
        });
    }

    @Test
    void delete(){
        var result = userService.delete(USER_1);
        assertTrue(result);
    }



}
