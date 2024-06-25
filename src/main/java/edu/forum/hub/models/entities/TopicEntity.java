package edu.forum.hub.models.entities;

import edu.forum.hub.controllers.dtos.topicRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topics")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TopicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "author_id")
    private String author;

    @Column(nullable = false)
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "course_id")
    private String course;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Setter
    private Boolean status;

    @OneToMany(mappedBy = "topic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ReplyEntity> replies = new ArrayList<>();

    // CONSTRUCTOR
    public TopicEntity(topicRequestDto topicRequestDto) {
        this.title = topicRequestDto.title();
        this.message = topicRequestDto.message();
        this.author = topicRequestDto.author();
        this.course = topicRequestDto.course();
        this.creationDate = LocalDateTime.now();
        this.status = false;
    }

    public void setReplies(List<ReplyEntity> replies) {
        replies.forEach(reply -> reply.setTopic(this));
        this.replies = replies;
    }
}
