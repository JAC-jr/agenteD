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
@Entity(name = "Integration")
@Table(name = "integration")
public class Integration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "integration_id", nullable = false)
    private Integer integration_id;

    @Column(name = "status", length = 45)
    private String status;

    @Column(name = "integration_type", length = 45)
    private String integrationType;

    @Column(name = "channel", length = 45)
    private String channel;

    @Column(name = "description", length = 45)
    private String description;

    @Column(name = "num_test")
    private Integer numTest;

    @Column(name = "consecutive_failed_test")
    private Integer consecutiveFailedTest;

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

    @Column(name = "\"req/seg\"", length = 45)
    private String reqSeg;

    @Column(name = "test_interv")
    private Long testInterv;

    @Column(name = "application_id")
    private Integer applicationId;

    @Column(name = "consecutive_successful_test")
    private Integer consecutiveSuccessfulTest;

    @Column(name = "url")
    private String url;

    @Column(name = "json")
    private String json;

    @Column(name = "history_failed_test")
    private Long historyFailedTest;

    @Column(name = "history_successful_test")
    private Long historySuccessfulTest;

    @Column(name = "response_time", length = 45)
    private Long response_time;
}