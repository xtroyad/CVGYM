package com.cvgym.CVGYM.gym;

import com.cvgym.CVGYM.course.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Gym {
    private Long id;
    private String city;
    private String ccaa;
    private String address;
    private String number;
    private String zip;
    private String phoneNumber;
    //-----------------------------------//
    private Long managerId;

    private Map<Long, Course> courses = new ConcurrentHashMap<>();


}
