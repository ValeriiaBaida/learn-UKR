package ua.learnukr.services;

import ua.learnukr.models.entities.CompletedTask;
import ua.learnukr.models.entities.LessonSection;
import ua.learnukr.models.entities.Task;
import ua.learnukr.models.enums.AnswerType;

import java.util.List;

public class UserProgressService {
    // Метод обчислює прогрес користувача для завершених підсекції
    public double computeProgress(List<LessonSection> completedSections, List<LessonSection> sections) {
        int totalSections = sections.size();

        // Кількість завершених підсекцій
        long completedCount = sections.stream()
                // Фільтрація по завершеним підсекціям
                .filter(completedSections::contains)
                .count();

        // Обчислення прогресу у відсотках (заокруглення до двох знаків після коми)
        return Math.ceil((double) completedCount / totalSections * 100) / 100;
    }

    // Метод обчислює прогрес користувача для завершених завдань
    public double computeTaskProgress(List<CompletedTask> completedTasks, List<Task> tasks) {
        int totalTasks = tasks.size();

        long completedCount = completedTasks.stream()
                .map(ct -> ct.getId().getTask())
                .filter(tasks::contains)
                .count();
        // Обчислення прогресу у відсотках (заокруглення до двох знаків після коми)
        return Math.ceil((double) completedCount / totalTasks * 100) / 100;
    }

    // Метод обчислює прогрес користувача для завершених правильних завдань
    public double computeTaskCorrectProgress(List<CompletedTask> completedTasks, List<Task> tasks) {
        int totalTasks = tasks.size();

        long completedCount = completedTasks.stream()
                .filter(ct -> tasks.contains(ct.getId().getTask()) && ct.getType() == AnswerType.CORRECT)
                .count();
        // Обчислення прогресу у відсотках (заокруглення до двох знаків після коми)
        return Math.ceil((double) completedCount / totalTasks * 100) / 100;
    }

    // метод перевіряє, чи всі завдання користувача є правильними
    public boolean isAllCorrect(List<CompletedTask> completedTasks, int size) {
        return completedTasks.stream().filter(task -> task.getType() == AnswerType.CORRECT).count() == size;
    }
}
