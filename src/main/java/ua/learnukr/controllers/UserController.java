package ua.learnukr.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.learnukr.models.entities.User;
import ua.learnukr.repositories.UserRepository;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Метод для отримання сторінки входу
    @GetMapping("/login")
    public ModelAndView getLoginPage(ModelAndView modelAndView) {
        modelAndView.setViewName("login.html");
        return modelAndView;
    }

    // Метод для отримання сторінки реєстрації
    @GetMapping("/registration")
    public ModelAndView getRegistrationPage(ModelAndView modelAndView) {
        modelAndView.setViewName("registration.html");
        return modelAndView;
    }

    // Метод для обробки запиту на реєстрацію користувача
    @PostMapping("/registration")
    public ModelAndView registration(@RequestParam("name") String name,
                                     @RequestParam("surname") String surname,
                                     @RequestParam("email") String email,
                                     @RequestParam("password") String password) {
        // Перевірка, чи існує вже користувач з такою електронною поштою
        User existingUser = userRepository.findByEmail(email);
        if (existingUser != null) {
            return new ModelAndView("redirect:/registration?error");
        }
        // Створення нового користувача та збереження його в базу даних
        User user = new User(name, surname, email, passwordEncoder.encode(password));
        userRepository.save(user);
        // Перенаправлення на головну сторінку після успішної реєстрації
        return new ModelAndView("redirect:/topics");
    }
}
