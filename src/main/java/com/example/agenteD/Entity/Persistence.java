package com.example.agenteD.Entity;

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
    private Integer persistence_id;

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

    @Column(name = "consecutive_successfull_test")
    private Integer consecutiveSuccessfullTest;

    @Column(name = "consecutive_failed_test")
    private Integer consecutiveFailedTest;

    @Column(name = "hist_failed_test")
    private Long histFailedTest;

    @Column(name = "hist_successfull_test")
    private Long histSuccessfullTest;

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

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + persistence_id + ", " +
                "dbName = " + dbName + ", " +
                "status = " + status + ", " +
                "description = " + description + ", " +
                "applicationId = " + applicationId + ", " +
                "serviceName = " + serviceName + ", " +
                "host = " + host + ", " +
                "port = " + port + ", " +
                "numTest = " + numTest + ", " +
                "consecutiveSuccessfullTest = " + consecutiveSuccessfullTest + ", " +
                "consecutiveFailedTest = " + consecutiveFailedTest + ", " +
                "histFailedTest = " + histFailedTest + ", " +
                "histSuccessfullTest = " + histSuccessfullTest + ", " +
                "minTestFailed = " + minTestFailed + ", " +
                "maxTestFailed = " + maxTestFailed + ", " +
                "lowAlarm = " + lowAlarm + ", " +
                "highAlarm = " + highAlarm + ", " +
                "lastTestDate = " + lastTestDate + ", " +
                "reqSeg = " + reqSeg + ", " +
                "testInterv = " + testInterv + ")";
    }
}