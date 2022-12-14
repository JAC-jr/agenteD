package com.example.MonitorAgent.Entity;

import lombok.*;

import javax.persistence.*;

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

    @Column(name = "replica_last_ip")
    private String replica_last_ip;

    @Column(name = "replica_status")
    private String replica_status;

    @Column(name = "replica_last_status")
    private String replica_last_status;

    @Column(name = "replica_previous_name")
    private String replica_previous_name;

    @Column(name = "replica_date")
    private String replica_date;

    @Column(name = "previous_replica_date")
    private String previous_replica_date;

    @Column(name = "replica_name")
    private String replica_name;

}
