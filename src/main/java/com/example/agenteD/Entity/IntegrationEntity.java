package com.example.agenteD.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public class IntegrationEntity {

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Entity
    @Table(name = "api")
    public class Integration {
        @Id
        @Column(name = "api_id", nullable = false)
        private Long integration_id;
        private String status;
        private String integration_type;
        private String channel;
        private Long aplication_id;
        private String description;
        private Integer num_test;
        private Integer consecutive_successfull_test;
        private Integer consecutive_failed_test;
        private Long hist_failed_test;
        private Long hist_successfull_test;
        private Integer min_test_failed;
        private Integer max_test_failed;
        private Integer low_alarm;
        private Integer high_alarm;
        private String last_test_date;
        private String req_sec;
        private Long test_interv;
    }
}
