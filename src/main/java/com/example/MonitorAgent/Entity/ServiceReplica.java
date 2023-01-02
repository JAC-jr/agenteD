package com.example.MonitorAgent.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Entity(name = "service_Replica")
    @Table(name = "service_replica")
public class ServiceReplica {
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "replica_id", nullable = false)
        private Integer Replica_id;

        @Column(name = "api_id")
        private Integer serviceId;

        @Column(name = "replica_ip")
        private String replicaIp;

        @Column(name = "metadata")
        private String metadata;

        @Column(name = "replica_status")
        private String status;

        @Column(name = "replica_creation_date")
        private String creation_date;

        @Column(name = "replica_name")
        private String replica_name;

        @Column(name = "replica_last_test_date")
        private LocalDateTime last_test_date;

        @Column(name = "actual_state")
        private boolean actualState;

        @Column(name = "label_hash")
        private String label_hash;
    }
