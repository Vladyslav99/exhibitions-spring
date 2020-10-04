package my.spring.exhibitions.service;

import my.spring.exhibitions.dto.ExhibitionEventDTO;
import my.spring.exhibitions.entity.*;
import my.spring.exhibitions.repository.ExhibitionEventRepository;
import my.spring.exhibitions.repository.ExhibitionRepository;
import my.spring.exhibitions.repository.HallRepository;
import my.spring.exhibitions.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;


    @Override
    public List<ExhibitionEvent> findAll() {
        return exhibitionEventRepository.findAll();
    }

    @Override
    public Optional<ExhibitionEvent> findById(Long id) {
        return exhibitionEventRepository.findById(id);
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
                    .eventStatus(ExhibitionEventStatus.FOR_SALE)
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
    public boolean saveExhibitionEvent(Optional<ExhibitionEvent> exhibitionEventOptional,
                                       ExhibitionEventStatus status) {
        if (!exhibitionEventOptional.isPresent()){
            return false;
        }
        ExhibitionEvent exhibitionEvent = exhibitionEventOptional.get();
        exhibitionEvent.setEventStatus(status);
        exhibitionEventRepository.save(exhibitionEvent);
        return true;
    }

    @Override
    public Set<ExhibitionEvent> findAllByDateFromBetweenDateTo(LocalDate dateFrom, LocalDate dateTo) {
        return exhibitionEventRepository.findAllByDateFromGreaterThanEqualAndDateToLessThanEqual(dateFrom, dateTo);
    }

    @Override
    public Set<ExhibitionEvent> findAllByDatesBetweenDateAndStatus(LocalDate date, ExhibitionEventStatus status) {
        return exhibitionEventRepository.findAllByDatesBetweenDateAndStatus(date, status);
    }

    @Override
    public Set<ExhibitionEvent> findAllByEventStatus(ExhibitionEventStatus exhibitionEventStatus) {
        return exhibitionEventRepository.findAllByEventStatus(exhibitionEventStatus);
    }

    @Override
    public Set<ExhibitionEvent> findAllByTicketCostBetween(BigDecimal from, BigDecimal to) {
        return exhibitionEventRepository.findAllByTicketCostBetween(from, to);
    }

    @Override
    public Set<ExhibitionEvent> findAllByExhibitionId(Long exhibitionId) {
        return exhibitionEventRepository.findAllByExhibitionId(exhibitionId);
    }

    @Override
    public boolean bookTicket(Long exhibitionId) {
        Optional<ExhibitionEvent> exhibitionEventOptional =
                exhibitionEventRepository.findById(exhibitionId);
        if (!exhibitionEventOptional.isPresent()) {
            return false;
        }
        ExhibitionEvent exhibitionEvent = exhibitionEventOptional.get();
        if (exhibitionEvent.getSoldPlaces().equals(exhibitionEvent.getMaxAvailablePlaces())){
            return false;
        }
        exhibitionEvent.setSoldPlaces(exhibitionEvent.getSoldPlaces() + 1);
        if (exhibitionEvent.getSoldPlaces().equals(exhibitionEvent.getMaxAvailablePlaces())){
            exhibitionEvent.setEventStatus(ExhibitionEventStatus.SOLD_OUT);
        }
        exhibitionEventRepository.save(exhibitionEvent);
        Optional<User> userOptional = getAuthorizedUser();
        if (!userOptional.isPresent()){
            return false;
        }
        Order order = new Order();
        order.setExhibitionEvent(exhibitionEvent);
        order.setUser(userOptional.get());
        orderRepository.save(order);
        return true;
    }

    private Optional<User> getAuthorizedUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal instanceof UserDetails ?
                ((UserDetails)principal).getUsername() : principal.toString();
        return userService.findUserByUsername(username);
    }

    @Override
    public Page<ExhibitionEvent> findPaginated(Pageable pageable) {
        return exhibitionEventRepository.findAll(pageable);
    }
}
