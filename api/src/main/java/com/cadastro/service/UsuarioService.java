package com.cadastro.service;

import com.cadastro.dto.CadastroUsuarioDTO;
import com.cadastro.dto.UsuarioResponseDTO;
import com.cadastro.entity.Usuario;
import com.cadastro.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço para lógica de negócio de usuário
 * 
 * Responsável por:
 * - Validação de dados
 * - Persistência de usuários
 * - Consultas de usuários
 */
@Service
@Slf4j
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Cadastra um novo usuário
     * 
     * @param cadastroDTO dados do usuário a cadastrar
     * @return dados do usuário cadastrado
     * @throws IllegalArgumentException se o email já existe
     */
    public UsuarioResponseDTO cadastrarUsuario(CadastroUsuarioDTO cadastroDTO) {
        log.info("Iniciando cadastro de novo usuário: {}", cadastroDTO.getEmail());

        // Validar se email já existe
        if (usuarioRepository.existsByEmail(cadastroDTO.getEmail())) {
            log.warn("Tentativa de cadastro com email duplicado: {}", cadastroDTO.getEmail());
            throw new IllegalArgumentException("Email já cadastrado no sistema");
        }

        // Criar nova entidade Usuario
        Usuario usuario = Usuario.builder()
                .nome(cadastroDTO.getNome())
                .sobrenome(cadastroDTO.getSobrenome())
                .email(cadastroDTO.getEmail())
                .senha(cadastroDTO.getSenha()) // Em produção, seria necessário fazer hash da senha
                .cep(cadastroDTO.getCep())
                .rua(cadastroDTO.getRua())
                .numero(cadastroDTO.getNumero())
                .complemento(cadastroDTO.getComplemento())
                .bairro(cadastroDTO.getBairro())
                .cidade(cadastroDTO.getCidade())
                .estado(cadastroDTO.getEstado())
                .build();

        // Salvar no banco de dados
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        log.info("Usuário cadastrado com sucesso. ID: {}, Email: {}", usuarioSalvo.getId(), usuarioSalvo.getEmail());

        return UsuarioResponseDTO.fromEntity(usuarioSalvo);
    }

    /**
     * Busca um usuário pelo ID
     * 
     * @param id ID do usuário
     * @return dados do usuário
     * @throws IllegalArgumentException se o usuário não for encontrado
     */
    public UsuarioResponseDTO buscarUsuarioPorId(Long id) {
        log.info("Buscando usuário com ID: {}", id);

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Usuário não encontrado. ID: {}", id);
                    return new IllegalArgumentException("Usuário não encontrado");
                });

        return UsuarioResponseDTO.fromEntity(usuario);
    }

    /**
     * Busca um usuário pelo email
     * 
     * @param email email do usuário
     * @return dados do usuário
     * @throws IllegalArgumentException se o usuário não for encontrado
     */
    public UsuarioResponseDTO buscarUsuarioPorEmail(String email) {
        log.info("Buscando usuário com email: {}", email);

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.warn("Usuário não encontrado. Email: {}", email);
                    return new IllegalArgumentException("Usuário não encontrado");
                });

        return UsuarioResponseDTO.fromEntity(usuario);
    }

    /**
     * Lista todos os usuários cadastrados
     * 
     * @return lista de usuários
     */
    public List<UsuarioResponseDTO> listarTodosUsuarios() {
        log.info("Listando todos os usuários");

        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * Retorna o total de usuários cadastrados
     * 
     * @return total de usuários
     */
    public long contarUsuarios() {
        long total = usuarioRepository.count();
        log.info("Total de usuários cadastrados: {}", total);
        return total;
    }

    /**
     * Deleta um usuário
     * 
     * @param id ID do usuário a deletar
     * @throws IllegalArgumentException se o usuário não for encontrado
     */
    public void deletarUsuario(Long id) {
        log.info("Deletando usuário com ID: {}", id);

        if (!usuarioRepository.existsById(id)) {
            log.warn("Usuário não encontrado para deleção. ID: {}", id);
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        usuarioRepository.deleteById(id);
        log.info("Usuário deletado com sucesso. ID: {}", id);
    }
}
