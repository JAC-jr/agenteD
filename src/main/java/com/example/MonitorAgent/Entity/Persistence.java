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

    @Column(name = "num_test")
    private Integer numTest;

    @Column(name = "consecutive_failed_test")
    private Integer consecutiveFailedTest;

    @Column(name = "history_failed_test")
    private Long historyFailedTest;

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

    @Column(name = "consecutive_successful_test")
    private Integer consecutiveSuccessfulTest;

    @Column(name = "history_successful_test")
    private Long historySuccessfulTest;

    @Column(name = "url", length = 45)
    private String url;

    @Column(name = "user_name", length = 45)
    private String userName;

    @Column(name = "password", length = 45)
    private String password;

    @Column(name = "sql_sentence", length = 45)
    private String sqlSentence;

    @Column(name = "db_type", length = 45)
    private String dbType;

    @Column(name = "response_time", length = 45)
    private Long response_time;

}