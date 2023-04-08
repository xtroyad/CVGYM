package com.cvgym.CVGYM.courseSet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Course {
    private Long id;
    private String name;
    private int intensity;
    private String description;
}
