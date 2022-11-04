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
@Table(name = "api")
public class Api {
    @Id
    @Column(name = "api_id", nullable = false)
    private Long api_id;
    private String status;
    private Integer application_id;
    private String common;
    private String description;
    private String service_name;
    private String name_space;
    private String num_test;
    private String test_duration;
    private String consecutive_successfull_test;
    private String consecutive_failed_test;
    private String hist_failed_test;
    private String hist_successfull_test;
    private String min_test_failed;
    private String max_test_failed;
    private String low_alarm;
    private String high_alarm;
    private String last_test_date;
    private String req_sec;








    
}
