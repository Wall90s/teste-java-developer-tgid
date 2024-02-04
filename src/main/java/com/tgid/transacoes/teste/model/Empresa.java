package com.tgid.transacoes.teste.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cnpj;

    private BigDecimal saldo;

    @JsonProperty("taxa_saque")
    private Float taxaSaque;

    @JsonProperty("taxa_deposito")
    private Float taxaDeposito;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("empresa")
    private List<Cliente> clientes;

    public Empresa() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public BigDecimal getSaldo() {
        return this.saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Float getTaxaSaque() {
        return this.taxaSaque;
    }

    public void setTaxaSaque(Float taxaSaque) {
        this.taxaSaque = taxaSaque;
    }

    public Float getTaxaDeposito() {
        return this.taxaDeposito;
    }

    public void setTaxaDeposito(Float taxaDeposito) {
        this.taxaDeposito = taxaDeposito;
    }

    public List<Cliente> getClientes() {
        return this.clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

}
