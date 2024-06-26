package edu.forum.hub.repository;

import edu.forum.hub.models.entities.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicRepository extends JpaRepository<TopicEntity, Long> {
    boolean existsByTitle(String title);

    boolean existsByContent(String content);

    List<TopicEntity> findByCourseName(@Param("courseName") String courseName);
}
