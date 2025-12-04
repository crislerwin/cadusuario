package com.cadastro.controller;

import com.cadastro.dto.CadastroUsuarioDTO;
import com.cadastro.dto.UsuarioResponseDTO;
import com.cadastro.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Controlador REST para gerenciar usuários
 * 
 * Fornece endpoints para:
 * - POST /usuarios - Cadastrar novo usuário
 * - GET /usuarios/{id} - Buscar usuário por ID
 * - GET /usuarios/email/{email} - Buscar usuário por email
 * - GET /usuarios - Listar todos os usuários
 * - DELETE /usuarios/{id} - Deletar usuário
 */
@RestController
@RequestMapping("/usuarios")
@Slf4j
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Cadastra um novo usuário
     * 
     * @param cadastroDTO dados do usuário
     * @param bindingResult resultado da validação
     * @return ResponseEntity com dados do usuário cadastrado
     */
    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(
            @Valid @RequestBody CadastroUsuarioDTO cadastroDTO,
            BindingResult bindingResult) {

        log.info("Recebida requisição de cadastro: {}", cadastroDTO.getEmail());

        // Validar erros de validação
        if (bindingResult.hasErrors()) {
            Map<String, String> erros = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    erros.put(error.getField(), error.getDefaultMessage())
            );
            log.warn("Erros de validação no cadastro: {}", erros);
            return ResponseEntity.badRequest().body(erros);
        }

        try {
            UsuarioResponseDTO usuarioCadastrado = usuarioService.cadastrarUsuario(cadastroDTO);
            log.info("Usuário cadastrado com sucesso: {}", usuarioCadastrado.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCadastrado);
        } catch (IllegalArgumentException e) {
            log.error("Erro ao cadastrar usuário: {}", e.getMessage());
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", e.getMessage());
            return ResponseEntity.badRequest().body(erro);
        } catch (Exception e) {
            log.error("Erro inesperado ao cadastrar usuário", e);
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", "Erro ao cadastrar usuário");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
        }
    }

    /**
     * Busca um usuário pelo ID
     * 
     * @param id ID do usuário
     * @return ResponseEntity com dados do usuário
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable Long id) {
        log.info("Buscando usuário com ID: {}", id);

        try {
            UsuarioResponseDTO usuario = usuarioService.buscarUsuarioPorId(id);
            return ResponseEntity.ok(usuario);
        } catch (IllegalArgumentException e) {
            log.error("Usuário não encontrado: {}", e.getMessage());
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
        }
    }

    /**
     * Busca um usuário pelo email
     * 
     * @param email email do usuário
     * @return ResponseEntity com dados do usuário
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<?> buscarUsuarioPorEmail(@PathVariable String email) {
        log.info("Buscando usuário com email: {}", email);

        try {
            UsuarioResponseDTO usuario = usuarioService.buscarUsuarioPorEmail(email);
            return ResponseEntity.ok(usuario);
        } catch (IllegalArgumentException e) {
            log.error("Usuário não encontrado: {}", e.getMessage());
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
        }
    }

    /**
     * Lista todos os usuários cadastrados
     * 
     * @return ResponseEntity com lista de usuários
     */
    @GetMapping
    public ResponseEntity<?> listarTodosUsuarios() {
        log.info("Listando todos os usuários");

        try {
            List<UsuarioResponseDTO> usuarios = usuarioService.listarTodosUsuarios();
            Map<String, Object> resposta = new HashMap<>();
            resposta.put("total", usuarios.size());
            resposta.put("usuarios", usuarios);
            return ResponseEntity.ok(resposta);
        } catch (Exception e) {
            log.error("Erro ao listar usuários", e);
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", "Erro ao listar usuários");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
        }
    }

    /**
     * Retorna o total de usuários cadastrados
     * 
     * @return ResponseEntity com o total
     */
    @GetMapping("/stats/total")
    public ResponseEntity<?> obterTotalUsuarios() {
        log.info("Obtendo total de usuários");

        try {
            long total = usuarioService.contarUsuarios();
            Map<String, Long> resposta = new HashMap<>();
            resposta.put("total", total);
            return ResponseEntity.ok(resposta);
        } catch (Exception e) {
            log.error("Erro ao obter total de usuários", e);
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", "Erro ao obter total de usuários");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
        }
    }

    /**
     * Deleta um usuário
     * 
     * @param id ID do usuário a deletar
     * @return ResponseEntity com mensagem de sucesso
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable Long id) {
        log.info("Deletando usuário com ID: {}", id);

        try {
            usuarioService.deletarUsuario(id);
            Map<String, String> resposta = new HashMap<>();
            resposta.put("mensagem", "Usuário deletado com sucesso");
            return ResponseEntity.ok(resposta);
        } catch (IllegalArgumentException e) {
            log.error("Usuário não encontrado: {}", e.getMessage());
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
        } catch (Exception e) {
            log.error("Erro ao deletar usuário", e);
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", "Erro ao deletar usuário");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
        }
    }

    /**
     * Health check do servidor
     * 
     * @return ResponseEntity com status OK
     */
    @GetMapping("/health")
    public ResponseEntity<?> health() {
        Map<String, String> resposta = new HashMap<>();
        resposta.put("status", "OK");
        resposta.put("mensagem", "API de Cadastro de Usuários está funcionando");
        return ResponseEntity.ok(resposta);
    }
}
