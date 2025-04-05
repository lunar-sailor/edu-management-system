package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor @Getter @Setter
public class Teacher {

    @Id
    private String tchNo;   //教师号

    private String name;    //

    private Gender gender;



}
