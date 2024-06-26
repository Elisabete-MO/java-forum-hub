package edu.forum.hub.repository;

import edu.forum.hub.models.entities.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {
}
