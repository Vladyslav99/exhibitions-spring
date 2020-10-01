package my.spring.exhibitions.service;

import my.spring.exhibitions.dto.ExhibitionEventDTO;
import my.spring.exhibitions.entity.Exhibition;
import my.spring.exhibitions.entity.ExhibitionEvent;
import my.spring.exhibitions.entity.ExhibitionEventStatus;
import my.spring.exhibitions.entity.Hall;
import my.spring.exhibitions.repository.ExhibitionEventRepository;
import my.spring.exhibitions.repository.ExhibitionRepository;
import my.spring.exhibitions.repository.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ExhibitionEventServiceImpl implements ExhibitionEventService {

    @Autowired
    private ExhibitionRepository exhibitionRepository;

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private ExhibitionEventRepository exhibitionEventRepository;

    @Override
    public List<ExhibitionEvent> findAll() {
        return exhibitionEventRepository.findAll();
    }

    @Override
    public boolean saveExhibitionEvent(ExhibitionEventDTO exhibitionEventDTO) {
        Optional<Exhibition> exhibitionOptional = exhibitionRepository.findById(exhibitionEventDTO.getExhibitionId());
        List<Hall> halls = hallRepository.findAllById(exhibitionEventDTO.getHallIds());

        if (exhibitionOptional.isPresent() && halls.size() > 0) {
            ExhibitionEvent exhibitionEvent = ExhibitionEvent.builder()
                    .dateFrom(exhibitionEventDTO.getDateFrom())
                    .dateTo(exhibitionEventDTO.getDateTo())
                    .timeFrom(exhibitionEventDTO.getTimeFrom())
                    .timeTo(exhibitionEventDTO.getTimeTo())
                    .ticketCost(exhibitionEventDTO.getTicketCost())
                    .soldPlaces(0l)
                    .eventStatus(ExhibitionEventStatus.PLANNED)
                    .maxAvailablePlaces(exhibitionEventDTO.getMaxAvailablePlaces())
                    .exhibition(exhibitionOptional.get())
                    .halls(halls)
                    .build();
            exhibitionEventRepository.save(exhibitionEvent);
            return true;
        }
        return false;
    }

    @Override
    public Set<ExhibitionEvent> findAllByDateFromBetweenDateTo(LocalDate dateFrom, LocalDate dateTo) {
        return exhibitionEventRepository.findAllByDateFromGreaterThanEqualAndDateToGreaterThanEqual(dateFrom, dateTo);
    }

    @Override
    public Page<ExhibitionEvent> findPaginated(Pageable pageable) {
        return exhibitionEventRepository.findAll(pageable);
    }
}
