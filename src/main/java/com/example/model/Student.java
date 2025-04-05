package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter @NoArgsConstructor
//@Table(name = "student")
public class Student {
    @Id
    private String Stuno;   //学号，主键

    private String name;    //姓名

    private Gender gender;  //性别 枚举类型

    private LocalDate birthDate;//出生日期

}