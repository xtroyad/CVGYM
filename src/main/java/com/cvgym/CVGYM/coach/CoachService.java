package com.cvgym.CVGYM.coach;

import com.cvgym.CVGYM.gym.Gym;
import com.cvgym.CVGYM.manager.Manager;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CoachService {
    private Map<Long, Coach> coaches = new ConcurrentHashMap<>();
    private AtomicLong id = new AtomicLong();
    public Coach createCoach(Coach coach, Long gymId){
        long tem = id.incrementAndGet();
        coach.setId(tem);
        coach.setGymId(gymId);
        coaches.put(tem,coach);
        return coach;
    }
    public Collection<Coach> getAll() {
        return coaches.values();
    }
    public Optional<Coach> findById(Long id) {
        if (containsKey(id)) {
            Coach c = coaches.get(id);
            return Optional.of(c);
        } else {
            return Optional.empty();
        }
    }

    public boolean containsKey(Long id) {
        return coaches.containsKey(id);
    }

    public Coach putCoach(Long id, Coach coach) {
        coach.setId(id);
        return coaches.put(id, coach);

    }

    public boolean deleteCoach(Long id) {
        if (containsKey(id)) {
            coaches.remove(id);
            return true;
        } else {
            return false;
        }
    }

    public void deleteAllCoaches(Long gymId){
        if(!coaches.values().isEmpty()){
            for (Coach coach: coaches.values()) {
                if(coach.getGymId() == gymId){
                    coaches.remove(coach.getId());
                }
            }
        }

    }

}
