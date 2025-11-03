package com.unilab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.unilab.model.Professor;
import com.unilab.model.Usuario;
import com.unilab.service.ProfessorService;
import com.unilab.service.UsuarioService;

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
    private UsuarioService usuarioService;

    @PostMapping("/cadastrar-professor")
    public String cadastrar(@ModelAttribute Professor professor, 
                          Authentication authentication, 
                          RedirectAttributes redirectAttributes) {
    
        // Obtém o e-mail do usuário autenticado
        String email = authentication.getName();
    
        // Busca o usuário no banco
        Usuario usuario = usuarioService.buscarPorEmail(email);
    
        if (usuario != null) {
            professorService.criarProfessor(professor);
            redirectAttributes.addFlashAttribute("mensagem", "Professor salvo com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao salvar: usuário não encontrado.");
        }
    
        return "redirect:/main/gerenciar-professor";
    }

    
}
