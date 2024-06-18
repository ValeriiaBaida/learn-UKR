package ua.learnukr.models.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import ua.learnukr.services.comparators.LessonSectionComparator;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Lesson {
    private static final Comparator<LessonSection> lessonSectionComparator = new LessonSectionComparator();

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    @Column(name = "_order")
    private Integer order;
    private String name;

    @OneToMany(mappedBy = "lesson")
    private List<LessonSection> sections;
    @ManyToOne
    private Topic topic;

    public Lesson(Integer order, String name, Topic topic) {
        this.order = order;
        this.name = name;
        this.topic = topic;
    }

    public List<LessonSection> getSections() {
        sections.sort(lessonSectionComparator);
        return sections;
    }
}
