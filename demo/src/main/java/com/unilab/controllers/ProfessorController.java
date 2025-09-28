package com.unilab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.unilab.model.Agendamento;
import com.unilab.service.AgendamentoService;
import com.unilab.service.ProfessorService;

/**
 * Classe Controller dos Professores do sistema
 *
 * @author -> Breno Nunes -> @github.com/brenonun3s
 * @date 20/03/2025
 */

@Controller
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping("/login")
    public String loginProf() {
        return "telaDeLoginProfessor";
    }

    @GetMapping("/novo-agendamento")
    public String novoAgendamento(Model model) {
        model.addAttribute("agendamento", new Agendamento());
        return "meusagendamentos";
    }

    
}
