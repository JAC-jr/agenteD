package com.example.MonitorAgent.ResponseModel;

import lombok.Data;

    @Data
    public class ResponseBase {
        private Integer timeLapse;
        private String statusCode;
        @Override
        public String toString() {
            return "Response{" +
                    "statusCode=" + statusCode +
                    ", timeLapse='" + timeLapse +
                    '}';
        }
}
