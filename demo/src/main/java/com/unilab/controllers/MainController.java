package com.unilab.controllers;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unilab.model.Agendamento;
import com.unilab.model.Laboratorio;
import com.unilab.model.Professor;
import com.unilab.model.Usuario;
import com.unilab.service.AgendamentoService;
import com.unilab.service.LaboratorioService;
import com.unilab.service.ProfessorService;
import com.unilab.service.UsuarioService;

import lombok.RequiredArgsConstructor;

/**
 * Classe Controller que carregará apenas os templates
 *
 * @author -> Breno Nunes -> @github.com/brenonun3s
 * @date 20/03/2025
 */

@Controller
@RequestMapping("main")
@RequiredArgsConstructor
public class MainController {

    private final UsuarioService usuarioService;
    private final AgendamentoService agendamentoService;
    private final ProfessorService professorService;
    private final LaboratorioService laboratorioService;

    @GetMapping("")
    public String index() {
        return "index";
    }

    @GetMapping("/sobre")
    public String sobre() {
        return "sobre";
    }

    @GetMapping("/tutoriais")
    public String tutoriais() {
        return "tutoriais";
    }

    @GetMapping("/suporte")
    public String suporte(){
        return "documento";
    }

    @GetMapping("/login-form")
    public String telaLoginProfessor(){
        return "telaLogin";
    }

    @GetMapping("/seja-bem-vindo-adm")
    public String sejaBemVindo(){
        return "sejabemvindoadm";
    }

    @GetMapping("/seja-bem-vindo-professor")
    public String sejaBemVindoProfessor(){
        return "sejabemvindoprofessor";
    }

    @GetMapping("/agendar-laboratorio")
    public String agendarLaboratorio(Model model){
        model.addAttribute("agendamento", new Agendamento());
        model.addAttribute("professores", professorService.listarProfessores());
        model.addAttribute("laboratorios", laboratorioService.listarLaboratorios());
        return "cadastraragendamento";
    }

    @GetMapping("/historico")
    public String historico(){
        return "historico";
    }

    @GetMapping("/cadastrar-professor")
    public String cadastrarProfessor(Model model){
        model.addAttribute("professor", new Professor());
        return "cadastarprofessor";
    }

    @GetMapping("/cadastrar-laboratorio")
    public String cadastrarLaboratorio(Model model){
        model.addAttribute("laboratorio", new Laboratorio());
        return "cadastrarlaboratorio";
    }

    @GetMapping("/atualizar-professor")
    public String atualizarProfessor(){
        return "atualizarprofessor";
    }

    @GetMapping("/atualizar-laboratorio")
    public String atualizarLaboratorio(){
        return "atualizarlaboratorio";
    }

    @GetMapping("/gerenciar-laboratorio")
    public String gerenciarLaboratorio(Model model){
        List<Laboratorio> laboratorios = laboratorioService.listarLaboratorios();
        model.addAttribute("laboratorios", laboratorios);
        return "admgerenciarlab";
    }

    @GetMapping("/gerenciar-professor")
    public String gerenciarProfessor(Model model){
        List<Professor> professores = professorService.listarProfessores();
        model.addAttribute("professores", professores);
        return "admgerenciarprof";
    }

    @GetMapping("/meus-agendamentos")
    public String meusAgendamentos(Authentication auth, Model model) {
        String email = auth.getName();
        Usuario usuario = usuarioService.buscarPorEmail(email);
    
        List<Agendamento> agendamentos = agendamentoService.listarPorUsuario(usuario);
        model.addAttribute("agendamentos", agendamentos);
    
        // Apenas se for útil na view:
        model.addAttribute("professores", professorService.listarProfessores());
        model.addAttribute("laboratorios", laboratorioService.listarLaboratorios());
    
        String rotaVoltar = null;
    
        if (usuario.getRole() == null) {
            rotaVoltar = "/";
        } else if (usuario.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
            rotaVoltar = "/main/seja-bem-vindo-adm";
        } else if (usuario.getRole().equalsIgnoreCase("ROLE_USER")) {
            rotaVoltar = "/main/seja-bem-vindo-professor";
        }
    
        model.addAttribute("rotaVoltar", rotaVoltar);
        return "meusagendamentos";
    }
    


}
