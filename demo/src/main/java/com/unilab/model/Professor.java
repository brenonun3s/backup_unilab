package com.unilab.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe Entidade que representa os professores do sistema
 *
 * @author -> Breno Nunes -> @github.com/brenonun3s
 * @date 19/03/2025
 */

@Entity
@Table(name = "professores")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome_professor", nullable = false, length = 150)
    private String nome;

    @Column(name = "email", nullable = false, length = 155)
    private String email;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "departamento_professor")
    private String departamento;

    @Column(name = "matricula_professor")
    private String matricula;

    

}

