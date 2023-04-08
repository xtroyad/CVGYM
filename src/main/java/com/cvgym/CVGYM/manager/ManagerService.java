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
    @Autowired
    private GymService gymService;
    private Map<Long, Manager> managers = new ConcurrentHashMap<>();
    private AtomicLong id = new AtomicLong();

    public Manager createManager(Manager manager, Long gymId){
        long tem = id.incrementAndGet();
        manager.setId(tem);
        manager.setGymId(gymId);
        gymService.putManager(gymId,tem);
        managers.put(tem,manager);
        return manager;
    }

    public Collection<Manager> getAll() {
        return managers.values();
    }

    public Optional<Manager> findById(Long id) {

        if (containsKey(id)) {
            Manager m = managers.get(id);
            return Optional.of(m);
        } else {
            return Optional.empty();
        }
    }

    public boolean containsKey(Long id) {
        return managers.containsKey(id);
    }

    public Manager putManager(Long id, Manager manager) {
        manager.setId(id);
        return managers.put(id, manager);

    }

    public boolean deleteManager(Long id) {
        if (containsKey(id)) {
            managers.remove(id);
            return true;
        } else {
            return false;
        }
    }

}
