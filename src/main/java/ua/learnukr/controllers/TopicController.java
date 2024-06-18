package ua.learnukr.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.learnukr.models.entities.*;
import ua.learnukr.repositories.*;
import ua.learnukr.services.NavigatorService;
import ua.learnukr.services.UserProgressService;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/topics")
@RequiredArgsConstructor
public class TopicController {
    private static final UserProgressService USER_PROGRESS_SERVICE = new UserProgressService();
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private CompletedTaskRepository completedTaskRepository;

    // Метод для відображення списку тем та відповідну інфромацію про користувача
    @GetMapping
    public ModelAndView getTopics(@AuthenticationPrincipal User user,
                                  @AuthenticationPrincipal Principal principal,
                                  @AuthenticationPrincipal UserDetails userDetails,
                                  ModelAndView modelAndView) {

        Sort sort = Sort.by(Sort.Direction.ASC, "order");
        user = userRepository.findById(user.getId()).orElseThrow();

        // Додавання списку тем до моделі для відображення
        modelAndView.addObject("topics", topicRepository.findAll(sort));
        modelAndView.addObject("user", user);
        modelAndView.addObject("service", USER_PROGRESS_SERVICE);
        modelAndView.addObject("repository", completedTaskRepository);
        modelAndView.setViewName("topics.html");
        return modelAndView;
    }

    // Метод для отримання конкретної теми
    @GetMapping("/{id}")
    public ModelAndView getTopic(@PathVariable UUID id,
                                 @RequestParam(value = "l", defaultValue = "1") Integer lessonParam,
                                 @RequestParam(value = "s", defaultValue = "1") Integer sectionParam,
                                 @AuthenticationPrincipal User user,
                                 ModelAndView modelAndView) {
        lessonParam--;
        sectionParam--;

        Sort sort = Sort.by(Sort.Direction.ASC, "order");
        // Отримання теми з бази даних за її ID
        Topic topic = topicRepository.findById(id).orElseThrow();
        // Отримання списку уроків для цієї теми, відсортованих
        List<Lesson> lessons = lessonRepository.findAllByTopic(topic, sort);
        // Отримання конкретної секції уроку зі списку
        LessonSection lessonSection = lessons.get(lessonParam).getSections().get(sectionParam);


        user = userRepository.findById(user.getId()).orElseThrow();
        // Оновлення інформації про користувача
        user.getCompletedSections().add(lessonSection);
        userRepository.save(user);

        // Додавання даних до моделі для відображення на сторінці
        modelAndView.addObject("topic", topic);
        modelAndView.addObject("lessons", lessons);
        modelAndView.addObject("section", lessonSection);
        modelAndView.addObject("user", user);

        // Визначення навігації між уроками теми
        NavigatorService.computePrevSection(lessons, lessonParam, sectionParam)
                .ifPresent((navigator) -> modelAndView.addObject("prev", navigator));
        NavigatorService.computeNextSection(lessons, lessonParam, sectionParam)
                .ifPresent((navigator) -> modelAndView.addObject("next", navigator));

        modelAndView.setViewName("topic.html");
        return modelAndView;
    }

    // Метод для отримання завдань конкретної теми
    @GetMapping("/{topicId}/tasks")
    public ModelAndView getTasks(@PathVariable UUID topicId,
                                 @AuthenticationPrincipal User user,
                                 ModelAndView modelAndView) {
        user = userRepository.findById(user.getId()).orElseThrow();
        Sort sort = Sort.by(Sort.Direction.ASC, "order");
        // Отримання теми з бази даних за її ID
        Topic topic = topicRepository.findById(topicId).orElseThrow();
        // Отримання уроків для даної теми
        List<Lesson> lessons = lessonRepository.findAllByTopic(topic, sort);
        // Отримання завдань для даної теми
        List<Task> tasks = taskRepository.findAllByTopic(topic);

        // Додавання даних до моделі для відображення на сторінці
        modelAndView.addObject("topic", topic);
        modelAndView.addObject("lessons", lessons);
        modelAndView.addObject("tasks", tasks);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("topic.html");
        return modelAndView;
    }
}
