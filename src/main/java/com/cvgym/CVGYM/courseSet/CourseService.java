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
    // Todo: IMPORTANT: BEFORE MAKING ANY SEARCH BY ID, containsKey(). IS CALLED TO AVOID EXCEPTIONS.
    public Course createCourse(Course course){
        long tem = id.incrementAndGet();
        course.setId(tem);
        hasACourseService.createCourseInGym(null,course);
        courses.put(tem, course);
        return course;
    }

//    public Course putCourse(Long id, Course course) {
//        return courses.put(id, course);
//
//    }

    public Collection<Course> getAll(){
        return courses.values();
    }

    public Optional<Course> findById(Long id) {

        if (containsKey(id)) {
            Course c = courses.get(id);
            return Optional.of(c);
        } else {
            return Optional.empty();
        }

    }
    public boolean containsKey(Long id) {
        return courses.containsKey(id);
    }

    public Optional<List<Gym>> getGyms(Long courseId) {
        return hasACourseService.getGyms(courseId);
    }

    public Optional<Gym> addGym(Gym gym, Long courseId){
        if(courses.containsKey(courseId)){
            HasACourse aux = hasACourseService.createCourseInGym(gym, courses.get(courseId));
            return Optional.of(aux.getGym());
        }else{
            return Optional.empty();
        }

    }
}
