package com.example.MonitorAgent.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

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
    private Integer service_id;

    @Column(name = "description", length = 45)
    private String description;

    @Column(name = "application_id")
    private Integer applicationId;

    @Column(name = "service_name", length = 45)
    private String serviceName;

    @Column(name = "operation", length = 45)
    private String operation;

    @Column(name = "port", length = 45)
    private String port;

    @Column(name = "method", length = 45)
    private String method;

    @Column(name = "test_url", length = 45)
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
    private LocalDate lastTestsDate;

    @Column(name = "status", length = 45)
    private String status;

}