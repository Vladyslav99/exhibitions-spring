package my.spring.exhibitions.service;

import my.spring.exhibitions.dto.ExhibitionEventDTO;
import my.spring.exhibitions.entity.ExhibitionEvent;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface ExhibitionEventService extends AbstractService<ExhibitionEvent>{
    List<ExhibitionEvent> findAll();

    boolean saveExhibitionEvent(ExhibitionEventDTO exhibitionEventDTO);

    Set<ExhibitionEvent> findAllByDateFromBetweenDateTo(LocalDate dateFrom, LocalDate dateTo);
}
