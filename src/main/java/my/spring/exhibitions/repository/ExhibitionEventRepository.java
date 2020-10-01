package my.spring.exhibitions.repository;

import my.spring.exhibitions.entity.ExhibitionEvent;
import my.spring.exhibitions.entity.ExhibitionEventStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Set;

public interface ExhibitionEventRepository extends JpaRepository<ExhibitionEvent, Long> {
    Set<ExhibitionEvent> findAllByDateFromGreaterThanEqualAndDateToGreaterThanEqual(LocalDate dateFrom, LocalDate dateTo);

    Set<ExhibitionEvent> findAllByEventStatus(ExhibitionEventStatus exhibitionEventStatus);
}
