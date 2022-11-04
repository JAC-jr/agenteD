package com.example.agenteD.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "load_balancer")
public class Load_balancer {
    @Id
    @Column(name = "vserver_id", nullable = false)
    private Long vserver_id;
    private String status;
    private String ip_server;
    private String description;
    private String port;
    private String num_test;
    private String last_test_date;
    private String successful_consecutive_test;
    private String failed_consecutive_test;
    private String history_successfull_test;
    private String history_failed_test;
    private String min_fail_test;
    private String max_fail_test;
    private String low_alarm;
    private String high_alarm;
    private String test_duration;
    private String req_sec;



}
