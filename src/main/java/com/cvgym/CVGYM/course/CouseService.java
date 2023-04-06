package com.cvgym.CVGYM.course;

import com.cvgym.CVGYM.gym.Gym;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CouseService {
    private Map<Long, Course> courses = new ConcurrentHashMap<>();
    private AtomicLong id = new AtomicLong();

    public Course createCourse(Course course){
        long tem = id.incrementAndGet();
        course.setId(tem);
        courses.put(tem, course);
        return course;
    }

    public Collection<Course> getAll(){
        return courses.values();
    }
}
