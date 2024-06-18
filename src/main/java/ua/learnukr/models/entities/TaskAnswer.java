package ua.learnukr.models.entities;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.learnukr.models.enums.AnswerType;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TaskAnswer {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String answer;
    @Enumerated
    private AnswerType type;

    @ManyToOne
    private Task task;

    public TaskAnswer(String answer, Task task) {
        this.answer = answer;
        this.type = AnswerType.INCORRECT;
        this.task = task;
    }

    public TaskAnswer(String answer, Task task, AnswerType type) {
        this.answer = answer;
        this.type = type;
        this.task = task;
    }
}
