package edu.forum.hub.repository;

import edu.forum.hub.models.entities.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicRepository extends JpaRepository<TopicEntity, Long> {
    boolean existsByTitle(String title);

    boolean existsByContent(String content);

    @Query("SELECT t FROM TopicEntity t " +
            "WHERE t.course.name = :courseName AND YEAR(t.creationDate) = :year")
    List<TopicEntity> findByCourseNameAndYear(@Param("courseName") String courseName,
                                              @Param("year") int year);

    TopicEntity findByTitle(String title);
}
