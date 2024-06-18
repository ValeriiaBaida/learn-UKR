package ua.learnukr.models.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import ua.learnukr.services.comparators.LessonComparator;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Topic {
    private static final Comparator<Lesson> lessonComparator = new LessonComparator();

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    @Column(name = "_order")
    private Integer order;
    private String name;

    @OneToMany(mappedBy = "topic")
    private List<Lesson> lessons;
    @OneToMany(mappedBy = "topic")
    private List<Task> tasks;

    public Topic(Integer order, String name) {
        this.order = order;
        this.name = name;
    }

    // Метод для отримання списку уроків з сортуванням
    public List<Lesson> getLessons() {
        lessons.sort(lessonComparator);
        return lessons;
    }
}
