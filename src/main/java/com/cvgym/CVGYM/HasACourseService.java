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

        if(gym!=null) {

            if(gymCoursesMap.containsKey(gym.getId())){

                List<Course> courseList = gymCoursesMap.get(gym.getId());
                courseList.add(course);
                gymCoursesMap.put(gym.getId(), courseList);
            }else{

                List<Course> auxCourse= new ArrayList<>();
                if(course!=null){
                    auxCourse.add(course);
                }

                gymCoursesMap.put(gym.getId(),auxCourse);
            }

        }

        if(course!=null){
            if(courseGymsMap.containsKey(course.getId())){
                List<Gym> gymList = courseGymsMap.get(course.getId());
                gymList.add(gym);
                courseGymsMap.put(course.getId(), gymList );
            }else{
                List<Gym> auxGym= new  ArrayList<>();
                auxGym.add(gym);
                courseGymsMap.put(course.getId(),auxGym);
            }
        }


        return new HasACourse(gym,course);

    }

    public Optional<List<Course>> getCourses(Long gymId){

        if(containsGymKey(gymId)){
            return Optional.of(gymCoursesMap.get(gymId)) ;
        }else {
            return Optional.empty();
        }
    }

    public Optional<List<Gym>> getGyms(Long courseId){
        if(containsCourseKey(courseId)){
            return Optional.of(courseGymsMap.get(courseId)) ;
        }else {
            return Optional.empty();
        }
    }


    public boolean containsGymKey(Long id) {
        return gymCoursesMap.containsKey(id);
    }
    public boolean containsCourseKey(Long id) {
        return courseGymsMap.containsKey(id);
    }




}
