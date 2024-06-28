package edu.forum.hub.repository;

import edu.forum.hub.models.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, Long>{
}
