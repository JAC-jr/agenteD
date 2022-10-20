package com.example.agenteD.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Application")
public class Application {

        @Id
        @Getter @Setter
        @Column(name = "in1")
        private Integer in1;

        @Getter @Setter @Column(name = "in2")
        private Integer in2;

        @Getter @Setter @Column(name = "out1")
        private Integer out1;

        @Getter @Setter @Column(name = "out2")
        private Integer out2;

}
