package my.spring.exhibitions.controller;

import my.spring.exhibitions.dto.ExhibitionDTO;
import my.spring.exhibitions.dto.ExhibitionEventDTO;
import my.spring.exhibitions.dto.HallDTO;
import my.spring.exhibitions.entity.Exhibition;
import my.spring.exhibitions.entity.ExhibitionEvent;
import my.spring.exhibitions.entity.ExhibitionEventStatus;
import my.spring.exhibitions.entity.Hall;
import my.spring.exhibitions.service.ExhibitionEventService;
import my.spring.exhibitions.service.ExhibitionService;
import my.spring.exhibitions.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class AdminController {

    private static final int PAGE_SIZE = 5;

    @Autowired
    private ExhibitionService exhibitionService;

    @Autowired
    private HallService hallService;

    @Autowired
    private ExhibitionEventService exhibitionEventService;

    @GetMapping("/admin/exhibition_panel")
    public String showAdminExhibitionPanel(@RequestParam("page") Optional<Integer> page,
                                           @ModelAttribute("exhibitionDTO") ExhibitionDTO exhibitionDTO,
                                           Model model) {
        int currentPage = page.orElse(1);

        Page<Exhibition> exhibitionPage = exhibitionService.findPaginated(PageRequest.of(currentPage - 1, PAGE_SIZE));
        model.addAttribute("exhibitionPage", exhibitionPage);
        model.addAttribute("exhibitions", exhibitionPage.getContent());

        if (exhibitionPage.getTotalPages() > 0) {
            model.addAttribute("pageNumbers", collectPageNumbers(exhibitionPage.getTotalPages()));
        }

        return "admin_exhibition_panel";
    }

    private List<Integer> collectPageNumbers(int totalPages) {
        return IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
    }

    @PostMapping("/admin/exhibition_panel")
    public String addExhibition(@Valid @ModelAttribute("exhibitionDTO") ExhibitionDTO exhibitionDTO,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin_exhibition_panel";
        }

        if (!exhibitionService.saveExhibition(exhibitionDTO)) {
            return "redirect:/error";
        }

        return "redirect:/admin/exhibition_panel";
    }


    @GetMapping("/admin/hall_panel")
    public String showAdminHallPanel(@RequestParam("page") Optional<Integer> page,
                                     @ModelAttribute("hallDTO") HallDTO hallDTO,
                                     Model model) {
        int currentPage = page.orElse(1);

        Page<Hall> hallPage = hallService.findPaginated(PageRequest.of(currentPage - 1, PAGE_SIZE));
        model.addAttribute("hallPage", hallPage);
        model.addAttribute("halls", hallPage.getContent());

        if (hallPage.getTotalPages() > 0) {
            model.addAttribute("pageNumbers", collectPageNumbers(hallPage.getTotalPages()));
        }

        return "admin_hall_panel";
    }

    @PostMapping("/admin/hall_panel")
    public String addHall(@Valid @ModelAttribute("hallDTO") HallDTO hallDTO,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin_hall_panel";
        }
        if (!hallService.saveHall(hallDTO)) {
            return "redirect:/error";
        }
        return "redirect:/admin/hall_panel";
    }

    @GetMapping({"/admin/exhibition_event_panel"})
    public String showAdminExhibitionEventPanel(@RequestParam("page") Optional<Integer> page,
                                                @ModelAttribute("exhibitionEventDTO") ExhibitionEventDTO
                                                        exhibitionEventDTO, Model model) {
        int currentPage = page.orElse(1); //duplicated code
        Page<ExhibitionEvent> exhibitionEventPage =
                exhibitionEventService.findPaginated(PageRequest.of(currentPage - 1, PAGE_SIZE));
        List<Exhibition> exhibitions = exhibitionService.findAll();
        List<Hall> halls = hallService.findAll();
        model.addAttribute("exhibitions", exhibitions);
        model.addAttribute("halls", halls);
        model.addAttribute("exhibitionEventPage", exhibitionEventPage);
        model.addAttribute("exhibitionEvents", exhibitionEventPage.getContent());
        if (exhibitionEventPage.getTotalPages() > 0) {
            model.addAttribute("pageNumbers", collectPageNumbers(exhibitionEventPage.getTotalPages()));
        }
        return "admin_exhibition_event_panel";
    }

    @PostMapping("/admin/exhibition_event_panel")
    public String addExhibitionEvent(@Valid @ModelAttribute("exhibitionEventDTO") ExhibitionEventDTO exhibitionEventDTO,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin_exhibition_event_panel";
        }
        if (!exhibitionEventService.saveExhibitionEvent(exhibitionEventDTO)) {
            return "redirect:/error";
        }
        return "redirect:/admin/exhibition_event_panel";
    }

    @PostMapping("/admin/exhibition_event_panel/update_status")
    public String updateEventStatus(@RequestParam("eventId") Optional<Long> eventId,
                                    @RequestParam("status") Optional<String> status) {

        if (!eventId.isPresent() || !status.isPresent()){
            return "redirect:/admin/exhibition_event_panel";
        }
        Optional<ExhibitionEvent> exhibitionEvent = exhibitionEventService.findById(eventId.get());

        if (exhibitionEventService.saveExhibitionEvent(exhibitionEvent, ExhibitionEventStatus.valueOf(status.get()))){
            return "redirect:/admin/exhibition_event_panel";
        }

        return "redirect:/error";
    }
}
