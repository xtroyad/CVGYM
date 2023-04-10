package com.cvgym.CVGYM;


import com.cvgym.CVGYM.courseSet.Course;
import com.cvgym.CVGYM.courseSet.CourseService;

import com.cvgym.CVGYM.gym.Gym;
import com.cvgym.CVGYM.gym.GymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class HasACourseService {

    private Map<Long, List<Course>> gymCoursesMap = new ConcurrentHashMap<>();
    private Map<Long, List<Gym>> courseGymsMap = new ConcurrentHashMap<>();


    public HasACourse createCourseInGym(Gym gym, Course course) {

        List<Course> courseList;
        if (gymCoursesMap.containsKey(gym.getId())) {
            courseList = gymCoursesMap.get(gym.getId());
        } else {
            courseList = new ArrayList<>();
        }

        courseList.add(course);
        gymCoursesMap.put(gym.getId(), courseList);

        List<Gym> gymList;
        if (courseGymsMap.containsKey(course.getId())) {
            gymList = courseGymsMap.get(course.getId());
        } else {
            gymList = new ArrayList<>();
        }
        gymList.add(gym);
        courseGymsMap.put(course.getId(), gymList);


        return new HasACourse(gym, course);

    }

    public Optional<List<Course>> getCourses(Long gymId) {

        if (containsGymKey(gymId)) {
            return Optional.of(gymCoursesMap.get(gymId));
        } else {
            return Optional.of((new ArrayList<>()));

        }
    }

    public Optional<List<Gym>> getGyms(Long courseId) {
        if (containsCourseKey(courseId)) {
            return Optional.of(courseGymsMap.get(courseId));
        } else {
            return Optional.empty();
        }
    }


    public boolean containsGymKey(Long id) {
        return gymCoursesMap.containsKey(id);
    }

    public boolean containsCourseKey(Long id) {
        return courseGymsMap.containsKey(id);
    }

    public void deleteGym(Long gymId) {
        if (containsGymKey(gymId)) {
            gymCoursesMap.remove(gymId);

            for(Long kCourse: courseGymsMap.keySet()){

                List<Gym> listGyms = courseGymsMap.get(kCourse);
                List<Gym> listAux= new ArrayList<>();

                for(Gym g :listGyms){
                    if(!g.getId().equals(gymId)){
                        listAux.add(g);
                    }
                }

                courseGymsMap.put(kCourse, listAux);
            }
        }
    }

    public void deleteCourse(Long courseId) {
        if (containsCourseKey(courseId)) {
            courseGymsMap.remove(courseId);
            for (List<Course> listCourses : gymCoursesMap.values()) {
                for (Course course : listCourses) {
                    if (course.getId() == courseId) {
                        listCourses.remove(course.getId());
                    }
                }
            }
        }
    }


}