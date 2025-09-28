package com.unilab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unilab.model.Professor;

/**
 * Repositório responsável pelas operações de persistência da entidade {@link ProfessorRepository}.
 * Estende {@link JpaRepository}, fornecendo métodos CRUD e consultas personalizadas.
 *
 * @author Breno Nunes
 * @since 20/03/2025
 */

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
