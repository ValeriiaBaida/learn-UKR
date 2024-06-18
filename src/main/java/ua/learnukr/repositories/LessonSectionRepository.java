package ua.learnukr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.learnukr.models.entities.LessonSection;

import java.util.UUID;

@Repository
public interface LessonSectionRepository extends JpaRepository<LessonSection, UUID> {
}
