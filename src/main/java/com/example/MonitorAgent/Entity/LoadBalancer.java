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
@Entity(name = "LoadBalancer")
@Table(name = "load_balancer")
public class LoadBalancer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vserver_id", nullable = false)
    private Integer vserver_id;

    @Column(name = "description", length = 45)
    private String description;

    @Column(name = "status", length = 45)
    private String status;

    @Column(name = "num_test")
    private Integer numTest;

    @Column(name = "last_test_date")
    private LocalDateTime lastTestDate;

    @Column(name = "successful_consecutive_test")
    private Integer successfulConsecutiveTest;

    @Column(name = "failed_consecutive_test")
    private Integer failedConsecutiveTest;

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

    @Column(name = "application_id")
    private Integer applicationId;

    @Column(name = "history_successful_test")
    private Long historySuccessfulTest;

    @Column(name = "response_time", length = 45)
    private Long response_time;

}