package com.tgid.transacoes.teste.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgid.transacoes.teste.model.Cliente;
import com.tgid.transacoes.teste.model.Empresa;
import com.tgid.transacoes.teste.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> listaClientes(){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.listaClientes());
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Cliente> retornaClientePeloCpf(@PathVariable String cpf){
        if(clienteService.retornaClientePeloCpf(cpf) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(clienteService.retornaClientePeloCpf(cpf)); 
    }

    @PostMapping
    public ResponseEntity<Cliente> criaCliente(@RequestBody Cliente novoCliente){
        if(clienteService.criaCliente(novoCliente) == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.criaCliente(novoCliente));
    }

    @PutMapping
    public ResponseEntity<Cliente> editaCliente(@RequestBody Cliente cliente){
        if(clienteService.editaCliente(cliente) == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.editaCliente(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Empresa> excluiCliente(@PathVariable Long id){
        if(clienteService.excluiCliente(id)){
            clienteService.excluiCliente(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
