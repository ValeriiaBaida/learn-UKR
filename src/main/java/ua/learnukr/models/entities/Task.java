package ua.learnukr.models.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    @Column(name = "_order")
    private Integer order;
    @Column(columnDefinition = "TEXT")
    private String task;

    @ManyToOne
    private Topic topic;
    @OneToMany(mappedBy = "task")
    private List<TaskAnswer> answers;

    public Task(Integer order, String task, Topic topic) {
        this.order = order;
        this.task = task;
        this.topic = topic;
    }
}
