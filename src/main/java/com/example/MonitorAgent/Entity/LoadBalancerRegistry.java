package com.example.MonitorAgent.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "LoadBalancer_Registry")
@Table(name = "load_balancer_registry")
public class LoadBalancerRegistry {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "registry_id", nullable = false)
        private Integer registryId;

        @Column(name = "vserver_id", length = 100)
        private Integer vserverId;

        @Column(name = "description", length = 100)
        private String description;

        @Column(name = "status", length = 45)
        private String status;

        @Column(name = "url_server")
        private String urlServer;

        @Column(name = "last_test_date")
        private LocalDateTime lastTestDate;

        @Column(name = "successful_consecutive_test")
        private Integer successfulConsecutiveTest;

        @Column(name = "failed_consecutive_test")
        private Integer failedConsecutiveTest;

        @Column(name = "history_failed_test")
        private Long historyFailedTest;

        @Column(name = "history_successful_test")
        private Long historySuccessfulTest;

        @Column(name = "application_id")
        private Integer applicationId;

        @Column(name = "response_time", length = 45)
        private Long response_time;

    }
