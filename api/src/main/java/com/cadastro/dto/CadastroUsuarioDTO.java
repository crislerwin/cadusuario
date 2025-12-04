package com.cadastro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * DTO para requisição de cadastro de usuário
 * 
 * Contém os dados necessários para criar um novo usuário no sistema
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CadastroUsuarioDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @NotBlank(message = "Sobrenome é obrigatório")
    @Size(min = 3, max = 100, message = "Sobrenome deve ter entre 3 e 100 caracteres")
    private String sobrenome;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    private String senha;

    @NotBlank(message = "CEP é obrigatório")
    @Pattern(regexp = "^\\d{5}-?\\d{3}$", message = "CEP deve estar no formato 00000-000")
    private String cep;

    @NotBlank(message = "Rua é obrigatória")
    @Size(min = 3, message = "Rua deve ter no mínimo 3 caracteres")
    private String rua;

    @NotBlank(message = "Número é obrigatório")
    @Pattern(regexp = "^\\d+$", message = "Número deve conter apenas dígitos")
    private String numero;

    private String complemento;

    @NotBlank(message = "Bairro é obrigatório")
    @Size(min = 2, message = "Bairro deve ter no mínimo 2 caracteres")
    private String bairro;

    @NotBlank(message = "Cidade é obrigatória")
    @Size(min = 2, message = "Cidade deve ter no mínimo 2 caracteres")
    private String cidade;

    @NotBlank(message = "Estado é obrigatório")
    @Pattern(regexp = "^[A-Z]{2}$", message = "Estado deve ser uma sigla (ex: SP)")
    private String estado;
}
