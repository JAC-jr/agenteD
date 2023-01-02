package com.example.MonitorAgent.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Persistence_registry")
@Table(name = "persistence_registry")
public class PersistenceRegistry {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "registry_id", nullable = false)
        private Integer registry_id;

        @Column(name = "db_id", length = 45)
        private Integer db_id;

        @Column(name = "db_name", length = 45)
        private String dbName;

        @Column(name = "description", length = 45)
        private String description;

        @Column(name = "status", length = 45)
        private String status;

        @Column(name = "application_id")
        private Integer applicationId;

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

        @Column(name = "db_type", length = 45)
        private String dbType;

}
