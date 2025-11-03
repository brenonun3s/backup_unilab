package com.unilab.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unilab.model.Agendamento;
import com.unilab.model.Usuario;

/**
 * Repositório responsável pelas operações de persistência da entidade {@link Agendamento}.
 * Estende {@link JpaRepository}, fornecendo métodos CRUD e consultas personalizadas.
 *
 * @author Breno Nunes
 * @since 20/03/2025
 */

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    Agendamento findByData(String data);
    List<Agendamento> findByUsuario(Usuario usuario);
}
