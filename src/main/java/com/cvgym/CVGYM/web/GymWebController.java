package com.cvgym.CVGYM.web;

import com.cvgym.CVGYM.HasACourseService;
import com.cvgym.CVGYM.coach.CoachService;
import com.cvgym.CVGYM.courseSet.Course;
import com.cvgym.CVGYM.courseSet.CourseService;
import com.cvgym.CVGYM.gym.Gym;
import com.cvgym.CVGYM.gym.GymService;
import com.cvgym.CVGYM.manager.Manager;
import com.cvgym.CVGYM.manager.ManagerService;
import com.cvgym.CVGYM.question.Question;
import com.cvgym.CVGYM.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Controller
public class GymWebController {

    @Autowired
    private GymService gymService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private HasACourseService hasACourseService;
    @Autowired
    private CoachService coachService;


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

    @GetMapping("/edit-Gym")
    public String editGymPage(Model model,  @RequestParam Long gymId) {
        Optional<Gym> op=gymService.findById(gymId);

        if(op.isPresent()){

            if(op.get().getManagerId()!=null && op.get().getManagerId()!=0L){

                Optional<Manager> op2=managerService.findById(op.get().getManagerId());
                model.addAttribute("manager",op2.get());
            }else{
                model.addAttribute("manager",new Manager(" "," ",0l));
            }
            model.addAttribute("gym",op.get());

            return "forms/editGym";
        }
        else{
            return "redirect:/error";
        }

    }
    @GetMapping("/coaches/")
    public String coaches(Model model) {
        model.addAttribute("gym", gymService.getAll());
        model.addAttribute("coaches", coachService.getAll());
        return "coaches";
    }

    @GetMapping("/create-Class/")
    public String createClassPage() {
        return "forms/createClass";
    }

    @GetMapping("/edit-Class")
    public String edirCourse(Model model,  @RequestParam Long courseId) {
        model.addAttribute("course", courseService.findById(courseId).get());
        return "forms/editClass";
    }

    @GetMapping("/add-Coach/")
    public String addCoachPage(Model model) {
        model.addAttribute("gym", gymService.getAll());
        return "forms/addCoach";
    }

    @GetMapping("/add-class-to-gym/")
    public String addClassToGymPage(Model model) {
        model.addAttribute("course", courseService.getAll());
        model.addAttribute("gym",gymService.getAll());
        return "forms/addClass";
    }

    @GetMapping("/mailbox/")
    public String showMailbox(Model model) {
        model.addAttribute("questions",questionService.getAllQuestions());
        return "mailbox";
    }
    @GetMapping("/course/")
    public String showAllCourses(Model model) {
        model.addAttribute("courses", courseService.getAll());
        return "courses";
    }

    @GetMapping("/create-question/")
    public String createQuestion() {
        return "forms/contact";
    }

    @GetMapping("/help/")
    public String help() {
        return "helpFAQs";
    }

    //--------------------------------------------------------------------------------------------------------------------
    @GetMapping("/centers/")

    public String showCenters(Model model) {

        model.addAttribute("ccaa", gymService.getAllCCAA());
        model.addAttribute("gyms", gymService.getAll());

        return "centers";
    }

    @GetMapping("/center")

    public String showCenter(Model model, @RequestParam Long gymId) {

        Optional<Gym> op = gymService.findById(gymId);

        if (op.isPresent()) {

            model.addAttribute("gym", op.get());

            model.addAttribute("courses", hasACourseService.getCourses(gymId).get());
            return "center";
        } else {
            // If gym does not exist, return status 404 (Not Found)
            return "redirect:/error";
        }


    }
    //--------------------------------------------------------------------------------------------------------------------



    @PostMapping("/gym/")
    public String newGym(Model model, Gym gym, @RequestParam("name") String name, @RequestParam("lastName") String lastName) {
        gymService.createGym(gym);
        managerService.createManager(new Manager(name, lastName), gym.getId());
        return "redirect:/centers/";
    }

    @PutMapping("/gym/{gymId}")
    public String upDateGym(Gym gym, @PathVariable Long gymId, @RequestParam("name") String name, @RequestParam("lastName") String lastName) {
        if (gymService.containsKey(gymId)) {
            gymService.putGym(gymId, gym);
            return "redirect:/centers/";
        } else {
            // If gym does not exist, return status 404 (Not Found)
            return "redirect:/error";
        }
    }

    //--------------------------------------------------------------------------------------------------------------------



    @PostMapping("/course/")
    public String newClass(Model model, Course course) {
        courseService.createCourse(course);
        return "redirect:/";
    }

    @PostMapping("/contact/")
    public String newQuestion(Model model, Question question){
        questionService.createQuestion(question);
        return "redirect:/";
    }

    @GetMapping("/contacts/")
    public String showQuestions(Model model){
        model.addAttribute(questionService.getAllQuestions());
        return null;
    }
    @PostMapping("/coach/{gymId}")
    public String showQuestions(Model model,@PathVariable Long gymId){
        model.addAttribute(questionService.getAllQuestions());
        return null;
    }




}