package com.cvgym.CVGYM.web;

import com.cvgym.CVGYM.gym.Gym;
import com.cvgym.CVGYM.gym.GymService;
import com.cvgym.CVGYM.manager.Manager;
import com.cvgym.CVGYM.manager.MangerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HompageController {

    @Autowired
    private GymService gymService;
    @Autowired
    private MangerService mangerService;
    @GetMapping("/centers")
    public String showCenters(Model model){
        model.addAttribute("ccaa",gymService.getAllCCAA());
        model.addAttribute("gyms",gymService.getAll());

        return "centers";
    }

    @PostMapping("/gym")
    public String newGym (Model model, Gym gym, @RequestParam("name") String name, @RequestParam("lastName") String lastName){
        gymService.createGym(gym);
        mangerService.createManager(new Manager(name, lastName), gym.getId());
        return "/centers";
    }


}
