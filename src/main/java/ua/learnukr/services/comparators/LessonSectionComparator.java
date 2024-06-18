package ua.learnukr.services.comparators;

import org.springframework.stereotype.Service;
import ua.learnukr.models.entities.LessonSection;

import java.util.Comparator;


@Service
public class LessonSectionComparator implements Comparator<LessonSection> {
    // Порівняння підсекцій уроків за їхнім порядком
    @Override
    public int compare(LessonSection o1, LessonSection o2) {
        return o1.getOrder() - o2.getOrder();
    }
}
