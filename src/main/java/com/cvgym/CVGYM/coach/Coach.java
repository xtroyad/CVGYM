package com.cvgym.CVGYM.coach;

import com.cvgym.CVGYM.course.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coach {
    private Long id;
    private String fullName;
    private Long gymId;
//---------------------------------------------------
    private Map<Long, Course> courses = new HashMap<>();
}
