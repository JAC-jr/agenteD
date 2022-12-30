package com.example.MonitorAgent.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Entity(name = "services_Replica")
    @Table(name = "services_replica")
public class ServicesReplica {
        

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "replica_id", nullable = false)
        private Integer servicesReplica_id;

        @Column(name = "services_id")
        private Integer servicesReplicaId;

        @Column(name = "replica_ip")
        private String servicesReplicaIp;

        @Column(name = "metadata")
        private String metadata;

        @Column(name = "replica_status")
        private String servicesReplica_status;

        @Column(name = "replica_creation_date")
        private String servicesReplica_creation_date;

        @Column(name = "replica_name")
        private String servicesReplica_name;

        @Column(name = "replica_last_test_date")
        private LocalDateTime servicesReplica_last_test_date;

        @Column(name = "actual_state")
        private boolean servicesReplicaActualState;

        @Column(name = "label_hash")
        private String servicesReplica_label_hash;
    }
