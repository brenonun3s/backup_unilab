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
 * Classe Entidade que representa os Laboratorios do sistema
 *
 * @author -> Breno Nunes -> @github.com/brenonun3s
 * @date 19/03/2025
 */

@Entity
@Table(name = "laboratorios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Laboratorio{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "numero_laboratorio", nullable = false)
    private Long numeroLaboratorio;

    @Column(name = "departamento_laboratorio")
    private String departamentoLaboratorio;

    @Column(name = "localizacao_lab")
    private String localizacao;

    @Column(name = "capacidade_lab")
    private Integer capacidade;

    @Column(name = "status")
    private String status;

    @Column
    private String ferramentasDisponiveis;

    @Column
    private String observacao;



}
