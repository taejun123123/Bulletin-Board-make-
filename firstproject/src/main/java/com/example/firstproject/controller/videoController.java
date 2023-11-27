package com.example.firstproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
@Controller
@Slf4j
public class videoController {
    @GetMapping("baby")
    public String videoIndex(Model model){

        model.addAttribute("videoUrl","/video/baby.mp4");
        return  "videos/video";
    }
}
