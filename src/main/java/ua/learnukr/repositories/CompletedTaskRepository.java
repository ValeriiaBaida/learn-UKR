package ua.learnukr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.learnukr.models.entities.CompletedTask;
import ua.learnukr.models.entities.User;
import ua.learnukr.models.entities.embeddables.CompletedTaskId;

import java.util.List;

@Repository
public interface CompletedTaskRepository extends JpaRepository<CompletedTask, CompletedTaskId> {
    @Query("SELECT ct FROM CompletedTask ct WHERE ct.id.user=:user")
    List<CompletedTask> findAllByUser(@Param("user") User user);
}
