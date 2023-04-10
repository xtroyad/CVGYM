package com.cvgym.CVGYM.manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manager {
    private Long id;
    private String name;
    private String lastName;
    private Long gymId;

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