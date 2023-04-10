package com.cvgym.CVGYM.rest;

import com.cvgym.CVGYM.HasACourseService;
import com.cvgym.CVGYM.coach.Coach;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@RequestMapping("/api/")
@RestController
public class GymRESTController {
    @Autowired
    private GymService gymService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private CoachService coachService;
    @Autowired
    private HasACourseService hasACourseService;


    //------------------------------------------------------------------------------------------------------------------

    @GetMapping("/gyms/")
    public Collection<Gym> getAllGyms() {
        return gymService.getAll();
    }

    @GetMapping("/gym")

    public ResponseEntity<Gym> getGym(@RequestParam Long gymId) {
        // Find gym by id
        Optional<Gym> op = gymService.findById(gymId);
        // If gym exists, return it with status 200 (OK)
        if (op.isPresent()) {
            return new ResponseEntity<>(op.get(), HttpStatus.OK);
        } else {
            // If gym does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/gym-courses")

    public ResponseEntity<List<Course>> getCoursesOfOurGym(@RequestParam Long gymId) {

        Optional<List<Course>> op = gymService.getCourses(gymId);
        // If gym exists, return it with status 200 (OK)
        if (op.isPresent()) {
            return new ResponseEntity<>(op.get(), HttpStatus.OK);
        } else {
            // If gym does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/gym-courses")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Course> addCourse(@RequestParam Long courseId, @RequestParam Long gymId) {
        //Comprobamos que existe el curso
        Optional<Course> opCourse= courseService.findById(courseId);
        if(opCourse.isPresent()){
            //Comprobamos que existe el gimnasio
            Optional<Course> op = gymService.addCourse(opCourse.get(), gymId);
            if (op.isPresent()) {
                return new ResponseEntity<>(op.get(), HttpStatus.OK);
            } else {
                // If gym does not exist, return status 404 (Not Found)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    @PostMapping( "/gym/")
    @ResponseStatus(HttpStatus.CREATED)
    public Gym createGym(@RequestBody Gym gym) {
        gymService.createGym(gym);
        return gym;
    }

    @DeleteMapping("/gym")
    public ResponseEntity<Gym> removeElementByID(@RequestParam Long gymId){
        // Delete gym by id
        if (gymService.containsKey(gymId)) {
            Optional<Gym> gym = gymService.findById(gymId);
            if(gym.get().getManagerId() != null && gym.get().getManagerId() !=0){
                managerService.deleteManager(gym.get().getManagerId());
            }
            System.out.println(gym.get().getId());
            System.out.println(gym.get().getNumber());

            coachService.deleteAllCoaches(gymId);

            hasACourseService.deleteGym(gymId);
            gymService.delete(gymId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            // If gym does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/gym")
    public ResponseEntity<Gym> updateElementByID(@RequestBody Gym gym, @RequestParam Long gymId){
        if (gymService.containsKey(gymId)) {
            Long managerId = gymService.findById(gymId).get().getManagerId();
            gym.setId(gymId);
            gym.setManagerId(managerId);
            gymService.putGym(gymId,gym);
            return new ResponseEntity<>(gym, HttpStatus.OK);
        } else {
            // If gym does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //------------------------------------------------------------------------------------------------------------------

    @GetMapping("/courses/")
    public Collection<Course> getAllCourses() {
        return courseService.getAll();
    }

    @GetMapping("/course")
    public ResponseEntity<Course> getCourse(@RequestParam Long courseId)  {
        // Find gym by id
        Optional<Course> op = courseService.findById(courseId);
        // If course exists, return it with status 200 (OK)
        if (op.isPresent()) {
            return new ResponseEntity<>(op.get(), HttpStatus.OK);
        } else {
            // If gym does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/course-gyms")
    public ResponseEntity<Collection<Gym>> getAllGymsWithOurCourse(@RequestParam Long courseId){

        Optional<List<Gym>> op = courseService.getGyms(courseId);
        // If course exists, return it with status 200 (OK)
        if (op.isPresent()) {
            return new ResponseEntity<>(op.get(), HttpStatus.OK);
        } else {
            // If gym does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/course-gyms")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Gym> addGymWithOurCourses(@RequestBody Gym gym, @RequestParam Long courseId){

        Optional<Gym> op = courseService.addGym(gym, courseId);
        if (op.isPresent()) {
            return new ResponseEntity<>(op.get(), HttpStatus.OK);
        } else {
            // If gym does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @PostMapping(value = "/course/")
    @ResponseStatus(HttpStatus.CREATED)
    public Course createCourse(@RequestBody Course course) {
        courseService.createCourse(course);
        return course;
    }

    @DeleteMapping("/course")
    public ResponseEntity removeCourseByID(@RequestParam Long courseId){
        // Delete course by id
        if (courseService.deleteCourse(courseId)) {
            hasACourseService.deleteCourse(courseId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            // If course does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/course")
    public ResponseEntity<Course> updateCourseByID(@RequestBody Course course, @RequestParam Long courseId){
        if (courseService.containsKey(courseId)) {
            course.setId(courseId);
            courseService.putCourse(courseId,course);
            return new ResponseEntity<>(course, HttpStatus.OK);
        } else {
            // If gym does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    @GetMapping("/questions/")
    public Collection<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/question")
    public ResponseEntity<Question> getQuestion(@RequestParam Long questionId) {

        // Find Question by id
        Optional<Question> op = questionService.findById(questionId);
        // If Question exists, return it with status 200 (OK)
        if (op.isPresent()) {
            return new ResponseEntity<>(op.get(), HttpStatus.OK);
        } else {
            // If Question does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/question/")
    public Question createCourse(@RequestBody Question question) {
        questionService.createQuestion(question);
        return question;
    }

    @DeleteMapping("/question")
    public ResponseEntity removeQuestionByID(@RequestParam Long questionId){
        // Delete course by id
        if (questionService.deleteQuestion(questionId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            // If course does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/question")
    public ResponseEntity<Question> updateQuestionByID(@RequestBody Question question, @RequestParam Long questionId){
        if (questionService.containsKey(questionId)) {
            question.setId(questionId);
            questionService.putQuestion(questionId,question);
            return new ResponseEntity<>(question, HttpStatus.OK);
        } else {
            // If gym does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    @GetMapping("/managers/")
    public Collection<Manager> getAllManager() {
        return managerService.getAll();
    }
    @GetMapping("/manager")
    public ResponseEntity<Manager> getManager(@RequestParam Long managerId) {

        // Find Manager by id
        Optional<Manager> op = managerService.findById(managerId);
        // If Manager exists, return it with status 200 (OK)
        if (op.isPresent()) {
            return new ResponseEntity<>(op.get(), HttpStatus.OK);
        } else {
            // If Manager does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/manager")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity <Manager> createManager(@RequestParam Long gymId,@RequestBody Manager manager) {
        // Find gym by id
        Optional<Gym> op = gymService.findById(gymId);
        // If gym exists add manager, return it with status 200 (OK)
        if (op.isPresent()) {
            managerService.createManager(manager,gymId);
            return new ResponseEntity<>(manager, HttpStatus.OK);
        } else {
            // If gym does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/manager")
    public ResponseEntity removeManagerByID(@RequestParam Long managerId){
        // Delete manager by id
        if (managerService.deleteManager(managerId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            // If manager does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/manager")
    public ResponseEntity<Manager> updateManagerByID(@RequestBody Manager manager, @RequestParam Long managerId){

        System.out.println(managerId);
        if (managerService.containsKey(managerId)) {
            Long gymId = managerService.findById(managerId).get().getGymId();
            manager.setId(managerId);
            manager.setGymId(gymId);
            managerService.putManager(managerId,manager);
            return new ResponseEntity<>(manager, HttpStatus.OK);
        } else {
            // If gym does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    @GetMapping("/coaches/")
    public Collection<Coach> getAllCoaches() {
        return coachService.getAll();
    }

    @GetMapping("/coach")
    public ResponseEntity<Coach> getCoach(@RequestParam Long coachId) {

        // Find Coach by id
        Optional<Coach> op = coachService.findById(coachId);
        // If Coach exists, return it with status 200 (OK)
        if (op.isPresent()) {
            return new ResponseEntity<>(op.get(), HttpStatus.OK);
        } else {
            // If Coach does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping(value = "/coach")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity <Coach> createCoach(@RequestParam Long gymId,@RequestBody Coach coach) {
        // Find gym by id
        Optional<Gym> op = gymService.findById(gymId);
        // If gym exists add coach, return it with status 200 (OK)
        if (op.isPresent()) {
            coachService.createCoach(coach,gymId);
            return new ResponseEntity<>(coach, HttpStatus.OK);
        } else {
            // If gym does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/coach")
    public ResponseEntity removeCoachByID(@RequestParam Long coachId){
        // Delete coach by id
        if (coachService.deleteCoach(coachId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            // If course does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/coach")
    public ResponseEntity<Coach> updateCoachByID(@RequestBody Coach coach, @RequestParam Long coachId){
        if (coachService.containsKey(coachId)) {
            coach.setId(coachId);
            coachService.putCoach(coachId,coach);
            return new ResponseEntity<>(coach, HttpStatus.OK);
        } else {
            // If gym does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }







}