package com.example.MonitorAgent.InterrogationMethods.IntegrationMethod;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PicResponseModel {
    private String code;
    private String description;
    private String codigoEntidad;
    private String cuenta;
    private String divisa;
    private String saldoDisponible;
    private String dispTotalAcu;
    private String saldoDispuesto;
    private String saldoRetenido;
    private String saldoRetenidoPrec;
    private String saldoRetenidoHoy;
    private String sdoRetenido;
    private String excedido;
    private String cupoCanje;
    private String cupoRemesas;
    private String saldoCanje;
    private String saldoRemesas;
    private String limite;
    private String saldoRetencionLiq;
    private String disponibleConjunto;
}
