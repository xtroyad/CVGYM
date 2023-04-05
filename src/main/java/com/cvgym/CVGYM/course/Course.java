package com.cvgym.CVGYM.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.util.pattern.PathPattern;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Course {
    private Long id;
    private String name;
    private int intensity;
    private String description;
}
