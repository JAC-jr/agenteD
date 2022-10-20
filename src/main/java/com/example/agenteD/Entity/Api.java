package com.example.agenteD.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Api")
public class Api {

    @Id
    @Getter @Setter @Column(name = "in1")
    private Integer in1;

    @Getter @Setter @Column(name = "in2")
    private Integer in2;

    @Getter @Setter @Column(name = "out1")
    private Integer out1;

    @Getter @Setter @Column(name = "out2")
    private Integer out2;
}
