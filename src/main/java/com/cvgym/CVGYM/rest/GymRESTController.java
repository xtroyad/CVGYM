package com.cvgym.CVGYM.rest;

import com.cvgym.CVGYM.course.Course;
import com.cvgym.CVGYM.course.CouseService;
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
    @Autowired
    private CouseService courseService;




    @GetMapping("/gyms/")
    public Collection<Gym> getAllGyms() {
        return gymService.getAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/gym/", consumes = "application/json")
    public Gym createGym(@RequestBody Gym gym){
        gymService.createGym(gym);
        return gym;
    }


    @GetMapping("/courses/")
    public Collection<Course> getAllCourses() {
        return courseService.getAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/course/", consumes = "application/json")
    public Gym createCourse(@RequestBody Gym gym){
        gymService.createGym(gym);
        return gym;
    }









}
