package com.cvgym.CVGYM.rest;

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


    //------------------------------------------------------------------------------------------------------------------

    @GetMapping("/gyms/")
    public Collection<Gym> getAllGyms() {
        return gymService.getAll();
    }

    @GetMapping("/gyms")

    public ResponseEntity<Gym> getGym(@RequestParam Long id) {
        // Find gym by id
        Optional<Gym> op = gymService.findById(id);
        // If gym exists, return it with status 200 (OK)
        if (op.isPresent()) {
            return new ResponseEntity<>(op.get(), HttpStatus.OK);
        } else {
            // If gym does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/gyms-courses")

    public ResponseEntity<List<Course>> getCoursesOfOurGym(@RequestParam Long id) {

        Optional<List<Course>> op = gymService.getCourses(id);
        // If gym exists, return it with status 200 (OK)
        if (op.isPresent()) {
            return new ResponseEntity<>(op.get(), HttpStatus.OK);
        } else {
            // If gym does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/gyms-courses")
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

    @DeleteMapping("/gyms")
    public ResponseEntity<Gym> removeElementByID(@RequestParam Long id){
        // Delete gym by id
        if (gymService.containsKey(id)) {
            Optional<Gym> gym = gymService.findById(id);
            managerService.deleteManager(gym.get().getManagerId());
            coachService.deleteAllCoaches(id);
            gymService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            // If gym does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/gyms")
    public ResponseEntity<Gym> updateElementByID(@RequestBody Gym gym, @RequestParam Long id){
        if (gymService.containsKey(id)) {
            gymService.putGym(id,gym);
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

    @GetMapping("/courses")
    public ResponseEntity<Course> getCourse(@RequestParam Long id)  {
        // Find gym by id
        Optional<Course> op = courseService.findById(id);
        // If course exists, return it with status 200 (OK)
        if (op.isPresent()) {
            return new ResponseEntity<>(op.get(), HttpStatus.OK);
        } else {
            // If gym does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/courses-gym")
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

    @PostMapping("/courses-gym")
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

    /*Todo:DELETE y PUT -> borrar de tabla intermedia*/

    @DeleteMapping("/courses")
    public ResponseEntity removeCourseByID(@RequestParam Long id){
        // Delete course by id
        if (courseService.deleteCourse(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            // If course does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/courses")
    public ResponseEntity<Course> updateCourseByID(@RequestBody Course course, @RequestParam Long id){
        if (courseService.containsKey(id)) {
            courseService.putCourse(id,course);
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

    @GetMapping("/questions")
    public ResponseEntity<Question> getQuestion(@RequestParam Long id) {

        // Find Question by id
        Optional<Question> op = questionService.findById(id);
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

    @DeleteMapping("/questions")
    public ResponseEntity removeQuestionByID(@RequestParam Long id){
        // Delete course by id
        if (questionService.deleteQuestion(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            // If course does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/questions")
    public ResponseEntity<Question> updateQuestionByID(@RequestBody Question question, @RequestParam Long id){
        if (questionService.containsKey(id)) {
            questionService.putQuestion(id,question);
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
    @GetMapping("/managers")

    public ResponseEntity<Manager> getManager(@RequestParam Long id) {

        // Find Manager by id
        Optional<Manager> op = managerService.findById(id);
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

    @DeleteMapping("/managers")
    public ResponseEntity removeManagerByID(@RequestParam Long id){
        // Delete manager by id
        if (managerService.deleteManager(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            // If manager does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/managers")
    public ResponseEntity<Manager> updateManagerByID(@RequestBody Manager manager, @RequestParam Long id){
        if (managerService.containsKey(id)) {
            managerService.putManager(id,manager);
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

    @GetMapping("/coaches")

    public ResponseEntity<Coach> getCoach(@RequestParam Long id) {

        // Find Coach by id
        Optional<Coach> op = coachService.findById(id);
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

    @DeleteMapping("/coaches")
    public ResponseEntity removeCoachByID(@RequestParam Long id){
        // Delete coach by id
        if (coachService.deleteCoach(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            // If course does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/coaches")
    public ResponseEntity<Coach> updateCoachByID(@RequestBody Coach coach, @RequestParam Long id){
        if (coachService.containsKey(id)) {
            coachService.putCoach(id,coach);
            return new ResponseEntity<>(coach, HttpStatus.OK);
        } else {
            // If gym does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }







}
