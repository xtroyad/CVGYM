package com.cvgym.CVGYM.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private Long id;
    private String mail;
    private String subject;
    private String description;
    private String typeQuestion;

}
