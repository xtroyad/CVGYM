package com.cvgym.CVGYM.coach;

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

    /***
     * Method that creates a coach and assigns a gym to him
     * @param coach
     * @param gymId
     * @return the coach created
     */
    public Coach createCoach(Coach coach, Long gymId){
        long tem = id.incrementAndGet();
        coach.setId(tem);
        coach.setGymId(gymId);
        coaches.put(tem,coach);
        return coach;
    }

    /***
     * Method that gives you the collection of coaches
     * @return the entire collection of existing coaches
     */
    public Collection<Coach> getAll() {
        return coaches.values();
    }

    /***
     * Method that searches through all coaches and returns the one with the sent id
     * @param id
     * @return coach with this id
     */
    public Optional<Coach> findById(Long id) {
        if (containsKey(id)) {
            Coach c = coaches.get(id);
            return Optional.of(c);
        } else {
            return Optional.empty();
        }
    }

    /***
     * Method that returns true if this map maps one or more keys to the specified value
     * @param id
     * @return a boolean value equal to true if there is a trainer with that id
     */
    public boolean containsKey(Long id) {
        return coaches.containsKey(id);
    }

    /***
     * Method that updates a coach's information
     * @param id
     * @param coach
     * @return the value to which this map previously associated the key, or null if the map contained no mapping for the key.
     */
    public Coach putCoach(Long id, Coach coach) {
        coach.setId(id);
        return coaches.put(id, coach);

    }

    /***
     * Method that removes a coach from the coach's map
     * @param id
     * @return true or false depending on whether the deletion was successful or not.
     */
    public boolean deleteCoach(Long id) {
        if (containsKey(id)) {
            coaches.remove(id);
            return true;
        } else {
            return false;
        }
    }

    /***
     * Method that deletes all coaches
     * @param gymId
     */
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
