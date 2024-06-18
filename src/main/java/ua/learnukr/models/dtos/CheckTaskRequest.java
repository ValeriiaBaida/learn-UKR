package ua.learnukr.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CheckTaskRequest {
    private String id;
    private String answer;
}
