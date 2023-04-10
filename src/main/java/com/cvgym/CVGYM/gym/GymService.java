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


    public Gym createGym(Gym gym) {
        long tem = id.incrementAndGet();
        gym.setId(tem);
        gyms.put(tem, gym);
        return gym;
    }

    public Course addCourse(Long gymId,Course course){
        HasACourse pair =hasACourseService.createCourseInGym(gyms.get(gymId),course);
        return pair.getCourse();
    }



    public Gym putGym(Long gymId, Gym gym) {
        return gyms.put(gymId, gym);

    }

    public Gym putManager(Long gymId, Long managerId) {
        Gym g = gyms.get(gymId);
        g.setManagerId(managerId);
        return g;
    }

    public Collection<Gym> getAll() {
        return gyms.values();
    }

    public HashSet<String> getAllCCAA() {
        HashSet<String> ccaa = new HashSet<>();
        for (Gym g : gyms.values()) {
            ccaa.add(g.getCcaa());
        }
        return ccaa;
    }

    public Optional<Gym> findById(Long id) {

        if (containsKey(id)) {
            Gym g = gyms.get(id);
            return Optional.of(g);
        } else {
            return Optional.empty();
        }

    }


    public boolean delete(Long id) {
        if (containsKey(id)) {
            gyms.remove(id);
            return true;
        } else {
            return false;
        }
    }

    public boolean containsKey(Long id) {
        return gyms.containsKey(id);
    }

    public Optional<List<Course>> getCourses(Long gymId) {
        Optional<Gym> op = Optional.of(gyms.get(gymId));
        if(op.isPresent()){
            return hasACourseService.getCourses(gymId);
        }else{
            return Optional.empty();
        }

    }

    public Optional<Course> addCourse(Course course, Long gymId){
        if(gyms.containsKey(gymId)){
            HasACourse aux = hasACourseService.createCourseInGym(gyms.get(gymId),course );
            return Optional.of(aux.getCourse());
        }else{
            return Optional.empty();
        }

    }

    public void updateManagerId(Long gymId){
        Optional<Gym> gym = findById(gymId);
        gym.get().setManagerId(0L);
    }


}