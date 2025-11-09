package com.unilab.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Query("""
        SELECT a FROM Agendamento a
        WHERE a.laboratorio.id = :laboratorioId
          AND a.data = :data
          AND (
                (a.horarioInicio <= :horarioFim AND a.horarioFim >= :horarioInicio)
              )
    """)
    List<Agendamento> verificarConflito(
        @Param("laboratorioId") Long laboratorioId,
        @Param("data") String data,
        @Param("horarioInicio") String horarioInicio,
        @Param("horarioFim") String horarioFim
    );
}
