package com.example.agenteD.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "application_id", nullable = false)
    @Getter @Setter
    private Integer application_id;

    @Column(name = "application_name")
    @Getter @Setter
    private String application_name;

}
