package ua.learnukr.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ua.learnukr.models.dtos.CheckTaskRequest;
import ua.learnukr.models.dtos.CheckTaskResponse;
import ua.learnukr.models.entities.CompletedTask;
import ua.learnukr.models.entities.Task;
import ua.learnukr.models.entities.Topic;
import ua.learnukr.models.entities.User;
import ua.learnukr.models.entities.embeddables.CompletedTaskId;
import ua.learnukr.models.enums.AnswerType;
import ua.learnukr.repositories.CompletedTaskRepository;
import ua.learnukr.repositories.TaskRepository;
import ua.learnukr.repositories.TopicRepository;
import ua.learnukr.repositories.UserRepository;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/topics/{topicId}/tasks")
@RequiredArgsConstructor
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompletedTaskRepository completedTaskRepository;
    @Autowired
    private TopicRepository topicRepository;

    // Обробник для перевірки відповіді на завдання
    @PostMapping
    public ResponseEntity<CheckTaskResponse> checkTask(@PathVariable UUID topicId,
                                                       @RequestBody CheckTaskRequest checkTaskRequest,
                                                       @AuthenticationPrincipal User user) {

        user = userRepository.findById(user.getId()).orElseThrow();
        Task task = taskRepository.findById(UUID.fromString(checkTaskRequest.getId())).orElseThrow();
        CompletedTaskId id = new CompletedTaskId(user, task);

        // Перевірка відповіді користувача на завдання
        boolean isCorrect = task.getAnswers()
                .stream()
                .anyMatch(a -> checkTaskRequest.getAnswer().equals(a.getAnswer()) && a.getType() == AnswerType.CORRECT);
        // Отримання правильної відповіді на завдання
        String correctAnswer = task.getAnswers()
                .stream()
                .filter(a -> a.getType() == AnswerType.CORRECT)
                .findFirst()
                .orElseThrow()
                .getAnswer();

        Topic topic = topicRepository.findById(topicId).orElseThrow();
        List<Task> tasks = taskRepository.findAllByTopic(topic);
        // Збереження інформації про завершене завдання
        completedTaskRepository.save(new CompletedTask(id, isCorrect ? AnswerType.CORRECT : AnswerType.INCORRECT));

        // Підрахунок кількості правильних завершених завдань користувача для даної теми
        long correctCount = completedTaskRepository.findAllByUser(user)
                .stream()
                .filter(ct -> tasks.contains(ct.getId().getTask()))
                // Фільтрування лише правильних завершених завдань
                .filter(ct -> ct.getType() == AnswerType.CORRECT)
                .count();
        // Перевірка, чи користувач виконав всі завдання для даної теми та додавання монет, якщо так
        if (tasks.size() == correctCount && !user.getCompletedTestTopic().contains(topic)) {
            user.setCoins(user.getCoins() + 2);
            user.getCompletedTestTopic().add(topic);
            userRepository.save(user);
        }

        return new ResponseEntity<>(new CheckTaskResponse(isCorrect, correctAnswer), HttpStatus.OK);
    }
}
