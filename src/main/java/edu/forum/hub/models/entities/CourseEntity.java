package edu.forum.hub.models.entities;

import edu.forum.hub.controllers.dtos.CourseRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
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

    //CONSTRUCTORS
    public CourseEntity(CourseRequestDto course) {
        this.name = course.name();
        this.category = course.category();
    }

    //METHODS
    public void addTopic(TopicEntity topic) {
        topic.setCourse(this);
        this.topics.add(topic);
    }

}
