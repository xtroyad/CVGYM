package com.cvgym.CVGYM.manager;

import com.cvgym.CVGYM.gym.Gym;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String lastName;

    @OneToOne(cascade=CascadeType.ALL)
    private Gym gym;

    public Manager(String n, String ln, Long i){
        name= n;
        id=i;
        lastName = ln;
    }

    public Manager(String n, String ln){
        name= n;

        lastName = ln;
    }

}