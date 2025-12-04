package com.cadastro.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Entidade Usuario
 * 
 * Representa um usuário cadastrado no sistema com todos os seus dados
 * pessoais e de endereço.
 */
@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "Sobrenome é obrigatório")
    @Size(min = 3, max = 100, message = "Sobrenome deve ter entre 3 e 100 caracteres")
    @Column(nullable = false)
    private String sobrenome;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    @Column(nullable = false)
    private String senha;

    @NotBlank(message = "CEP é obrigatório")
    @Column(nullable = false)
    private String cep;

    @NotBlank(message = "Rua é obrigatória")
    @Column(nullable = false)
    private String rua;

    @NotBlank(message = "Número é obrigatório")
    @Column(nullable = false)
    private String numero;

    @Column(nullable = true)
    private String complemento;

    @NotBlank(message = "Bairro é obrigatório")
    @Column(nullable = false)
    private String bairro;

    @NotBlank(message = "Cidade é obrigatória")
    @Column(nullable = false)
    private String cidade;

    @NotBlank(message = "Estado é obrigatório")
    @Size(min = 2, max = 2, message = "Estado deve ser uma sigla (ex: SP)")
    @Column(nullable = false)
    private String estado;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @PrePersist
    protected void onCreate() {
        dataCriacao = LocalDateTime.now();
    }

    /**
     * Retorna o nome completo do usuário
     */
    public String getNomeCompleto() {
        return nome + " " + sobrenome;
    }

    /**
     * Retorna o endereço completo formatado
     */
    public String getEnderecoCompleto() {
        StringBuilder endereco = new StringBuilder();
        endereco.append(rua).append(", ").append(numero);
        if (complemento != null && !complemento.isEmpty()) {
            endereco.append(" - ").append(complemento);
        }
        endereco.append(" - ").append(bairro).append(" - ");
        endereco.append(cidade).append("/").append(estado).append(" - ").append(cep);
        return endereco.toString();
    }
}
