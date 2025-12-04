package com.cadastro.dto;

import com.cadastro.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO para resposta de usuário
 * 
 * Contém os dados do usuário para retorno em requisições GET
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private String cep;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private LocalDateTime dataCriacao;

    /**
     * Converte uma entidade Usuario para DTO
     */
    public static UsuarioResponseDTO fromEntity(Usuario usuario) {
        return UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .sobrenome(usuario.getSobrenome())
                .email(usuario.getEmail())
                .cep(usuario.getCep())
                .rua(usuario.getRua())
                .numero(usuario.getNumero())
                .complemento(usuario.getComplemento())
                .bairro(usuario.getBairro())
                .cidade(usuario.getCidade())
                .estado(usuario.getEstado())
                .dataCriacao(usuario.getDataCriacao())
                .build();
    }
}
