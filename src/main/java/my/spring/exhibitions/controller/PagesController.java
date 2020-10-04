package my.spring.exhibitions.controller;

import my.spring.exhibitions.entity.ExhibitionEvent;
import my.spring.exhibitions.service.ExhibitionEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class PagesController {

    @Autowired
    private ExhibitionEventService exhibitionEventService;

    @RequestMapping({"/", "/main"})
    public String showMain(){
        return "main";
    }

    @RequestMapping("/purchase_successful")
    public String showSuccessfulPage(@RequestParam("exhibitionEventId") Optional<Long> exhibitionEventId){

        if (!exhibitionEventId.isPresent()){
            return "redirect:/exhibitions";
        }

        if (!exhibitionEventService.bookTicket(exhibitionEventId.get())){
            return "redirect:/error";
        }
        return "purchase_successful";
    }
}
