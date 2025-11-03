package com.unilab.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.unilab.exceptions.AgendamentoNaoLocalizadoException;
import com.unilab.model.Agendamento;
import com.unilab.model.Usuario;
import com.unilab.service.AgendamentoService;
import com.unilab.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AgendamentoController {

    private final UsuarioService usuarioService;
    private final AgendamentoService agendamentoService;


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/deletar-agendamento/{id}")
    public ResponseEntity<Object> deletarAgendamento(@PathVariable("id") Long id) {
        return agendamentoService.buscarAgendamento(id)
                .map(agendamento -> {
                    agendamentoService.deletarAgendamento(agendamento);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/atualizar-agendamento/{id}")
    public ResponseEntity<Object> atualizarAgendamento(@PathVariable("id") Long id, @RequestBody Agendamento agendamento) {
        return agendamentoService.buscarAgendamento(id)
                .map(agendamentoExistente -> {
                    agendamentoService.atualizarAgendamento(agendamentoExistente, agendamento);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/listar-agendamentos")
    public ResponseEntity<List<Agendamento>> listarAgendamentos() {
        try {
            List<Agendamento> agendamentos = agendamentoService.listarAgendamentos();
            if (agendamentos.isEmpty()) {
                throw new AgendamentoNaoLocalizadoException("Não possui laboratórios cadastrados!");
            }
            return ResponseEntity.ok(agendamentos);
        } catch (Exception e) {
            throw new RuntimeException("Erro Inesperado! Gentileza contatar o setor de suporte!");
        }
    }

    @PostMapping("/solicitar-agendamento")
    public String agendar(@ModelAttribute Agendamento agendamento, 
                          Authentication authentication, 
                          RedirectAttributes redirectAttributes) {
    
        // Obtém o e-mail do usuário autenticado
        String email = authentication.getName();
    
        // Busca o usuário no banco
        Usuario usuario = usuarioService.buscarPorEmail(email);
    
        if (usuario != null) {
            agendamento.setUsuario(usuario);
            agendamentoService.solicitarAgendamento(agendamento);
            redirectAttributes.addFlashAttribute("mensagem", "Agendamento salvo com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao salvar: usuário não encontrado.");
        }
    
        return "redirect:/main/meus-agendamentos";
    }
}
    




