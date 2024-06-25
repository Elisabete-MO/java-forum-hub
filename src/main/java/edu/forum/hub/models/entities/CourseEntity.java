package edu.forum.hub.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String category;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade =
            CascadeType.ALL)
    private List<TopicEntity> topics = new ArrayList<>();

    //METHODS
    public void addTopic(TopicEntity topic) {
        topic.setCourse(this);
        this.topics.add(topic);
    }
}
