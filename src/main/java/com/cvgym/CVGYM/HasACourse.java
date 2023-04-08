package com.cvgym.CVGYM;

import com.cvgym.CVGYM.courseSet.Course;
import com.cvgym.CVGYM.courseSet.CourseService;
import com.cvgym.CVGYM.gym.Gym;
import com.cvgym.CVGYM.gym.GymService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
public class HasACourse {
    private Gym gym;
    private Course course;

    public HasACourse (Gym g, Course c){
        gym=g;
        course=c;
    }



}
