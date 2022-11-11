package com.example.agenteD.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Application")
@Table(name = "application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id", nullable = false)
    private Integer id;

    @Column(name = "application_name", length = 45)
    private String applicationName;

    @Column(name = "description", length = 45)
    private String description;

    @Column(name = "status", length = 45)
    private String status;

    @Column(name = "num_test")
    private Long numTest;

    @Column(name = "last_test_date")
    private LocalDate lastTestDate;

    @Column(name = "successful_consecutive_test")
    private Integer successfulConsecutiveTest;

    @Column(name = "failed_consecutive_test")
    private Integer failedConsecutiveTest;

    @Column(name = "history_successful_test")
    private Long historySuccessfulTest;

    @Column(name = "history_failed_test")
    private Long historyFailedTest;

    @Column(name = "min_fail_test")
    private Integer minFailTest;

    @Column(name = "max_fail_test")
    private Integer maxFailTest;

    @Column(name = "low_alarm")
    private Integer lowAlarm;

    @Column(name = "high_alarm")
    private Integer highAlarm;

    @Column(name = "\"req/seg\"", length = 45)
    private String reqSeg;

    @Column(name = "test_interv")
    private Long testInterv;

}