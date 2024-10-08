package test.task.app.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Сущность пользователя, представляющая пользователя в системе.
 * Этот класс реализует интерфейс {@link UserDetails} и используется для
 * хранения информации о пользователе, включая его имя, электронную почту,
 * пароль, а также задачи, которые он создал или к которым был назначен
 */
@Getter
@Setter
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails, BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    @Size(min = 3)
    private String password;

    @CreatedDate
    private LocalDate createdAt;

    @LastModifiedDate
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private List<Task> createdTasks;

    @ManyToMany(mappedBy = "assignees", fetch = FetchType.EAGER)
    private List<Task> assignedTasks;

    /**
     * Возвращает коллекцию прав пользователя.
     * @return коллекция прав пользователя (в данном случае пустая).
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<GrantedAuthority>();
    }

    /**
     * Возвращает имя пользователя (в данном случае - электронную почту).
     * @return электронная почта пользователя.
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Возвращает пароль пользователя.
     * @return пароль пользователя.
     */
    @Override
    public String getPassword() {
        return password;
    }
}
