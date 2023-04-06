package com.cvgym.CVGYM.rest;

import com.cvgym.CVGYM.course.Course;
import com.cvgym.CVGYM.course.CouseService;
import com.cvgym.CVGYM.gym.Gym;
import com.cvgym.CVGYM.gym.GymService;
import com.cvgym.CVGYM.manager.MangerService;
import com.cvgym.CVGYM.question.Question;
import com.cvgym.CVGYM.question.QuestionService;
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
    @Autowired
    private QuestionService questionService;




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
    public Course createCourse(@RequestBody Course course){
        courseService.createCourse(course);
        return course;
    }

    @GetMapping("/questions/")
    public Collection<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/question/", consumes = "application/json")
    public Question createCourse(@RequestBody Question question){
        questionService.createQuestion(question);
        return question;
    }








}
