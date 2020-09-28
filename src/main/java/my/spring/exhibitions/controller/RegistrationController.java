package my.spring.exhibitions.controller;

import my.spring.exhibitions.dto.UserDTO;
import my.spring.exhibitions.serviice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String showRegistrationPage(@ModelAttribute("userDTO") UserDTO userDTO, Model model) {
        model.addAttribute(userDTO);
        return "registration";
    }

    @PostMapping("/registration")
    public String registrate(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "registration";
        }
        userService.saveUser(userDTO);
        return "redirect:/login";
    }
}
