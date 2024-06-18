package ua.learnukr.models.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SectionNavigator {
    private int lesson = 0;
    private int section = 0;
    private String name;
}
