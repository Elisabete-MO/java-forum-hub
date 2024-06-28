package edu.forum.hub.models.entities;

import edu.forum.hub.controllers.dtos.UserRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade =
            CascadeType.ALL, orphanRemoval = true)
    private List<ProfileEntity> profiles;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade =
            CascadeType.ALL)
    private List<ReplyEntity> replies;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade =
            CascadeType.ALL)
    private List<TopicEntity> topics;

    //CONSTRUCTORS
    public UserEntity(UserRequestDto userRequestDto) {
        this.name = userRequestDto.name();
        this.email = userRequestDto.email();
        this.password = userRequestDto.password();
    }

    //METHODS
    public void addProfile(ProfileEntity profile) {
        profile.setUser(this);
        this.profiles.add(profile);
    }

    public void removeProfile(ProfileEntity profile) {
        this.profiles.remove(profile);
    }

    public void addReply(ReplyEntity reply) {
        reply.setUser(this);
        this.replies.add(reply);
    }

    public void addTopic(TopicEntity topic) {
        topic.setUser(this);
        this.topics.add(topic);
    }
}
