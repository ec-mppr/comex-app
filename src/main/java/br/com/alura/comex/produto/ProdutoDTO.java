package br.com.alura.comex.produto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Positive;

public record ProdutoDTO(
    @Length(min = 2, message = "O nome deve ter no mínimo 2 caracteres") String nome,
    @Positive(message = "O preço deve ser maior que R$0,00") double preco,
    String descricao,
    Integer quantidade,
    Long categoria) {
}
