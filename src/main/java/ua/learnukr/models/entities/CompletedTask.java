package ua.learnukr.models.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.learnukr.models.entities.embeddables.CompletedTaskId;
import ua.learnukr.models.enums.AnswerType;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompletedTask {
    @EmbeddedId
    private CompletedTaskId id;
    @Enumerated
    private AnswerType type;
}
