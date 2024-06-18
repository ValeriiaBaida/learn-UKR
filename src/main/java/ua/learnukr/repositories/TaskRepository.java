package ua.learnukr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.learnukr.models.entities.Task;
import ua.learnukr.models.entities.Topic;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    @Query("SELECT t FROM Task t WHERE t.topic=:topic ORDER BY t.order ASC")
    List<Task> findAllByTopic(@Param("topic") Topic topic);
}
