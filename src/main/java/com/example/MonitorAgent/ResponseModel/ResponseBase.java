package com.example.MonitorAgent.ResponseModel;

import lombok.Data;

    @Data
    public class ResponseBase {

        private Object headers;

        private String statusCode;
        private Integer statusCodeValue;

        @Override
        public String toString() {
            return "Response{" +
                    "statusCode=" + statusCode +
                    ", statusCodeValue='" + statusCodeValue +
                    '}';
        }
}
