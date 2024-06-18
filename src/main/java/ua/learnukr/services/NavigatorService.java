
package ua.learnukr.services;

import ua.learnukr.models.dtos.SectionNavigator;
import ua.learnukr.models.entities.Lesson;

import java.util.List;
import java.util.Optional;

public class NavigatorService {

    public static Optional<SectionNavigator> computePrevSection(List<Lesson> lessons, int lessonIndex, int sectionIndex) {
        SectionNavigator prev = new SectionNavigator();
        if (sectionIndex > 0) {
            prev.setLesson(lessonIndex);
            prev.setSection(sectionIndex - 1);
            prev.setName(
                    lessons.get(prev.getLesson())
                            .getSections()
                            .get(prev.getSection())
                            .getName()
            );
        } else if (lessonIndex > 0) {
            prev.setLesson(lessonIndex - 1);
            prev.setSection(lessons.get(prev.getLesson()).getSections().size() - 1);
            prev.setName(
                    lessons.get(prev.getLesson())
                            .getSections()
                            .get(prev.getSection())
                            .getName()
            );
        } else {
            return Optional.empty();
        }
        return Optional.of(prev);
    }

    public static Optional<SectionNavigator> computeNextSection(List<Lesson> lessons, int lessonIndex, int sectionIndex) {
        SectionNavigator next = new SectionNavigator();
        if (sectionIndex < lessons.get(lessonIndex).getSections().size() - 1) {
            next.setLesson(lessonIndex);
            next.setSection(sectionIndex + 1);
            next.setName(
                    lessons.get(next.getLesson())
                            .getSections()
                            .get(next.getSection())
                            .getName()
            );
        } else if (lessonIndex < lessons.size() - 1) {
            next.setLesson(lessonIndex + 1);
            next.setName(
                    lessons.get(next.getLesson())
                            .getSections()
                            .get(next.getSection())
                            .getName()
            );
        } else {
            return Optional.empty();
        }
        return Optional.of(next);
    }
}

