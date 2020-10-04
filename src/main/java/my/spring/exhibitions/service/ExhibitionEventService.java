package my.spring.exhibitions.service;

import my.spring.exhibitions.dto.ExhibitionEventDTO;
import my.spring.exhibitions.entity.ExhibitionEvent;
import my.spring.exhibitions.entity.ExhibitionEventStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ExhibitionEventService extends AbstractService<ExhibitionEvent> {
    List<ExhibitionEvent> findAll();

    Optional<ExhibitionEvent> findById(Long id);

    boolean saveExhibitionEvent(ExhibitionEventDTO exhibitionEventDTO);

    boolean saveExhibitionEvent(Optional<ExhibitionEvent> exhibitionEvent, ExhibitionEventStatus status);

    Set<ExhibitionEvent> findAllByDateFromBetweenDateTo(LocalDate dateFrom, LocalDate dateTo);

    Set<ExhibitionEvent> findAllByDatesBetweenDateAndStatus(LocalDate date, ExhibitionEventStatus status);

    Set<ExhibitionEvent> findAllByEventStatus(ExhibitionEventStatus exhibitionEventStatus);

    Set<ExhibitionEvent> findAllByTicketCostBetween(BigDecimal from, BigDecimal to);

    Set<ExhibitionEvent> findAllByExhibitionId(Long exhibitionId);

    boolean bookTicket(Long exhibitionId);
}
