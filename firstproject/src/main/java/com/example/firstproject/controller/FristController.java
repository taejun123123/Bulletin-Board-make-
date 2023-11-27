package com.example.firstproject.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
@Controller
public class FristController {
    @GetMapping("/hi")
    public String niceToMeetyou(Model model)
    {
        model.addAttribute("username","공시생");
        return "greetings";
    }
}
