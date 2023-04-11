package com.cvgym.CVGYM.courseSet;

import com.cvgym.CVGYM.HasACourse;
import com.cvgym.CVGYM.HasACourseService;
import com.cvgym.CVGYM.gym.Gym;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CourseService {
    @Autowired
    private HasACourseService hasACourseService;
    private Map<Long, Course> courses = new ConcurrentHashMap<>();
    private AtomicLong id = new AtomicLong();

    /***
     * Method that creates a course
     * @param course
     * @return the course created
     */
    public Course createCourse(Course course){
        long tem = id.incrementAndGet();
        course.setId(tem);
        courses.put(tem, course);
        return course;
    }

    /***
     * Method that gives you the collection of courses
     * @return
     */
    public Collection<Course> getAll(){
        return courses.values();
    }

    /***
     * Method that searches through all courses and returns the one with the sent id
     * @param id
     * @return
     */
    public Optional<Course> findById(Long id) {

        if (containsKey(id)) {
            Course c = courses.get(id);
            return Optional.of(c);
        } else {
            return Optional.empty();
        }

    }

    /***
     * Method that returns true if this map maps one or more keys to the specified value
     * @param id
     * @return a boolean value equal to true if there is a course with that id
     */
    public boolean containsKey(Long id) {
        return courses.containsKey(id);
    }

    /***
     * Method that updates a course's information
     * @param id
     * @param course
     * @return the value to which this map previously associated the key, or null if the map contained no mapping for the key.
     */
    public Course putCourse(Long id, Course course) {
        course.setId(id);
        hasACourseService.updateInfoCourse(id,course);
        return courses.put(id, course);

    }

    /***
     * Method that removes a course from the course's map
     * @param id
     * @return true or false depending on whether the deletion was successful or not.
     */
    public boolean deleteCourse(Long id) {
        if (containsKey(id)) {
            courses.remove(id);
            return true;
        } else {
            return false;
        }
    }

    /***
     * Method that returns all the gyms where that course is taught.
     * @param courseId
     * @return a optional value
     */
    public Optional<List<Gym>> getGyms(Long courseId) {
        return hasACourseService.getGyms(courseId);
    }

    /***
     * Method that adds a gym to the list of gyms where that course is taught.
     * @param gym
     * @param courseId
     * @return a optional value
     */
    public Optional<Gym> addGym(Gym gym, Long courseId){
        if(courses.containsKey(courseId)){
            HasACourse aux = hasACourseService.createCourseInGym(gym, courses.get(courseId));
            return Optional.of(aux.getGym());
        }else{
            return Optional.empty();
        }

    }
}