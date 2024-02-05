package com.tgid.transacoes.teste.model;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class Transacao {

    private Cliente cliente;

    private Empresa empresa;

    @JsonProperty("valor_transacao")
    private BigDecimal valorTransacao;

    private BigDecimal taxaAdministrativa = new BigDecimal(2);

    public Transacao() {
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public BigDecimal getValorTransacao() {
        return this.valorTransacao;
    }

    public void setValorTransacao(BigDecimal valorTransacao) {
        this.valorTransacao = valorTransacao;
    }

    public void setTaxaAdministrativa(BigDecimal taxaAdministrativa) {
        this.taxaAdministrativa = taxaAdministrativa;
    }

    public BigDecimal getTaxaAdministrativa() {
        return this.taxaAdministrativa;
    }

}
