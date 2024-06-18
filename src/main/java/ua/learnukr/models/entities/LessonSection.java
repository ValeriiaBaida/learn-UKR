package ua.learnukr.models.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import ua.learnukr.services.comparators.FragmentSectionComparator;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class LessonSection {
    private static final Comparator<FragmentSection> fragmentSectionComparator = new FragmentSectionComparator();

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    @Column(name = "_order")
    private Integer order;
    private String name;
    @ManyToOne
    private Lesson lesson;
    @OneToMany(mappedBy = "section")
    private List<FragmentSection> fragments;


    public LessonSection(Lesson lesson) {
        this.order = 1;
        this.name = lesson.getName();
        this.lesson = lesson;
    }

    public LessonSection(String name, Lesson lesson) {
        this.order = 1;
        this.name = name;
        this.lesson = lesson;
    }

    public LessonSection(Integer order, String name, Lesson lesson) {
        this.order = order;
        this.name = name;
        this.lesson = lesson;
    }

    public List<FragmentSection> getFragments() {
        fragments.sort(fragmentSectionComparator);
        return fragments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonSection that = (LessonSection) o;
        return Objects.equals(id, that.id) && Objects.equals(order, that.order) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, name);
    }
}
