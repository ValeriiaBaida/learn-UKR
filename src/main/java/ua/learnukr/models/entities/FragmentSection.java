package ua.learnukr.models.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.learnukr.models.enums.FragmentType;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FragmentSection {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id;
    @Column(name = "_order")
    private Integer order;
    @Enumerated
    private FragmentType fragmentType;
    @ElementCollection
    @Column(columnDefinition = "TEXT")
    private List<String> contents;

    @ManyToOne
    private LessonSection section;

    public FragmentSection(FragmentType fragmentType, String content, LessonSection section) {
        this.order = 1;
        this.fragmentType = fragmentType;
        this.contents = Arrays.stream(content.split("\n")).map(String::trim).filter(e -> !e.isBlank()).toList();
        this.section = section;
    }

    public FragmentSection(Integer order, FragmentType fragmentType, String content, LessonSection section) {
        this.order = order;
        this.fragmentType = fragmentType;
        this.contents = Arrays.stream(content.split("\n\n")).map(String::trim).filter(e -> !e.isBlank()).toList();
        this.section = section;
    }

    public FragmentSection(Integer order, FragmentType fragmentType, List<String> contents, LessonSection section) {
        this.order = order;
        this.fragmentType = fragmentType;
        this.contents = contents;
        this.section = section;
    }
}
