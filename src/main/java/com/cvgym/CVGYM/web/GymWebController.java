package com.cvgym.CVGYM.web;

import com.cvgym.CVGYM.course.Course;
import com.cvgym.CVGYM.course.CouseService;
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
public class GymWebController {

    @Autowired
    private GymService gymService;
    @Autowired
    private MangerService mangerService;
    @Autowired
    private CouseService couserService;


    @GetMapping("/")
    public String homepage() {
        return "homepage";
    }

    @GetMapping("/prices/")
    public String prices() {
        return "prices";
    }

    @GetMapping("/create-Gym/")
    public String createGymPage() {
        return "forms/createGym";
    }

    @GetMapping("/create-Class/")
    public String createClassPage() {
        return "forms/createClass";
    }

    @GetMapping("/add-Coach/")
    public String addCoachPage() {
        return "forms/addCoach";
    }

    @GetMapping("/add-class-to-gym/")
    public String addClassToGymPage() {
        return "forms/addClass";
    }

    //--------------------------------------------------------------------------------------------------------------------
    @GetMapping("/centers/")

    public String showCenters(Model model) {

        model.addAttribute("ccaa", gymService.getAllCCAA());
        model.addAttribute("gyms", gymService.getAll());

        return "centers";
    }

    @PostMapping("/gym/")
    public String newGym(Model model, Gym gym, @RequestParam("name") String name, @RequestParam("lastName") String lastName) {
        gymService.createGym(gym);
        mangerService.createManager(new Manager(name, lastName), gym.getId());
        return "redirect:/centers/";
    }



    @PostMapping("/course/")
    public String newClass(Model model, Course course) {
        couserService.createCourse(course);
        return "redirect:/";
    }


}