package my.spring.exhibitions.controller;

import my.spring.exhibitions.entity.Exhibition;
import my.spring.exhibitions.entity.ExhibitionEvent;
import my.spring.exhibitions.entity.ExhibitionEventStatus;
import my.spring.exhibitions.service.ExhibitionEventService;
import my.spring.exhibitions.service.ExhibitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class ExhibitionEventsController {

    @Autowired
    private ExhibitionEventService exhibitionEventService;

    @Autowired
    private ExhibitionService exhibitionService;

    @GetMapping("/exhibitions")
    public String showAllAvailableExhibitions(Model model) {
        Set<ExhibitionEvent> exhibitionEvents
                = exhibitionEventService.findAllByEventStatus(ExhibitionEventStatus.FOR_SALE);
        List<Exhibition> exhibitions = exhibitionService.findAll();
        model.addAttribute("exhibitions", exhibitions);
        model.addAttribute("exhibitionEvents", exhibitionEvents);
        return "exhibitions";
    }

    @GetMapping("/exhibitions/between_dates")
    public String showAllAvailableExhibitionsBetweenDates(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<LocalDate> date, Model model) {

        if (!date.isPresent()) {

            return "redirect:/exhibitions";
        }
        Set<ExhibitionEvent> exhibitionEvents =
                exhibitionEventService.findAllByDatesBetweenDateAndStatus(date.get(), ExhibitionEventStatus.FOR_SALE);
        List<Exhibition> exhibitions = exhibitionService.findAll();
        model.addAttribute("exhibitions", exhibitions);
        model.addAttribute("exhibitionEvents", exhibitionEvents);
        return "exhibitions";
    }

    @GetMapping("/exhibitions/ticketCost_between")
    public String showAllAvailableExhibitionsPriceBetween(
            @RequestParam("ticketCostFrom") Optional<BigDecimal> ticketCostFrom,
            @RequestParam("ticketCostTo") Optional<BigDecimal> ticketCostTo, Model model) {

        if (!ticketCostFrom.isPresent() || !ticketCostTo.isPresent()
                || ticketCostFrom.get().compareTo(BigDecimal.ONE) < 0
                || ticketCostTo.get().compareTo(ticketCostFrom.get()) <= 0) {
            return "redirect:/exhibitions";
        }
        Set<ExhibitionEvent> exhibitionEvents =
                exhibitionEventService.findAllByTicketCostBetween(ticketCostFrom.get(), ticketCostTo.get());
        List<Exhibition> exhibitions = exhibitionService.findAll();
        model.addAttribute("exhibitions", exhibitions);
        model.addAttribute("exhibitionEvents", exhibitionEvents);
        return "exhibitions";
    }

    @GetMapping("/exhibitions/theme")
    public String showAllAvailableExhibitionsPriceBetween(@RequestParam("exhibitionId") Optional<Long> exhibitionId,
                                                          Model model) {

        if (!exhibitionId.isPresent() || exhibitionId.get() <= 0) {
            return "redirect:/exhibitions";
        }
        Set<ExhibitionEvent> exhibitionEvents =
                exhibitionEventService.findAllByExhibitionId(exhibitionId.get());
        List<Exhibition> exhibitions = exhibitionService.findAll();
        model.addAttribute("exhibitions", exhibitions);
        model.addAttribute("exhibitionEvents", exhibitionEvents);
        return "exhibitions";
    }
}
