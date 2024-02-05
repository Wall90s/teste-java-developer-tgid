package com.tgid.transacoes.teste.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tgid.transacoes.teste.model.Cliente;
import com.tgid.transacoes.teste.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listaClientes() {
        return clienteRepository.findAll();
    }

    public Cliente retornaClientePeloCpf(String cpf) {
        for (Cliente cliente : clienteRepository.findAll()) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    public Cliente criaCliente(Cliente cliente){
        if (ValidadorCpf.validaCpf(cliente.getCpf())){
            return clienteRepository.save(cliente);
        }
        return null;
    }

    public Cliente editaCliente(Cliente cliente){
        Optional<Cliente> clienteOptional = clienteRepository.findById(cliente.getId());

        if (clienteOptional.isPresent()) {
            return clienteRepository.save(cliente);
        }
        return null;
    }

    public boolean excluiCliente(Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        if (clienteOptional.isPresent()) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
