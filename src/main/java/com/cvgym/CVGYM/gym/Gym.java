package com.cvgym.CVGYM.gym;

import com.cvgym.CVGYM.coach.Coach;
import com.cvgym.CVGYM.courseSet.Course;
import com.cvgym.CVGYM.manager.Manager;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.EnableMBeanExport;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity

public class Gym {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String city;
    private String ccaa;
    private String address;
    private String number;
    private String zip;
    private String phoneNumber;
    //-----------------------------------//
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Manager manager;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gym")
    private List<Coach> coaches = new ArrayList<>();

    //    @ManyToMany(mappedBy="gyms")
//    private List<Course> courses = new ArrayList<>();

    @ManyToMany
    private List<Course> courses = new ArrayList<>();


}
