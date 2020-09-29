package my.spring.exhibitions.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "EXHIBITIONS")
public class Exhibition {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String themeEnglish;

    @Column(nullable = false)
    private String themeUkrainian;

    @Column(nullable = false)
    private String descriptionEnglish;

    @Column(nullable = false)
    private String descriptionUkrainian;

    @Column(nullable = false)
    private String imageUrl;

}
