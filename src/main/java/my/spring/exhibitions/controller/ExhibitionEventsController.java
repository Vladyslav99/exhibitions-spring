package my.spring.exhibitions.controller;

import my.spring.exhibitions.entity.ExhibitionEvent;
import my.spring.exhibitions.entity.ExhibitionEventStatus;
import my.spring.exhibitions.service.ExhibitionEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller
public class ExhibitionEventsController {

    @Autowired
    private ExhibitionEventService exhibitionEventService;

    @GetMapping("/exhibitions")
    public String showAllAvailableExhibitions(Model model){
        Set<ExhibitionEvent> exhibitionEvents
                = exhibitionEventService.findAllByEventStatus(ExhibitionEventStatus.FOR_SALE);
        model.addAttribute("exhibitionEvents", exhibitionEvents);
        return "exhibitions";
    }
}
