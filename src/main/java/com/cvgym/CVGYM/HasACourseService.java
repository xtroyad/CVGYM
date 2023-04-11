package com.cvgym.CVGYM;


import com.cvgym.CVGYM.courseSet.Course;
import com.cvgym.CVGYM.courseSet.CourseService;

import com.cvgym.CVGYM.gym.Gym;
import com.cvgym.CVGYM.gym.GymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class HasACourseService {

    private Map<Long, List<Course>> gymCoursesMap = new ConcurrentHashMap<>();
    private Map<Long, List<Gym>> courseGymsMap = new ConcurrentHashMap<>();


    /***
     * Method that creates the relationship between a gym and the course that is taught in it
     * @param gym
     * @param course
     * @return HasACourse object
     */
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

    /***
     * Method that returns all the courses that are taught in a specific gym.
     * @param gymId
     * @return list of courses
     */
    public Optional<List<Course>> getCourses(Long gymId) {

        if (containsGymKey(gymId)) {
            return Optional.of(gymCoursesMap.get(gymId));
        } else {
            return Optional.of((new ArrayList<>()));

        }
    }

    /***
     * Method that returns all the gyms where it is taught in a particular course.
     * @param courseId
     * @return
     */
    public Optional<List<Gym>> getGyms(Long courseId) {
        if (containsCourseKey(courseId)) {
            return Optional.of(courseGymsMap.get(courseId));
        } else {
            return Optional.empty();
        }
    }

    /***
     * Method that returns true if this map maps one gym or more keys to the specified value
     * @param id
     * @return a boolean value equal to true if there is a trainer with that id
     */
    public boolean containsGymKey(Long id) {
        return gymCoursesMap.containsKey(id);
    }

    /***
     * Method that returns true if this map maps one course or more keys to the specified value
     * @param id
     * @return a boolean value equal to true if there is a trainer with that id
     */
    public boolean containsCourseKey(Long id) {
        return courseGymsMap.containsKey(id);
    }

    /***
     * Method that deletes a gym and also deletes its occurrences in the list of gyms of a specific course.
     * @param gymId
     */
    public void deleteGym(Long gymId) {
        if (containsGymKey(gymId)) {
            gymCoursesMap.remove(gymId);

            for(Long courseId: courseGymsMap.keySet()){

                List<Gym> listGyms = courseGymsMap.get(courseId);
                List<Gym> listAux= new ArrayList<>();

                for(Gym g :listGyms){
                    if(!g.getId().equals(gymId)){
                        listAux.add(g);
                    }
                }

                courseGymsMap.put(courseId, listAux);
            }
        }
    }

    /***
     * Method that deletes a course and also deletes its occurrences in the course list of a specific gym
     * @param courseId
     */
    public void deleteCourse(Long courseId) {
        if (containsCourseKey(courseId)) {
            courseGymsMap.remove(courseId);
            for (Long gymId : gymCoursesMap.keySet()) {
                List<Course> listCourses = gymCoursesMap.get(gymId);
                List<Course> listAux= new ArrayList<>();

                for (Course course : listCourses) {
                    if (!course.getId().equals(courseId)) {
                        listAux.add(course);
                    }
                }

                gymCoursesMap.put(gymId, listAux);
            }
        }
    }

    /***
     * Method that deletes a course taught in a specific gym
     * @param gymId
     * @param courseId
     */
    public void deleteCourseFromGym(Long gymId, Long courseId) {
        if (containsCourseKey(courseId)) {

            List<Course> listCourses = gymCoursesMap.get(gymId);
            List<Course> listAux= new ArrayList<>();
            for (Course course : listCourses) {
                if (!course.getId().equals(courseId)) {
                    listAux.add(course);
                }
            }
            gymCoursesMap.put(gymId, listAux);
        }
    }

    /***
     * Method that updates the information of a course and updates it in the different relationships it has with the gyms.
     * @param courseId
     * @param course
     */
    public void updateInfoCourse(Long courseId, Course course) {
        if (containsCourseKey(courseId)) {
            for(Long gymId: gymCoursesMap.keySet()){
                List<Course> listCourses = gymCoursesMap.get(gymId);
                List<Course> listAux= new ArrayList<>();
                for(Course c :listCourses){
                    if(c.getId().equals(courseId)){
                        listAux.add(course);
                    }else{
                        listAux.add(c);
                    }
                }
                gymCoursesMap.put(gymId, listAux);
            }
        }
    }

    /***
     * Method that updates the information of a gym and updates it in the different relationships it has with the courses.
     * @param gymId
     * @param gym
     */
    public void updateInfoGym(Long gymId, Gym gym) {
        if (containsGymKey(gymId)) {
            for(Long courseId: courseGymsMap.keySet()){
                List<Gym> listGyms = courseGymsMap.get(courseId);
                List<Gym> listAux= new ArrayList<>();
                for(Gym g :listGyms){
                    if(g.getId().equals(gymId)){
                        listAux.add(gym);
                    }else{
                        listAux.add(g);
                    }
                }
                courseGymsMap.put(courseId, listAux);
            }
        }
    }


}