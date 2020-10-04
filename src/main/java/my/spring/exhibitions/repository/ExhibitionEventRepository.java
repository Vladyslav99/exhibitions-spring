package my.spring.exhibitions.repository;

import my.spring.exhibitions.entity.ExhibitionEvent;
import my.spring.exhibitions.entity.ExhibitionEventStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public interface ExhibitionEventRepository extends JpaRepository<ExhibitionEvent, Long> {
    Set<ExhibitionEvent> findAllByDateFromGreaterThanEqualAndDateToLessThanEqual(LocalDate dateFrom, LocalDate dateTo);

    Set<ExhibitionEvent> findAllByEventStatus(ExhibitionEventStatus exhibitionEventStatus);

    @Query(value = "SELECT  * FROM exhibition_events ev where ev.date_from <= ?1 AND ev.date_to >= ?1",
            nativeQuery = true)
    Set<ExhibitionEvent> findAllByDatesBetweenDateAndStatus(LocalDate date, ExhibitionEventStatus status);

    Set<ExhibitionEvent> findAllByTicketCostBetween(BigDecimal from, BigDecimal to);

    Set<ExhibitionEvent> findAllByExhibitionId(Long id);

}
