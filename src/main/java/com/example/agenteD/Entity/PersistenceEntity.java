package com.example.agenteD.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public class PersistenceEntity {

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Entity
    @Table(name = "api")
    public class Persistence {
        @Id
        @Column(name = "api_id", nullable = false)
        private Long db_id;
        private String db_name;
        private String status;
        private String description;
        private Integer application_id;
        private String service_name;
        private String host;
        private String port;
        private Integer num_test;
        private Integer consecutive_successfull_test;
        private Integer consecutive_failed_test;
        private long hist_successfull_test;
        private long hist_failed_test;
        private Integer max_test_failed;
        private Integer min_test_failed;
        private Integer low_alarm;
        private Integer high_alarm;
        private String last_test_date;
        private String req_sec;
        private long test_interv;
    }
}
