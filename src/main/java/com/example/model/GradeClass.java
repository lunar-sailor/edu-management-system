package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class GradeClass {

    @Id
    private String ClassNo;

    private String ClassName;

    @OneToOne
    private Teacher headTeacher;
    @OneToMany
    private List<Student> students;
}
