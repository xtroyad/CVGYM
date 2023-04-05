package com.cvgym.CVGYM.manager;

import com.cvgym.CVGYM.gym.GymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MangerService {
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


}
