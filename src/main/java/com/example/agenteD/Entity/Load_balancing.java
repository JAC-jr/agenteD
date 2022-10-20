package com.example.agenteD.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Load_balancing")
public class Load_balancing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vserver_id", nullable = false)
    @Getter
    @Setter
    private Long id;

}
