package com.example.MonitorAgent.InterrogationMethods.LoadBalancerMethod;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class F5ResponseModel {

    private String codigo;
    private String descripcion;
    private String data;
    private String ticketId;
    private String clave;
}
