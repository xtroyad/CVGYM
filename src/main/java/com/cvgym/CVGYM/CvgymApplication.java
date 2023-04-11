package com.cvgym.CVGYM;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;

@SpringBootApplication
public class CvgymApplication {

    public static void main(String[] args) throws FileNotFoundException {

        SpringApplication.run(CvgymApplication.class, args);
	}


}
