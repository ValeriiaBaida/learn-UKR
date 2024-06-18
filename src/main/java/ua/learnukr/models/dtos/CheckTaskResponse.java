package ua.learnukr.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CheckTaskResponse {
    private boolean result;
    private String correctAnswer;
}
