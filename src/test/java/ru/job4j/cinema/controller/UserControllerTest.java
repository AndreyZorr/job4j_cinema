package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.service.user.UserService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserControllerTest {

    private UserService userService;
    private UserController userController;

    private User user;

    @BeforeEach
    public void initServices() {
        userService = mock(UserService.class);
        userController = new UserController(userService);
        user = new User(1, "email@email.com", "name", "password");
    }

    @Test
    public void whenRequestRegisterPageThenGetRegisterPage() {
        var view = userController.getRegistrationPage();

        assertThat(view).isEqualTo("users/register");
    }

    @Test
    public void whenRegisterUserThenGetSameDataAndRedirectToLoginPage() {
        var userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        when(userService.save(userArgumentCaptor.capture())).thenReturn(Optional.of(user));

        var model = new ConcurrentModel();
        var response = new MockHttpServletResponse();
        var view = userController.register(model, user, response);
        var actualUser = userArgumentCaptor.getValue();

        assertThat(view).isEqualTo("redirect:/films");
        assertThat(actualUser).isEqualTo(user);
    }
}