package com.example.agenteD.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "api")
public class Api {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "api_id", nullable = false)
    private Long api_id;

    @Column(name = "status", length = 45)
    private String status;

    @Column(name = "application_id")
    private Integer applicationId;

    @Column(name = "common")
    private Boolean common;

    @Column(name = "description", length = 45)
    private String description;

    @Column(name = "num_test")
    private Integer numTest;

    @Column(name = "consecutive_successfull_test")
    private Integer consecutiveSuccessfullTest;

    @Column(name = "consecutive_failed_test")
    private Integer consecutiveFailedTest;

    @Column(name = "hist_failed_test")
    private Long histFailedTest;

    @Column(name = "hist_successfull_test")
    private Long histSuccessfullTest;

    @Column(name = "min_test_failed")
    private Integer minTestFailed;

    @Column(name = "max_test_failed")
    private Integer maxTestFailed;

    @Column(name = "low_alarm")
    private Integer lowAlarm;

    @Column(name = "high_alarm")
    private Integer highAlarm;

    @Column(name = "last_test_date")
    private LocalDate lastTestDate;

    @Column(name = "\"req/seg\"", length = 45)
    private String reqSeg;
}