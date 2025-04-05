package com.example.login.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "sys_user")
public class SysUser {
    @Id
    private Long id;

    private String username;

    private String password;

    private String email;
    private String realName;
    private Integer status = 1;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}