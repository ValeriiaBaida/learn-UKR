package ua.learnukr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.learnukr.models.entities.TaskAnswer;

import java.util.UUID;

@Repository
public interface TaskAnswerRepository extends JpaRepository<TaskAnswer, UUID> {
}
