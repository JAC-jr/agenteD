package com.example.MonitorAgent.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Service")
@Table(name = "service")
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id", nullable = false)
    private Integer serviceId;

    @Column(name = "description", length = 45)
    private String description;

    @Column(name = "health", length = 45)
    private String health;

    @Column(name = "application_id")
    private Integer applicationId;

    @Column(name = "service_name", length = 45)
    private String serviceName;

    @Column(name = "label_app", length = 45)
    private String labelApp;

    @Column(name = "test_url", length = 200)
    private String testUrl;

    @Column(name = "num_test")
    private Integer numTest;

    @Column(name = "consecutive_failed_test")
    private Integer consecutiveFailedTest;

    @Column(name = "hist_failed_test")
    private Long histFailedTest;

    @Column(name = "max_failed_test")
    private Integer maxFailedTest;

    @Column(name = "min_failed_test")
    private Integer minFailedTest;

    @Column(name = "low_alarm")
    private Integer lowAlarm;

    @Column(name = "high_alarm")
    private Integer highAlarm;

    @Column(name = "\"req/seg\"", length = 45)
    private String reqSeg;

    @Column(name = "test_interv")
    private Long testInterv;

    @Column(name = "hist_successful_test")
    private Long histSuccessfulTest;

    @Column(name = "last_tests_date")
    private LocalDateTime lastTestsDate;

    @Column(name = "name_space", length = 45)
    private String nameSpace;

    @Column(name = "status", length = 45)
    private String status;

    @Column(name = "consecutive_successful_test")
    private Integer consecutiveSuccessfulTest;

    @Column(name = "response_time", length = 45)
    private Long response_time;

}