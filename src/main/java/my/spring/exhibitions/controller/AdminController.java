package my.spring.exhibitions.controller;

import my.spring.exhibitions.dto.ExhibitionDTO;
import my.spring.exhibitions.dto.HallDTO;
import my.spring.exhibitions.entity.Exhibition;
import my.spring.exhibitions.entity.Hall;
import my.spring.exhibitions.serviice.ExhibitionService;
import my.spring.exhibitions.serviice.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class AdminController {

    @Autowired
    private ExhibitionService exhibitionService;

    @Autowired
    private HallService hallService;

    @GetMapping("/admin")
    public String showAdminPage() {
        return "admin";
    }

    @GetMapping("/admin/exhibition_panel")
    public String showAdminExhibitionPanel(@RequestParam("page") Optional<Integer> page,
                                           @ModelAttribute("exhibitionDTO") ExhibitionDTO exhibitionDTO,
                                           Model model) {
        int currentPage = page.orElse(1);

        final int pageSize = 5;
        Page<Exhibition> exhibitionPage = exhibitionService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("exhibitionPage", exhibitionPage);
        model.addAttribute("exhibitions", exhibitionPage.getContent());
        model.addAttribute(exhibitionDTO);

        if (exhibitionPage.getTotalPages() > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, exhibitionPage.getTotalPages())
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "admin_exhibition_panel";
    }

    @PostMapping("/admin/exhibition_panel")
    public String addExhibition(@Valid @ModelAttribute("exhibitionDTO") ExhibitionDTO exhibitionDTO,
                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "admin_exhibition_panel";
        }

        //need handle errors and display messages
        if (!exhibitionService.saveExhibition(exhibitionDTO)) {
            return "admin_exhibition_panel";
        }

        return "redirect:/admin/exhibition_panel";
    }


    @GetMapping("/admin/hall_panel")
    public String showAdminHallPanel(@RequestParam("page") Optional<Integer> page,
                                     @ModelAttribute("hallDTO") HallDTO hallDTO,
                                     Model model) {
        int currentPage = page.orElse(1);

        final int pageSize = 5;
        Page<Hall> hallPage = hallService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("hallPage", hallPage);
        model.addAttribute("halls", hallPage.getContent());
        model.addAttribute(hallDTO);

        if (hallPage.getTotalPages() > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, hallPage.getTotalPages())
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "admin_hall_panel";
    }

    @PostMapping("/admin/hall_panel")
    public String addHall(@Valid @ModelAttribute("hallDTO") HallDTO hallDTO,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin_hall_panel";
        }

        //need handle errors and display messages
        if (!hallService.saveHall(hallDTO)) {
            return "admin_hall_panel";
        }

        return "redirect:/admin/hall_panel";
    }
}
