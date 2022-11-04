package com.example.agenteD.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public class ServiceEntity {

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Entity
    @Table(name = "api")
    public class Service {
        @Id
        @Column(name = "api_id", nullable = false)
        private Long service_id;
        private String description;
        private String status;
        private Integer application_id;
        private String service_name;
        private String operation;
        private String port;
        private String method;
        private String test_url;
        private Integer num_test;
        private Integer consecutive_successfull_test;
        private Integer consecutive_failed_test;
        private long hist_successfull_test;
        private long hist_failed_test;
        private Integer max_failed_test;
        private Integer min_failed_test;
        private Integer low_alarm;
        private Integer high_alarm;
        private String last_test_date;
        private String req_sec;
        private long test_interv;

    }
}
