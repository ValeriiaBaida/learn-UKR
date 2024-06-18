package ua.learnukr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.learnukr.models.entities.FragmentSection;

import java.util.UUID;

@Repository
public interface FragmentSectionRepository extends JpaRepository<FragmentSection, UUID> {
}
