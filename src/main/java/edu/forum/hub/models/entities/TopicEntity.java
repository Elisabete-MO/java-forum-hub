package edu.forum.hub.models.entities;

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

    @Setter
    @Column(nullable = false)
    private String title;

    @Setter
    @Column(nullable = false)
    private String content;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private CourseEntity course;

    @Setter
    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Setter
    private Boolean status;

    @OneToMany(mappedBy = "topic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ReplyEntity> replies;

    //CONSTRUCTORS
    public TopicEntity(String title, String content, UserEntity user, CourseEntity course) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.course = course;
        this.creationDate = LocalDateTime.now();
        this.status = false;
        this.replies = new ArrayList<>();
    }
}
