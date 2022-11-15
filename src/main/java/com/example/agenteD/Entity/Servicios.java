package com.example.agenteD.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Services")
@Table(name = "services")
public class Servicios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id", nullable = false)
    private Integer service_id;

    @Column(name = "description", length = 45)
    private String description;

    @Column(name = "estatus", length = 45)
    private String estatus;

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

    @Column(name = "\"consecutive_succefull-test\"")
    private Integer consecutiveSuccefullTest;

    @Column(name = "consecutive_failed_test")
    private Integer consecutiveFailedTest;

    @Column(name = "hist_successfull_test")
    private Long histSuccessfullTest;

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

    @Column(name = "last_tets_date")
    private LocalDate lastTetsDate;

    @Column(name = "\"req/seg\"", length = 45)
    private String reqSeg;

    @Column(name = "test_interv")
    private Long testInterv;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + service_id+ ", " +
                "description = " + description + ", " +
                "estatus = " + estatus + ", " +
                "applicationId = " + applicationId + ", " +
                "serviceName = " + serviceName + ", " +
                "operation = " + operation + ", " +
                "port = " + port + ", " +
                "method = " + method + ", " +
                "testUrl = " + testUrl + ", " +
                "numTest = " + numTest + ", " +
                "consecutiveSuccefullTest = " + consecutiveSuccefullTest + ", " +
                "consecutiveFailedTest = " + consecutiveFailedTest + ", " +
                "histSuccessfullTest = " + histSuccessfullTest + ", " +
                "histFailedTest = " + histFailedTest + ", " +
                "maxFailedTest = " + maxFailedTest + ", " +
                "minFailedTest = " + minFailedTest + ", " +
                "lowAlarm = " + lowAlarm + ", " +
                "highAlarm = " + highAlarm + ", " +
                "lastTetsDate = " + lastTetsDate + ", " +
                "reqSeg = " + reqSeg + ", " +
                "testInterv = " + testInterv + ")";
    }
}