package com.cvgym.CVGYM;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HompageController {

    @GetMapping("/fd")
    public String homepage (){
        //model.addAttribute("name", nombre);
        return "homepage";
    }
}