package com.cvgym.CVGYM.rest;

import com.cvgym.CVGYM.gym.Gym;
import com.cvgym.CVGYM.gym.GymService;
import com.cvgym.CVGYM.manager.MangerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RequestMapping("/api/")
@RestController
public class GymRESTController {
    @Autowired
    private GymService gymService;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/gyms/", consumes = "application/json")
    public Gym createGym(@RequestBody Gym gym){
        gymService.createGym(gym);
        return gym;
    }

    @GetMapping("/gyms/")
    public Collection<Gym> getAll() {
        return gymService.getAll();
    }





}
