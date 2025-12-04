package com.cadastro.repository;

import com.cadastro.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório para acesso aos dados de usuário
 * 
 * Fornece operações CRUD e consultas personalizadas para a entidade Usuario
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Busca um usuário pelo email
     * 
     * @param email email do usuário
     * @return Optional contendo o usuário se encontrado
     */
    Optional<Usuario> findByEmail(String email);

    /**
     * Verifica se um email já existe no banco de dados
     * 
     * @param email email a verificar
     * @return true se o email existe, false caso contrário
     */
    boolean existsByEmail(String email);
}
