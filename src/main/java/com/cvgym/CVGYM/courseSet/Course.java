package com.cvgym.CVGYM.courseSet;

import com.cvgym.CVGYM.gym.Gym;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int intensity;
    private String description;

    //    @ManyToMany
//    private List<Gym> gyms;
    @ManyToMany(mappedBy="courses", cascade = CascadeType.ALL)
    private List<Gym> gyms = new ArrayList<>();
}
