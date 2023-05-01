package com.cvgym.CVGYM.rest;

import com.cvgym.CVGYM.coach.Coach;
import com.cvgym.CVGYM.coach.CoachRepository;
import com.cvgym.CVGYM.courseSet.Course;
import com.cvgym.CVGYM.courseSet.CourseRepository;
import com.cvgym.CVGYM.gym.Gym;
import com.cvgym.CVGYM.gym.GymRepository;
import com.cvgym.CVGYM.manager.Manager;
import com.cvgym.CVGYM.manager.ManagerRepository;
import com.cvgym.CVGYM.question.Question;
import com.cvgym.CVGYM.question.QuestionRepository;
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
    private GymRepository gymRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private CoachRepository coachRepository;


    //------------------------------------------------------------------------------------------------------------------

    @GetMapping("/gyms/")
    public Collection<Gym> getAllGyms() {
        return gymRepository.findAll();
    }

    @GetMapping("/gym")
    public ResponseEntity<Gym> getGym(@RequestParam Long gymId) {
        // Find gym by id
        Optional<Gym> op = gymRepository.findById(gymId);
        // If gym exists, return it with status 200 (OK)
        if (op.isPresent()) {
            return new ResponseEntity<>(op.get(), HttpStatus.OK);
        } else {
            // If gym does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/gym")
    @ResponseStatus(HttpStatus.CREATED)
    public Gym createGym(@RequestBody Gym gym, @RequestParam("name") String name, @RequestParam("lastName") String lastName) {
        Manager manager = new Manager(name, lastName);
        gym.setManager(manager);
        managerRepository.save(manager);
        Gym g = gymRepository.save(gym);
        manager.setGym(g);
        managerRepository.save(manager);
        return g;
    }

    @DeleteMapping("/gym")
    public ResponseEntity<Gym> removeElementByID(@RequestParam Long gymId) {
        // Delete gym by id
        Optional<Gym> gym = gymRepository.findById(gymId);
        if (gym.isPresent()) {
            gymRepository.delete(gym.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            // If gym does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/gym")
    public ResponseEntity<Gym> updateElementByID2(@RequestBody Gym gym, @RequestParam Long gymId) {
        Optional<Gym> opgym = gymRepository.findById(gymId);
        if (opgym.isPresent()) {
            Manager manager = opgym.get().getManager();

            gym.setId(gymId);
            gym.setManager(manager);

            gymRepository.save(gym);
            return new ResponseEntity<>(gym, HttpStatus.OK);
        } else {
            // If gym does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    @GetMapping("/gym-courses")
    public ResponseEntity<List<Course>> getCoursesOfOurGym(@RequestParam Long gymId) {

        Optional<Gym> op = gymRepository.findById(gymId);
        // If gym exists, return it with status 200 (OK)
        if (op.isPresent()) {
            return new ResponseEntity<>(op.get().getCourses(), HttpStatus.OK);
        } else {
            // If gym does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/gym-courses") //Todo: va chingón
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Course> addCourse(@RequestParam Long courseId, @RequestParam Long gymId) {

        System.out.println(courseId);
        System.out.println(gymId);


        //Comprobamos que existe el curso
        Optional<Course> opCourse = courseRepository.findById(courseId);
        if (opCourse.isPresent()) {
            //Comprobamos que existe el gimnasio

            Course course = opCourse.get();

            Optional<Gym> op = gymRepository.findById(gymId);

            if (op.isPresent()) {

                Gym gym = op.get();

                gym.getCourses().add(course);

                gymRepository.save(gym);


                return new ResponseEntity<>(opCourse.get(), HttpStatus.OK);
            } else {
                // If gym does not exist, return status 404 (Not Found)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/gym-courses")
    public ResponseEntity removeCourseFromGym(@RequestParam Long gymId, @RequestParam Long courseId) {
        // Find course by id
        Optional<Course> op = courseRepository.findById(courseId);

        if (op.isPresent()) {
            // Delete course by id from a gym
            courseRepository.delete(op.get());

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            // If course does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    @GetMapping("/courses/")
    public Collection<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @GetMapping("/course")
    public ResponseEntity<Course> getCourse(@RequestParam Long courseId) {
        // Find course by id
        Optional<Course> op = courseRepository.findById(courseId);
        // If course exists, return it with status 200 (OK)
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
        courseRepository.save(course);
        return course;
    }

    @DeleteMapping("/course")
    public ResponseEntity removeCourseByID(@RequestParam Long courseId) {
        // Delete course by id
        Optional<Course> op = courseRepository.findById(courseId);
        if (op.isPresent()) {
            Course course = op.get();
            for (Gym gym : course.getGyms()) {
                gym.getCourses().remove(course);
            }
            courseRepository.delete(op.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            // If course does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/course")
    public ResponseEntity<Course> updateCourseByID(@RequestBody Course course, @RequestParam Long courseId) {
        Optional<Course> op = courseRepository.findById(courseId);
        if (op.isPresent()) {
            course.setId(courseId);
            courseRepository.save(course);
            return new ResponseEntity<>(course, HttpStatus.OK);
        } else {
            // If gym does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    @GetMapping("/course-gyms")
    public ResponseEntity<Collection<Gym>> getAllGymsWithOurCourse(@RequestParam Long courseId) {

        Optional<Course> op = courseRepository.findById(courseId);
        // If course exists, return it with status 200 (OK)
        if (op.isPresent()) {
            return new ResponseEntity<>(op.get().getGyms(), HttpStatus.OK);
        } else {
            // If gym does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/course-gyms")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Gym> addGymWithOurCourses(@RequestBody Gym gym, @RequestParam Long courseId) {

        Optional<Course> op = courseRepository.findById(courseId);
        if (op.isPresent()) {
            Course course = op.get();
            course.getGyms().add(gym);
            courseRepository.save(course);
            return new ResponseEntity<>(gym, HttpStatus.OK);
        } else {
            // If gym does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    @GetMapping("/questions/")
    public Collection<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @GetMapping("/question")
    public ResponseEntity<Question> getQuestion(@RequestParam Long questionId) {

        // Find Question by id
        Optional<Question> op = questionRepository.findById(questionId);
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
        questionRepository.save(question);
        return question;
    }

    @DeleteMapping("/question")
    public ResponseEntity removeQuestionByID(@RequestParam Long questionId) {
        Optional<Question> op = questionRepository.findById(questionId);
        // Delete course by id
        if (op.isPresent()) {
            questionRepository.delete(op.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            // If course does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/question")
    public ResponseEntity<Question> updateQuestionByID(@RequestBody Question question, @RequestParam Long questionId) {
        Optional<Question> op = questionRepository.findById(questionId);
        if (op.isPresent()) {
            question.setId(questionId);
            questionRepository.save(question);
            return new ResponseEntity<>(question, HttpStatus.OK);
        } else {
            // If gym does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @GetMapping("/managers/")
    public Collection<Manager> getAllManager() {
        return managerRepository.findAll();
    }
    @GetMapping("/manager")
    public ResponseEntity<Manager> getManager(@RequestParam Long managerId) {

        // Find Manager by id
        Optional<Manager> op = managerRepository.findById(managerId);
        // If Manager exists, return it with status 200 (OK)
        if (op.isPresent()) {
            return new ResponseEntity<>(op.get(), HttpStatus.OK);
        } else {
            // If Manager does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Se crea un manager cuando se crea un nuevo gimnasio
    /*@PostMapping(value = "/manager")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity <Manager> createManager(@RequestParam Long gymId,@RequestBody Manager manager) {
        // Find gym by id
        Optional<Gym> op = gymRepository.findById(gymId);
        // If gym exists add manager, return it with status 200 (OK)
        if (op.isPresent()) {
            Gym gym = op.get();
            manager.setGym(gym);
            managerRepository.save(manager);
            return new ResponseEntity<>(manager, HttpStatus.OK);
        } else {
            // If gym does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

    //Se elimina un manager cuando se elimina el gimnasio con el que está relacionado (borrado en cascada)
    /*@DeleteMapping("/manager")
    public ResponseEntity removeManagerByID(@RequestParam Long managerId){
        Optional<Manager> op = managerRepository.findById(managerId);
        // Delete manager by id
        if (op.isPresent()) {
            managerRepository.deleteById(managerId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            // If manager does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

    @PutMapping("/manager")
    public ResponseEntity<Manager> updateManagerByID(@RequestBody Manager manager, @RequestParam Long managerId){
        Optional<Manager> op = managerRepository.findById(managerId);
        System.out.println(managerId);
        if (op.isPresent()) {
            Gym gym = managerRepository.findById(managerId).get().getGym();
            manager.setId(managerId);
            manager.setGym(gym);
            managerRepository.save(manager);
            return new ResponseEntity<>(manager, HttpStatus.OK);
        } else {
            // If gym does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    @GetMapping("/coaches/")
    public Collection<Coach> getAllCoaches() {
        return coachRepository.findAll();
    }

    @GetMapping("/coach")
    public ResponseEntity<Coach> getCoach(@RequestParam Long coachId) {

        // Find Coach by id
        Optional<Coach> op = coachRepository.findById(coachId);
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
        Optional<Gym> op = gymRepository.findById(gymId);
        // If gym exists add coach, return it with status 200 (OK)
        if (op.isPresent()) {
            Gym gym = op.get();
            coach.setGym(gym);
            coachRepository.save(coach);
            return new ResponseEntity<>(coach, HttpStatus.OK);
        } else {
            // If gym does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/coach")
    public ResponseEntity removeCoachByID(@RequestParam Long coachId){
        Optional<Coach> op = coachRepository.findById(coachId);
        // Delete coach by id
        if (op.isPresent()) {
            coachRepository.deleteById(coachId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            // If course does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/coach")
    public ResponseEntity<Coach> updateCoachByID(@RequestBody Coach coach, @RequestParam Long coachId){
        Optional<Coach> op = coachRepository.findById(coachId);
        if (op.isPresent()) {
            coach.setId(coachId);
            coachRepository.save(coach);
            return new ResponseEntity<>(coach, HttpStatus.OK);
        } else {
            // If gym does not exist, return status 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}