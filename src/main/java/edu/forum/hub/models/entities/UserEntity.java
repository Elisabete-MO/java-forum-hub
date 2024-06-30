package edu.forum.hub.models.entities;

import edu.forum.hub.controllers.dtos.UserRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserEntity implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
