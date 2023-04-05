package com.cvgym.CVGYM.gym;

import com.cvgym.CVGYM.manager.MangerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class GymService {

    private Map<Long, Gym> gyms = new ConcurrentHashMap<>();
    private AtomicLong id = new AtomicLong();


    public Gym createGym(Gym gym) {
        long tem = id.incrementAndGet();
        gym.setId(tem);
        gyms.put(tem, gym);
        return gym;
    }




    public Gym putManager(Long gymId, Long managerId) {
        Gym g = gyms.get(gymId);
        g.setManagerId(managerId);
        return g;
    }

    public Collection<Gym> getAll(){
        return gyms.values();
    }
    public HashSet<String> getAllCCAA(){
        HashSet<String> ccaa = new HashSet<>();
        for(Gym g : gyms.values()){
            ccaa.add(g.getCcaa());
        }
        return ccaa;
    }

}
