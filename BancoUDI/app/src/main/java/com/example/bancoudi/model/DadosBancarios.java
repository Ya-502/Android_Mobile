package com.example.bancoudi.model;

import java.util.ArrayList;

public class DadosBancarios {
    private String titular;
    private Double saldo;

    public DadosBancarios(String titular, Double saldo) {
        this.titular = titular;
        this.saldo = saldo;
    }

    public DadosBancarios() {
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
}
