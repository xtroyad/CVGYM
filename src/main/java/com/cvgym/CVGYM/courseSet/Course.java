package com.cvgym.CVGYM.courseSet;

import com.cvgym.CVGYM.gym.Gym;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int intensity;
    private String description;

    @JsonBackReference
    @ManyToMany(mappedBy="courses")
    private List<Gym> gyms;
}
