package com.cvgym.CVGYM.coach;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coach {
    private Long id;
    private String name;
    private String lastName;
    private Long gymId;
}
