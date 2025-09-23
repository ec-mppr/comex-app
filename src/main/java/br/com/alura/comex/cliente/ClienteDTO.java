package br.com.alura.comex.cliente;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteDTO(
        @NotBlank @CPF String cpf,

        @NotBlank String nome,

        @Email String email,

        @Length(min = 8, max = 14) String telefone,

        String logradouro,

        String bairro,

        String cidade,

        @Length(min = 2, max = 2) String estado,

        String cep

) {

}
