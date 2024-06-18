package ua.learnukr.services.comparators;

import org.springframework.stereotype.Service;
import ua.learnukr.models.entities.Lesson;

import java.util.Comparator;

@Service
public class LessonComparator implements Comparator<Lesson> {
    // Порівняння уроків за їхнім порядком
    @Override
    public int compare(Lesson o1, Lesson o2) {
        return o1.getOrder() - o2.getOrder();
    }
}
