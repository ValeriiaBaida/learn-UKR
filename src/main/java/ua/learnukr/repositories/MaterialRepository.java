package ua.learnukr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.learnukr.models.entities.Material;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MaterialRepository extends JpaRepository<Material, UUID> {
    @Query("SELECT m FROM Material m WHERE m.name=:name")
    Optional<Material> findByName(@Param("name") String name);
}
