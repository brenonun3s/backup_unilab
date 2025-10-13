package com.unilab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unilab.model.dto.UsuarioDTO;
import com.unilab.model.dto.UsuarioResponse;
import com.unilab.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
 
 @Autowired
 UsuarioService service;

@PostMapping("/cadastrar")
public ResponseEntity<?> cadastrar(@RequestBody UsuarioDTO dto) {
    try {
        UsuarioResponse novoUsuario = service.cadastrarUsuario(dto);
        return ResponseEntity.ok(novoUsuario);

    } catch (IllegalArgumentException e) {
        // Erros de regra de negócio (ex: email já cadastrado)
        return ResponseEntity.badRequest().body(e.getMessage());

    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao cadastrar usuário.");
    }
}

}
