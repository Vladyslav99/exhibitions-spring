package my.spring.exhibitions.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "EXHIBITION_EVENTS")
public class ExhibitionEvent {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private LocalDate dateFrom;

    @Column(nullable = false)
    private LocalDate dateTo;

    @Column(nullable = false)
    private LocalTime timeFrom;

    @Column(nullable = false)
    private LocalTime timeTo;

    @Column(nullable = false)
    private BigDecimal ticketCost;

    @Column(nullable = false)
    private Long maxAvailablePlaces;

    @Column(nullable = false)
    private Long soldPlaces;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExhibitionEventStatus eventStatus;

    @ManyToOne
    private Exhibition exhibition;

    @ManyToMany
    private List<Hall> halls;
}
