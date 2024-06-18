package ua.learnukr.services.comparators;

import org.springframework.stereotype.Service;
import ua.learnukr.models.entities.FragmentSection;

import java.util.Comparator;

@Service
public class FragmentSectionComparator implements Comparator<FragmentSection> {
    // Порівняння фрагментів за їхнім порядком
    @Override
    public int compare(FragmentSection o1, FragmentSection o2) {
        return o1.getOrder() - o2.getOrder();
    }
}
