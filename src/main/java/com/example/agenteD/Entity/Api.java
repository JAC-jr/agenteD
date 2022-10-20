package com.example.agenteD.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Api")
public class Api {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "api_id", nullable = false)
    @Getter @Setter
    private Integer api_id;

    @Column(name = "description")
    @Getter @Setter
    private String description;
}
