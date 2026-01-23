package by.epam.spring.http.controller;

import by.epam.spring.database.entity.Role;
import by.epam.spring.dto.PageResponse;
import by.epam.spring.dto.UserCreateEditDto;
import by.epam.spring.dto.UserFilter;
import by.epam.spring.dto.UserReadDto;
import by.epam.spring.service.CompanyService;
import by.epam.spring.service.UserService;
import by.epam.spring.validation.group.CreateAction;
import by.epam.spring.validation.group.UpdateAction;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static by.epam.spring.database.entity.QUser.user;

@Slf4j
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CompanyService companyService;


    @GetMapping()
    public String findAll(Model model, UserFilter filter, Pageable pageable) {
        var page = userService.findAll(filter, pageable);
        var pageResponse = PageResponse.of(page);
        model.addAttribute("users", pageResponse);
        model.addAttribute("filter", filter);
        return "user/users";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        return userService.findById(id)
                .map(user -> {
                    model.addAttribute("user", user);
                    model.addAttribute("roles", Role.values());
                    model.addAttribute("companies",companyService.findAll());
                    return "user/user";
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/registration")
    public String registration(Model model,@ModelAttribute("user") UserCreateEditDto user){
        model.addAttribute("user",user);
        model.addAttribute("roles", Role.values());
        model.addAttribute("companies",companyService.findAll());
        return "user/registration";
    }

    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
    public String create(@ModelAttribute @Validated({Default.class, CreateAction.class}) UserCreateEditDto user,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("user",user);
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
            return "redirect:/users/registration";
        }
        return "redirect:/users/" + userService.create(user).getId();
    }

    //    @PutMapping("{id}")
    @PostMapping("{id}/update")
    public String update(@PathVariable("id") Long id, @ModelAttribute @Validated({Default.class, UpdateAction.class}) UserCreateEditDto user) {
        return userService.update(id, user)
                .map(it -> "redirect:/users/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    //    @DeleteMapping("{id}")
    @PostMapping("{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        if (!userService.delete(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/users";
    }

}
