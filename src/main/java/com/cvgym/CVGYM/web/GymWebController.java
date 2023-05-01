package com.cvgym.CVGYM.web;


import com.cvgym.CVGYM.courseSet.Course;
import com.cvgym.CVGYM.gym.Gym;
import com.cvgym.CVGYM.gym.GymService;
import com.cvgym.CVGYM.manager.Manager;
import com.cvgym.CVGYM.question.Question;
import com.cvgym.CVGYM.coach.CoachRepository;
import com.cvgym.CVGYM.courseSet.CourseRepository;
import com.cvgym.CVGYM.gym.GymRepository;
import com.cvgym.CVGYM.manager.ManagerRepository;
import com.cvgym.CVGYM.question.QuestionRepository;
import jakarta.annotation.PostConstruct;
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
    private GymRepository gymRepository;
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CoachRepository coachRepository;

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
    public String editGymPage(Model model, @RequestParam Long gymId) {
        Optional<Gym> op = gymRepository.findById(gymId);

        if (op.isPresent()) {

            if (op.get().getManager().getId() != null && op.get().getManager().getId() != 0L) {

                Optional<Manager> op2 = managerRepository.findById(op.get().getManager().getId());
                model.addAttribute("manager", op2.get());
            } else {
                model.addAttribute("manager", new Manager(" ", " ", 0l));
            }
            model.addAttribute("gym", op.get());

            return "forms/editGym";
        } else {
            return "redirect:/error";
        }

    }

    @GetMapping("/coaches/")
    public String coaches(Model model) {
        model.addAttribute("gym", gymRepository.findAll());
        model.addAttribute("coaches", coachRepository.findAll());
        return "coaches";
    }

    @GetMapping("/create-Class/")
    public String createClassPage() {
        return "forms/createClass";
    }

    @GetMapping("/edit-Class")
    public String edirCourse(Model model, @RequestParam Long courseId) {
        model.addAttribute("course", courseRepository.findById(courseId).get());
        return "forms/editClass";
    }

    @GetMapping("/add-Coach/")
    public String addCoachPage(Model model) {
        model.addAttribute("gym", gymRepository.findAll());
        return "forms/addCoach";
    }

    @GetMapping("/add-class-to-gym/")
    public String addClassToGymPage(Model model) {
        model.addAttribute("course", courseRepository.findAll());
        model.addAttribute("gym", gymRepository.findAll());
        return "forms/addClass";
    }

    @GetMapping("/mailbox/")
    public String showMailbox(Model model) {
        model.addAttribute("questions", questionRepository.findAll());
        return "mailbox";
    }

    @GetMapping("/course/")
    public String showAllCourses(Model model) {
        model.addAttribute("courses", courseRepository.findAll());
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
        model.addAttribute("gyms", gymRepository.findAll());

        return "centers";
    }

    @GetMapping("/center")
    public String showCenter(Model model, @RequestParam Long gymId) {

        Optional<Gym> op = gymRepository.findById(gymId);

        if (op.isPresent()) {
            model.addAttribute("gym", op.get());
            model.addAttribute("courses", op.get().getCourses());
            return "center";
        } else {
            // If gym does not exist, return status 404 (Not Found)
            return "redirect:/error";
        }


    }
    //--------------------------------------------------------------------------------------------------------------------



    /*@PostMapping("/gym/")
    public String newGym(Model model, Gym gym, @RequestParam("name") String name, @RequestParam("lastName") String lastName) {
        gymService.createGym(gym);
        managerService.createManager(new Manager(name, lastName), gym.getId());
        return "redirect:/centers/";
    }*/


    @PostMapping("/gym/")
    public String newGym(Model model, Gym gym, @RequestParam("name") String name, @RequestParam("lastName") String lastName) {
        //New Manager
        Manager manager = new Manager(name, lastName);
        gym.setManager(manager);
        managerRepository.save(manager);
        Gym g = gymRepository.save(gym);

        manager.setGym(g);
        managerRepository.save(manager);



        return "redirect:/centers/";
    }

    @PutMapping("/gym/{gymId}")
    public String upDateGym(Gym gym, @PathVariable Long gymId, @RequestParam("name") String name, @RequestParam("lastName") String lastName) {
        Optional<Gym> op = gymRepository.findById(gymId);
        if (op.isPresent()) {

            //TODO revisar con que valor viene el id del gym para que el metodo save funcione bien
            gym.setId(gymId);
            gymRepository.save(gym);
            //gymService.putGym(gymId, gym);
            return "redirect:/centers/";
        } else {
            // If gym does not exist, return status 404 (Not Found)
            return "redirect:/error";
        }
    }

    //--------------------------------------------------------------------------------------------------------------------


    @PostMapping("/course/")
    public String newClass(Model model, Course course) {
        courseRepository.save(course);
        return "redirect:/";
    }

    @PostMapping("/contact/")
    public String newQuestion(Model model, Question question) {
        questionRepository.save(question);
        return "redirect:/";
    }

    @GetMapping("/contacts/")
    public String showQuestions(Model model) {
        model.addAttribute(questionRepository.findAll());
        return null;
    }

    /*@PostMapping("/coach/{gymId}")
    public String showQuestions(Model model,@PathVariable Long gymId){
        model.addAttribute(questionService.getAllQuestions());
        return null;
    }*/


}