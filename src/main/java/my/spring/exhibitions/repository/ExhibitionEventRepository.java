package my.spring.exhibitions.repository;

import my.spring.exhibitions.entity.ExhibitionEvent;
import my.spring.exhibitions.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface ExhibitionEventRepository extends JpaRepository<ExhibitionEvent, Long> {
    Set<ExhibitionEvent> findAllByDateFromGreaterThanEqualAndDateToGreaterThanEqual(LocalDate dateFrom, LocalDate dateTo);
}
