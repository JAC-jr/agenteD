package com.example.agenteD.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

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
    private Integer id;

    @Column(name = "description", length = 45)
    private String description;

    @Column(name = "status", length = 45)
    private String status;

    @Column(name = "ip_server", length = 45)
    private String ipServer;

    @Column(name = "aplication_id")
    private Integer aplicationId;

    @Column(name = "port", length = 45)
    private String port;

    @Column(name = "num_test")
    private Integer numTest;

    @Column(name = "last_test_date")
    private LocalDate lastTestDate;

    @Column(name = "successful_consecutive_test")
    private Integer successfulConsecutiveTest;

    @Column(name = "failed_consecutive_test")
    private Integer failedConsecutiveTest;

    @Column(name = "history_successfull_test")
    private Long historySuccessfullTest;

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

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "description = " + description + ", " +
                "status = " + status + ", " +
                "ipServer = " + ipServer + ", " +
                "aplicationId = " + aplicationId + ", " +
                "port = " + port + ", " +
                "numTest = " + numTest + ", " +
                "lastTestDate = " + lastTestDate + ", " +
                "successfulConsecutiveTest = " + successfulConsecutiveTest + ", " +
                "failedConsecutiveTest = " + failedConsecutiveTest + ", " +
                "historySuccessfullTest = " + historySuccessfullTest + ", " +
                "historyFailedTest = " + historyFailedTest + ", " +
                "minFailTest = " + minFailTest + ", " +
                "maxFailTest = " + maxFailTest + ", " +
                "lowAlarm = " + lowAlarm + ", " +
                "highAlarm = " + highAlarm + ", " +
                "reqSeg = " + reqSeg + ", " +
                "testInterv = " + testInterv + ")";
    }
}