package com.example.MonitorAgent.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Api")
@Table(name = "api")
public class Api {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "api_id", nullable = false)
    private Integer api_id;

    @Column(name = "status", length = 45)
    private String status;

    @Column(name = "health", length = 45)
    private String health;

    @Column(name = "application_id")
    private Integer applicationId;

    @Column(name = "common")
    private Boolean common;

    @Column(name = "base_url", length = 45)
    private String base_url;

    @Column(name = "label_app", length = 45)
    private String label_app;

    @Column(name = "name_space", length = 45)
    private String nameSpace;

    @Column(name = "num_test")
    private Integer numTest;

    @Column(name = "consecutive_failed_test")
    private Integer consecutiveFailedTest;

    @Column(name = "hist_failed_test")
    private Long histFailedTest;

    @Column(name = "min_test_failed")
    private Integer minTestFailed;

    @Column(name = "max_test_failed")
    private Integer maxTestFailed;

    @Column(name = "low_alarm")
    private Integer lowAlarm;

    @Column(name = "high_alarm")
    private Integer highAlarm;

    @Column(name = "last_test_date")
    private LocalDateTime lastTestDate;

    @Column(name = "response_time", length = 45)
    private Long response_time;

    @Column(name = "test_interv")
    private Long testInterv;

    @Column(name = "consecutive_successful_test")
    private Integer consecutiveSuccessfulTest;

    @Column(name = "hist_successful_test")
    private Long histSuccessfulTest;

}