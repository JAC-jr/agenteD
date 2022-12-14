package com.example.MonitorAgent.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Persistence")
@Table(name = "persistence")
public class Persistence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "db_id", nullable = false)
    private Integer db_id;

    @Column(name = "db_name", length = 45)
    private String dbName;

    @Column(name = "status", length = 45)
    private String status;

    @Column(name = "description", length = 45)
    private String description;

    @Column(name = "application_id")
    private Integer applicationId;

    @Column(name = "service_name", length = 45)
    private String serviceName;

    @Column(name = "host", length = 45)
    private String host;

    @Column(name = "port", length = 45)
    private String port;

    @Column(name = "num_test")
    private Integer numTest;

    @Column(name = "consecutive_failed_test")
    private Integer consecutiveFailedTest;

    @Column(name = "hist_failed_test")
    private Long histFailedTest;

    @Column(name = "\"min_test-failed\"")
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

    @Column(name = "test_interv")
    private Long testInterv;

    @Column(name = "consecutive_successful_test")
    private Integer consecutiveSuccessfulTest;

    @Column(name = "hist_successful_test")
    private Long histSuccessfulTest;

}