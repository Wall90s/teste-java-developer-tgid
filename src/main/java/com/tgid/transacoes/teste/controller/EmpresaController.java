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
import com.tgid.transacoes.teste.model.Empresa;
import com.tgid.transacoes.teste.service.EmpresaService;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {
    
    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    public ResponseEntity<List<Empresa>> listaEmpresas(){
        return ResponseEntity.status(HttpStatus.OK).body(empresaService.listaEmpresas());
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<Empresa> retornaEmpresaPeloCnpj(@PathVariable String cnpj){
        if (empresaService.retornaEmpresaPeloCnpj(cnpj) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(empresaService.retornaEmpresaPeloCnpj(cnpj));
    }

    @PostMapping
    public ResponseEntity<Empresa> criaEmpresa(@RequestBody Empresa novaEmpresa){
        if(empresaService.criaEmpresa(novaEmpresa) == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaService.criaEmpresa(novaEmpresa)); 
    }

    @PutMapping
    public ResponseEntity<Empresa> editaEmpresa(@RequestBody Empresa empresa){
        if(empresaService.editaEmpresa(empresa) == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(empresaService.editaEmpresa(empresa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Empresa> excluiEmpresa(@PathVariable Long id){
        if(empresaService.excluiEmpresa(id)){
            empresaService.excluiEmpresa(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
}
