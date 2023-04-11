package com.cvgym.CVGYM.gym;

import com.cvgym.CVGYM.HasACourse;
import com.cvgym.CVGYM.HasACourseService;
import com.cvgym.CVGYM.courseSet.Course;
import com.cvgym.CVGYM.manager.Manager;
import com.cvgym.CVGYM.manager.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class GymService {
    @Autowired
    private HasACourseService hasACourseService;

    private Map<Long, Gym> gyms = new ConcurrentHashMap<>();
    private AtomicLong id = new AtomicLong();

    /***
     * Method that creates a gym and assigns a gym to him
     * @param gym
     * @return the gym created
     */
    public Gym createGym(Gym gym) {
        long tem = id.incrementAndGet();
        gym.setId(tem);
        gyms.put(tem, gym);
        return gym;
    }

    /***
     * Method that gives you the collection of gyms
     * @return the entire collection of existing gyms
     */
    public Collection<Gym> getAll() {
        return gyms.values();
    }

    /***
     * Method that searches through all gyms and returns the one with the sent id
     * @param id
     * @return gym with this id
     */
    public Optional<Gym> findById(Long id) {

        if (containsKey(id)) {
            Gym g = gyms.get(id);
            return Optional.of(g);
        } else {
            return Optional.empty();
        }

    }

    /***
     *  Method that returns true if this map maps one or more keys to the specified value
     * @param id
     * @return a boolean value equal to true if there is a trainer with that id
     */
    public boolean containsKey(Long id) {
        return gyms.containsKey(id);
    }

    /***
     *  Method that updates a gym's information
     * @param gymId
     * @param gym
     * @return the value to which this map previously associated the key, or null if the map contained no mapping for the key.
     */
    public Gym putGym(Long gymId, Gym gym) {
        hasACourseService.updateInfoGym(gymId,gym);
        return gyms.put(gymId, gym);

    }

    /***
     * Method that removes a gym from the gym's map
     * @param id
     * @return true or false depending on whether the deletion was successful or not.
     */
    public boolean delete(Long id) {
        if (containsKey(id)) {
            gyms.remove(id);
            return true;
        } else {
            return false;
        }
    }

    /***
     * Method assigns a manager to a gym
     * @param gymId
     * @param managerId
     * @return the gym with the added manager
     */
    public Gym putManager(Long gymId, Long managerId) {
        Gym g = gyms.get(gymId);
        g.setManagerId(managerId);
        return g;
    }

    /***
     * Method that deletes the manager of a gym
     * @param gymId
     */
    public void updateManagerId(Long gymId){
        Optional<Gym> gym = findById(gymId);
        gym.get().setManagerId(0L);
    }

    /***
     * Method gives all the autonomous communities where there are CVGYM gyms.
     * @return autonomous communities
     */
    public HashSet<String> getAllCCAA() {
        HashSet<String> ccaa = new HashSet<>();
        for (Gym g : gyms.values()) {
            ccaa.add(g.getCcaa());
        }
        return ccaa;
    }

    /***
     * Method that returns all the courses offered in a specific gym
     * @param gymId
     * @return a optional value
     */
    public Optional<List<Course>> getCourses(Long gymId) {
        Optional<Gym> op = Optional.of(gyms.get(gymId));
        if(op.isPresent()){
            return hasACourseService.getCourses(gymId);
        }else{
            return Optional.empty();
        }

    }

    /***
     * Method that adds a course to the course list of a given gym
     * @param course
     * @param gymId
     * @return a optional value
     */
    public Optional<Course> addCourse(Course course, Long gymId){
        if(gyms.containsKey(gymId)){
            HasACourse aux = hasACourseService.createCourseInGym(gyms.get(gymId),course );
            return Optional.of(aux.getCourse());
        }else{
            return Optional.empty();
        }

    }


}