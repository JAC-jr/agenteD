package com.example.MonitorAgent.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Service_registry")
@Table(name = "service_registry")
public class ServicioRegistry {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "registry_id", nullable = false)
        private Integer registry_id;

        @Column(name = "service_id", length = 45)
        private Integer service_id;

        @Column(name = "status", length = 45)
        private String status;

        @Column(name = "health", length = 45)
        private String health;

        @Column(name = "application_id")
        private Integer applicationId;

        @Column(name = "label_app", length = 45)
        private String label_app;

        @Column(name = "name_space", length = 45)
        private String nameSpace;

        @Column(name = "consecutive_failed_test")
        private Integer consecutiveFailedTest;

        @Column(name = "hist_failed_test")
        private Long histFailedTest;

        @Column(name = "last_test_date")
        private LocalDateTime lastTestDate;

        @Column(name = "response_time", length = 45)
        private Long response_time;

        @Column(name = "consecutive_successful_test")
        private Integer consecutiveSuccessfulTest;

        @Column(name = "hist_successful_test")
        private Long histSuccessfulTest;
}

