package com.example.MonitorAgent.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Api_Replica")
@Table(name = "api_replica")
public class ApiReplica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "replica_id", nullable = false)
    private Integer replica_id;

    @Column(name = "api_id")
    private Integer apiId;

    @Column(name = "replica_ip")
    private String replicaIp;

    @Column(name = "metadata")
    private String metadata;

    @Column(name = "replica_status")
    private String replica_status;

    @Column(name = "replica_creation_date")
    private String replica_creation_date;

    @Column(name = "replica_name")
    private String replica_name;

    @Column(name = "replica_last_test_date")
    private LocalDateTime replica_last_test_date;

    @Column(name = "replica_last_ok_status_date")
    private LocalDateTime replica_last_ok_status_date;

    @Column(name = "replica_last_fail_status_date")
    private LocalDateTime replica_last_fail_status_date;





}