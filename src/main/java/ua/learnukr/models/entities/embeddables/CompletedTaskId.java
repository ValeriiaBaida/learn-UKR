package ua.learnukr.models.entities.embeddables;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.learnukr.models.entities.Task;
import ua.learnukr.models.entities.User;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompletedTaskId {
    @ManyToOne
    private User user;
    @ManyToOne
    private Task task;
}
