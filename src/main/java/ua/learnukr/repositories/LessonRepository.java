package ua.learnukr.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.learnukr.models.entities.Lesson;
import ua.learnukr.models.entities.Topic;

import java.util.List;
import java.util.UUID;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, UUID> {
    @Query("SELECT l FROM Lesson l WHERE l.topic=:topic")
    List<Lesson> findAllByTopic(@Param("topic") Topic topic, Sort sort);
}