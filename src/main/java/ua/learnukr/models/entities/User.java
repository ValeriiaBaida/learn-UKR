package ua.learnukr.models.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class User implements UserDetails {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String name;
    private String surname;
    @Column(unique = true)
    private String email;
    private String password;
    private Integer coins = 2;

    @ManyToMany
    private Set<LessonSection> completedSections = new HashSet<>();
    @ManyToMany
    private Set<Topic> completedTestTopic = new HashSet<>();

    public User(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    // Метод для перевірки, чи не закінчився термін дії облікового запису
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Метод для перевірки, чи не заблокований обліковий запис
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Метод для перевірки, чи не закінчився термін дії облікових даних
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Метод для перевірки, чи активний обліковий запис
    @Override
    public boolean isEnabled() {
        return true;
    }
}
