package com.cvgym.CVGYM.gym;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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




}
