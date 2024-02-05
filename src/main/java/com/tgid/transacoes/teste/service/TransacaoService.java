package com.tgid.transacoes.teste.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tgid.transacoes.teste.model.Cliente;
import com.tgid.transacoes.teste.model.Empresa;
import com.tgid.transacoes.teste.model.Transacao;

@Service
public class TransacaoService {

    @Autowired
    private Transacao transacao;

    public boolean saldoSuficiente(BigDecimal valorDesejado, BigDecimal saldo) {
        if (0 >= saldo.subtract(valorDesejado).compareTo(BigDecimal.ZERO)) {
            return true;
        }
        return false;
    }

    public BigDecimal calculoTaxaAdmistrativa(BigDecimal taxaAdministrativa, BigDecimal valorDesejado) {
        BigDecimal porcentagem = new BigDecimal(100);

        return valorDesejado.multiply(taxaAdministrativa).divide(porcentagem);
    }

    public Cliente deposito(Cliente cliente, Empresa empresa, BigDecimal deposito) {
        if (saldoSuficiente(deposito, cliente.getSaldo())) {
            BigDecimal taxaAdministrativa = calculoTaxaAdmistrativa(transacao.getTaxaAdministrativa(), deposito);

            BigDecimal totalParaDeposito = deposito.subtract(taxaAdministrativa);

            cliente.setSaldo(cliente.getSaldo().subtract(deposito));

            empresa.setSaldo(empresa.getSaldo().add(totalParaDeposito));

            return cliente;
        }
        return null;
    }

    public Cliente saque(Cliente cliente, Empresa empresa, BigDecimal saque) {
        if (saldoSuficiente(saque, empresa.getSaldo())) {
            BigDecimal taxaAdministrativa = calculoTaxaAdmistrativa(transacao.getTaxaAdministrativa(), saque);

            BigDecimal totalParaSaque = saque.add(taxaAdministrativa);

            empresa.setSaldo(empresa.getSaldo().subtract(totalParaSaque));

            cliente.setSaldo(cliente.getSaldo().add(saque));

            return cliente;
        }
        return null;
    }

}
