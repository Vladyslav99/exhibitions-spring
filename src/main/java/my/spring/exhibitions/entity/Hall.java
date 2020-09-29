package my.spring.exhibitions.entity;

import lombok.*;

import javax.persistence.*;


@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "HALLS")
public class Hall {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String nameEnglish;

    @Column(nullable = false)
    private String nameUkrainian;

    @Column(nullable = false)
    private String descriptionEnglish;

    @Column(nullable = false)
    private String descriptionUkrainian;

    @Column(nullable = false)
    private String imageUrl;

}
