package com.humblexiang.minimalistrestserverinitialize.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Humble.X
 * @since 2022/8/31
 */
@Entity
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @CreationTimestamp
    private Date createdTime;

    @UpdateTimestamp
    private Date updatedTime;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> bookList;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Author author = (Author) o;
        return id != null && Objects.equals(id, author.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
