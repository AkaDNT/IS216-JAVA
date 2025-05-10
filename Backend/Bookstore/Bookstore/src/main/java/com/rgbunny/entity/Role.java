package com.rgbunny.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Role")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, name = "role_name")
    private AppRole roleName;
}
