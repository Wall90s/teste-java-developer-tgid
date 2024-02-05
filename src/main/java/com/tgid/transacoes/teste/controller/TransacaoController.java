package com.tgid.transacoes.teste.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgid.transacoes.teste.model.Cliente;
import com.tgid.transacoes.teste.model.Transacao;
import com.tgid.transacoes.teste.repository.ClienteRepository;
import com.tgid.transacoes.teste.service.TransacaoService;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public void teste() {
        ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<Cliente> deposito(@PathVariable Long idCliente, @RequestBody Transacao transacao) {
        Cliente cliente = clienteRepository.getReferenceById(idCliente);

        if (transacaoService.deposito(cliente, cliente.getEmpresa(), transacao.getValorTransacao()) == null) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(transacaoService.deposito(cliente, cliente.getEmpresa(), transacao.getValorTransacao()));
    }
}
