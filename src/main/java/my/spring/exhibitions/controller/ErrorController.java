package my.spring.exhibitions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ErrorController {

    @GetMapping("/error")
    public String showErrorPage(){
        return "error";
    }
}
