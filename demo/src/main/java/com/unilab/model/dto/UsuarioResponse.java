package com.unilab.model.dto;


import java.util.UUID;

public record UsuarioResponse(UUID id, String nome, String email) {
}
