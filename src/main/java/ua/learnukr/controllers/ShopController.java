package ua.learnukr.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ua.learnukr.exceptions.NoCoinsException;
import ua.learnukr.models.entities.Material;
import ua.learnukr.models.entities.User;
import ua.learnukr.repositories.MaterialRepository;
import ua.learnukr.repositories.UserRepository;

import java.io.File;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MaterialRepository materialRepository;

    // Отримання сторінки магазину
    @GetMapping
    public ModelAndView getShopPage(@AuthenticationPrincipal User user,
                                    ModelAndView modelAndView) {
        // Отримання користувача з бази даних за допомогою його ID
        user = userRepository.findById(user.getId()).orElseThrow();

        // Додавання об'єктів користувача та матеріалів до моделі для відображення на сторінці
        modelAndView.addObject("user", user);
        modelAndView.addObject("materials", materialRepository.findAll());
        // Встановлення представлення на "shop.html"
        modelAndView.setViewName("shop.html");
        return modelAndView;
    }

    // Отримання документа з магазину
    @GetMapping("/{fileName}")
    public ResponseEntity<Resource> getImage(@PathVariable("fileName") String documentName,
                                             @AuthenticationPrincipal User user) {
        // Отримання користувача з бази даних за допомогою його ID
        user = userRepository.findById(user.getId()).orElseThrow();
        Material material = materialRepository.findByName(documentName).orElseThrow();

        // Перевірка, чи має користувач достатньо монет для придбання матеріалу
        if (!material.getUsers().contains(user)) {
            if (user.getCoins() >= material.getPrice()) {
                // Зменшення кількості монет у користувача та додавання матеріалу до його списку
                user.setCoins(user.getCoins() - material.getPrice());
                material.getUsers().add(user);
                materialRepository.save(material);
                userRepository.save(user);
            } else {
                // Виняток, якщо користувач не має достатньо монет
                throw new NoCoinsException();
            }
        }

        File document = new File(String.format("storage/%s", documentName));
        // Перевірка існування файлу та його типу
        if (!document.exists() || !document.isFile()) {
            return ResponseEntity.notFound().build();
        }
        //  Надсилання файлу як відповідь на запит GET для отримання документа
        try {
            Resource documentResource = new FileSystemResource(document);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .contentLength(document.length())
                    .body(documentResource);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
