package ua.learnukr.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.learnukr.models.entities.User;


@RestController
@RequestMapping
@RequiredArgsConstructor
public class MainController {
    @GetMapping
    public ModelAndView getMainPage(@AuthenticationPrincipal User user, ModelAndView modelAndView) {
        // Додавання об'єкта користувача до моделі для передачі на сторінку
        modelAndView.addObject("user", user);
        // Встановлення представлення на "index.html"
        modelAndView.setViewName("index.html");
        return modelAndView;
    }
}
