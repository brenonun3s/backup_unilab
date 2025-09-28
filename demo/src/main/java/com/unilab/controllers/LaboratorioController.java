package com.unilab.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.unilab.exceptions.LaboratorioNaoExisteException;
import com.unilab.model.Laboratorio;
import com.unilab.service.LaboratorioService;



@Controller
public class LaboratorioController {

    @Autowired
    LaboratorioService laboratorioService;

    @PostMapping("/cadastrar-laboratorio")
    public String solicitarPorFormulario(@ModelAttribute Laboratorio laboratorio) {
        laboratorioService.criarLaboratorio(laboratorio);
        return "redirect:/main/gerenciar-laboratorio";
    }

    // OK
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/deletar-laboratorio/{id}")
    public ResponseEntity<Object> deletarLaboratorio(@PathVariable("id") Long id) {
        return laboratorioService.buscarLaboratorio(id)
                .map(laboratorio -> {
                    laboratorioService.deletarLaboratorio(laboratorio);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // OK
    @PutMapping("/atualizar-laboratorio/{id}")
    public ResponseEntity<Object> atualizarLaboratorio(@PathVariable("id") Long id,
            @RequestBody Laboratorio laboratorio) {
        return laboratorioService.buscarLaboratorio(id)
                .map(laboratorioExistente -> {
                    laboratorioService.atualizarLaboratorio(laboratorioExistente, laboratorio);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());

    }

    // OK
    @GetMapping("/listar-laboratorios")
    public ResponseEntity<List<Laboratorio>> listarLaboratorios() {
        try {
            List<Laboratorio> laboratorios = laboratorioService.listarLaboratorios();
            if (laboratorios.isEmpty()) {
                throw new LaboratorioNaoExisteException("Não possui laboratórios cadastrados!");
            }
            return ResponseEntity.ok(laboratorios);
        } catch (Exception e) {
            throw new RuntimeException("Erro Inesperado! Gentileza contatar o sobre!");
        }
    }

    

}
