package com.cvgym.CVGYM.manager;

import com.cvgym.CVGYM.gym.GymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.channels.FileChannel;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ManagerService {

    /////////////////////////////////
    @Autowired
    private ManagerRepository managerRepository;
    /////////////////////////////////
    @Autowired
    private GymService gymService;
    private Map<Long, Manager> managers = new ConcurrentHashMap<>();
    private AtomicLong id = new AtomicLong();

    /***
     * Method that creates a manager
     * @param manager
     * @param gymId
     * @return the manager created
     */
    public Manager createManager(Manager manager, Long gymId){
        long tem = id.incrementAndGet();
        manager.setId(tem);
        manager.setGymId(gymId);
        gymService.putManager(gymId,tem);
        managers.put(tem,manager);
        return manager;
    }

    /***
     * Method that gives you the collection of managers
     * @return the entire collection of existing managers
     */
    public Collection<Manager> getAll() {
        return managers.values();
    }

    /***
     * Method that searches through all managers and returns the one with the sent id
     * @param id
     * @return managers with this id
     */
    public Optional<Manager> findById(Long id) {

        if (containsKey(id)) {
            Manager m = managers.get(id);
            return Optional.of(m);
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
        return managers.containsKey(id);
    }

    /***
     * Method that updates a manager's information
     * @param id
     * @param manager
     * @return the value to which this map previously associated the key, or null if the map contained no mapping for the key.
     */
    public Manager putManager(Long id, Manager manager) {
        manager.setId(id);
        return managers.put(id, manager);

    }

    /***
     * Method that removes a manager from the manager's map
     * @param id
     * @return true or false depending on whether the deletion was successful or not.
     */
    public boolean deleteManager(Long id) {
        if (containsKey(id)) {
            Optional<Manager> manager = findById(id);
            gymService.updateManagerId(manager.get().getGymId());
            managers.remove(id);
            return true;
        } else {
            return false;
        }
    }

}
