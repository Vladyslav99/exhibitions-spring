package my.spring.exhibitions.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "ROLES")
@Getter
public class Role {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;
}
