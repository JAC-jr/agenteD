package com.example.agenteD.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

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
    private Long integration_id;

    @Column(name = "status", length = 45)
    private String status;

    @Column(name = "integration_type", length = 45)
    private String integrationType;

    @Column(name = "channel", length = 45)
    private String channel;

    @Column(name = "application_id")
    private Long applicationId;

    @Column(name = "description", length = 45)
    private String description;

    @Column(name = "num_test")
    private Integer numTest;

    @Column(name = "consecutive_successful_test")
    private Integer consecutiveSuccessfulTest;

    @Column(name = "consecutive_failed_test")
    private Integer consecutiveFailedTest;

    @Column(name = "his_failed_test")
    private Long hisFailedTest;

    @Column(name = "his_successful_test")
    private Long hisSuccessfulTest;

    @Column(name = "min_test_failed")
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
                "id = " + integration_id + ", " +
                "status = " + status + ", " +
                "integrationType = " + integrationType + ", " +
                "channel = " + channel + ", " +
                "applicationId = " + applicationId + ", " +
                "description = " + description + ", " +
                "numTest = " + numTest + ", " +
                "consecutiveSuccessfulTest = " + consecutiveSuccessfulTest + ", " +
                "consecutiveFailedTest = " + consecutiveFailedTest + ", " +
                "hisFailedTest = " + hisFailedTest + ", " +
                "hisSuccessfulTest = " + hisSuccessfulTest + ", " +
                "minTestFailed = " + minTestFailed + ", " +
                "maxTestFailed = " + maxTestFailed + ", " +
                "lowAlarm = " + lowAlarm + ", " +
                "highAlarm = " + highAlarm + ", " +
                "lastTestDate = " + lastTestDate + ", " +
                "reqSeg = " + reqSeg + ", " +
                "testInterv = " + testInterv + ")";
    }
}