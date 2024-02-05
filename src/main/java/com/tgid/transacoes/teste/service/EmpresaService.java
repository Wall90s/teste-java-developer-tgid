package com.tgid.transacoes.teste.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tgid.transacoes.teste.model.Empresa;
import com.tgid.transacoes.teste.repository.EmpresaRepository;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public List<Empresa> listaEmpresas() {
        return empresaRepository.findAll();
    }

    public Empresa retornaEmpresaPeloCnpj(String cnpj) {
        for (Empresa empresa : empresaRepository.findAll()) {
            if (empresa.getCnpj().equals(cnpj)) {
                return empresa;
            }
        }
        return null;
    }

    public Empresa criaEmpresa(Empresa novaEmpresa) {
        if (ValidorCnpj.validaCnpj(novaEmpresa.getCnpj())) {
            return empresaRepository.save(novaEmpresa);
        }
        return null;
    }

    public Empresa editaEmpresa(Empresa empresa) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(empresa.getId());

        if (empresaOptional.isPresent()) {
            return empresaRepository.save(empresa);
        }
        return null;
    }

    public boolean excluiEmpresa(Long id) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(id);

        if (empresaOptional.isPresent()) {
            empresaRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
